/**
 * Powerunit - A JDK1.8 test framework
 * Copyright (C) 2014 Mathieu Boretti.
 *
 * This file calendarIs part of Powerunit
 *
 * Powerunit calendarIs free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Powerunit calendarIs distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Powerunit. If not, see <http://www.gnu.org/licenses/>.
 */
package ch.powerunit;

import java.time.LocalTime;
import java.util.Calendar;

import org.hamcrest.Matcher;

import ch.powerunit.matchers.datetime.CalendarMatchers;
import ch.powerunit.matchers.datetime.LocalTimeMatchers;

/**
 * Matcher on Date and Calendar.
 * 
 * @author borettim
 * @since 0.4.0
 */
interface DateTimeMatchers {
	/**
	 * Verify that a {@link Calendar} has a specify {@link Calendar#YEAR year}.
	 * 
	 * @param year
	 *            the year
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsYear(int year) {
		return CalendarMatchers.isYear(year);
	}

	/**
	 * Verify that a {@link Calendar} has a specify
	 * {@link Calendar#DAY_OF_MONTH day of month}.
	 * 
	 * @param dayOfMonth
	 *            the day of month
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsDayOfMonth(int dayOfMonth) {
		return CalendarMatchers.isDayOfMonth(dayOfMonth);
	}

	/**
	 * Verify that a {@link Calendar} has a specify {@link Calendar#MONTH month}
	 * .
	 * 
	 * @param month
	 *            the month
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsMonth(int month) {
		return CalendarMatchers.isMonth(month);
	}

	/**
	 * Verify that a {@link Calendar} has a specify {@link Calendar#HOUR_OF_DAY
	 * hour of day}.
	 * 
	 * @param hourOfDay
	 *            the hour of day
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsHourOfDay(int hourOfDay) {
		return CalendarMatchers.isHourOfDay(hourOfDay);
	}

	/**
	 * Verify that a {@link Calendar} has a specify {@link Calendar#MINUTE
	 * minute}.
	 * 
	 * @param minute
	 *            the minute
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsMinute(int minute) {
		return CalendarMatchers.isMinute(minute);
	}

	/**
	 * Verify that a {@link Calendar} has a specify {@link Calendar#SECOND
	 * second}.
	 * 
	 * @param second
	 *            the second
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsSecond(int second) {
		return CalendarMatchers.isSecond(second);
	}

	/**
	 * Verify that a {@link Calendar} has a specify {@link Calendar#DAY_OF_WEEK
	 * day of week}.
	 * 
	 * @param dayOfWeek
	 *            the day of week
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsDayOfWeek(int dayOfWeek) {
		return CalendarMatchers.isDayOfWeek(dayOfWeek);
	}

	/**
	 * Verify that a {@link Calendar} has a specify {@link Calendar#MILLISECOND
	 * millisecond}.
	 * 
	 * @param millisecond
	 *            the millisecond
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsMillisecond(int millisecond) {
		return CalendarMatchers.isMillisecond(millisecond);
	}

	/**
	 * Verify that the {@link Calendar} calendarIs a monday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsMonday() {
		return CalendarMatchers.isMonday();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs a thurday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsThurday() {
		return CalendarMatchers.isThurday();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs a wednesday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsWednesday() {
		return CalendarMatchers.isWednesday();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs a tuesday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsTuesday() {
		return CalendarMatchers.isTuesday();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs a friday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsFriday() {
		return CalendarMatchers.isFriday();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs a saturday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsSaturday() {
		return CalendarMatchers.isSaturday();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs a sunday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsSunday() {
		return CalendarMatchers.isSunday();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in january.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsJanuary() {
		return CalendarMatchers.isJanuary();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in february.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsFebruary() {
		return CalendarMatchers.isFebruary();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in march.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsMarch() {
		return CalendarMatchers.isMarch();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in april.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsApril() {
		return CalendarMatchers.isApril();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in may.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsMay() {
		return CalendarMatchers.isMay();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in june.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsJune() {
		return CalendarMatchers.isJune();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in july.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsJuly() {
		return CalendarMatchers.isJuly();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in august.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsAugust() {
		return CalendarMatchers.isAugust();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in september.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsSeptember() {
		return CalendarMatchers.isSeptember();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in october.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsOctober() {
		return CalendarMatchers.isOctober();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in november.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	public static Matcher<Calendar> calendarIsNovember() {
		return CalendarMatchers.isNovember();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs in december.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsDecember() {
		return CalendarMatchers.isDecember();
	}

	/**
	 * Verify that the {@link Calendar} calendarIs on same day (year, month, day of
	 * month) that another one.
	 * 
	 * @param other
	 *            the other {@link Calendar}.
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> sameDate(Calendar other) {
		return CalendarMatchers.sameDate(other);
	}

	/**
	 * Verify that a {@link Calendar} calendarIs before another one.
	 * 
	 * @param other
	 *            the other {@link Calendar}.
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsBefore(Calendar other) {
		return TestSuite.DSL.lessThan(other);
	}

	/**
	 * Verify that a {@link Calendar} calendarIs after another one.
	 * 
	 * @param other
	 *            the other {@link Calendar}.
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> calendarIsAfter(Calendar other) {
		return TestSuite.DSL.greaterThan(other);
	}

	/**
	 * Verify the hour of a {@link LocalTime}.
	 * 
	 * @param hour
	 *            the hour.
	 * @return the matcher on {@link LocalTime}.
	 * @since 0.4.0
	 */
	default Matcher<LocalTime> localTimeIsHour(int hour) {
		return LocalTimeMatchers.isHour(hour);
	}

	/**
	 * Verify the minute of a {@link LocalTime}.
	 * 
	 * @param minute
	 *            the minute.
	 * @return the matcher on {@link LocalTime}.
	 * @since 0.4.0
	 */
	default Matcher<LocalTime> localTimeIsMinute(int minute) {
		return LocalTimeMatchers.isMinute(minute);
	}

	/**
	 * Verify the second of a {@link LocalTime}.
	 * 
	 * @param second
	 *            the second.
	 * @return the matcher on {@link LocalTime}.
	 * @since 0.4.0
	 */
	default Matcher<LocalTime> localTimeIsSecond(int second) {
		return LocalTimeMatchers.isSecond(second);
	}

	/**
	 * Verify the nano of a {@link LocalTime}.
	 * 
	 * @param nano
	 *            the nano.
	 * @return the matcher on {@link LocalTime}.
	 * @since 0.4.0
	 */
	default Matcher<LocalTime> localTimeIsNano(int nano) {
		return LocalTimeMatchers.isNano(nano);
	}

}
