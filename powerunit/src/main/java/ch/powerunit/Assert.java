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

import ch.powerunit.impl.AssertThatExceptionImpl;
import ch.powerunit.impl.AssertThatIterableImpl;
import ch.powerunit.impl.AssertThatObjectImpl;
import ch.powerunit.impl.AssertThatStringImpl;

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
     * (using the <code>equalTo</code> Hamcrest matcher).
     * 
     * @param <T>
     *            the object type.
     * @param obj
     *            the object
     * @return {@link AssertThatObject the assert DSL on this object}
     */
    default <T> AssertThatObject<T> assertThat(T obj) {
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
    default <T> AssertThatObject<T> assertThat(String msg, T obj) {
        return new AssertThatObjectImpl<T>(true, msg, () -> obj);
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
     * <code>equalTo</code> Hamcrest matcher).
     * 
     * @param obj
     *            the String
     * @return {@link AssertThatString the assert DSL on this object}
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
     * <code>equalTo</code> Hamcrest matcher).
     * 
     * @param msg
     *            a message
     * @param obj
     *            the String
     * @return {@link AssertThatString the assert DSL on this object}
     */
    default AssertThatString assertThat(String msg, String obj) {
        return new AssertThatStringImpl(true, msg, () -> obj);
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
     * This will check that <code>myIterable</code> has a size of 0.
     * 
     * @param <T>
     *            the element type.
     * @param obj
     *            the object (Iterable)
     * @return {@link AssertThatIterable the assert DSL on this iterable}
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
    default <T> AssertThatIterable<T> assertThatIterable(String msg,
            Iterable<T> obj) {
        return new AssertThatIterableImpl<T>(true, msg, () -> obj);
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
    default <T, R> AssertThatObject<R> assertThatFunction(
            Function<T, R> function, T input) {
        return new AssertThatObjectImpl<R>(true, null,
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
    default <T, R> AssertThatObject<R> assertThatFunction(String msg,
            Function<T, R> function, T input) {
        return new AssertThatObjectImpl<R>(true, msg,
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
    default <T, U, R> AssertThatObject<R> assertThatBiFunction(
            BiFunction<T, U, R> function, T input1, U input2) {
        return new AssertThatObjectImpl<R>(true, null, () -> function.apply(
                input1, input2));
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
    default <T, U, R> AssertThatObject<R> assertThatBiFunction(String msg,
            BiFunction<T, U, R> function, T input1, U input2) {
        return new AssertThatObjectImpl<R>(true, msg, () -> function.apply(
                input1, input2));
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
     *     throw new Throwable(&quot;test&quot;);
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
     * For instance
     * 
     * <pre>
     * assertWhen((p) -&gt; {
     *     throw new Throwable(&quot;test&quot;);
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
     *     throw new Throwable(&quot;test&quot;);
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
     * For instance
     * 
     * <pre>
     * assertWhen(&quot;msg&quot;, (p) -&gt; {
     *     throw new Throwable(&quot;test&quot;);
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
    default <P, T extends Throwable> AssertThatException<T> assertWhen(
            String msg, Statement<P, T> underTest, P param) {
        return new AssertThatExceptionImpl<P, T>(true, underTest, param, msg);
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
     * will immediately fail the current test.
     */
    default void fail() {
        fail("Manual failure");
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
     * will immediately fail the current test.
     * 
     * @param msg
     *            a message
     */
    default void fail(String msg) {
        throw new AssertionError(msg);
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
     * will immediately fail the current test.
     * 
     * @param msg
     *            a message
     * @param innerError
     *            the error cause
     */
    default void fail(String msg, Throwable innerError) {
        throw new AssertionError(msg, innerError);
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
     * will immediately fail the current test.
     * 
     * @param innerError
     *            the error cause
     */
    default void fail(Throwable innerError) {
        throw new AssertionError(innerError.getMessage(), innerError);
    }

}
