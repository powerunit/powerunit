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
package ch.powerunit.impl.validator;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import ch.powerunit.Rule;

public interface RuleProcessorValidator extends ProcessValidator {
	default void ruleAnnotationValidation(ProcessingEnvironment processingEnv,
			RoundEnvironment roundEnv) {
		Set<Element> exists = new HashSet<>();
		Set<? extends Element> elements = roundEnv
				.getElementsAnnotatedWith(Rule.class);
		TypeElement testRule = processingEnv.getElementUtils().getTypeElement(
				"ch.powerunit.TestRule");
		for (Element element : elements) {
			if (element.getKind() != ElementKind.FIELD) {
				error("@Rule must prefix a field -- " + element
						+ " is not a field");
				continue;
			}
			Element parent = element.getEnclosingElement();
			if (exists.contains(parent)) {
				warn("Class "
						+ elementAsString(parent)
						+ "\n\t contains more than one @Rule field\n\tOnly one @Run method is field for a test class");
			}
			exists.add(parent);
			if (element.getModifiers().contains(Modifier.STATIC)) {
				warn("Field "
						+ elementAsString(element)
						+ "\n\tis prefixed with @Rule and is static\n\tA rule field can't be static");
			}
			if (!element.getModifiers().contains(Modifier.PUBLIC)) {
				warn("Field "
						+ elementAsString(element)
						+ "\n\tis prefixed with @Rule and is not public\n\tA rule field must be public");
			}
			if (!element.getModifiers().contains(Modifier.FINAL)) {
				warn("Field "
						+ elementAsString(element)
						+ "\n\tis prefixed with @Rule and is not fianl\n\tA rule field must be final");
			}
			TypeMirror rt = element.asType();
			if (rt.getKind() != TypeKind.DECLARED) {
				warn("Field " + elementAsString(element)
						+ "\n\tis prefixed with @Rule and is " + rt
						+ "\n\tA rule field must be " + testRule);
			} else {
				DeclaredType dt = (DeclaredType) rt;
				if (!processingEnv.getTypeUtils().isSubtype(
						dt.asElement().asType(), testRule.asType())) {
					warn("Field " + elementAsString(element)
							+ "\n\tis prefixed with @Rule and is "
							+ dt.asElement().asType()
							+ "\n\tA rule field must be " + testRule);
				}
			}

		}
	}
}
