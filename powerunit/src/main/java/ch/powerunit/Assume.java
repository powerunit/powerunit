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

import java.util.function.BiFunction;
import java.util.function.Function;

import ch.powerunit.exception.AssumptionError;
import ch.powerunit.impl.AssertThatExceptionImpl;
import ch.powerunit.impl.AssertThatIterableImpl;
import ch.powerunit.impl.AssertThatObjectImpl;
import ch.powerunit.impl.AssertThatStringImpl;

/**
 * This is the assume features.
 * 
 * @author borettim
 *
 */
interface Assume {

	/**
	 * Assume the value of an object. This is used to skipped the test if the
	 * assumption fail.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeThat(myObject).is(myOtherObject);
	 * </pre>
	 * 
	 * This will check that <code>myObject</code> is <code>myOtherObject</code>
	 * (using the <code>equalTo</code> Hamcrest matcher).
	 * 
	 * @param <T>
	 *            the object type.
	 * @param obj
	 *            the object
	 * @return {@link AssertThatObject the assert DSL on this object}
	 */
	default <T> AssertThatObject<T> assumeThat(T obj) {
		return assumeThat(null, obj);
	}

	/**
	 * Assume the value of an object. This is used to skipped the test if the
	 * assumption fail.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeThat(&quot;msg&quot;, myObject).is(myOtherObject);
	 * </pre>
	 * 
	 * This will check that <code>myObject</code> is <code>myOtherObject</code>
	 * (using the <code>equalTo</code> Hamcrest matcher).
	 * 
	 * @param <T>
	 *            the object type.
	 * @param msg
	 *            a message
	 * @param obj
	 *            the object
	 * @return {@link AssertThatObject the assert DSL on this object}
	 */
	default <T> AssertThatObject<T> assumeThat(String msg, T obj) {
		return new AssertThatObjectImpl<T>(false, msg, () -> obj);
	}

	/**
	 * Assume the value of a String.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeThat(myString).is(&quot;&quot;);
	 * </pre>
	 * 
	 * This will check that <code>myString</code> is <code>""</code> (using the
	 * <code>equalTo</code> Hamcrest matcher).
	 * 
	 * @param obj
	 *            the String
	 * @return {@link AssertThatString the assert DSL on this object}
	 */
	default AssertThatString assumeThat(String obj) {
		return assumeThat(null, obj);
	}

	/**
	 * Assume the value of a String.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeThat(&quot;msg&quot;, myString).is(&quot;&quot;);
	 * </pre>
	 * 
	 * This will check that <code>myString</code> is <code>""</code> (using the
	 * <code>equalTo</code> Hamcrest matcher).
	 * 
	 * @param msg
	 *            a message
	 * @param obj
	 *            the String
	 * @return {@link AssertThatString the assert DSL on this object}
	 */
	default AssertThatString assumeThat(String msg, String obj) {
		return new AssertThatStringImpl(true, msg, () -> obj);
	}

	/**
	 * Assume on an iterable object. This is used to skipped the test if the
	 * assumption fail.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeThatIterable(myIterable).hasSize(0);
	 * </pre>
	 * 
	 * This will check that <code>myIterable</code> has a size of 0.
	 * 
	 * @param <T>
	 *            the element type.
	 * @param obj
	 *            the object (Iterable)
	 * @return {@link AssertThatIterable the assert DSL on this iterable}
	 */
	default <T> AssertThatIterable<T> assumeThatIterable(Iterable<T> obj) {
		return assumeThatIterable(null, obj);
	}

	/**
	 * Assume on an iterable object. This is used to skipped the test if the
	 * assumption fail.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeThatIterable(&quot;msg&quot;, myIterable).hasSize(0);
	 * </pre>
	 * 
	 * This will check that <code>myIterable</code> has a size of 0.
	 * 
	 * @param <T>
	 *            the element type.
	 * @param msg
	 *            a message
	 * @param obj
	 *            the object (Iterable)
	 * @return {@link AssertThatIterable the assert DSL on this iterable}
	 */
	default <T> AssertThatIterable<T> assumeThatIterable(String msg,
			Iterable<T> obj) {
		return new AssertThatIterableImpl<T>(false, msg, () -> obj);
	}

