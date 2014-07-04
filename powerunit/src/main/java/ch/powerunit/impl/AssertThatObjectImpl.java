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

import ch.powerunit.AssertThatObject;
import ch.powerunit.exception.AssumptionError;

public class AssertThatObjectImpl<T> implements AssertThatObject<T> {

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
    public void is(Matcher<? super T> matching) {
        Description message = new StringDescription();
        message.appendText("expecting ");
        matching.describeTo(message);
        message.appendText(" but ");
        T obj = provider.get();
        if (!matching.matches(obj)) {
            matching.describeMismatch(obj, message);
            if (assertion) {
                throw new AssertionError((msg == null ? "" : msg + "\n")
                        + message.toString());
            } else {
                throw new AssumptionError((msg == null ? "" : msg + "\n")
                        + message.toString());
            }
        }
    }

}
