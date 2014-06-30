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

import ch.powerunit.Ignore;
import ch.powerunit.Test;

public interface IgnoreProcessorValidator extends ProcessValidator {
	default void ignoreAnnotationValidation(RoundEnvironment roundEnv) {
		roundEnv.getElementsAnnotatedWith(Ignore.class).forEach(
				this::ignoreOneAnnotationValidation);

	}

	default void ignoreOneAnnotationValidation(Element element) {
		if (element.getKind() == ElementKind.CLASS) {
			return;
		}
		if (element.getKind() == ElementKind.METHOD) {
			if (element.getAnnotation(Test.class) == null) {
				warn("Method "
						+ elementAsString(element)
						+ "\n\tis prefixed with @Ignore and is prefixed by @Test\n\tThe ignore annotation can only be used on method that are annotated with @test");
			}
			return;
		}
		warn("@Ignore on not supported element" + elementAsString(element));
	}
}
