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

import java.time.LocalTime;

import org.hamcrest.Matcher;

import ch.powerunit.TestSuite;

/**
 * @author borettim
 * @since 0.4.0
 */
public final class LocalTimeMatchers {
	private LocalTimeMatchers() {
	}

	public static Matcher<LocalTime> isHour(int hour) {
		return TestSuite.DSL.featureMatcher(LocalTime.class, Integer.class,
				"hour", (p) -> p.getHour(), TestSuite.DSL.equalTo(hour));
	}

	public static Matcher<LocalTime> isMinute(int minute) {
		return TestSuite.DSL.featureMatcher(LocalTime.class, Integer.class,
				"minute", (p) -> p.getMinute(), TestSuite.DSL.equalTo(minute));
	}

	public static Matcher<LocalTime> isSecond(int second) {
		return TestSuite.DSL.featureMatcher(LocalTime.class, Integer.class,
				"second", (p) -> p.getSecond(), TestSuite.DSL.equalTo(second));
	}

	public static Matcher<LocalTime> isNano(int nano) {
		return TestSuite.DSL.featureMatcher(LocalTime.class, Integer.class,
				"nano", (p) -> p.getNano(), TestSuite.DSL.equalTo(nano));
	}
}
