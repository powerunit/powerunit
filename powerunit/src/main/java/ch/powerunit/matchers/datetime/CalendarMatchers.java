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

import java.util.Calendar;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import ch.powerunit.TestSuite;

/**
 * @author borettim
 *
 */
public final class CalendarMatchers {

	private CalendarMatchers() {
	}

	private static final class CalendarFieldMatcher extends
			FeatureMatcher<Calendar, Integer> {

		private final int field;

		public CalendarFieldMatcher(Matcher<? super Integer> subMatcher,
				String featureName, int field) {
			super(subMatcher, featureName, featureName);
			this.field = field;
		}

		@Override
		protected Integer featureValueOf(Calendar actual) {
			return actual.get(field);
		}

	}

	public static Matcher<Calendar> isYear(int year) {
		return new CalendarFieldMatcher(TestSuite.DSL.equalTo(year), "year",
				Calendar.YEAR);
	}

	public static Matcher<Calendar> isDayOfMonth(int dayOfMonth) {
		return new CalendarFieldMatcher(TestSuite.DSL.equalTo(dayOfMonth),
				"day of month", Calendar.DAY_OF_MONTH);
	}

	public static Matcher<Calendar> isMonth(int month) {
		return new CalendarFieldMatcher(TestSuite.DSL.equalTo(month), "month",
				Calendar.MONTH);
	}

	public static Matcher<Calendar> isHourOfDay(int hourOfDay) {
		return new CalendarFieldMatcher(TestSuite.DSL.equalTo(hourOfDay),
				"hour of day", Calendar.HOUR_OF_DAY);
	}

	public static Matcher<Calendar> isMinute(int minute) {
		return new CalendarFieldMatcher(TestSuite.DSL.equalTo(minute),
				"minute", Calendar.MINUTE);
	}

	public static Matcher<Calendar> isSecond(int second) {
		return new CalendarFieldMatcher(TestSuite.DSL.equalTo(second),
				"second", Calendar.SECOND);
	}

	public static Matcher<Calendar> isMillisecond(int millisecond) {
		return new CalendarFieldMatcher(TestSuite.DSL.equalTo(millisecond),
				"millisecond", Calendar.MILLISECOND);
	}

	public static Matcher<Calendar> isDayOfWeek(int dayOfWeek) {
		return new CalendarFieldMatcher(TestSuite.DSL.equalTo(dayOfWeek),
				"day of week", Calendar.DAY_OF_WEEK);
	}

	public static Matcher<Calendar> isMonday() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("Monday",
				TestSuite.DSL.equalTo(Calendar.MONDAY)), "day of week",
				Calendar.DAY_OF_WEEK);
	}

	public static Matcher<Calendar> isThurday() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("Thursday",
				TestSuite.DSL.equalTo(Calendar.THURSDAY)), "day of week",
				Calendar.DAY_OF_WEEK);
	}

	public static Matcher<Calendar> isWednesday() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("Wednesday",
				TestSuite.DSL.equalTo(Calendar.WEDNESDAY)), "day of week",
				Calendar.DAY_OF_WEEK);
	}

	public static Matcher<Calendar> isTuesday() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("Tuesday",
				TestSuite.DSL.equalTo(Calendar.TUESDAY)), "day of week",
				Calendar.DAY_OF_WEEK);
	}

	public static Matcher<Calendar> isFriday() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("Friday",
				TestSuite.DSL.equalTo(Calendar.FRIDAY)), "day of week",
				Calendar.DAY_OF_WEEK);
	}

	public static Matcher<Calendar> isSaturday() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("Saturday",
				TestSuite.DSL.equalTo(Calendar.SATURDAY)), "day of week",
				Calendar.DAY_OF_WEEK);
	}

	public static Matcher<Calendar> isSunday() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("Sunday",
				TestSuite.DSL.equalTo(Calendar.SUNDAY)), "day of week",
				Calendar.DAY_OF_WEEK);
	}

	public static Matcher<Calendar> isJanuary() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("January",
				TestSuite.DSL.equalTo(Calendar.JANUARY)), "month",
				Calendar.MONTH);
	}

	public static Matcher<Calendar> isFebruary() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("February",
				TestSuite.DSL.equalTo(Calendar.FEBRUARY)), "month",
				Calendar.MONTH);
	}

	public static Matcher<Calendar> isMarch() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("March",
				TestSuite.DSL.equalTo(Calendar.MARCH)), "month", Calendar.MONTH);
	}

	public static Matcher<Calendar> isApril() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("April",
				TestSuite.DSL.equalTo(Calendar.APRIL)), "month", Calendar.MONTH);
	}

	public static Matcher<Calendar> isMay() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("May",
				TestSuite.DSL.equalTo(Calendar.MAY)), "month", Calendar.MONTH);
	}

	public static Matcher<Calendar> isJune() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("June",
				TestSuite.DSL.equalTo(Calendar.JUNE)), "month", Calendar.MONTH);
	}

	public static Matcher<Calendar> isJuly() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("July",
				TestSuite.DSL.equalTo(Calendar.JULY)), "month", Calendar.MONTH);
	}

	public static Matcher<Calendar> isAugust() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("August",
				TestSuite.DSL.equalTo(Calendar.AUGUST)), "month",
				Calendar.MONTH);
	}

	public static Matcher<Calendar> isSeptember() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("September",
				TestSuite.DSL.equalTo(Calendar.SEPTEMBER)), "month",
				Calendar.MONTH);
	}

	public static Matcher<Calendar> isOctober() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("October",
				TestSuite.DSL.equalTo(Calendar.OCTOBER)), "month",
				Calendar.MONTH);
	}

	public static Matcher<Calendar> isNovember() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("November",
				TestSuite.DSL.equalTo(Calendar.NOVEMBER)), "month",
				Calendar.MONTH);
	}

	public static Matcher<Calendar> isDecember() {
		return new CalendarFieldMatcher(TestSuite.DSL.describedAs("December",
				TestSuite.DSL.equalTo(Calendar.DECEMBER)), "month",
				Calendar.MONTH);
	}

	public static Matcher<Calendar> sameDate(Calendar other) {
		return other == null ? TestSuite.DSL.nullValue(Calendar.class)
				: TestSuite.DSL.both(isYear(other.get(Calendar.YEAR)))
						.and(isDayOfMonth(other.get(Calendar.DAY_OF_MONTH)))
						.and(isMonth(other.get(Calendar.DAY_OF_MONTH)));
	}
}
