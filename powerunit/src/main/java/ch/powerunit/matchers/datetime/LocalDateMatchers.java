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
package ch.powerunit.matchers.datetime;

import java.time.LocalDate;
import java.time.Month;

import org.hamcrest.Matcher;

import ch.powerunit.TestSuite;

/**
 * @author borettim
 * @since 0.4.0
 */
public final class LocalDateMatchers {
	private LocalDateMatchers() {
	}

	public static Matcher<LocalDate> isYear(int year) {
		return TestSuite.DSL.featureMatcher(LocalDate.class, Integer.class,
				"year", (p) -> p.getYear(), TestSuite.DSL.equalTo(year));
	}

	public static Matcher<LocalDate> isMonth(Month month) {
		return TestSuite.DSL.featureMatcher(LocalDate.class, Month.class,
				"month", (p) -> p.getMonth(), TestSuite.DSL.equalTo(month));
	}

	public static Matcher<LocalDate> isDayOfMonth(int day) {
		return TestSuite.DSL.featureMatcher(LocalDate.class, Integer.class,
				"day", (p) -> p.getDayOfMonth(), TestSuite.DSL.equalTo(day));
	}

}
