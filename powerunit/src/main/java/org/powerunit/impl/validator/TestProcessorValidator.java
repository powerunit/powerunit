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

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeKind;

import org.powerunit.Test;

public interface TestProcessorValidator extends ProcessValidator {
	default void testAnnotationValidation(RoundEnvironment roundEnv) {
		roundEnv.getElementsAnnotatedWith(Test.class).forEach(
				this::testOneAnnotationValidation);
	}

	default void testOneAnnotationValidation(Element element) {
		if (element.getKind() != ElementKind.METHOD) {
			error("@Test must prefix a method -- " + element
					+ " is not a method");
			return;
		}
		ExecutableElement ee = (ExecutableElement) element;
		if (ee.getModifiers().contains(Modifier.STATIC)) {
			warn("Method "
					+ elementAsString(ee)
					+ "\n\tis prefixed with @Test and is static\n\tA test method can't be static");
		}
		if (!ee.getModifiers().contains(Modifier.PUBLIC)) {
			warn("Method "
					+ elementAsString(ee)
					+ "\n\tis prefixed with @Test and is not public \n\tA test method must be public");
		}
		if (!TypeKind.VOID.equals(ee.getReturnType().getKind())) {
			warn("Method "
					+ elementAsString(ee)
					+ "\n\tis prefixed with @Test and is not void\n\tA test method must be void");
		}
		if (ee.getParameters().size() != 0) {
			warn("Method"
					+ elementAsString(ee)
					+ "\n\tis prefixed with @Test and is not 0-args\n\tA test method must be 0-args");
		}
	}
}
