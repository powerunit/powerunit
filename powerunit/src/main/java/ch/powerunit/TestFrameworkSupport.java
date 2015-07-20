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
import java.util.regex.Pattern;
import java.util.stream.Collector;

import org.hamcrest.Matcher;

import ch.powerunit.bifunction.BiFunctionTester;
import ch.powerunit.bifunction.lang.BiFunctionTesterStartDSL;
import ch.powerunit.collector.CollectorTester;
import ch.powerunit.collector.lang.CollectorTesterDSL0;
import ch.powerunit.comparator.ComparatorTester;
import ch.powerunit.comparator.lang.ComparatorTesterDSLStart;
import ch.powerunit.function.FunctionTester;
import ch.powerunit.function.lang.FunctionTesterStartDSL;
import ch.powerunit.matchers.MatcherTester;
import ch.powerunit.matchers.lang.MatcherTesterDSL1;
import ch.powerunit.pattern.PatternTester;
import ch.powerunit.pattern.lang.PatternTester0;

/**
 * This is the interface for the test support.
 * 
 * @author borettim
 * @since 0.3.0
 */
interface TestFrameworkSupport {

	/**
	 * Start the creation of a tester definition for a matcher.
	 * <p>
	 * <b>{@link ch.powerunit.matchers.MatcherTester#of(Class) Please refer to
	 * the complete documentation}</b>
	 * 
	 * @param matcherClass
	 *            the class of the Matcher.
	 * @return {@link ch.powerunit.matchers.lang.MatcherTesterDSL1 The DSL}
	 * @see ch.powerunit.matchers.MatcherTester#of(Class)
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
	 * <b>{@link ch.powerunit.comparator.ComparatorTester#of(Class) Please refer
	 * to the complete documentation}</b> <br>
	 * For example :
	 * 
	 * <pre>
	 * &#064;TestDelegate
	 * public final ComparatorTester&lt;Integer, MyComparator&gt; direct = testerOfComparator(
	 * 		MyComparator.class).withLessSamples(-6).withEqualSamples(12)
	 * 		.withGreaterSamples(16).build();
	 * </pre>
	 * 
	 * @param comparatorClass
	 *            the class of the Comparator
	 * @return {@link ch.powerunit.comparator.lang.ComparatorTesterDSLStart the
	 *         DSL}
	 * @see ch.powerunit.comparator.ComparatorTester#of(Class)
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
	 * <b>{@link ch.powerunit.function.FunctionTester#of(Function) Please refer
	 * to the complete documentation}</b>
	 * 
	 * @param functionUnderTest
	 *            the function to be tested
	 * @return {@link ch.powerunit.function.lang.FunctionTesterStartDSL the DSL}
	 * @see ch.powerunit.function.FunctionTester#of(Function)
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
	 * <b>{@link ch.powerunit.bifunction.BiFunctionTester#of(BiFunction) Please
	 * refer to the complete documentation}</b> <br>
	 * For example :
	 * 
	 * <pre>
	 * &#064;TestDelegate
	 * public final BiFunctionTester&lt;Short, Integer, Long&gt; tester1 = testerOfBiFunction(
	 * 		this::bifunctionToBeTested).passingAsParameter((short) 1, 2)
	 * 		.thenExpectingResult(3l).testNamed(&quot;tested&quot;)
	 * 		.passingAsParameter((short) 2, 4).thenExpectingResult(6l).build();
	 * </pre>
	 * 
	 * @param bifunctionUnderTest
	 *            the bifunction to be tested
	 * @return {@link ch.powerunit.bifunction.lang.BiFunctionTesterStartDSL the
	 *         DSL}
	 * @see ch.powerunit.bifunction.BiFunctionTester#of(BiFunction)
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

	/**
	 * Start the DSL to create a tester of Pattern, based on a String.
	 * <p>
	 * The passed String will be compiled as a Pattern. <br>
	 * For instance :
	 * 
	 * <pre>
	 * &#064;TestDelegate
	 * public final PatternTester sample1 = testerOfPattern(&quot;a+&quot;).receiving(&quot;b&quot;)
	 * 		.thenNoMatching().receiving(&quot;aa&quot;).thenMatching().build();
	 * </pre>
	 * 
	 * @param pattern
	 *            the pattern, as a String
	 * @return {@link PatternTester0#receiving(String) The next step of the
	 *         DSL.}
	 * @see #testerOfPattern(Pattern)
	 * @see ch.powerunit.pattern.PatternTester
	 * @since 0.4.0
	 */
	default PatternTester0 testerOfPattern(String pattern) {
		return PatternTester.of(pattern);
	}

	/**
	 * Start the DSL to create a tester of Pattern, based on a String.
	 * <p>
	 * For instance :
	 * 
	 * <pre>
	 * &#064;TestDelegate
	 * public final PatternTester sample1 = testerOfPattern(Pattern.compile(&quot;a+&quot;))
	 * 		.receiving(&quot;b&quot;).thenNoMatching().receiving(&quot;aa&quot;).thenMatching().build();
	 * </pre>
	 * 
	 * @param pattern
	 *            the pattern.
	 * @return {@link PatternTester0#receiving(String) The next step of the
	 *         DSL.}
	 * @see ch.powerunit.pattern.PatternTester
	 * @since 0.4.0
	 */
	default PatternTester0 testerOfPattern(Pattern pattern) {
		return PatternTester.of(pattern);
	}

	/**
	 * Return a builder to create a tester of {@link java.util.stream.Collector
	 * Collector}.
	 * <p>
	 * <b>{@link ch.powerunit.collector.CollectorTester The documentation of the
	 * tester for the exact that are applied.}</b>
	 * 
	 * @param collectorToTest
	 *            the {@link java.util.stream.Collector Collector} to be tested.
	 * @param <T>
	 *            the input type of the {@link java.util.stream.Collector
	 *            Collector} .
	 * 
	 * @param <R>
	 *            the return type of the {@link java.util.stream.Collector
	 *            Collector}.
	 * @return {@link CollectorTesterDSL0 the DSL to build the tester}
	 * @since 0.4.0
	 * @see #testerOfCollector(Class, Class, Collector)
	 */
	default <T, R> CollectorTesterDSL0<T, ?, R> testerOfCollector(
			Collector<T, ?, R> collectorToTest) {
		return CollectorTester.of(collectorToTest);
	}

	/**
	 * Return a builder to create a tester of {@link java.util.stream.Collector
	 * Collector}.
	 * <p>
	 * <b>{@link ch.powerunit.collector.CollectorTester The documentation of the
	 * tester for the exact that are applied.}</b>
	 * 
	 * @param inputClass
	 *            the class of the input of the
	 *            {@link java.util.stream.Collector Collector}.
	 * @param outputClass
	 *            the class of the output of the
	 *            {@link java.util.stream.Collector Collector}.
	 * @param collectorToTest
	 *            the {@link java.util.stream.Collector Collector} to be tested.
	 * @param <T>
	 *            the input type of the {@link java.util.stream.Collector
	 *            Collector}.
	 * 
	 * @param <R>
	 *            the return type of the {@link java.util.stream.Collector
	 *            Collector}.
	 * @return {@link CollectorTesterDSL0 the DSL to build the tester}
	 * @since 0.4.0
	 * @see #testerOfCollector(Collector)
	 */
	default <T, R> CollectorTesterDSL0<T, ?, R> testerOfCollector(
			Class<T> inputClass, Class<T> outputClass,
			Collector<T, ?, R> collectorToTest) {
		return CollectorTester.of(collectorToTest);
	}
}
