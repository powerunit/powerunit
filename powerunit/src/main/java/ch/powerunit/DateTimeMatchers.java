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
	default Matcher<Calendar> isYear(int year) {
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
	default Matcher<Calendar> isDayOfMonth(int dayOfMonth) {
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
	default Matcher<Calendar> isMonth(int month) {
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
	default Matcher<Calendar> isHourOfDay(int hourOfDay) {
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
	default Matcher<Calendar> isMinute(int minute) {
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
	default Matcher<Calendar> isSecond(int second) {
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
	default Matcher<Calendar> isDayOfWeek(int dayOfWeek) {
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
	default Matcher<Calendar> isMillisecond(int millisecond) {
		return CalendarMatchers.isMillisecond(millisecond);
	}

	/**
	 * Verify that the {@link Calendar} is a monday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isMonday() {
		return CalendarMatchers.isMonday();
	}

	/**
	 * Verify that the {@link Calendar} is a thurday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isThurday() {
		return CalendarMatchers.isThurday();
	}

	/**
	 * Verify that the {@link Calendar} is a wednesday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isWednesday() {
		return CalendarMatchers.isWednesday();
	}

	/**
	 * Verify that the {@link Calendar} is a tuesday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isTuesday() {
		return CalendarMatchers.isTuesday();
	}

	/**
	 * Verify that the {@link Calendar} is a friday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isFriday() {
		return CalendarMatchers.isFriday();
	}

	/**
	 * Verify that the {@link Calendar} is a saturday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isSaturday() {
		return CalendarMatchers.isSaturday();
	}

	/**
	 * Verify that the {@link Calendar} is a sunday.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isSunday() {
		return CalendarMatchers.isSunday();
	}

	/**
	 * Verify that the {@link Calendar} is in january.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isJanuary() {
		return CalendarMatchers.isJanuary();
	}

	/**
	 * Verify that the {@link Calendar} is in february.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isFebruary() {
		return CalendarMatchers.isFebruary();
	}

	/**
	 * Verify that the {@link Calendar} is in march.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isMarch() {
		return CalendarMatchers.isMarch();
	}

	/**
	 * Verify that the {@link Calendar} is in april.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isApril() {
		return CalendarMatchers.isApril();
	}

	/**
	 * Verify that the {@link Calendar} is in may.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isMay() {
		return CalendarMatchers.isMay();
	}

	/**
	 * Verify that the {@link Calendar} is in june.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isJune() {
		return CalendarMatchers.isJune();
	}

	/**
	 * Verify that the {@link Calendar} is in july.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isJuly() {
		return CalendarMatchers.isJuly();
	}

	/**
	 * Verify that the {@link Calendar} is in august.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isAugust() {
		return CalendarMatchers.isAugust();
	}

	/**
	 * Verify that the {@link Calendar} is in september.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isSeptember() {
		return CalendarMatchers.isSeptember();
	}

	/**
	 * Verify that the {@link Calendar} is in october.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isOctober() {
		return CalendarMatchers.isOctober();
	}

	/**
	 * Verify that the {@link Calendar} is in november.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	public static Matcher<Calendar> isNovember() {
		return CalendarMatchers.isNovember();
	}

	/**
	 * Verify that the {@link Calendar} is in december.
	 * 
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isDecember() {
		return CalendarMatchers.isDecember();
	}

	/**
	 * Verify that the {@link Calendar} is on same day (year, month, day of
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
	 * Verify that a {@link Calendar} is before another one.
	 * 
	 * @param other
	 *            the other {@link Calendar}.
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isBefore(Calendar other) {
		return TestSuite.DSL.lessThan(other);
	}

	/**
	 * Verify that a {@link Calendar} is after another one.
	 * 
	 * @param other
	 *            the other {@link Calendar}.
	 * @return the matcher on {@link Calendar}.
	 * @since 0.4.0
	 */
	default Matcher<Calendar> isAfter(Calendar other) {
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
