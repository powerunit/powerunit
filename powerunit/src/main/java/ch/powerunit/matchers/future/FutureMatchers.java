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
package ch.powerunit.matchers.future;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import ch.powerunit.TestSuite;

/**
 * @author borettim
 * @since 0.4.0
 */
public final class FutureMatchers {
	private FutureMatchers() {
	}

	@SuppressWarnings("unchecked")
	public static <T> Matcher<Future<T>> futureIsCancelled(boolean expected) {
		return (Matcher) TestSuite.DSL.featureMatcher(Future.class,
				Boolean.class, "is cancelled", (f) -> f.isCancelled(),
				TestSuite.DSL.equalTo(expected));
	}

	@SuppressWarnings("unchecked")
	public static <T> Matcher<Future<T>> futureIsDone(boolean expected) {
		return (Matcher) TestSuite.DSL.featureMatcher(Future.class,
				Boolean.class, "is done", (f) -> f.isDone(),
				TestSuite.DSL.equalTo(expected));
	}

	public static <T> Matcher<Future<T>> futureIsBeforeTimeout(
			Matcher<? super T> matching, long timeout, TimeUnit unit) {
		return new FutureGetMatcher<T>(matching, timeout, unit);
	}

	private static class FutureGetMatcher<T> extends TypeSafeMatcher<Future<T>> {

		private final Matcher<? super T> subMatcher;

		private final long timeout;

		private final TimeUnit unit;

		private T reply;

		private Exception exception;

		public FutureGetMatcher(Matcher<? super T> subMatcher, long timeout,
				TimeUnit unit) {
			this.subMatcher = subMatcher;
			this.timeout = timeout;
			this.unit = unit;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText("future value is get before ")
					.appendValue(timeout).appendText(" ").appendValue(unit)
					.appendText(" and must be ").appendDescriptionOf(subMatcher);
		}

		@Override
		protected void describeMismatchSafely(Future<T> item,
				Description mismatchDescription) {
			if (exception != null) {
				mismatchDescription.appendText(" exception ")
						.appendValue(exception.getClass())
						.appendText(" because of ")
						.appendValue(exception.getMessage());
			} else {
				subMatcher.describeMismatch(reply, mismatchDescription);
			}
		}

		@Override
		protected boolean matchesSafely(Future<T> item) {
			try {
				reply = item.get(timeout, unit);
				if (subMatcher.matches(reply)) {
					return true;
				}
			} catch (InterruptedException | ExecutionException
					| TimeoutException | CancellationException e) {
				exception = e;
				return false;
			}
			return false;
		}

	}
}
