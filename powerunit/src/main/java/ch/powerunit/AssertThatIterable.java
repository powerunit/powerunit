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
 * DSL on assertion on iterable.
 * <p>
 * This interface is returned by the various methods
 * {@link TestSuite#assertThatIterable(Iterable) assertThatIterable} exposed by
 * {@link TestSuite}.
 *
 * @author borettim
 * @param <T>
 *            the Element type of the Iterator
 * @see AssertThatObject assertion on object are also available on iterable.
 */
public interface AssertThatIterable<T> extends AssertThatObject<Iterable<T>> {
	/**
	 * Check the size of the iterable.
	 * 
	 * @param size
	 *            the expected size.
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	default boolean hasSize(int size) {
		return is((Matcher) Matchers.hasSize(size));
	}

	/**
	 * Check the size is not the one passed.
	 * 
	 * @param size
	 *            the not expected size.
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	default boolean hasNotSize(int size) {
		return isNot((Matcher) Matchers.hasSize(size));
	}

	/**
	 * Check that the iterable contains the items.
	 * 
	 * @param items
	 *            the expected items.
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	default boolean contains(@SuppressWarnings("unchecked") T... items) {
		return is(Matchers.contains(items));
	}

	/**
	 * Check that the iterable contains the items.
	 * 
	 * @param items
	 *            the matcher for each item.
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	default boolean containsMatching(
			@SuppressWarnings("unchecked") Matcher<? super T>... items) {
		return is(Matchers.contains(items));
	}

	/**
	 * Check that the iterable contains the items in any order.
	 * 
	 * @param items
	 *            the expected items.
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	default boolean containsInAnyOrder(
			@SuppressWarnings("unchecked") T... items) {
		return is(Matchers.containsInAnyOrder(items));
	}

	/**
	 * Check that the iterable contains the items in any order.
	 * 
	 * @param items
	 *            the matcher for each item.
	 * @return true if the assertion is valid ; If the assertion is false,
	 *         depending on {@link Test#fastFail()} : If <code>true</code>, then
	 *         fail the test, else, return false and the test will be failed
	 *         later.
	 */
	default boolean containsInAnyOrderMatching(
			@SuppressWarnings("unchecked") Matcher<? super T>... items) {
		return is(Matchers.containsInAnyOrder(items));
	}
}