	/**
	 * Assume on a function. This is used to skipped the test if the assumption
	 * fail.
	 * <p>
	 * The purpose of this variant of <i>assumeThat</i> provides a way to apply
	 * a function on some input and to check the result.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeThatFunction((a) -&gt; a + &quot;x&quot;, &quot;b&quot;).is(&quot;bx&quot;)
	 * </pre>
	 * 
	 * This will pass the <code>b</code> string to the passed function (which
	 * add a <code>x</code> add the end of the string and then it will check
	 * that this string is <code>bx</code> (which is the case).
	 * 
	 * @param <T>
	 *            the object type of the input of the function
	 * @param <R>
	 *            the object type of the result
	 * @param function
	 *            the function
	 * @param input
	 *            the input to the function
	 * @return {@link AssertThatObject then assert DSL on the result of the
	 *         function}
	 */
	default <T, R> AssertThatObject<R> assumeThatFunction(
			Function<T, R> function, T input) {
		return new AssertThatObjectImpl<R>(false, null,
				() -> function.apply(input));
	}

	/**
	 * Assume on a function. This is used to skipped the test if the assumption
	 * fail.
	 * <p>
	 * The purpose of this variant of <i>assertThat</i> provides a way to apply
	 * a function on some input and to check the result.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeThatFunction(&quot;msg&quot;, (a) -&gt; a + &quot;x&quot;, &quot;b&quot;).is(&quot;bx&quot;)
	 * </pre>
	 * 
	 * This will pass the <code>b</code> string to the passed function (which
	 * add a <code>x</code> add the end of the string and then it will check
	 * that this string is <code>bx</code> (which is the case).
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
	 * @return {@link AssertThatObject then assert DSL on the result of the
	 *         function}
	 */
	default <T, R> AssertThatObject<R> assumeThatFunction(String msg,
			Function<T, R> function, T input) {
		return new AssertThatObjectImpl<R>(false, msg,
				() -> function.apply(input));
	}

	/**
	 * Assume on a bifunction.
	 * <p>
	 * The purpose of this variant of <i>assumeThat</i> provides a way to apply
	 * a bifunction on some input and to check the result.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeThatBiFunction((a, b) -&gt; a + b, &quot;a&quot;, &quot;b&quot;).is(&quot;ab&quot;)
	 * </pre>
	 * 
	 * This will pass the <code>b</code> string to the passed function (which
	 * add a <code>x</code> add the end of the string and then it will check
	 * that this string is <code>bx</code> (which is the case).
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
	 * @return {@link AssertThatObject then assert DSL on the result of the
	 *         bifunction}
	 */
	default <T, U, R> AssertThatObject<R> assumeThatBiFunction(
			BiFunction<T, U, R> function, T input1, U input2) {
		return new AssertThatObjectImpl<R>(false, null, () -> function.apply(
				input1, input2));
	}

	/**
	 * Assume on a bifunction.
	 * <p>
	 * The purpose of this variant of <i>assumeThat</i> provides a way to apply
	 * a bifunction on some input and to check the result.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeThatBiFunction((a, b) -&gt; a + b, &quot;a&quot;, &quot;b&quot;).is(&quot;ab&quot;)
	 * </pre>
	 * 
	 * This will pass the <code>b</code> string to the passed function (which
	 * add a <code>x</code> add the end of the string and then it will check
	 * that this string is <code>bx</code> (which is the case).
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
	 */
	default <T, U, R> AssertThatObject<R> assumeThatBiFunction(String msg,
			BiFunction<T, U, R> function, T input1, U input2) {
		return new AssertThatObjectImpl<R>(false, msg, () -> function.apply(
				input1, input2));
	}

