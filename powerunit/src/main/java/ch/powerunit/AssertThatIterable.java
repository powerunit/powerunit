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
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	default void hasSize(int size) {
		is((Matcher) Matchers.hasSize(size));
	}

	/**
	 * Check the size is not the one passed.
	 * 
	 * @param size
	 *            the not expected size.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	default void hasNotSize(int size) {
		isNot((Matcher) Matchers.hasSize(size));
	}

	/**
	 * Check that the iterable contains the items.
	 * 
	 * @param items
	 *            the expected items.
	 */
	default void contains(@SuppressWarnings("unchecked") T... items) {
		is(Matchers.contains(items));
	}

	/**
	 * Check that the iterable contains the items.
	 * 
	 * @param items
	 *            the matcher for each item.
	 */
	default void containsMatching(
			@SuppressWarnings("unchecked") Matcher<? super T>... items) {
		is(Matchers.contains(items));
	}

	/**
	 * Check that the iterable contains the items in any order.
	 * 
	 * @param items
	 *            the expected items.
	 */
	default void containsInAnyOrder(@SuppressWarnings("unchecked") T... items) {
		is(Matchers.containsInAnyOrder(items));
	}

	/**
	 * Check that the iterable contains the items in any order.
	 * 
	 * @param items
	 *            the matcher for each item.
	 */
	default void containsInAnyOrderMatching(
			@SuppressWarnings("unchecked") Matcher<? super T>... items) {
		is(Matchers.containsInAnyOrder(items));
	}
}
