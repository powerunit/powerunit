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
package ch.powerunit.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ch.powerunit.Test;
import ch.powerunit.TestSuite;

public class DemoFutureTest implements TestSuite {

	private static Object run1() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}
		return new Object();
	}

	private static Object run2() {
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
		}
		return new Object();
	}

	@Test
	public void test1() {
		assertThat(
				Executors.newSingleThreadExecutor()
						.submit(DemoFutureTest::run1)).is(
				futureIsBeforeTimeout(notNullValue(), 10, TimeUnit.SECONDS));
	}

	@Test
	public void test2() {
		assertWhen(
				(p) -> {
					assertThat(
							Executors.newSingleThreadExecutor().submit(
									DemoFutureTest::run2)).is(
							futureIsBeforeTimeout(notNullValue(), 10,
									TimeUnit.MICROSECONDS));
				})
				.throwException(
						exceptionMessage(containsString("expecting future value is get before <10L> <MICROSECONDS> and must be not null but  exception <class java.util.concurrent.TimeoutException>")));
	}
}