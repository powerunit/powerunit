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

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;

import ch.powerunit.Parameter;

public interface ParameterProcessorValidator extends ProcessValidator {
	default void parameterAnnotationValidation(RoundEnvironment roundEnv) {
		roundEnv.getElementsAnnotatedWith(Parameter.class).forEach(
				this::parameterOneAnnotationValidation);
	}

	default void parameterOneAnnotationValidation(Element element) {
		if (element.getKind() != ElementKind.FIELD) {
			error("@Parameter must prefix a field -- " + element
					+ " is not a field");
			return;
		}
		if (element.getModifiers().contains(Modifier.STATIC)) {
			warn("Field "
					+ elementAsString(element)
					+ "\n\tis prefixed with @Parameter and is static\n\tA parameter field can't be static");
		}
		if (!element.getModifiers().contains(Modifier.PUBLIC)) {
			warn("Field "
					+ elementAsString(element)
					+ "\n\tis prefixed with @Parameter and is not public\n\tA parameter field must be public");
		}
		if (element.getAnnotation(Parameter.class).value() < 0) {
			warn("Field " + elementAsString(element)
					+ "\n\tis prefixed with @Parameter and value is "
					+ element.getAnnotation(Parameter.class).value()
					+ "\n\tA parameter field value must be >=0");
		}
	}
}
