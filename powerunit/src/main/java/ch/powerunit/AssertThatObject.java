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
     */
    void is(Matcher<? super T> matching);

    /**
     * Check that the object is the passed one (shortcut to Matcher.equalTo).
     * 
     * @param compareTo
     *            the value.
     */
    default void is(T compareTo) {
        is(Matchers.equalTo(compareTo));
    }

    /**
     * Check that the object is not the passed one.
     * 
     * @param compareTo
     *            the value
     */
    default void isNot(T compareTo) {
        isNot(Matchers.equalTo(compareTo));
    }

    /**
     * Check that the object is not matching the passed matcher.
     * 
     * @param matching
     *            the matcher
     */
    default void isNot(Matcher<T> matching) {
        is(Matchers.not(matching));
    }

    /**
     * Check that the object is null.
     */
    default void isNull() {
        is(Matchers.nullValue());
    }

    /**
     * Check that the object is not null.
     */
    default void isNotNull() {
        is(Matchers.notNullValue());
    }

    /**
     * Check that the object is of a specific class (or subclasses).
     * 
     * @param clazz
     *            the class
     */
    default void isA(Class<? super T> clazz) {
        is(Matchers.isA(clazz));
    }

}
