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
package ch.powerunit.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import org.hamcrest.Matcher;

import ch.powerunit.TestInterface;
import ch.powerunit.function.impl.FunctionTesterImpl;
import ch.powerunit.function.impl.SupplierEqualsToMatcher;
import ch.powerunit.function.lang.FunctionTesterDefineDSL;
import ch.powerunit.function.lang.FunctionTesterEndDSL;
import ch.powerunit.function.lang.FunctionTesterNextDSL;
import ch.powerunit.function.lang.FunctionTesterStartDSL;

/**
 * Tester for function.
 * 
 * @author borettim
 * @since 0.3.0
 */
@TestInterface(FunctionTesterImpl.class)
public final class FunctionTester<T, R> {
	private final Function<T, R> underTest;
	private final List<Supplier<T>> input;
	private final List<Supplier<Matcher<? super R>>> result;
	private final List<Supplier<String>> name;

	private static class FunctionTesterDSL<T, R> implements
			FunctionTesterDefineDSL<T, R>, FunctionTesterEndDSL<T, R>,
			FunctionTesterNextDSL<T, R>, FunctionTesterStartDSL<T, R> {

		private final Function<T, R> underTest;

		private List<Supplier<T>> tmpInput = new ArrayList<>();

		private List<Supplier<Matcher<? super R>>> tmpResult = new ArrayList<>();

		private List<Supplier<String>> tmpName = new ArrayList<>();

		private void finalizeCase() {
			if (tmpName.size() < tmpInput.size()) {
				tmpName.add(() -> "");
			}
		}

		public FunctionTesterDSL(Function<T, R> underTest) {
			this.underTest = underTest;
		}

		@Override
		public FunctionTesterDefineDSL<T, R> passingAsParameter(T input) {
			return passingAsParameter(() -> input);
		}

		@Override
		public FunctionTesterDefineDSL<T, R> passingAsParameter(
				Supplier<T> input) {
			Objects.requireNonNull(input, "input can't be null");
			finalizeCase();
			tmpInput.add(input);
			return this;
		}

		@Override
		public FunctionTesterNextDSL<T, R> thenExpectingResult(R result) {
			return thenExpectingResult(() -> result);
		}

		@Override
		public FunctionTesterNextDSL<T, R> thenExpectingResult(
				Supplier<R> result) {
			Objects.requireNonNull(result, "matching can't be null");
			return thenExpectingResultThat(new SupplierEqualsToMatcher<R>(
					result));
		}

		@Override
		public FunctionTesterNextDSL<T, R> thenExpectingResultThat(
				Matcher<? super R> matching) {
			Objects.requireNonNull(matching, "matching can't be null");
			return thenExpectingResultThat(() -> matching);
		}

		@Override
		public FunctionTesterNextDSL<T, R> thenExpectingResultThat(
				Supplier<Matcher<? super R>> matching) {
			Objects.requireNonNull(matching, "matching can't be null");
			tmpResult.add(matching);
			return this;
		}

		@Override
		public FunctionTesterEndDSL<T, R> testNamed(String name) {
			return testNamed(() -> name);
		}

		@Override
		public FunctionTesterEndDSL<T, R> testNamed(Supplier<String> name) {
			Objects.requireNonNull(name, "name can't be null");
			tmpName.add(name);
			return this;
		}

		@Override
		public FunctionTester<T, R> build() {
			finalizeCase();
			return new FunctionTester<T, R>(underTest, tmpInput, tmpResult,
					tmpName);
		}

	}

	private FunctionTester(Function<T, R> underTest,
			List<Supplier<T>> tmpInput,
			List<Supplier<Matcher<? super R>>> tmpResult,
			List<Supplier<String>> tmpName) {
		this.underTest = underTest;
		this.input = tmpInput;
		this.result = tmpResult;
		this.name = tmpName;
	}

	/**
	 * Start the creation of a tester of function.
	 * 
	 * @param functionUnderTest
	 *            the function to be tested
	 * @return {@link FunctionTesterStartDSL the DSL}
	 * @throws NullPointerException
	 *             when functionUnderTest is null
	 * @param <T>
	 *            the input argument type
	 * @param <T>
	 *            the result type
	 * @see ch.powerunit.TestDelegate
	 */
	public static <T, R> FunctionTesterStartDSL<T, R> of(
			Function<T, R> functionUnderTest) {
		Objects.requireNonNull(functionUnderTest,
				"functionUnderTest can't be null");
		return new FunctionTesterDSL<T, R>(functionUnderTest);
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the underTest
	 */
	public Function<T, R> getUnderTest() {
		return underTest;
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the input
	 */
	public List<Supplier<T>> getInput() {
		return Collections.unmodifiableList(input);
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
