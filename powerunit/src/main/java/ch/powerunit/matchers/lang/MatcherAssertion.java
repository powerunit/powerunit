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
 * Use to define the expectation on one single instance of a matcher.
 * 
 * @author borettim
 * @since 0.3.0
 */
public interface MatcherAssertion<T extends Matcher<?>> {
	/**
	 * Indicate that this matcher instance must accept the null value.
	 * 
	 * @return continuation of the DSL.
	 */
	MatcherAssertion<T> nullAccepted();

	/**
	 * Indicate that this matcher instance must reject the null value with a
	 * message accepted by a matcher.
	 * 
	 * @param rejectedMessage
	 *            the matcher on the message
	 * @return continuation of the DSL
	 */
	MatcherAssertion<T> nullRejected(Matcher<String> rejectedMessage);

	/**
	 * Indicate that this matcher instance must reject the null value with a
	 * message.
	 * 
	 * @param rejectedMessage
	 *            the expected message
	 * @return continuation of the DSL
	 */
	MatcherAssertion<T> nullRejected(String rejectedMessage);

	/**
	 * Define a list of value accepted by this matcher.
	 * 
	 * @param values
	 *            the values accepted by this matcher
	 * @return continuation of the DSL
	 */
	MatcherAssertion<T> accepting(Object... values);

	/**
	 * Define a list of value rejected by this matcher.
	 * 
	 * @param reject
	 *            the values (and expectation on rejecting message) rejected by
	 *            this this matcher.
	 * @return continuation of the DSL
	 * @see ch.powerunit.matchers.MatcherTester#value(Object)
	 */
	MatcherAssertion<T> rejecting(Reject... reject);

	/**
	 * Definition of a rejected value.
	 * 
	 * @author borettim
	 * @since 0.3.0
	 */
	interface Reject {

	}
}
