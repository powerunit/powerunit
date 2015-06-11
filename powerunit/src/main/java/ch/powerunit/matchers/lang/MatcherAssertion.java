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
 * @author borettim
 * @since 0.3.0
 */
public interface MatcherAssertion<T extends Matcher<?>> {
	/**
	 * @return
	 */
	MatcherAssertion<T> nullAccepted();

	/**
	 * @param rejectedMessage
	 * @return
	 */
	MatcherAssertion<T> nullRejected(Matcher<String> rejectedMessage);

	/**
	 * @param rejectedMessage
	 * @return
	 */
	MatcherAssertion<T> nullRejected(String rejectedMessage);

	/**
	 * @param values
	 * @return
	 */
	MatcherAssertion<T> accepting(Object... values);

	/**
	 * @param reject
	 * @return
	 */
	MatcherAssertion<T> rejecting(Reject... reject);

	/**
	 * @author borettim
	 * @since 0.3.0
	 */
	interface Reject {

	}
}
