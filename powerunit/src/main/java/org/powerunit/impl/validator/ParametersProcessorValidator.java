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
package org.powerunit.impl.validator;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import org.powerunit.Parameters;

public interface ParametersProcessorValidator extends ProcessValidator {
	default void parametersValidation(ProcessingEnvironment processingEnv,
			RoundEnvironment roundEnv) {
		Set<Element> exists = new HashSet<>();
		Set<? extends Element> elements = roundEnv
				.getElementsAnnotatedWith(Parameters.class);
		TypeElement stream = processingEnv.getElementUtils().getTypeElement(
				"java.util.stream.Stream");
		for (Element element : elements) {
			if (element.getKind() != ElementKind.METHOD) {
				error("@Parameters must prefix a method -- " + element
						+ " is not a method");
				continue;
			}
			Element parent = element.getEnclosingElement();
			if (exists.contains(parent)) {
				warn("Class "
						+ elementAsString(parent)
						+ "\n\t contains more than one @Parameters method\n\tOnly one @Parameters method is allowed for a test class");
			}
			exists.add(parent);
			ExecutableElement ee = (ExecutableElement) element;
			if (!ee.getModifiers().contains(Modifier.STATIC)) {
				warn("Method "
						+ elementAsString(element)
						+ "\n\tis prefixed with @Parameters and is not static\n\tThe parameters method must be static");
			}
			if (!ee.getModifiers().contains(Modifier.PUBLIC)) {
				warn("Method "
						+ elementAsString(element)
						+ "\n\tis prefixed with @Parameters and is not public\n\tThe parameters method must be public");
			}
			TypeMirror rt = ee.getReturnType();
			if (rt.getKind() != TypeKind.DECLARED) {
				warn("Method " + elementAsString(element)
						+ "\n\tis prefixed with @Parameters and return " + rt
						+ "\n\tThe parameters method must return " + stream);
			} else {
				DeclaredType dt = (DeclaredType) rt;
				if (!processingEnv.getTypeUtils().isSubtype(
						dt.asElement().asType(), stream.asType())) {
					warn("Method " + elementAsString(element)
							+ "\n\tis prefixed with @Parameters and return "
							+ dt.asElement().asType()
							+ "\n\tThe parameters method must return " + stream);
				}
			}
			if (ee.getParameters().size() != 0) {
				warn("Method "
						+ elementAsString(element)
						+ "\n\tis prefixed with @Parameters and is not 0-args\n\tThe parameters method must be 0-args");
			}
		}
	}
}
