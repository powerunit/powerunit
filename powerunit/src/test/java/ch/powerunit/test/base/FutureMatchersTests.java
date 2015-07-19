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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.mockito.Mock;
import org.mockito.Mockito;

import ch.powerunit.Categories;
import ch.powerunit.Rule;
import ch.powerunit.Test;
import ch.powerunit.TestRule;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;

public class FutureMatchersTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<FutureMatchersTest> runner = new DefaultPowerUnitRunnerImpl<>(
				FutureMatchersTest.class);
		runner.addListener(new BootstrapTestListener<FutureMatchersTest>());
		runner.run();

	}

	@Categories("base")
	public static class FutureMatchersTest implements TestSuite {
		@Mock
		private Future<Object> mock;

		@Rule
		public final TestRule rule = mockitoRule();

		@Test
		public void testCancelled() {
			Mockito.when(mock.isCancelled()).thenReturn(true);
			assertThat(mock).is(futureIsCancelled(true));
		}

		@Test
		public void testDone() {
			Mockito.when(mock.isDone()).thenReturn(true);
			assertThat(mock).is(futureIsDone(true));
		}

		@Test
		public void testGetOK() throws InterruptedException,
				ExecutionException, TimeoutException {
			Mockito.when(
					mock.get(Mockito.anyLong(), Mockito.any(TimeUnit.class)))
					.thenReturn("12");
			assertThat(mock).is(
					futureIsBeforeTimeout(equalTo("12"), 1000,
							TimeUnit.MICROSECONDS));
		}

		@Test
		public void testGetKOMatcher() throws InterruptedException,
				ExecutionException, TimeoutException {
			Mockito.when(
					mock.get(Mockito.anyLong(), Mockito.any(TimeUnit.class)))
					.thenReturn("12");
			assertWhen(
					(p) -> {
						assertThat(mock).is(
								futureIsBeforeTimeout(equalTo("42"), 1000,
										TimeUnit.MICROSECONDS));
					})
					.throwException(
							exceptionMessage(containsString("must be \"42\" but was \"12\"")));
		}

		@Test
		public void testGetKOTimeout() throws InterruptedException,
				ExecutionException, TimeoutException {
			Mockito.when(
					mock.get(Mockito.anyLong(), Mockito.any(TimeUnit.class)))
					.thenThrow(new TimeoutException("xxx"));
			assertWhen(
					(p) -> {
						assertThat(mock).is(
								futureIsBeforeTimeout(equalTo("42"), 1000,
										TimeUnit.MICROSECONDS));
					})
					.throwException(
							exceptionMessage(containsString("must be \"42\" but  exception <class java.util.concurrent.TimeoutException> because of \"xxx\"")));
		}
	}
}
