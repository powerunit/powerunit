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
package ch.powerunit.matchers;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import ch.powerunit.TestInterface;
import ch.powerunit.matchers.impl.MatcherAssertionImpl;
import ch.powerunit.matchers.impl.MatcherTesterImpl;
import ch.powerunit.matchers.impl.RejectImpl;
import ch.powerunit.matchers.lang.MatcherAssertion;
import ch.powerunit.matchers.lang.MatcherTesterDSL1;
import ch.powerunit.matchers.lang.MatcherTesterDSL2;
import ch.powerunit.matchers.lang.MatcherTesterDSL3;
import ch.powerunit.matchers.lang.MatcherAssertion.Reject;

/**
 * @author borettim
 * @since 0.3.0
 */
@TestInterface(MatcherTesterImpl.class)
public final class MatcherTester<T extends Matcher<?>> {
	/**
	 * @param matcherClass
	 * @return
	 */
	public static <T extends Matcher<?>> MatcherTesterDSL1<T> of(
			Class<T> matcherClass) {
		return new MatcherTesterDSL1<T>() {

			@Override
			public MatcherTester<T> with(MatcherAssertion<T>... assertions) {
				return new MatcherTester<T>(matcherClass, assertions);
			}

			@SuppressWarnings("unchecked")
			@Override
			public MatcherTester<T> with(MatcherAssertion<T> first) {
				return with(new MatcherAssertion[] { first });
			}

			@SuppressWarnings("unchecked")
			@Override
			public MatcherTester<T> with(MatcherAssertion<T> first,
					MatcherAssertion<T> second) {
				return with(new MatcherAssertion[] { first, second });
			}

			@SuppressWarnings("unchecked")
			@Override
			public MatcherTester<T> with(MatcherAssertion<T> first,
					MatcherAssertion<T> second, MatcherAssertion<T> third) {
				return with(new MatcherAssertion[] { first, second, third });
			}
		};
	}

	/**
	 * @param matcher
	 * @return
	 */
	public static <T extends Matcher<?>> MatcherTesterDSL2<T> matcher(T matcher) {
		return new MatcherTesterDSL2<T>() {

			@Override
			public MatcherAssertion<T> describedAs(Matcher<String> expected) {
				return new MatcherAssertionImpl<T>(matcher, expected);
			}

			@Override
			public MatcherAssertion<T> describedAs(String expected) {
				return describedAs(Matchers.equalTo(expected));
			}

		};
	}

	/**
	 * @param value
	 * @return
	 */
	public static MatcherTesterDSL3 value(Object value) {
		return new MatcherTesterDSL3() {

			@Override
			public Reject withMessage(Matcher<String> message) {
				return new RejectImpl(value, message);
			}

			@Override
			public Reject withMessage(String message) {
				return withMessage(Matchers.equalTo(message));
			}
		};
	}

	private final Class<T> matcherClass;

	private final MatcherAssertion<T> assertions[];

	/**
	 * @param matcherClass
	 * @param assertions
	 */
	private MatcherTester(Class<T> matcherClass,
			MatcherAssertion<T>[] assertions) {
		this.matcherClass = matcherClass;
		this.assertions = assertions;
	}

	/**
	 * @return the matcherClass
	 */
	public Class<T> getMatcherClass() {
		return matcherClass;
	}

	/**
	 * @return the assertions
	 */
	public MatcherAssertion<T>[] getAssertions() {
		return assertions;
	}

}
