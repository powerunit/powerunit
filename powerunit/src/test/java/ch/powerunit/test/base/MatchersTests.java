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
package ch.powerunit.test.base;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import ch.powerunit.Categories;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;

/**
 * @author borettim
 *
 */
public class MatchersTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<MatchersTest> runner = new DefaultPowerUnitRunnerImpl<>(
				MatchersTest.class);
		runner.addListener(new BootstrapTestListener<MatchersTest>());
		runner.run();

	}

	@Categories("base")
	public static class MatchersTest implements TestSuite {

		@Test
		public void testMatcherOptionalPresentTrueAndTrueThenTrue() {
			assertThat(optionalIsPresent().matches(Optional.of(new Object())))
					.is(true);
		}

		@Test
		public void testMatcherOptionalPresentTrueAndFalseThenFalse() {
			assertThat(
					optionalIsNotPresent().matches(Optional.of(new Object())))
					.is(false);
		}

		@Test
		public void testMatcherOptionalPresentFalseAndTrueThenFalse() {
			assertThat(optionalIsPresent().matches(Optional.empty())).is(false);
		}

		@Test
		public void testMatcherOptionalPresentfalseAndFalseThenTrue() {
			assertThat(optionalIsNotPresent().matches(Optional.empty())).is(
					true);
		}

		@Test
		public void testMatcherOptionalValueNotPresentThenFalse() {
			assertThat(optionalIs("a").matches(Optional.empty())).is(false);
		}

		@Test
		public void testMatcherOptionalValuePresentDifferentThenFalse() {
			assertThat(optionalIs("a").matches(Optional.of("b"))).is(false);
		}

		@Test
		public void testMatcherOptionalValuePresentSameThenTrue() {
			assertThat(optionalIs("a").matches(Optional.of("a"))).is(true);
		}

		@Test
		public void testMatcherOptionalDoublePresentTrueAndTrueThenTrue() {
			assertThat(optionalDoubleIsPresent().matches(OptionalDouble.of(1d)))
					.is(true);
		}

		@Test
		public void testMatcherOptionalDoublePresentTrueAndFalseThenFalse() {
			assertThat(
					optionalDoubleIsNotPresent().matches(OptionalDouble.of(1d)))
					.is(false);
		}

		@Test
		public void testMatcherOptionalDoublePresentFalseAndTrueThenFalse() {
			assertThat(
					optionalDoubleIsPresent().matches(OptionalDouble.empty()))
					.is(false);
		}

		@Test
		public void testMatcherOptionalDoublePresentfalseAndFalseThenTrue() {
			assertThat(
					optionalDoubleIsNotPresent()
							.matches(OptionalDouble.empty())).is(true);
		}

		@Test
		public void testMatcherOptionalDoubleValueNotPresentThenFalse() {
			assertThat(
					optionalDoubleIs(closeTo(1d, 0.1)).matches(
							OptionalDouble.empty())).is(false);
		}

		@Test
		public void testMatcherOptionalDoubleValuePresentDifferentThenFalse() {
			assertThat(
					optionalDoubleIs(closeTo(1d, 0.1)).matches(
							OptionalDouble.of(2d))).is(false);
		}

		@Test
		public void testMatcherOptionalDoubleValuePresentSameThenTrue() {
			assertThat(
					optionalDoubleIs(closeTo(2d, 0.1)).matches(
							OptionalDouble.of(2d))).is(true);
		}

		@Test
		public void testMatcherOptionalLongPresentTrueAndTrueThenTrue() {
			assertThat(optionalLongIsPresent().matches(OptionalLong.of(1))).is(
					true);
		}

		@Test
		public void testMatcherOptionalLongPresentTrueAndFalseThenFalse() {
			assertThat(optionalLongIsNotPresent().matches(OptionalLong.of(1)))
					.is(false);
		}

		@Test
		public void testMatcherOptionalLongPresentFalseAndTrueThenFalse() {
			assertThat(optionalLongIsPresent().matches(OptionalLong.empty()))
					.is(false);
		}

		@Test
		public void testMatcherOptionalLongPresentfalseAndFalseThenTrue() {
			assertThat(optionalLongIsNotPresent().matches(OptionalLong.empty()))
					.is(true);
		}

		@Test
		public void testMatcherOptionalLongValueNotPresentThenFalse() {
			assertThat(optionalLongIs(1l).matches(OptionalLong.empty())).is(
					false);
		}

		@Test
		public void testMatcherOptionalLongValuePresentDifferentThenFalse() {
			assertThat(optionalLongIs(1l).matches(OptionalLong.of(2)))
					.is(false);
		}

		@Test
		public void testMatcherOptionalLongValuePresentSameThenTrue() {
			assertThat(optionalLongIs(2l).matches(OptionalLong.of(2))).is(true);
		}

		//
		@Test
		public void testMatcherOptionalIntPresentTrueAndTrueThenTrue() {
			assertThat(optionalIntIsPresent().matches(OptionalInt.of(1))).is(
					true);
		}

		@Test
		public void testMatcherOptionalIntPresentTrueAndFalseThenFalse() {
			assertThat(optionalIntIsNotPresent().matches(OptionalInt.of(1)))
					.is(false);
		}

		@Test
		public void testMatcherOptionalIntPresentFalseAndTrueThenFalse() {
			assertThat(optionalIntIsPresent().matches(OptionalInt.empty())).is(
					false);
		}

		@Test
		public void testMatcherOptionalIntPresentfalseAndFalseThenTrue() {
			assertThat(optionalIntIsNotPresent().matches(OptionalInt.empty()))
					.is(true);
		}

		@Test
		public void testMatcherOptionalIntValueNotPresentThenFalse() {
			assertThat(optionalIntIs(1).matches(OptionalInt.empty())).is(false);
		}

		@Test
		public void testMatcherOptionalIntValuePresentDifferentThenFalse() {
			assertThat(optionalIntIs(1).matches(OptionalInt.of(2))).is(false);
		}

		@Test
		public void testMatcherOptionalIntValuePresentSameThenTrue() {
			assertThat(optionalIntIs(2).matches(OptionalInt.of(2))).is(true);
		}

		@Test
		public void testCalendarYear() {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.YEAR, 2012);
			assertThat(c).is(calendarIsYear(2012));
		}

		@Test
		public void testCalendarJanuary() {
			Calendar c = Calendar.getInstance();
			c.set(Calendar.MONTH, Calendar.JANUARY);
			assertThat(c).is(calendarIsJanuary());
		}

		@Test
		public void testLocalTimeHour() {
			assertThat(LocalTime.of(12, 11, 1, 15)).is(localTimeIsHour(12));
		}
	}
}
