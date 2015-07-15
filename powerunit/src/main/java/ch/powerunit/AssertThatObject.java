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

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * DSL for assertion on object.
 * <p>
 * This interface is returned by the various methods
 * {@link TestSuite#assertThat(Object) assertThat} exposed by {@link TestSuite}.
 *
 * @author borettim
 * @param <T>
 *            the object type.
 */
public interface AssertThatObject<T> {

	/**
	 * Check that the object is matching the passed matcher.
	 * 
	 * @param matching
	 *            the matcher.
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	boolean is(Matcher<? super T> matching);

	/**
	 * Check that the object is the passed one (shortcut to Matcher.equalTo).
	 * 
	 * @param compareTo
	 *            the value.
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	default boolean is(T compareTo) {
		return is(Matchers.equalTo(compareTo));
	}

	/**
	 * Check that the object is not the passed one.
	 * 
	 * @param compareTo
	 *            the value
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	default boolean isNot(T compareTo) {
		return isNot(Matchers.equalTo(compareTo));
	}

	/**
	 * Check that the object is not matching the passed matcher.
	 * 
	 * @param matching
	 *            the matcher
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	default boolean isNot(Matcher<T> matching) {
		return is(Matchers.not(matching));
	}

	/**
	 * Check that the object is null.
	 * 
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	default boolean isNull() {
		return is(Matchers.nullValue());
	}

	/**
	 * Check that the object is not null.
	 * 
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	default boolean isNotNull() {
		return is(Matchers.notNullValue());
	}

	/**
	 * Check that the object is of a specific class (or subclasses).
	 * 
	 * @param clazz
	 *            the class
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	default boolean isA(Class<? super T> clazz) {
		return is(Matchers.isA(clazz));
	}

}