	/**
	 * Assume that a statement (a piece of code) throw an exception. This is
	 * used to skipped the test if the assumption fail.
	 * <p>
	 * The goal of <code>assumeWhen</code> is to provide a way to validate that
	 * an exception is thrown.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeWhen((p) -&gt; {
	 * 	throw new Throwable(&quot;test&quot;);
	 * }).throwException(exceptionMessage(&quot;test&quot;));
	 * </pre>
	 * 
	 * Will run a piece of code that always thrown an exception and then
	 * validate that the message of the exception is <code>test</code>.
	 * 
	 * @param underTest
	 *            the {@link Statement} <code>(p)-&gt;{}</code>
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @param <T>
	 *            the exception type
	 */
	default <T extends Throwable> AssertThatException<T> assumeWhen(
			Statement<?, T> underTest) {
		return assumeWhen(null, underTest, null);
	}

	/**
	 * Assume that a statement (a piece of code) throw an exception. This is
	 * used to skipped the test if the assumption fail.
	 * <p>
	 * The goal of <code>assumeWhen</code> is to provide a way to validate that
	 * an exception is thrown.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeWhen((p) -&gt; {
	 * 	throw new Throwable(&quot;test&quot;);
	 * }, null).throwException(exceptionMessage(&quot;test&quot;));
	 * </pre>
	 * 
	 * Will run a piece of code, passing null as parameter, that always thrown
	 * an exception and then validate that the message of the exception is
	 * <code>test</code>.
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
	 * 
	 */
	default <P, T extends Throwable> AssertThatException<T> assumeWhen(
			Statement<P, T> underTest, P param) {
		return assumeWhen(null, underTest, param);
	}

	/**
	 * Assume that a statement (a piece of code) throw an exception. This is
	 * used to skipped the test if the assumption fail.
	 * <p>
	 * The goal of <code>assumeWhen</code> is to provide a way to validate that
	 * an exception is thrown.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeWhen(&quot;msg&quot;, (p) -&gt; {
	 * 	throw new Throwable(&quot;test&quot;);
	 * }).throwException(exceptionMessage(&quot;test&quot;));
	 * </pre>
	 * 
	 * Will run a piece of code that always thrown an exception and then
	 * validate that the message of the exception is <code>test</code>.
	 * 
	 * @param msg
	 *            a message
	 * @param underTest
	 *            the statement <code>(p)-&gt;{}</code>
	 * @return {@link AssertThatException the assert DSL on the exception}
	 * @param <T>
	 *            the exception type
	 */
	default <T extends Throwable> AssertThatException<T> assumeWhen(String msg,
			Statement<?, T> underTest) {
		return assumeWhen(msg, underTest, null);
	}

	/**
	 * Assume that a statement (a piece of code) throw an exception. This is
	 * used to skipped the test if the assumption fail.
	 * <p>
	 * The goal of <code>assumeWhen</code> is to provide a way to validate that
	 * an exception is thrown.
	 * <p>
	 * For instance
	 * 
	 * <pre>
	 * assumeWhen(&quot;msg&quot;, (p) -&gt; {
	 * 	throw new Throwable(&quot;test&quot;);
	 * }, null).throwException(exceptionMessage(&quot;test&quot;));
	 * </pre>
	 * 
	 * Will run a piece of code, passing null as parameter, that always thrown
	 * an exception and then validate that the message of the exception is
	 * <code>test</code>.
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
	 * 
	 * 
	 */
	default <P, T extends Throwable> AssertThatException<T> assumeWhen(
			String msg, Statement<P, T> underTest, P param) {
		return new AssertThatExceptionImpl<P, T>(false, underTest, param, msg);
	}

	/**
	 * Always produce a skip.
	 * <p>
	 * For instance :
	 * 
	 * <pre>
	 * skip();
	 * </pre>
	 * 
	 * will immediately skip the current test.
	 */
	default void skip() {
		skip("Manual skip");
	}

	/**
	 * Always produce a skip.
	 * <p>
	 * For instance :
	 * 
	 * <pre>
	 * skip(&quot;my message&quot;);
	 * </pre>
	 * 
	 * will immediately skip the current test.
	 * 
	 * @param msg
	 *            a message
	 */
	default void skip(String msg) {
		throw new AssumptionError(msg);
	}

}
