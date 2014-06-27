/**
 * Powerunit - A JDK1.8 test framework
 * Copyright (C) 2014 Mathieu Boretti.
 *
 * This file is part of Powerunit
 *
 * Powerunit is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Powerunit is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Powerunit. If not, see <http://www.gnu.org/licenses/>.
 */
package org.powerunit.processor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import org.powerunit.Categories;
import org.powerunit.impl.validator.IgnoreProcessorValidator;
import org.powerunit.impl.validator.ParameterProcessorValidator;
import org.powerunit.impl.validator.ParametersProcessorValidator;
import org.powerunit.impl.validator.RuleProcessorValidator;
import org.powerunit.impl.validator.TestProcessorValidator;

@SupportedAnnotationTypes({ "org.powerunit.Test", "org.powerunit.Rule",
		"org.powerunit.Parameters", "org.powerunit.Parameter",
		"org.powerunit.Ignore", "org.powerunit.Categories" })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class PowerUnitProcessor extends AbstractProcessor implements
		ParametersProcessorValidator, ParameterProcessorValidator,
		RuleProcessorValidator, TestProcessorValidator,
		IgnoreProcessorValidator {

	private boolean categoryDone = false;

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		if (!roundEnv.processingOver()) {
			testAnnotationValidation(roundEnv);
			ruleAnnotationValidation(processingEnv, roundEnv);
			parametersValidation(processingEnv, roundEnv);
			parameterAnnotationValidation(roundEnv);
			ignoreAnnotationValidation(roundEnv);
			try {
				if (!categoryDone) {
					generateCategorySuite(roundEnv);
				}
			} catch (IOException e) {
				error("Unable to generate test suite file, because of "
						+ e.getMessage());
			}
		}

		return true;
	}

	private void generateCategorySuite(RoundEnvironment roundEnv)
			throws IOException {
		categoryDone = true;
		Set<? extends Element> elements = roundEnv
				.getElementsAnnotatedWith(Categories.class);
		Map<String, List<TypeMirror>> categories = new HashMap<>();
		for (Element element : elements) {
			if (element.getKind() == ElementKind.CLASS) {
				TypeMirror current = element.asType();
				Categories ca = element.getAnnotation(Categories.class);
				if (ca != null) {
					String cat[] = ca.value();
					for (String c : cat) {
						List<TypeMirror> lst = categories.get(c);
						if (lst == null) {
							lst = new ArrayList<>();
						}
						lst.add(current);
						categories.put(c, lst);
					}
				}
			}
		}
		JavaFileObject suite = processingEnv.getFiler().createSourceFile(
				"org.powerunit.suite.Suites",
				elements.toArray(new Element[] {}));
		try (PrintWriter w = new PrintWriter(suite.openWriter());) {
			w.println("package org.powerunit.suite;");
			w.println();
			w.println("@javax.annotation.Generated(\"Test suites for powerunit\")");
			w.println("public final class Suites implements org.powerunit.TestResultListener {");
			w.println();
			w.println("  private Suites(String targetFolder) {");
			w.println("    this.targetFolder=new java.io.File(targetFolder);");
			w.println("    this.targetFolder.mkdirs();");
			w.println("  }");
			w.println();
			w.println("  private final static javax.xml.bind.JAXBContext context;");
			w.println();
			w.println("  static {");
			w.println("    try {");
			w.println("      context = javax.xml.bind.JAXBContext.newInstance(org.powerunit.report.Testsuites.class);");
			w.println("    } catch (javax.xml.bind.JAXBException e) {");
			w.println("      throw new java.lang.IllegalArgumentException(\"Unable to setup jaxb \"+ e.getMessage(), e);");
			w.println("    }");
			w.println("  }");
			w.println();
			w.println("  private static final java.util.Map<String,Class<?>[]> suites;");
			w.println();
			w.println("  static {");
			w.println("    java.util.Map<String,Class<?>[]> tmp = new java.util.HashMap<>();");
			for (Map.Entry<String, List<TypeMirror>> cat : categories
					.entrySet()) {
				w.println("    tmp.put(\"" + cat.getKey()
						+ "\",new Class<?>[]{");
				int counter = 0;
				for (TypeMirror tm : cat.getValue()) {
					if (counter > 0) {
						w.println(",");
					}
					w.print("      "
							+ ((DeclaredType) tm).asElement().toString()
							+ ".class");
					counter++;
				}
				w.println();
				w.println("    });");
			}
			w.println("    suites=java.util.Collections.unmodifiableMap(tmp);");
			w.println("  }");
			w.println();
			w.println("  public static java.util.Map<String,Class<?>[]> getSuites() {");
			w.println("    return suites;");
			w.println("  }");
			w.println();
			w.println("  private boolean error=false;");
			w.println();
			w.println("  private java.util.Map<String,org.powerunit.report.Testsuite> result = new java.util.HashMap<>();");
			w.println();
			w.println("  private java.io.File targetFolder ;");
			w.println();
			w.println("  @Override");
			w.println("  public void notifySetStart(String setName, String parameters) {");
			w.println("    System.out.println(\"Start of \"+setName);");
			w.println("    result.put(setName, new org.powerunit.report.Testsuite());");
			w.println("    result.get(setName).setName(setName.replace('$','.'));");
			w.println("    result.get(setName).setDisabled(0);");
			w.println("    result.get(setName).setErrors(0);");
			w.println("    result.get(setName).setFailures(0);");
			w.println("    result.get(setName).setTests(0);");
			w.println("  }");
			w.println();
			w.println("  @Override");
			w.println("  public void notifySetEnd(String setName, String parameters) {");
			w.println("    System.out.println(\"End of \"+setName);");
			w.println("    try {");
			w.println("      java.io.File target = new java.io.File(targetFolder,setName+\".xml\");");
			w.println("      context.createMarshaller().marshal(result.get(setName), target);");
			w.println("    } catch (javax.xml.bind.JAXBException e) {");
			w.println("      e.printStackTrace();");
			w.println("      System.exit(1);");
			w.println("    }");
			w.println("  }");
			w.println();
			w.println("  @Override");
			w.println("  public void notifyStart(org.powerunit.TestContext context) {");
			w.println("    System.out.println(\"Start of \"+context.getFullTestName());");
			w.println("  }");
			w.println();
			w.println("  @Override");
			w.println("  public void notifySuccess(org.powerunit.TestContext context) {");
			w.println("    System.out.println(\"End of \"+context.getFullTestName());");
			w.println("    result.get(context.getSetName()).setTests(result.get(context.getSetName()).getTests()+1);");
			w.println("    result.get(context.getSetName()).getTestcase().add(new org.powerunit.report.Testcase());");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).setName(context.getLocalTestName()+(context.getParameterName()==null?\"\":(\"[\"+context.getParameterName()+\"]\")));");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).setClassname(context.getTestSuiteObject().getClass().getCanonicalName());");
			w.println("  }");
			w.println();
			w.println("  @Override");
			w.println("  public void notifyFailure(org.powerunit.TestContext context, Throwable cause) {");
			w.println("    System.out.println(\"Failure of \"+context.getFullTestName()+\" because of \"+cause.getMessage());");
			w.println("    cause.printStackTrace();");
			w.println("    error=true;");
			w.println("    result.get(context.getSetName()).setFailures(result.get(context.getSetName()).getFailures()+1);");
			w.println("    result.get(context.getSetName()).getTestcase().add(new org.powerunit.report.Testcase());");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).setName(context.getLocalTestName()+(context.getParameterName()==null?\"\":(\"[\"+context.getParameterName()+\"]\")));");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).setClassname(context.getTestSuiteObject().getClass().getCanonicalName());");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).getFailure().add(new org.powerunit.report.Failure());");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).getFailure().get(result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).getFailure().size()-1).setType(cause.getClass().getCanonicalName());");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).getFailure().get(result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).getFailure().size()-1).setMessage(cause.getMessage());");
			w.println("  }");
			w.println();
			w.println("  @Override");
			w.println("  public void notifySkipped(org.powerunit.TestContext context) {");
			w.println("    System.out.println(\"Skip of \"+context.getFullTestName());");
			w.println("    result.get(context.getSetName()).setDisabled(result.get(context.getSetName()).getDisabled()+1);");
			w.println("    result.get(context.getSetName()).getTestcase().add(new org.powerunit.report.Testcase());");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).setName(context.getLocalTestName()+(context.getParameterName()==null?\"\":(\"[\"+context.getParameterName()+\"]\")));");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).setClassname(context.getTestSuiteObject().getClass().getCanonicalName());");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).setSkipped(\"\");");
			w.println("  }");
			w.println();
			w.println("  @Override");
			w.println("  public void notifyError(org.powerunit.TestContext context, Throwable cause) {");
			w.println("    System.out.println(\"Error of \"+context.getFullTestName()+\" because of \"+cause.getMessage());");
			w.println("    cause.printStackTrace();");
			w.println("    error=true;");
			w.println("    result.get(context.getSetName()).setErrors(result.get(context.getSetName()).getErrors()+1);");
			w.println("    result.get(context.getSetName()).getTestcase().add(new org.powerunit.report.Testcase());");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).setName(context.getLocalTestName()+(context.getParameterName()==null?\"\":(\"[\"+context.getParameterName()+\"]\")));");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).setClassname(context.getTestSuiteObject().getClass().getCanonicalName());");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).getError().add(new org.powerunit.report.Error());");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).getError().get(result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).getError().size()-1).setType(cause.getClass().getCanonicalName());");
			w.println("    result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).getError().get(result.get(context.getSetName()).getTestcase().get(result.get(context.getSetName()).getTestcase().size()-1).getError().size()-1).setMessage(cause.getMessage());");
			w.println("  }");
			w.println();
			w.println("  public static void main(String argv[]) {");
			w.println("    if (argv.length==1) {");
			w.println("       suites.keySet().stream().forEach((c)->{main(new String[]{argv[0],c});});");
			w.println("    return;");
			w.println("    }");
			w.println("    String name = argv[1];");
			w.println("    boolean success=true;");
			w.println("    for(Class<?> c:getSuites().get(name)) {");
			w.println("      Suites p = new Suites(argv[0]);");
			w.println("      org.powerunit.PowerUnitRunner r = new org.powerunit.impl.DefaultPowerUnitRunnerImpl(c);");
			w.println("      r.addListener(p);");
			w.println("      r.run();");
			w.println("      success=success&&!p.error;");
			w.println("    }");
			w.println("    if(!success) {");
			w.println("      System.out.println(\"Tests in error.\");");
			w.println("      System.exit(-1);");
			w.println("    }");
			w.println("  }");
			w.println();
			w.println("}");
		}
	}

	@Override
	public String elementAsString(Element ee) {
		return ee.toString() + " of " + ee.getEnclosingElement().toString();
	}

	@Override
	public void error(String msg) {
		processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg);
	}

	@Override
	public void warn(String msg) {
		processingEnv.getMessager().printMessage(
				Diagnostic.Kind.MANDATORY_WARNING, msg);
	}

}
