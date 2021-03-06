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

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;

import ch.powerunit.impl.AssertThatExceptionImpl;
import ch.powerunit.impl.AssertThatIterableImpl;
import ch.powerunit.impl.AssertThatObjectImpl;
import ch.powerunit.impl.AssertThatStringImpl;
import ch.powerunit.impl.FailureImpl;

/**
 * This is the assert features.
 *
 * @author borettim
 *
 */
interface Assert {

	/**
	 * Assert the value of an object.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertThat(myObject).is(myOtherObject);
	 * </pre>
	 * 
	 * This will check that <code>myObject</code> is <code>myOtherObject</code>
	 * (using the <code>equalTo</code> Hamcrest matcher). <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param <T>
	 *            the object type.
	 * @param obj
	 *            the object
	 * @return {@link AssertThatCastableObject the assert DSL on this object}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <T> AssertThatCastableObject<T> assertThat(T obj) {
		return assertThat(null, obj);
	}

	/**
	 * Assert the value of an object.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertThat(&quot;msg&quot;, myObject).is(myOtherObject);
	 * </pre>
	 * 
	 * This will check that <code>myObject</code> is <code>myOtherObject</code>
	 * (using the <code>equalTo</code> Hamcrest matcher). <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param <T>
	 *            the object type.
	 * @param msg
	 *            a message
	 * @param obj
	 *            the object
	 * @return {@link AssertThatCastableObject the assert DSL on this object}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <T> AssertThatCastableObject<T> assertThat(String msg, T obj) {
		return new AssertThatObjectImpl<T>(this, true, msg, () -> obj);
	}

	/**
	 * Assert the value of a String.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertThat(myString).is(&quot;&quot;);
	 * </pre>
	 * 
	 * This will check that <code>myString</code> is <code>""</code> (using the
	 * <code>equalTo</code> Hamcrest matcher). <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param obj
	 *            the String
	 * @return {@link AssertThatString the assert DSL on this object}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default AssertThatString assertThat(String obj) {
		return assertThat(null, obj);
	}

	/**
	 * Assert the value of a String.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertThat(&quot;msg&quot;, myString).is(&quot;&quot;);
	 * </pre>
	 * 
	 * This will check that <code>myString</code> is <code>""</code> (using the
	 * <code>equalTo</code> Hamcrest matcher). <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param msg
	 *            a message
	 * @param obj
	 *            the String
	 * @return {@link AssertThatString the assert DSL on this object}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default AssertThatString assertThat(String msg, String obj) {
		return new AssertThatStringImpl(this, true, msg, () -> obj);
	}

	/**
	 * Assert on an iterable object.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertThatIterable(myIterable).hasSize(0);
	 * </pre>
	 * 
	 * This will check that <code>myIterable</code> has a size of 0. <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param <T>
	 *            the element type.
	 * @param obj
	 *            the object (Iterable)
	 * @return {@link AssertThatIterable the assert DSL on this iterable}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <T> AssertThatIterable<T> assertThatIterable(Iterable<T> obj) {
		return assertThatIterable(null, obj);
	}

	/**
	 * Assert on an iterable object.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertThatIterable(&quot;msg&quot;, myIterable).hasSize(0);
	 * </pre>
	 * 
	 * This will check that <code>myIterable</code> has a size of 0. <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param <T>
	 *            the element type.
	 * @param msg
	 *            a message
	 * @param obj
	 *            the object (Iterable)
	 * @return {@link AssertThatIterable the assert DSL on this iterable}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <T> AssertThatIterable<T> assertThatIterable(String msg,
			Iterable<T> obj) {
		return new AssertThatIterableImpl<T>(this, true, msg, () -> obj);
	}

	/**
	 * Assert on a function.
	 * <p>
	 * The purpose of this variant of <i>assertThat</i> provides a way to apply
	 * a function on some input and to check the result.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertThatFunction((a) -&gt; a + &quot;x&quot;, &quot;b&quot;).is(&quot;bx&quot;)
	 * </pre>
	 * 
	 * This will pass the <code>b</code> string to the passed function (which
	 * add a <code>x</code> add the end of the string and then it will check
	 * that this string is <code>bx</code> (which is the case). <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param <T>
	 *            the object type of the input of the function
	 * @param <R>
	 *            the object type of the result
	 * @param function
	 *            the function
	 * @param input
	 *            the input to the function
	 * @return {@link AssertThatCastableObject then assert DSL on the result of
	 *         the function}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <T, R> AssertThatCastableObject<R> assertThatFunction(
			Function<T, R> function, T input) {
		return new AssertThatObjectImpl<R>(this, true, null,
				() -> function.apply(input));
	}

	/**
	 * Assert on a function.
	 * <p>
	 * The purpose of this variant of <i>assertThat</i> provides a way to apply
	 * a function on some input and to check the result.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertThatFunction(&quot;msg&quot;, (a) -&gt; a + &quot;x&quot;, &quot;b&quot;).is(&quot;bx&quot;)
	 * </pre>
	 * 
	 * This will pass the <code>b</code> string to the passed function (which
	 * add a <code>x</code> add the end of the string and then it will check
	 * that this string is <code>bx</code> (which is the case). <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param <T>
	 *            the object type of the input of the function
	 * @param <R>
	 *            the object type of the result
	 * @param msg
	 *            a message
	 * @param function
	 *            the function
	 * @param input
	 *            the input to the function
	 * @return {@link AssertThatCastableObject then assert DSL on the result of
	 *         the function}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <T, R> AssertThatCastableObject<R> assertThatFunction(String msg,
			Function<T, R> function, T input) {
		return new AssertThatObjectImpl<R>(this, true, msg,
				() -> function.apply(input));
	}

	/**
	 * Assert on a bifunction.
	 * <p>
	 * The purpose of this variant of <i>assertThat</i> provides a way to apply
	 * a bifunction on some input and to check the result.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertThatBiFunction((a, b) -&gt; a + b, &quot;a&quot;, &quot;b&quot;).is(&quot;ab&quot;)
	 * </pre>
	 * 
	 * This will pass the <code>b</code> string to the passed function (which
	 * add a <code>x</code> add the end of the string and then it will check
	 * that this string is <code>bx</code> (which is the case). <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param <T>
	 *            the object type of the first input of the function
	 * @param <U>
	 *            the object type fo the second input of the function
	 * @param <R>
	 *            the object type of the result
	 * @param function
	 *            the function
	 * @param input1
	 *            the first input to the function
	 * @param input2
	 *            the second input to the function
	 * @return {@link AssertThatCastableObject then assert DSL on the result of
	 *         the bifunction}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <T, U, R> AssertThatCastableObject<R> assertThatBiFunction(
			BiFunction<T, U, R> function, T input1, U input2) {
		return new AssertThatObjectImpl<R>(this, true, null,
				() -> function.apply(input1, input2));
	}

	/**
	 * Assert on a bifunction.
	 * <p>
	 * The purpose of this variant of <i>assertThat</i> provides a way to apply
	 * a bifunction on some input and to check the result.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertThatBiFunction((a, b) -&gt; a + b, &quot;a&quot;, &quot;b&quot;).is(&quot;ab&quot;)
	 * </pre>
	 * 
	 * This will pass the <code>b</code> string to the passed function (which
	 * add a <code>x</code> add the end of the string and then it will check
	 * that this string is <code>bx</code> (which is the case). <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param <T>
	 *            the object type of the first input of the function
	 * @param <U>
	 *            the object type fo the second input of the function
	 * @param <R>
	 *            the object type of the result
	 * @param msg
	 *            a message
	 * @param function
	 *            the function
	 * @param input1
	 *            the first input to the function
	 * @param input2
	 *            the second input to the function
	 * @return {@link AssertThatObject then assert DSL on the result of the
	 *         bifunction}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <T, U, R> AssertThatCastableObject<R> assertThatBiFunction(
			String msg, BiFunction<T, U, R> function, T input1, U input2) {
		return new AssertThatObjectImpl<R>(this, true, msg,
				() -> function.apply(input1, input2));
	}

	/**
	 * Assert that a statement (a piece of code) throw an exception.
	 * <p>
	 * The goal of <code>assertWhen</code> is to provide a way to validate that
	 * an exception is thrown.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertWhen((p) -&gt; {
	 * 	throw new Throwable(&quot;test&quot;);
	 * }).throwException(exceptionMessage(&quot;test&quot;));
	 * </pre>
	 * 
	 * Will run a piece of code that always thrown an exception and then
	 * validate that the message of the exception is <code>test</code>. <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param underTest
	 *            the {@link Statement} <code>(p)-&gt;{}</code>
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @param <T>
	 *            the exception type
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <T extends Throwable> AssertThatException<T> assertWhen(
			Statement<?, T> underTest) {
		return assertWhen(null, underTest, null);
	}

	/**
	 * Assert that a statement (a piece of code) throw an exception.
	 * <p>
	 * The goal of <code>assertWhen</code> is to provide a way to validate that
	 * an exception is thrown.
	 * <p>
	 * For instance, having this method :
	 * 
	 * <pre>
	 * public static void method1() throws Exception {
	 * 	throw new Exception(&quot;demo1&quot;);
	 * }
	 * </pre>
	 * 
	 * It can be tested by :
	 * 
	 * <pre>
	 * &#064;Test
	 * public void testNoArgNoReturn() {
	 * 	assertWhen(asStatement(DemoAssertThatExceptionTest::method1))
	 * 			.throwException(exceptionMessage(&quot;demo1&quot;));
	 * }
	 * </pre>
	 * 
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param underTest
	 *            the {@link Callable}
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @param <T>
	 *            the exception type
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 * @since 0.4.0
	 */
	default <T extends Exception> AssertThatException<T> assertWhen(
			Callable<?> underTest) {
		return assertWhen(null, (p) -> {
			underTest.call();
		}, null);
	}

