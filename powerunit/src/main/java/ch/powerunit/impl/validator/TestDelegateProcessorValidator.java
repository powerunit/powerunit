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

import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;

import ch.powerunit.TestDelegate;

public interface TestDelegateProcessorValidator extends ProcessValidator {
    default void testDelegateAnnotationValidation(
            ProcessingEnvironment processingEnv, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv
                .getElementsAnnotatedWith(TestDelegate.class);
        for (Element element : elements) {
            if (element.getKind() != ElementKind.FIELD) {
                error("@Test must prefix a field -- " + element
                        + " is not a field");
                return;
            }
            if (element.getModifiers().contains(Modifier.STATIC)) {
                warn("Method "
                        + elementAsString(element)
                        + "\n\tis prefixed with @TestDelegate and is static\n\tA testdelegate field can't be static");
            }
            if (!element.getModifiers().contains(Modifier.PUBLIC)) {
                warn("Method "
                        + elementAsString(element)
                        + "\n\tis prefixed with @TestDelegate and is not public \n\tA testdelegate field must be public");
            }
            if (!element.getModifiers().contains(Modifier.FINAL)) {
                warn("Field "
                        + elementAsString(element)
                        + "\n\tis prefixed with @TestDelegate and is not final\n\tA testdelegate field must be final");
            }
        }
    }
}
