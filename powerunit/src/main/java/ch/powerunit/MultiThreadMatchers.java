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

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matcher;

import ch.powerunit.matchers.future.FutureMatchers;

/**
 * @author borettim
 * @since 0.4.0
 */
interface MultiThreadMatchers {
	/**
	 * Verify if a {@link Future} is cancelled.
	 * 
	 * @param expected
	 *            the expected value.
	 * @return the Matcher on {@link Future}
	 * @param <T>
	 *            The type of the return value of the {@link Future}.
	 * @since 0.4.0
	 * 
	 */
	default <T> Matcher<Future<T>> futureIsCancelled(boolean expected) {
		return FutureMatchers.futureIsCancelled(expected);
	}

	/**
	 * Verify if a {@link Future} is done.
	 * 
	 * @param expected
	 *            the expected value.
	 * @return the Matcher on {@link Future}
	 * @param <T>
	 *            The type of the return value of the {@link Future}.
	 * @since 0.4.0
	 */
	default <T> Matcher<Future<T>> futureIsDone(boolean expected) {
		return FutureMatchers.futureIsDone(expected);
	}

	/**
	 * Retrieve the value of a {@link Future}, with timeout support and validate
	 * the value.
	 * <p>
	 * For instance :
	 * 
	 * <pre>
	 * assertThat(Executors.newSingleThreadExecutor().submit(DemoFutureTest::run1))
	 * 		.is(futureIsBeforeTimeout(notNullValue(), 10, TimeUnit.SECONDS));
	 * </pre>
	 * 
	 * @param matching
	 *            the matching on the value
	 * @param timeout
	 *            the timeout to get the value.
	 * @param unit
	 *            the timeunit for the value.
	 * @return the Matcher on {@link Future}
	 * @param <T>
	 *            The type of the return value of the {@link Future}.
	 * @since 0.4.0
	 */
	default <T> Matcher<Future<T>> futureIsBeforeTimeout(
			Matcher<? super T> matching, long timeout, TimeUnit unit) {
		return FutureMatchers.futureIsBeforeTimeout(matching, timeout, unit);
	}
}
