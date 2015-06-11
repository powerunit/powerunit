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
package ch.powerunit.matchers.lang;

import org.hamcrest.Matcher;

/**
 * Define the expectation on the description of the matcher.
 * 
 * @author borettim
 * @since 0.3.0
 * @param <T>
 */
public interface MatcherTesterDSL2<T extends Matcher<?>> {
	/**
	 * Define the expected describedAs message for the instance of the matcher.
	 * 
	 * @param expected
	 *            the matcher on the describedAs message
	 * @return the continuation of the DSL
	 */
	MatcherAssertion<T> describedAs(Matcher<String> expected);

	/**
	 * Define the expected describedAs message for the instance of the matcher.
	 * 
	 * @param expected
	 *            the expected message of the describedAs message
	 * @return the continuation of the DSL
	 */
	MatcherAssertion<T> describedAs(String expected);
}
