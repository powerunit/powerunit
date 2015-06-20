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
package ch.powerunit;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.hamcrest.Matcher;

import ch.powerunit.bifunction.BiFunctionTester;
import ch.powerunit.bifunction.lang.BiFunctionTesterStartDSL;
import ch.powerunit.comparator.ComparatorTester;
import ch.powerunit.comparator.lang.ComparatorTesterDSLStart;
import ch.powerunit.function.FunctionTester;
import ch.powerunit.function.lang.FunctionTesterStartDSL;
import ch.powerunit.matchers.MatcherTester;
import ch.powerunit.matchers.lang.MatcherTesterDSL1;

/**
 * @author borettim
 * @since 0.3.0
 */
interface TestFrameworkSupport {

	/**
	 * Start the creation of a tester definition for a matcher.
	 * <p>
	 * <b>{@link MatcherTester#of(Class) Please refer to the complete
	 * documentation}</b>
	 * 
	 * @param matcherClass
	 *            the class of the Matcher.
	 * @return {@link MatcherTesterDSL1 The DSL}
	 * @see MatcherTester#of(Class)
	 * @see ch.powerunit.TestDelegate
	 * @since 0.3.0
	 * @param <T>
	 *            The matcher class
	 */
	default <T extends Matcher<?>> MatcherTesterDSL1<T> testerOfMatcher(
			Class<T> matcherClass) {
		return MatcherTester.of(matcherClass);
	}

	/**
	 * Use this method to start the DSL to test a comparator.
	 * <p>
	 * <b>{@link ComparatorTester#of(Class) Please refer to the complete
	 * documentation}</b>
	 * 
	 * @param comparatorClass
	 *            the class of the Comparator
	 * @return {@link ComparatorTesterDSLStart the DSL}
	 * @see ComparatorTester#of(Class)
	 * @see ch.powerunit.TestDelegate
	 * @since 0.3.0
	 * @param <O>
	 *            The object of the comparator
	 * @param <C>
	 *            The comparator undertest
	 */
	default <O, C extends Comparator<O>> ComparatorTesterDSLStart<O, C> testerOfComparator(
			Class<C> comparatorClass) {
		return ComparatorTester.of(comparatorClass);
	}

	/**
	 * Use this method to start the DSL to test a function.
	 * <p>
	 * <b>{@link FunctionTester#of(Function) Please refer to the complete
	 * documentation}</b>
	 * 
	 * @param functionUnderTest
	 *            the function to be tested
	 * @return {@link FunctionTesterStartDSL the DSL}
	 * @see FunctionTester#of(Function)
	 * @see ch.powerunit.TestDelegate
	 * @since 0.3.0
	 * @param <T>
	 *            The input argument type
	 * @param <R>
	 *            The result type
	 */
	default <T, R> FunctionTesterStartDSL<T, R> testerOfFunction(
			Function<T, R> functionUnderTest) {
		return FunctionTester.of(functionUnderTest);
	}

	/**
	 * Use this method to start the DSL to test a bifunction.
	 * <p>
	 * <b>{@link BiFunctionTester#of(BiFunction) Please refer to the complete
	 * documentation}</b>
	 * 
	 * @param bifunctionUnderTest
	 *            the bifunction to be tested
	 * @return {@link BiFunctionTesterStartDSL the DSL}
	 * @see BiFunctionTester#of(BiFunction)
	 * @see ch.powerunit.TestDelegate
	 * @since 0.3.0
	 * @param <T>
	 *            The first input argument type
	 * @param <U>
	 *            the second input argument type
	 * @param <R>
	 *            The result type
	 */
	default <T, U, R> BiFunctionTesterStartDSL<T, U, R> testerOfBiFunction(
			BiFunction<T, U, R> bifunctionUnderTest) {
		return BiFunctionTester.of(bifunctionUnderTest);
	}
}