	/**
	 * Assert that a statement (a piece of code) throw an exception.
	 * <p>
	 * The goal of <code>assertWhen</code> is to provide a way to validate that
	 * an exception is thrown.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertWhen((p) -&gt; {
	 * 	throw new Throwable(&quot;test&quot;);
	 * }, null).throwException(exceptionMessage(&quot;test&quot;));
	 * </pre>
	 * 
	 * Will run a piece of code, passing null as parameter, that always thrown
	 * an exception and then validate that the message of the exception is
	 * <code>test</code>. <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param underTest
	 *            the {@link Statement} <code>(p)-&gt;{}</code>
	 * @param param
	 *            the parameter for the statement underTest
	 * @param <P>
	 *            the type of the parameter
	 * @param <T>
	 *            the exception type
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 * 
	 */
	default <P, T extends Throwable> AssertThatException<T> assertWhen(
			Statement<P, T> underTest, P param) {
		return assertWhen(null, underTest, param);
	}

	/**
	 * Assert that a statement (a piece of code) throw an exception.
	 * <p>
	 * The goal of <code>assertWhen</code> is to provide a way to validate that
	 * an exception is thrown.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertWhen(&quot;msg&quot;, (p) -&gt; {
	 * 	throw new Throwable(&quot;test&quot;);
	 * }).throwException(exceptionMessage(&quot;test&quot;));
	 * </pre>
	 * 
	 * Will run a piece of code that always thrown an exception and then
	 * validate that the message of the exception is <code>test</code>. <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param msg
	 *            a message
	 * @param underTest
	 *            the statement <code>(p)-&gt;{}</code>
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @param <T>
	 *            the exception type
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <T extends Throwable> AssertThatException<T> assertWhen(String msg,
			Statement<?, T> underTest) {
		return assertWhen(msg, underTest, null);
	}

	/**
	 * Assert that a statement (a piece of code) throw an exception.
	 * <p>
	 * The goal of <code>assertWhen</code> is to provide a way to validate that
	 * an exception is thrown.
	 * <p>
	 * 
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param msg
	 *            a message
	 * @param underTest
	 *            the callable
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @param <T>
	 *            the exception type
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 * @since 0.4.0
	 */
	default <T extends Exception> AssertThatException<T> assertWhen(String msg,
			Callable<?> underTest) {
		return assertWhen(msg, (p) -> {
			underTest.call();
		}, null);
	}

