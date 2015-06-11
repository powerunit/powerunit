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
package ch.powerunit.matchers.impl;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import ch.powerunit.matchers.lang.MatcherAssertion;

/**
 * @author borettim
 * @since 0.3.0
 */
public class MatcherAssertionImpl<T extends Matcher<?>> implements
		MatcherAssertion<T> {

	private final T matcher;
	private final Matcher<String> expected;
	private Boolean nullRejected; // As null is accepted
	private Matcher<String> nullRejectedMessage;
	private Object acceptedValues[];
	private Reject[] rejectedValue;

	@Override
	public MatcherAssertion<T> nullAccepted() {
		nullRejected = false;
		return this;
	}

	@Override
	public MatcherAssertion<T> nullRejected(Matcher<String> rejectedMessage) {
		nullRejected = true;
		nullRejectedMessage = rejectedMessage;
		return this;
	}

	@Override
	public MatcherAssertion<T> nullRejected(String rejectedMessage) {
		return nullRejected(Matchers.equalTo(rejectedMessage));
	}

	@Override
	public MatcherAssertion<T> accepting(Object... values) {
		this.acceptedValues = values;
		return this;
	}

	@Override
	public MatcherAssertion<T> rejecting(Reject... reject) {
		this.rejectedValue = reject;
		return this;
	}

	/**
	 * @param matcher
	 * @param expected
	 */
	public MatcherAssertionImpl(T matcher, Matcher<String> expected) {
		this.matcher = matcher;
		this.expected = expected;
	}

	/**
	 * @return the matcher
	 */
	public T getMatcher() {
		return matcher;
	}

	/**
	 * @return the expected
	 */
	public Matcher<String> getExpected() {
		return expected;
	}

	/**
	 * @return the nullRejected
	 */
	public Boolean getNullRejected() {
		return nullRejected;
	}

	/**
	 * @return the nullRejectedMessage
	 */
	public Matcher<String> getNullRejectedMessage() {
		return nullRejectedMessage;
	}

	/**
	 * @return the acceptedValues
	 */
	public Object[] getAcceptedValues() {
		return acceptedValues;
	}

	/**
	 * @return the rejectedValue
	 */
	public Reject[] getRejectedValue() {
		return rejectedValue;
	}

}
