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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

import ch.powerunit.exception.InternalError;

public interface ParametersValidator {
	default void checkParametersAnnotationForMethod(Method m) {
		if (!Modifier.isStatic(m.getModifiers())) {
			throw new InternalError("@Parameters method is not static "
					+ m.toString());
		}
		if (!Modifier.isPublic(m.getModifiers())) {
			throw new InternalError("@Parameters method is not public "
					+ m.toString());
		}
		if (!Stream.class.isAssignableFrom(m.getReturnType())) {
			throw new InternalError("@Parameters method is not Stream<...> "
					+ m.toString());
		}
		if (m.getParameterCount() != 0) {
			throw new InternalError("@Parameters method is not 0-parameter "
					+ m.toString());
		}
	}
}
