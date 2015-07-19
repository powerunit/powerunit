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

import java.util.function.Function;

/**
 * This is an intermediate and optional step on assertion to cast an object into
 * a sub-object.
 * 
 * @author borettim
 * @since 0.3.0
 * @param <T>
 *            the object type.
 */
public interface AssertThatCastableObject<T> extends AssertThatObject<T> {
	/**
	 * Cast the received value into a sub-class of the receiver, before applying
	 * the matcher.
	 * <p>
	 * For example, we have <code>Object myNumber =...;</code> which is known to
	 * be an <code>Integer</code>. It is possible to convert it by using :
	 * 
	 * <pre>
	 * assertThat(myNumber).as(Integer.class).is(10);
	 * </pre>
	 * 
	 * @param clazz
	 *            the target class
	 * @return {@link AssertThatObject the assertion on the result of the
	 *         conversion}.
	 * @param <P>
	 *            The subtype for the conversion
	 */
	<P extends T> AssertThatObject<P> as(Class<P> clazz);

	/**
	 * Convert a received object into another one, based on a passed converter.
	 * <p>
	 * For example, we have <code>String myString =...;</code> which is known to
	 * be a <code>String</code> representing an <code>Integer</code>. It is
	 * possible to convert it by using:
	 * 
	 * <pre>
	 * assertThat(myString).as(Integer.class, Integer::valueOf).is(1);
	 * </pre>
	 * 
	 * @param targetClass
	 *            he target class
	 * @param converter
	 *            the converter to be used to converter the object to the target
	 *            class.
	 * @return {@link AssertThatCastableObject the assertion on the result of
	 *         the conversion}.
	 * @param <P>
	 *            the target type
	 * @since 0.4.0
	 */
	<P> AssertThatCastableObject<P> as(Class<P> targetClass,
			Function<T, P> converter);
}