	/**
	 * Assert that a statement (a piece of code) throw an exception.
	 * <p>
	 * The goal of <code>assertWhen</code> is to provide a way to validate that
	 * an exception is thrown.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assertWhen(&quot;msg&quot;, (p) -&gt; {
	 * 	throw new Throwable(&quot;test&quot;);
	 * }, null).throwException(exceptionMessage(&quot;test&quot;));
	 * </pre>
	 * 
	 * Will run a piece of code, passing null as parameter, that always thrown
	 * an exception and then validate that the message of the exception is
	 * <code>test</code>. <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param msg
	 *            a message
	 * @param underTest
	 *            the statement <code>(p)-&gt;{}</code>
	 * @param param
	 *            the parameter for the statement underTest
	 * @param <P>
	 *            the type of the parameter
	 * @param <T>
	 *            the exception type
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 * 
	 */
	default <P, T extends Throwable> AssertThatException<T> assertWhen(
			String msg, Statement<P, T> underTest, P param) {
		return new AssertThatExceptionImpl<P, T>(this, true, underTest, param,
				msg);
	}

	/**
	 * Assert that a function throw an exception. As {@link Function} signature
	 * doesn't throws exception, it should be a RuntimeException. <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param msg
	 *            a message
	 * @param function
	 *            the function to be executed and that should return an
	 *            exception
	 * @param param
	 *            the parameter to be passed to the function.
	 * @param <P>
	 *            the type of the parameter
	 * @param <T>
	 *            the exception type
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @since 0.3.0
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <P, T extends RuntimeException> AssertThatException<T> assertWhenFunction(
			String msg, Function<P, ?> function, P param) {
		return assertWhen(msg, (p) -> function.apply(p), param);
	}

	/**
	 * Assert that a function throw an exception. As {@link Function} signature
	 * doesn't throws exception, it should be a RuntimeException. <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param function
	 *            the function to be executed and that should return an
	 *            exception
	 * @param param
	 *            the parameter to be passed to the function.
	 * @param <P>
	 *            the type of the parameter
	 * @param <T>
	 *            the exception type
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @since 0.3.0
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <P, T extends RuntimeException> AssertThatException<T> assertWhenFunction(
			Function<P, ?> function, P param) {
		return assertWhen((p) -> function.apply(p), param);
	}

	/**
	 * Assert that a bifunction throw an exception. As {@link BiFunction}
	 * signature doesn't throws exception, it should be a RuntimeException. <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param msg
	 *            a message
	 * @param bifunction
	 *            the bifunction to be executed and that should return an
	 *            exception
	 * @param param1
	 *            the first parameter to be used.
	 * @param param2
	 *            the second parameter to be used.
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @param <P1>
	 *            the type of the first parameter
	 * @param <P2>
	 *            the type of the second parameter
	 * @param <T>
	 *            the exception type
	 * @since 0.3.0
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <P1, P2, T extends RuntimeException> AssertThatException<T> assertWhenBiFunction(
			String msg, BiFunction<P1, P2, ?> bifunction, P1 param1, P2 param2) {
		return assertWhen(msg, (p) -> bifunction.apply(param1, param2));
	}

	/**
	 * Assert that a bifunction throw an exception. As {@link BiFunction}
	 * signature doesn't throws exception, it should be a RuntimeException. <br>
	 * <br>
	 * <i>By default, assertThat can only be used from the main thread of the
	 * test ; When used from another thread, the assertion will be lost. In the
	 * case the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param bifunction
	 *            the bifunction to be executed and that should return an
	 *            exception
	 * @param param1
	 *            the first parameter to be used.
	 * @param param2
	 *            the second parameter to be used.
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @param <P1>
	 *            the type of the first parameter
	 * @param <P2>
	 *            the type of the second parameter
	 * @param <T>
	 *            the exception type
	 * @since 0.3.0
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this assertion.
	 */
	default <P1, P2, T extends RuntimeException> AssertThatException<T> assertWhenBiFunction(
			BiFunction<P1, P2, ?> bifunction, P1 param1, P2 param2) {
		return assertWhen((p) -> bifunction.apply(param1, param2));
	}

