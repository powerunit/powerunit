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

import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.hamcrest.Matcher;

import ch.powerunit.exception.AssumptionError;
import ch.powerunit.helpers.StreamParametersMapFunction;
import ch.powerunit.rules.SystemPropertiesRule;
import ch.powerunit.rules.SystemStreamRule;
import ch.powerunit.rules.TemporaryFolder;
import ch.powerunit.rules.TemporaryFolder.TemporaryFolderBuilder;
import ch.powerunit.rules.TestListenerRule;
import ch.powerunit.rules.impl.TemporaryFolderImpl;

/**
 * This is the interface to be implemented by test class, in order to have
 * access to the test DSL.
 * <p>
 * This interface is only used to provide the DSL capabilities for the test ; It
 * is not used as a marker by the framework.
 * <p>
 * Several functionalities are provided here :
 * <ul>
 * <li>Assertion capabilities (assertion on Object, Iterable, Function, Piece of
 * code).</li>
 * <li>Matchers to be used as end parameter of the assertion.</li>
 * <li><code>{@link #before(Runnable...)}</code> to setup a before action.</li>
 * <li><code>{@link #after(Runnable...)}</code> to setup a after action.</li>
 * <li><code>{@link #mockitoRule()}</code> to setup mockito.</li>
 * <li><code>{@link #systemPropertiesRule(String...)}</code> to restore
 * properties system after test ; Several others method related to
 * systemProperties exist.</li>
 * <li><code>{@link #temporaryFolder()}</code> to support temporary folder.</li>
 * <li><code>{@link #parametersMap(int, Function)}</code> to create Map function
 * to be used on the stream to be provided by the {@link Parameters
 * &#64;Parameters}. Other variants of this method may exist.</li>
 * <li>Severals method of the form <code>tester...</code> (for instance
 * <code>{@link #testerOfMatcher(Class)}</code>) that can be used with the
 * <code>@{@link TestDelegate}</code> annotation to test some standard classes
 * (like Matchers, Comparator, Function, etc).
 * </ul>
 *
 * @author borettim
 *
 */
