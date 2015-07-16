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
}
