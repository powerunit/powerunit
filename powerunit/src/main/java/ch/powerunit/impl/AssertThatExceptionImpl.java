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
package ch.powerunit.impl;

import org.hamcrest.Matcher;

import ch.powerunit.AssertThatException;
import ch.powerunit.Statement;
import ch.powerunit.TestSuite;
import ch.powerunit.exception.AssumptionError;

public class AssertThatExceptionImpl<P, T extends Throwable> implements
		AssertThatException<T>, TestSuite {

	private final boolean assertion;

	private final Object underTest;

	public AssertThatExceptionImpl(Object underTest, boolean assertion,
			Statement<P, T> runnable, P param, String msg) {
		this.runnable = runnable;
		this.msg = msg;
		this.param = param;
		this.assertion = assertion;
		this.underTest = underTest;
	}

	private final Statement<P, T> runnable;

	private final String msg;

	private final P param;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public boolean throwException(Matcher<? super T> matching) {
		try {
			runnable.run(param);
		} catch (Throwable t) {// NOSONAR
			// As we really want all error
			if (assertion) {
				return assertThat(msg, t).is((Matcher) matching);
			} else {
				return assumeThat(msg, t).is((Matcher) matching);
			}
		}
		if (assertion) {
			TestContextImpl<Object> ctx = DefaultPowerUnitRunnerImpl
					.getCurrentContext(underTest);
			AssertionError e = new AssertionError((msg == null ? "" : msg
					+ "\n")
					+ "An exception was expected, but none was thrown");
			if (ctx == null || ctx.isFastFail()) {
				throw e;
			} else {
				ctx.addAssertionError(e);
				return false;
			}

		} else {
			throw new AssumptionError((msg == null ? "" : msg + "\n")
					+ "An exception was expected, but none was thrown");
		}
	}
}