	/**
	 * Always produce a failure.
	 * <p>
	 * For instance :
	 * 
	 * <pre>
	 * fail();
	 * </pre>
	 * 
	 * will immediately fail the current test. <br>
	 * <br>
	 * <i>By default, fail can only be used from the main thread of the test ;
	 * When used from another thread, the assertion will be lost. In the case
	 * the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @return Depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this failure.
	 */
	default boolean fail() {
		return fail("Manual failure");
	}

	/**
	 * Always produce a failure.
	 * <p>
	 * For instance :
	 * 
	 * <pre>
	 * fail(&quot;my message&quot;);
	 * </pre>
	 * 
	 * will immediately fail the current test. <br>
	 * <br>
	 * <i>By default, fail can only be used from the main thread of the test ;
	 * When used from another thread, the assertion will be lost. In the case
	 * the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param msg
	 *            a message
	 * @return Depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this failure.
	 */
	default boolean fail(String msg) {
		return FailureImpl.fail(this, new AssertionError(msg));
	}

	/**
	 * Always produce a failure.
	 * <p>
	 * For instance :
	 * 
	 * <pre>
	 * fail(&quot;my message&quot;, t);
	 * </pre>
	 * 
	 * will immediately fail the current test. <br>
	 * <br>
	 * <i>By default, fail can only be used from the main thread of the test ;
	 * When used from another thread, the assertion will be lost. In the case
	 * the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param msg
	 *            a message
	 * @param innerError
	 *            the error cause
	 * @return Depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this failure.
	 */
	default boolean fail(String msg, Throwable innerError) {
		return FailureImpl.fail(this, new AssertionError(msg, innerError));
	}

	/**
	 * Always produce a failure.
	 * <p>
	 * For instance :
	 * 
	 * <pre>
	 * fail(t);
	 * </pre>
	 * 
	 * will immediately fail the current test. <br>
	 * <br>
	 * <i>By default, fail can only be used from the main thread of the test ;
	 * When used from another thread, the assertion will be lost. In the case
	 * the {@link Test#fastFail() fastFail} attribute of {@link Test @Test}
	 * annotation is used, the assertion may not be lost, in case the thread use
	 * an assertion method from the test object instance. </i>
	 * 
	 * @param innerError
	 *            the error cause
	 * @return Depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 * @see Test#fastFail() The documentation of the <code>fastFail</code>
	 *      attribute of the <code>@Test</code> annotation, regarding the action
	 *      done by this failure.
	 */
	default boolean fail(Throwable innerError) {
		return FailureImpl.fail(this,
				new AssertionError(innerError.getMessage(), innerError));
	}

}
