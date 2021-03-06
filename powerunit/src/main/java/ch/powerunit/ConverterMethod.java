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

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * This is the converter support.
 * 
 * @author borettim
 * @since 0.4.0
 */
interface ConverterMethod {

	/**
	 * Return a converter that return null if the receiver is null or else
	 * return the result of the received converter.
	 * 
	 * @param converter
	 *            the converter to be used if the received is not null.
	 * @return the converter that support the null value.
	 * @param <T>
	 *            input type of the converter.
	 * @param <R>
	 *            output type of the converter.
	 * @since 0.4.0
	 */
	default <T, R> Function<T, R> nullToNullConverter(Function<T, R> converter) {
		Objects.requireNonNull(converter, "converter can't be null");
		return (p) -> {
			if (p == null) {
				return null;
			}
			return converter.apply(p);
		};
	}

	/**
	 * Return a function that convert {@link Date} to {@link Calendar}.
	 * <p>
	 * This function will return {@code null} when the received date is
	 * {@code null}.
	 * 
	 * @return a {@link Function} to converter {@link Date} to {@link Calendar}.
	 * @since 0.4.0
	 */
	default Function<Date, Calendar> dateToCalendar() {
		return nullToNullConverter((Date p) -> {
			Calendar c = Calendar.getInstance();
			c.setTime(p);
			return c;
		});
	}

	/**
	 * Return a function that convert {@link Calendar} to {@link Date}.
	 * <p>
	 * This function will return {@code null} when the received Calendar is
	 * {@code null}.
	 * 
	 * @return a {@link Function} to converter {@link Calendar} to {@link Date}.
	 * @since 0.4.0
	 */
	default Function<Calendar, Date> calendarToDate() {
		return nullToNullConverter((p) -> p.getTime());
	}

	/**
	 * Return a function that get a value from a {@link Map}, as converter.
	 * 
	 * @param key
	 *            the key to be used to retrieve the value from the {@link Map}
	 *            ; Can't be null.
	 * @return a {@link Function} to get a value from a {@link Map}.
	 * @param <K>
	 *            the type of the Key.
	 * @param <V>
	 *            the type of the Value.
	 * @since 0.4.0
	 */
	default <K, V> Function<Map<K, V>, V> mapToValue(K key) {
		Objects.requireNonNull(key, "key can't be null");
		return nullToNullConverter((Map<K, V> p) -> p.getOrDefault(key, null));
	}

	/**
	 * Functional Interface for exception method conversion.
	 * 
	 * @author borettim
	 * @since 0.4.0
	 */
	@FunctionalInterface
	interface NoArgumentWithThrowableMethod {
		void execute() throws Throwable;
	}

	/**
	 * Converter a piece of code to statement.
	 * 
	 * @param method
	 *            the no arg no return piece of code to be tested.
	 * @return the statement
	 * @since 0.4.0
	 */
	default Statement<?, Throwable> asStatement(
			NoArgumentWithThrowableMethod method) {
		return (p) -> {
			method.execute();
		};
	}
}