public interface TestSuite extends Assert, Assume, Matchers,
		TestFrameworkSupport, ConverterMethod, DateTimeMatchers {

	/**
	 * A static field that is a testsuite (to avoid implementing TestSuite in
	 * test, in the rare case when it may be required).
	 * <p>
	 * The main use case is to access the stream functionnalities from a
	 * {@link Parameters &#64;Parameters} annotated method, has this method must
	 * be static.
	 */
	static TestSuite DSL = new TestSuite() {
	};

	/**
	 * Build a before testrule.
	 * <p>
	 * The passed runnable will be used before each test. The exact location of
	 * the execution is depending on where this used on the testRule chain.
	 * <p>
	 * In the much simple case (just one method to be executed before each
	 * test), the syntax is :
	 * 
	 * <pre>
	 * &#064;Rule
	 * public TestRule rule = before(this::beforeMethodName);
	 * </pre>
	 * 
	 * @param befores
	 *            the befores
	 * @return {@link TestRule the rule chain}.
	 * @see Rule
	 */
	default TestRule before(Runnable... befores) {
		return Arrays.stream(befores).map(TestRule::before)
				.reduce((prev, next) -> prev.around(next)).get();
	}

	/**
	 * Build a after testrule.
	 * <p>
	 * The passed runnable will be used after each test. The exact location of
	 * the execution is depending on where this used on the testRule chain.
	 * <p>
	 * In the much simple case (just one method to be executed after each test),
	 * the syntax is :
	 * 
	 * <pre>
	 * &#064;Rule
	 * public TestRule rule = after(this::afterMethodName);
	 * </pre>
	 * 
	 * @param afters
	 *            the afters
	 * @return {@link TestRule the rule chain}.
	 * @see Rule
	 */
	default TestRule after(Runnable... afters) {
		return Arrays.stream(afters).map(TestRule::after)
				.reduce((prev, next) -> prev.around(next)).get();
	}

	/**
	 * Create a rule to support mockito.
	 * <p>
	 * This provide a way to setup Mockito before each test.
	 * 
	 * @return {@link TestRule the rule chain}.
	 * @see Rule
	 */
	default TestRule mockitoRule() {
		return TestRule.mockitoRule();
	}

	/**
	 * Produces a new rule for the temporary folder.
	 * <p>
	 * As the {@link TemporaryFolder} rule provides several methods that are
	 * required for the test, except in the case when only this rule is
	 * required, a direct usage in the rule DSL is not adapted.
	 * 
	 * For instance, assuming that it is required to mix a before and the
	 * {@link TemporaryFolder} rule, the code will look like :
	 * 
	 * <pre>
	 * private TemporaryFolder temporary = temporaryFolder();
	 * 
	 * &#064;Rule
	 * public TestRule rule = before(this::beforeMethodName).around(temporary);
	 * </pre>
	 * 
	 * This is required to ensure that the method of the {@link TemporaryFolder}
	 * object can be used (using the field named <code>temporary</code>).
	 * 
	 * @return the temporary folder rule.
	 * @see Rule
	 */
	default TemporaryFolder temporaryFolder() {
		return temporaryFolderBuilder().build();
	}

	/**
	 * Produces a new rule builder for the temporary folder.
	 * <p>
	 * As the {@link TemporaryFolder} rule provides several methods that are
	 * required for the test, except in the case when only this rule is
	 * required, a direct usage in the rule DSL is not adapted.
	 * 
	 * For instance, assuming that it is required to mix a before and the
	 * {@link TemporaryFolder} rule, the code will look like :
	 * 
	 * <pre>
	 * private TemporaryFolder temporary = temporaryFolderBuilder().build();
	 * 
	 * &#064;Rule
	 * public TestRule rule = before(this::beforeMethodName).around(temporary);
	 * </pre>
	 * 
	 * This is required to ensure that the method of the {@link TemporaryFolder}
	 * object can be used (using the field named <code>temporary</code>).
	 * <p>
	 * The builder provide several capabilities to create initial folder
	 * structure at the same time than the temporary folder itself.
	 * 
	 * @return the temporary folder rule builder.
	 * @see Rule
	 */
	default TemporaryFolderBuilder temporaryFolderBuilder() {
		return new TemporaryFolderImpl.TemporaryFolderBuilderImpl();
	}

	/**
	 * Create a rule to restore some system properties after the test
	 * 
	 * @param propertiesName
	 *            the properties to be restored
	 * @return {@link TestRule the rule chain}.
	 * @see Rule
	 */
	default TestRule systemPropertiesRule(String... propertiesName) {
		return new SystemPropertiesRule(propertiesName);
	}

	/**
	 * Set a property before the run and ensure correct restore.
	 * 
	 * @param propertyName
	 *            the name of the property
	 * @param propertyValue
	 *            the value of the property
	 * @return {@link TestRule the rule chain}.
	 * @see Rule
	 */
	default TestRule systemProperty(String propertyName,
			Supplier<String> propertyValue) {
		return SystemPropertiesRule.setSystemPropertyBeforeTestAndRestoreAfter(
				propertyName, propertyValue);
	}

	/**
	 * Set a property before the run and ensure correct restore.
	 * 
	 * @param propertyName
	 *            the name of the property
	 * @param propertyValue
	 *            the value of the property
	 * @return {@link TestRule the rule chain}.
	 * @see Rule
	 */
	default TestRule systemProperty(String propertyName, String propertyValue) {
		return SystemPropertiesRule.setSystemPropertyBeforeTestAndRestoreAfter(
				propertyName, propertyValue);
	}

	/**
	 * Start building a Parameter Mapper function, with an initial converter.
	 * <p>
	 * Not specified index are considered transformed by identity function.
	 * 
	 * @param idx
	 *            The parameter index
	 * @param mapFunction
	 *            the function to be applied
	 * @return the function on the parameter array
	 * @param <T>
	 *            The input type for the function
	 * @param <R>
	 *            the result type for the function
	 */
	default <T, R> StreamParametersMapFunction<T> parametersMap(int idx,
			Function<T, R> mapFunction) {
		return StreamParametersMapFunction.map(idx, mapFunction);
	}

	/**
	 * Start building a Parameter Mapper function, assuming that the input are
	 * String, and using the type of the {@link Parameter &#64;Parameter} field.
	 * <p>
	 * Fields not supported will not be mapped and must be handled manually,
	 * using {@link StreamParametersMapFunction#andMap(int, Function) andMap}
	 * method to avoid any unexpected error.
	 * <p>
	 * The goal of this method is to provide a way to receive so generic
	 * parameter and not having to care about typing. Let's take for example the
	 * following use case :
	 * <ul>
	 * <li>As an input for the test parameters, a CSV file is used. In this
	 * case, a framework like <a
	 * href="http://opencsv.sourceforge.net/">OpenCSV</a> can be used to load
	 * all the parameters, but they are all String.</li>
	 * <li>On the produced stream, the
	 * {@link java.util.stream.Stream#map(Function) map} method can be used to
	 * transform the received data into another format. Here, using the result
	 * of this method as parameter of the map method will ensure the
	 * transformation of the String to the right type, for simple type.</li>
	 * <li>For undetected field type, it is possible to use the method
	 * {@link StreamParametersMapFunction#andMap(int, Function) andMap} (on the
	 * returned object of this method), to add manual transformation.</li>
	 * </ul>
	 * <p>
	 * In this context, as the {@link Parameters &#64;Parameters} annotated
	 * method must be static, access to this method can be done using
	 * {@link #DSL DSL.} prefix.
	 * 
	 * @param testClass
	 *            the testClass, as this method is to be used in static mode.
	 * @return the function on the parameter array
	 * @see <a href="./helpers/doc-files/convertedType.html">Supported automated
	 *      conversion</a>
	 */
	default StreamParametersMapFunction<String> stringToParameterMap(
			Class<?> testClass) {
		return StreamParametersMapFunction.stringToParameterMap(testClass);
	}

	/**
	 * Provide a way to add a field to each parameter line.
	 * 
	 * @param field
	 *            The field to be added.
	 * @return the function that can be used on the stream (
	 *         {@link java.util.stream.Stream#map(Function)}).
	 * @since 0.1.0
	 * @param <T>
	 *            The object type to be added.
	 */
	default <T> Function<Object[], Object[]> addFieldToEachEntry(T field) {
		return StreamParametersMapFunction.addFieldToEachEntry(field);
	}

	/**
	 * Provide a filter for stream parameters, to keep only parameters accepted
	 * by a matcher.
	 * 
	 * @param matcher
	 *            the matcher
	 * @return the stream filter
	 */
	default Predicate<Object[]> parametersFilterUsingMatcher(
			Matcher<Object[]> matcher) {
		return matcherPredicate(matcher);
	}

	/**
	 * Expose a matcher as a predicate.
	 * 
	 * @param matcher
	 *            the matcher.
	 * @return the predicate
	 * @param <T>
	 *            The target object type
	 */
	default <T> Predicate<T> matcherPredicate(Matcher<T> matcher) {
		return matcher::matches;
	}

	/**
	 * Provide a test rule that suppress both {@link java.lang.System#err system
	 * err} and {@link java.lang.System#out system out}.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 * @since 0.4.0
	 */
	default SystemStreamRule disableBothStreams() {
		return SystemStreamRule.disableBothStreams();
	}

	/**
	 * Provide a test rule that replace both {@link java.lang.System#err system
	 * err} and {@link java.lang.System#out system out} with the provided one.
	 * 
	 * @param outReplacement
	 *            the replacement of the {@link java.lang.System#out system out}
	 *            stream.
	 * @param errRemplacement
	 *            the replacement of the {@link java.lang.System#err system err}
	 *            stream.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 * @since 0.4.0
	 */
	default SystemStreamRule replaceBothStream(PrintStream outReplacement,
			PrintStream errRemplacement) {
		return SystemStreamRule.replaceBothStream(outReplacement,
				errRemplacement);
	}

	/**
	 * Provide a test rule that suppress the {@link java.lang.System#out system
	 * out} stream.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 * @since 0.4.0
	 */
	default SystemStreamRule disableOutStream() {
		return SystemStreamRule.disableOutStream();
	}

	/**
	 * Provide a test rule that suppress the {@link java.lang.System#err system
	 * err} stream.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 * @since 0.4.0
	 */
	default SystemStreamRule disableErrStream() {
		return SystemStreamRule.disableErrStream();
	}

	/**
	 * Privde a test rule that replace the {@link java.lang.System#out system
	 * out} stream with the provided one
	 * 
	 * @param outReplacement
	 *            the replacement of the {@link java.lang.System#out system out}
	 *            stream.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 * @since 0.4.0
	 */
	default SystemStreamRule replaceOutStream(PrintStream outReplacement) {
		return SystemStreamRule.replaceOutStream(outReplacement);
	}

	/**
	 * Privde a test rule that replace the {@link java.lang.System#err system
	 * err} stream with the provided one
	 * 
	 * @param errReplacement
	 *            the replacement of the {@link java.lang.System#err system err}
	 *            stream.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 * @since 0.4.0
	 */
	default SystemStreamRule replaceErrStream(PrintStream errReplacement) {
		return SystemStreamRule.replaceErrStream(errReplacement);
	}

	/**
	 * Build a {@link TestListenerRule} based on the various method.
	 * 
	 * @param onStart
	 *            {@link TestListenerRule#onStart(TestContext) the action to be
	 *            done before the test start}. If null, nothing is done.
	 * @param onEnd
	 *            {@link TestListenerRule#onEnd(TestContext) the action to be
	 *            done after the test end}. If null, nothing is done.
	 * @param onFailure
	 *            {@link TestListenerRule#onFailure(TestContext, AssertionError)
	 *            the action to be done in case of failure}. If null, nothing is
	 *            done.
	 * @param onError
	 *            {@link TestListenerRule#onError(TestContext, Throwable) the
	 *            action to be done in case of error}. If null, nothing is done.
	 * @param onAssumptionSkip
	 *            {@link TestListenerRule#onAssumptionSkip(TestContext, AssumptionError)
	 *            the action to be done in case of assumption skipped}. If null
	 *            nothing is done.
	 * @return the Test Rule.
	 * @since 0.4.0
	 */
	default TestListenerRule testListenerRuleOf(
			Consumer<TestContext<Object>> onStart,
			Consumer<TestContext<Object>> onEnd,
			BiConsumer<TestContext<Object>, AssertionError> onFailure,
			BiConsumer<TestContext<Object>, Throwable> onError,
			BiConsumer<TestContext<Object>, AssumptionError> onAssumptionSkip) {
		return TestListenerRule.of(onStart, onEnd, onFailure, onError,
				onAssumptionSkip);
	}

	/**
	 * Build a {@link TestListenerRule} with only an action at start.
	 * 
	 * @param onStart
	 *            {@link TestListenerRule#onStart(TestContext) the action to be
	 *            done before the test start}.
	 * @return the Test Rule.
	 * @since 0.4.0
	 */
	default TestListenerRule testListenerRuleOnStart(
			Consumer<TestContext<Object>> onStart) {
		return TestListenerRule.onStart(onStart);
	}

	/**
	 * Build a {@link TestListenerRule} with only an action at end.
	 * 
	 * @param onEnd
	 *            {@link TestListenerRule#onEnd(TestContext) the action to be
	 *            done after the test end}.
	 * @return the Test Rule.
	 * @since 0.4.0
	 */
	default TestListenerRule testListenerRuleOnEnd(
			Consumer<TestContext<Object>> onEnd) {
		return TestListenerRule.onEnd(onEnd);
	}
}
