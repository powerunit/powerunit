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
package org.powerunit;

import java.util.regex.Pattern;

import org.hamcrest.Matchers;

/**
 * DSL for assertion on string result.
 * <p>
 * This interface is returned by the various methods
 * {@link TestSuite#assertThat(String) assertThat} exposed by {@link TestSuite}.
 * 
 * @author borettim
 *
 */
public interface AssertThatString extends AssertThatObject<String> {
	/**
	 * Check that a string another string.
	 * 
	 * @param substring
	 *            the other string.
	 */
	default void containsString(String substring) {
		is(Matchers.containsString(substring));
	}

	/**
	 * Check that a string starts with another one.
	 * 
	 * @param prefix
	 *            the prefix.
	 */
	default void startsWith(String prefix) {
		is(Matchers.startsWith(prefix));
	}

	/**
	 * Check that a string ends with another one.
	 * 
	 * @param prefix
	 *            the prefix.
	 */
	default void endsWith(String prefix) {
		is(Matchers.endsWith(prefix));
	}

	/**
	 * Validate a string with a {@link java.util.regex.Pattern}.
	 * 
	 * @param pattern
	 *            the pattern to be used.
	 */
	default void matchesRegex(Pattern pattern) {
		is(TestSuite.DSL.matchesRegex(pattern));
	}

	/**
	 * Validate a string with a regex.
	 * 
	 * 
	 * @param regex
	 *            The regex to be used for the validation.
	 */
	default void matchesRegex(String regex) {
		is(TestSuite.DSL.matchesRegex(regex));
	}
}
