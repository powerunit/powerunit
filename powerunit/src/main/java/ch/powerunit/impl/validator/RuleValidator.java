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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import ch.powerunit.TestRule;
import ch.powerunit.exception.InternalError;

public interface RuleValidator {
	default void checkRuleAnnotationForField(Field f) {
		if (Modifier.isStatic(f.getModifiers())) {
			throw new InternalError("@Rule field is static " + f.toString());
		}
		if (!Modifier.isPublic(f.getModifiers())) {
			throw new InternalError("@Rule field is not public " + f.toString());
		}
		if (!Modifier.isFinal(f.getModifiers())) {
			throw new InternalError("@Rule field is not final " + f.toString());
		}
		if (!TestRule.class.isAssignableFrom(f.getType())) {
			throw new InternalError("@Rule field is not TestRule "
					+ f.toString());
		}
	}
}
