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
package ch.powerunit.bifunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.hamcrest.Matcher;

import ch.powerunit.TestInterface;
import ch.powerunit.bifunction.impl.BiFunctionTesterImpl;
import ch.powerunit.function.impl.SupplierEqualsToMatcher;
import ch.powerunit.bifunction.lang.BiFunctionTesterDefineDSL;
import ch.powerunit.bifunction.lang.BiFunctionTesterEndDSL;
import ch.powerunit.bifunction.lang.BiFunctionTesterNextDSL;
import ch.powerunit.bifunction.lang.BiFunctionTesterStartDSL;

/**
 * Tester for function.
 * 
 * @author borettim
 * @since 0.3.0
 */
@TestInterface(BiFunctionTesterImpl.class)
public final class BiFunctionTester<T, U, R> {
	private final BiFunction<T, U, R> underTest;
	private final List<Supplier<T>> input1;
	private final List<Supplier<U>> input2;
	private final List<Supplier<Matcher<? super R>>> result;
	private final List<Supplier<String>> name;

	private static class BiFunctionTesterDSL<T, U, R> implements
			BiFunctionTesterDefineDSL<T, U, R>,
			BiFunctionTesterEndDSL<T, U, R>, BiFunctionTesterNextDSL<T, U, R>,
			BiFunctionTesterStartDSL<T, U, R> {

		private final BiFunction<T, U, R> underTest;

		private List<Supplier<T>> tmpInput1 = new ArrayList<>();

		private List<Supplier<U>> tmpInput2 = new ArrayList<>();

		private List<Supplier<Matcher<? super R>>> tmpResult = new ArrayList<>();

		private List<Supplier<String>> tmpName = new ArrayList<>();

		private void finalizeCase() {
			if (tmpName.size() < tmpInput1.size()) {
				tmpName.add(() -> "");
			}
		}

		public BiFunctionTesterDSL(BiFunction<T, U, R> underTest) {
			this.underTest = underTest;
		}

		@Override
		public BiFunctionTesterDefineDSL<T, U, R> passingAsParameter(T input1,
				U input2) {
			return passingAsParameter(() -> input1, () -> input2);
		}

		@Override
		public BiFunctionTesterDefineDSL<T, U, R> passingAsParameter(
				Supplier<T> input1, Supplier<U> input2) {
			Objects.requireNonNull(input1, "input1 can't be null");
			Objects.requireNonNull(input2, "input2 can't be null");
			finalizeCase();
			tmpInput1.add(input1);
			tmpInput2.add(input2);
			return this;
		}

		@Override
		public BiFunctionTesterNextDSL<T, U, R> thenExpectingResult(R result) {
			return thenExpectingResult(() -> result);
		}

		@Override
		public BiFunctionTesterNextDSL<T, U, R> thenExpectingResult(
				Supplier<R> result) {
			Objects.requireNonNull(result, "matching can't be null");
			return thenExpectingResultThat(new SupplierEqualsToMatcher<R>(
					result));
		}

		@Override
		public BiFunctionTesterNextDSL<T, U, R> thenExpectingResultThat(
				Matcher<? super R> matching) {
			Objects.requireNonNull(matching, "matching can't be null");
			return thenExpectingResultThat(() -> matching);
		}

		@Override
		public BiFunctionTesterNextDSL<T, U, R> thenExpectingResultThat(
				Supplier<Matcher<? super R>> matching) {
			Objects.requireNonNull(matching, "matching can't be null");
			tmpResult.add(matching);
			return this;
		}

		@Override
		public BiFunctionTesterEndDSL<T, U, R> testNamed(String name) {
			return testNamed(() -> name);
		}

		@Override
		public BiFunctionTesterEndDSL<T, U, R> testNamed(Supplier<String> name) {
			Objects.requireNonNull(name, "name can't be null");
			tmpName.add(name);
			return this;
		}

		@Override
		public BiFunctionTester<T, U, R> build() {
			finalizeCase();
			return new BiFunctionTester<T, U, R>(underTest, tmpInput1,
					tmpInput2, tmpResult, tmpName);
		}
	}

	private BiFunctionTester(BiFunction<T, U, R> underTest,
			List<Supplier<T>> tmpInput1, List<Supplier<U>> tmpInput2,
			List<Supplier<Matcher<? super R>>> tmpResult,
			List<Supplier<String>> tmpName) {
		this.underTest = underTest;
		this.input1 = tmpInput1;
		this.input2 = tmpInput2;
		this.result = tmpResult;
		this.name = tmpName;
	}

	/**
	 * Start the creation of a tester of function.
	 * 
	 * @param bifunctionUnderTest
	 *            the function to be tested
	 * @return {@link BiFunctionTesterStartDSL the DSL}
	 * @throws NullPointerException
	 *             when bifunctionUnderTest is null
	 * @param <T>
	 *            the first input argument type
	 * @param <U>
	 *            the second input argument type
	 * @param <R>
	 *            the result type
	 * @see ch.powerunit.TestDelegate
	 */
	public static <T, U, R> BiFunctionTesterStartDSL<T, U, R> of(
			BiFunction<T, U, R> bifunctionUnderTest) {
		Objects.requireNonNull(bifunctionUnderTest,
				"bifunctionUnderTest can't be null");
		return new BiFunctionTesterDSL<T, U, R>(bifunctionUnderTest);
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the underTest
	 */
	public BiFunction<T, U, R> getUnderTest() {
		return underTest;
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the first input
	 */
	public List<Supplier<T>> getInput1() {
		return Collections.unmodifiableList(input1);
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the first input
	 */
	public List<Supplier<U>> getInput2() {
		return Collections.unmodifiableList(input2);
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the result
	 */
	public List<Supplier<Matcher<? super R>>> getResult() {
		return Collections.unmodifiableList(result);
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the name
	 */
	public List<Supplier<String>> getName() {
		return Collections.unmodifiableList(name);
	}

}
