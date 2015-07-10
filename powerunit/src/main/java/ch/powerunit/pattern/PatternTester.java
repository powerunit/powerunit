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
package ch.powerunit.pattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import org.hamcrest.Matcher;

import ch.powerunit.TestInterface;
import ch.powerunit.TestSuite;
import ch.powerunit.pattern.impl.PatternTesterImpl;
import ch.powerunit.pattern.lang.PatternTester0;
import ch.powerunit.pattern.lang.PatternTester1;
import ch.powerunit.pattern.lang.PatternTester2;
import ch.powerunit.pattern.lang.PatternTester3;
import ch.powerunit.pattern.lang.PatternTester4;

/**
 * This is a tester to validate {@link Pattern}.
 * <p>
 * This tester will validate that a Pattern accept (
 * {@link Pattern#matcher(CharSequence)}) or not a specified string ; In the
 * first case (accepted), it also provide a way to validate the groups (
 * {@link java.util.regex.Matcher#group(int)}).
 * 
 * @author borettim
 * @since 0.4.0
 */
@TestInterface(PatternTesterImpl.class)
public final class PatternTester {
	private final Pattern underTest;

	private final List<String> inputs;

	private final List<Boolean> expectedResult;

	private final List<List<Integer>> havingGroup;

	private final List<List<Matcher<String>>> expectedGroup;

	private PatternTester(Pattern underTest, List<String> inputs,
			List<Boolean> expectedResult, List<List<Integer>> havingGroup,
			List<List<Matcher<String>>> expectedGroup) {
		this.underTest = underTest;
		this.inputs = inputs;
		this.expectedResult = expectedResult;
		this.havingGroup = havingGroup;
		this.expectedGroup = expectedGroup;
	}

	/**
	 * Start the DSL to create a tester of Pattern, based on a String.
	 * <p>
	 * The passed String will be compiled as a Pattern. <br>
	 * For instance :
	 * 
	 * <pre>
	 * &#064;TestDelegate
	 * public final PatternTester sample1 = PatternTester.of(&quot;a+&quot;).receiving(&quot;b&quot;)
	 * 		.thenNoMatching().receiving(&quot;aa&quot;).thenMatching().build();
	 * </pre>
	 * 
	 * @param pattern
	 *            the pattern, as a String
	 * @return {@link PatternTester0#receiving(String) The next step of the
	 *         DSL.}
	 * @see #of(Pattern)
	 */
	public static PatternTester0 of(String pattern) {
		return of(Pattern.compile(pattern));
	}

	/**
	 * Start the DSL to create a tester of Pattern, based on a String.
	 * <p>
	 * For instance :
	 * 
	 * <pre>
	 * &#064;TestDelegate
	 * public final PatternTester sample1 = PatternTester.of(Pattern.compile(&quot;a+&quot;))
	 * 		.receiving(&quot;b&quot;).thenNoMatching().receiving(&quot;aa&quot;).thenMatching().build();
	 * </pre>
	 * 
	 * @param pattern
	 *            the pattern.
	 * @return {@link PatternTester0#receiving(String) The next step of the
	 *         DSL.}
	 */
	public static PatternTester0 of(Pattern pattern) {
		return new PatternTesterDSL(pattern);
	}

	private static class PatternTesterDSL implements PatternTester0,
			PatternTester1, PatternTester2, PatternTester3, PatternTester4 {

		private final Pattern underTest;

		private final List<String> inputs = new ArrayList<>();

		private final List<Boolean> expectedResult = new ArrayList<>();

		private final List<List<Integer>> havingGroup = new ArrayList<>();

		private final List<List<Matcher<String>>> expectedGroup = new ArrayList<>();

		private List<Integer> currentGroup = null;

		private List<Matcher<String>> currentExpected = null;

		private PatternTesterDSL initMatching(boolean expected) {
			expectedResult.add(expected);
			currentGroup = new ArrayList<>();
			currentExpected = new ArrayList<>();
			havingGroup.add(Collections.unmodifiableList(currentGroup));
			expectedGroup.add(Collections.unmodifiableList(currentExpected));
			return this;
		}

		/**
		 * @param underTest
		 */
		private PatternTesterDSL(Pattern underTest) {
			this.underTest = underTest;
		}

		@Override
		public PatternTester2 receiving(String input) {
			inputs.add(Objects.requireNonNull(input, "input can't be null"));
			return this;
		}

		@Override
		public PatternTester1 thenNoMatching() {
			return initMatching(false);
		}

		@Override
		public PatternTester3 thenMatching() {
			return initMatching(true);
		}

		@Override
		public PatternTester4 havingGroup(int number) {
			if (number < 0) {
				throw new IllegalArgumentException("Number can't be <0");
			}
			currentGroup.add(number);
			return this;
		}

		@Override
		public PatternTester3 matching(Matcher<String> matching) {
			currentExpected.add(Objects.requireNonNull(matching,
					"matching can't be null"));
			return this;
		}

		@Override
		public PatternTester3 equalTo(String equalTo) {
			return matching(TestSuite.DSL.equalTo(Objects.requireNonNull(
					equalTo, "equalTo can't be null")));
		}

		@Override
		public PatternTester build() {
			return new PatternTester(underTest, inputs, expectedResult,
					havingGroup, expectedGroup);
		}

	}

	/**
	 * Used by the framework.
	 * 
	 * @return the underTest
	 */
	public Pattern getUnderTest() {
		return underTest;
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the inputs
	 */
	public List<String> getInputs() {
		return Collections.unmodifiableList(inputs);
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the expectedResult
	 */
	public List<Boolean> getExpectedResult() {
		return Collections.unmodifiableList(expectedResult);
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the havingGroup
	 */
	public List<List<Integer>> getHavingGroup() {
		return Collections.unmodifiableList(havingGroup);
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the expectedGroup
	 */
	public List<List<Matcher<String>>> getExpectedGroup() {
		return Collections.unmodifiableList(expectedGroup);
	}

}
