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
 * This class provide the support for tester of Matcher.
 * 
 * @author borettim
 * @since 0.3.0
 * @see #of(Class) To start the DSL.
 */
@TestInterface(MatcherTesterImpl.class)
public final class MatcherTester<T extends Matcher<?>> {
	/**
	 * Start the creation of a tester definition for a matcher.
	 * <p>
	 * For instance :
	 * 
	 * <pre>
	 * &#064;TestDelegate
	 * public final MatcherTester&lt;TestMatcher&gt; tester = MatcherTester.of(
	 * 		TestMatcher.class).with(
	 * 		matcher(new TestMatcher(null)).describedAs(&quot;is null&quot;).nullAccepted()
	 * 				.rejecting(value(&quot;x&quot;).withMessage(&quot; was \&quot;x\&quot;&quot;)),
	 * 		matcher(new TestMatcher(&quot;x&quot;)).describedAs(&quot;is \&quot;x\&quot;&quot;)
	 * 				.nullRejected(&quot; was null&quot;).accepting(&quot;x&quot;)
	 * 				.rejecting(value(&quot;y&quot;).withMessage(&quot; was \&quot;y\&quot;&quot;)),
	 * 		matcher(new TestMatcher(&quot;y&quot;)).describedAs(&quot;is \&quot;y\&quot;&quot;)
	 * 				.nullRejected(&quot; was null&quot;).accepting(&quot;y&quot;)
	 * 				.rejecting(value(&quot;x&quot;).withMessage(&quot; was \&quot;x\&quot;&quot;)));
	 * </pre>
	 * 
	 * @param matcherClass
	 *            the class of the matcher
	 * @return the next step of the DSL
	 * @see ch.powerunit.matchers.lang.MatcherTesterDSL1#with(MatcherAssertion...)
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

			@SuppressWarnings("unchecked")
			@Override
			public MatcherTester<T> with(MatcherAssertion<T> first,
					MatcherAssertion<T> second, MatcherAssertion<T> third,
					MatcherAssertion<T> fourth) {
				return with(new MatcherAssertion[] { first, second, third,
						fourth });
			}

			@SuppressWarnings("unchecked")
			@Override
			public MatcherTester<T> with(MatcherAssertion<T> first,
					MatcherAssertion<T> second, MatcherAssertion<T> third,
					MatcherAssertion<T> fourth, MatcherAssertion<T> fifth) {
				return with(new MatcherAssertion[] { first, second, third,
						fourth, fifth });
			}

			@SuppressWarnings("unchecked")
			@Override
			public MatcherTester<T> with(MatcherAssertion<T> first,
					MatcherAssertion<T> second, MatcherAssertion<T> third,
					MatcherAssertion<T> fourth, MatcherAssertion<T> fifth,
					MatcherAssertion<T> sixth) {
				return with(new MatcherAssertion[] { first, second, third,
						fourth, fifth, sixth });
			}
		};
	}

	/**
	 * Use this method to start the definition of one entry to be passed into
	 * the <code>with</code> created by {@link #of(Class)}.
	 * 
	 * @param matcher
	 *            The instance of the matcher to be tested.
	 * @return the next step of the DSL
	 * @see ch.powerunit.matchers.lang.MatcherTesterDSL2
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
	 * Use this method inside the
	 * {@link ch.powerunit.matchers.lang.MatcherAssertion#rejecting(Reject...)}
	 * to start the definition of a rejecting object.
	 * 
	 * @param value
	 *            The rejected value
	 * @return the next step of the DSL
	 * @see ch.powerunit.matchers.lang.MatcherTesterDSL3
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
	 * <b>Used internally</b>.
	 * 
	 * @return the matcherClass
	 */
	public Class<T> getMatcherClass() {
		return matcherClass;
	}

	/**
	 * <b>Used internally</b>.
	 * 
	 * @return the assertions
	 */
	public MatcherAssertion<T>[] getAssertions() {
		return assertions;
	}

}
