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

import ch.powerunit.matchers.MatcherTester;

/**
 * Definition of the expectation on a matcher.
 * 
 * @author borettim
 * @since 0.3.0
 */
public interface MatcherTesterDSL1<T extends Matcher<?>> {
	/**
	 * DSL to define the expectation.
	 * 
	 * @param assertions
	 *            the expectation
	 * @return the tester
	 * @see ch.powerunit.matchers.MatcherTester#matcher(Matcher)
	 */
	MatcherTester<T> with(MatcherAssertion<T>... assertions);

	/**
	 * DSL to define the expectation.
	 * 
	 * @param first
	 *            first expectation
	 * @return the tester
	 * @see ch.powerunit.matchers.MatcherTester#matcher(Matcher)
	 */
	MatcherTester<T> with(MatcherAssertion<T> first);

	/**
	 * DSL to define the expectation.
	 * 
	 * @param first
	 *            first expectation
	 * @param second
	 *            second expectation
	 * @return the tester
	 * @see ch.powerunit.matchers.MatcherTester#matcher(Matcher)
	 */
	MatcherTester<T> with(MatcherAssertion<T> first, MatcherAssertion<T> second);

	/**
	 * DSL to define the expectation.
	 * 
	 * @param first
	 *            first expectation
	 * @param second
	 *            second expectation
	 * @param third
	 *            third expectation
	 * @return the tester
	 * @see ch.powerunit.matchers.MatcherTester#matcher(Matcher)
	 */
	MatcherTester<T> with(MatcherAssertion<T> first,
			MatcherAssertion<T> second, MatcherAssertion<T> third);

	/**
	 * DSL to define the expectation.
	 * 
	 * @param first
	 *            first expectation
	 * @param second
	 *            second expectation
	 * @param third
	 *            third expectation
	 * @param fourth
	 *            fourth expectation
	 * @return the tester
	 * @see ch.powerunit.matchers.MatcherTester#matcher(Matcher)
	 */
	MatcherTester<T> with(MatcherAssertion<T> first,
			MatcherAssertion<T> second, MatcherAssertion<T> third,
			MatcherAssertion<T> fourth);

	/**
	 * DSL to define the expectation.
	 * 
	 * @param first
	 *            first expectation
	 * @param second
	 *            second expectation
	 * @param third
	 *            third expectation
	 * @param fourth
	 *            fourth expectation
	 * @param fifth
	 *            fifth expectation
	 * @return the tester
	 * @see ch.powerunit.matchers.MatcherTester#matcher(Matcher)
	 */
	MatcherTester<T> with(MatcherAssertion<T> first,
			MatcherAssertion<T> second, MatcherAssertion<T> third,
			MatcherAssertion<T> fourth, MatcherAssertion<T> fifth);

	/**
	 * DSL to define the expectation.
	 * 
	 * @param first
	 *            first expectation
	 * @param second
	 *            second expectation
	 * @param third
	 *            third expectation
	 * @param fourth
	 *            fourth expectation
	 * @param fifth
	 *            fifth expectation
	 * @param sixth
	 *            sixth expectation
	 * @return the tester
	 * @see ch.powerunit.matchers.MatcherTester#matcher(Matcher)
	 */
	MatcherTester<T> with(MatcherAssertion<T> first,
			MatcherAssertion<T> second, MatcherAssertion<T> third,
			MatcherAssertion<T> fourth, MatcherAssertion<T> fifth,
			MatcherAssertion<T> sixth);
}
