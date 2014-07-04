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
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import ch.powerunit.Parameter;

public interface ParameterProcessorValidator extends ProcessValidator {
    default void parameterAnnotationValidation(
            ProcessingEnvironment processingEnv, RoundEnvironment roundEnv) {
        TypeElement predicate = processingEnv.getElementUtils().getTypeElement(
                "java.util.function.BiFunction");
        Set<Name> exists = new HashSet<>();
        roundEnv.getElementsAnnotatedWith(Parameter.class)
                .forEach(
                        (Element element) -> {
                            if (element.getKind() != ElementKind.FIELD) {
                                error("@Parameter must prefix a field -- "
                                        + element + " is not a field");
                                return;
                            }

                            Element parent = element.getEnclosingElement();
                            if (element.getAnnotation(Parameter.class).filter()) {
                                if (exists.contains(parent.getSimpleName())) {
                                    warn("Class "
                                            + elementAsString(parent)
                                            + "\n\t contains more than one @Parameter field with filter = true\n\tOnly one @Parameter field can use the filter attribute with true value.");
                                }
                                exists.add(parent.getSimpleName());
                                TypeMirror rt = element.asType();
                                if (rt.getKind() != TypeKind.DECLARED) {
                                    warn("Field "
                                            + elementAsString(element)
                                            + "\n\tis prefixed with @Parameter(filter=true) and is "
                                            + element
                                            + "\n\tA predicate field must be "
                                            + predicate);
                                } else {
                                    DeclaredType dt = (DeclaredType) rt;
                                    if (!processingEnv.getTypeUtils()
                                            .isSubtype(dt.asElement().asType(),
                                                    predicate.asType())) {
                                        warn("Field "
                                                + elementAsString(element)
                                                + "\n\tis prefixed with @Parameter(filter=true) and is "
                                                + dt.asElement().asType()
                                                + "\n\tA predicate field must be "
                                                + predicate);
                                    }
                                }
                            }
                            if (element.getModifiers()
                                    .contains(Modifier.STATIC)) {
                                warn("Field "
                                        + elementAsString(element)
                                        + "\n\tis prefixed with @Parameter and is static\n\tA parameter field can't be static");
                            }
                            if (!element.getModifiers().contains(
                                    Modifier.PUBLIC)) {
                                warn("Field "
                                        + elementAsString(element)
                                        + "\n\tis prefixed with @Parameter and is not public\n\tA parameter field must be public");
                            }
                            if (element.getAnnotation(Parameter.class).value() < 0) {
                                warn("Field "
                                        + elementAsString(element)
                                        + "\n\tis prefixed with @Parameter and value is "
                                        + element
                                                .getAnnotation(Parameter.class)
                                                .value()
                                        + "\n\tA parameter field value must be >=0");
                            }
                        });
    }
}
