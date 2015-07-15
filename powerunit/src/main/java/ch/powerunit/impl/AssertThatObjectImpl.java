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

import java.util.function.Supplier;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import ch.powerunit.AssertThatCastableObject;
import ch.powerunit.AssertThatObject;
import ch.powerunit.exception.AssumptionError;
import ch.powerunit.exception.InternalError;

public class AssertThatObjectImpl<T> implements AssertThatObject<T>,
		AssertThatCastableObject<T> {

	private final boolean assertion;

	public AssertThatObjectImpl(boolean assertion, String msg,
			Supplier<T> provider) {
		this.provider = provider;
		this.msg = msg;
		this.assertion = assertion;
	}

	private final Supplier<T> provider;

	private final String msg;

	@Override
	public boolean is(Matcher<? super T> matching) {
		Description message = new StringDescription();
		message.appendText("expecting ");
		matching.describeTo(message);
		message.appendText(" but ");
		T obj = provider.get();
		if (!matching.matches(obj)) {
			matching.describeMismatch(obj, message);
			if (assertion) {
				TestContextImpl<Object> ctx = DefaultPowerUnitRunnerImpl
						.getCurrentContext();
				AssertionError e = new AssertionError((msg == null ? "" : msg
						+ "\n")
						+ message.toString());
				if (ctx == null || ctx.isFastFail()) {
					throw e;
				} else {
					ctx.addAssertionError(e);
					return false;
				}
			} else {
				throw new AssumptionError((msg == null ? "" : msg + "\n")
						+ message.toString());
			}
		}
		return true;
	}

	@Override
	public <P extends T> AssertThatObject<P> as(Class<P> clazz) {
		if (clazz == null) {
			throw new InternalError("clazz argument can't be null");
		}
		return new AssertThatObjectImpl<P>(assertion, msg, () -> {
			T v = provider.get();
			if (v == null) {
				return null;
			} else if (!clazz.isAssignableFrom(v.getClass())) {
				if (assertion) {
					throw new AssertionError((msg == null ? "" : msg + "\n")
							+ "The value " + v + " can't be casted to "
							+ clazz.getName());
				} else {
					throw new AssumptionError((msg == null ? "" : msg + "\n")
							+ "The value " + v + " can't be casted to "
							+ clazz.getName());
				}
			} else {
				return (P) v;
			}
		});
	}
}
