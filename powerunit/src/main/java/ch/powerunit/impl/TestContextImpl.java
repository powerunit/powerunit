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

import java.util.ArrayList;
import java.util.List;

import ch.powerunit.TestContext;

public final class TestContextImpl<T> implements TestContext<T> {

	public TestContextImpl(T testObject, String setName, String localName,
			String parameterName, String categories) {
		this.setName = setName;
		this.localName = localName;
		this.parameterName = parameterName;
		this.categories = categories;
		this.testObject = testObject;
	}

	private final String setName;

	private final String localName;

	private final String parameterName;

	private final String categories;

	private final T testObject;

	private boolean fastFail;

	private final List<AssertionError> errors = new ArrayList<>();

	@Override
	public String getFullTestName() {
		if (parameterName == null) {
			return setName + ":" + localName;
		} else {
			return setName + ":" + localName + "[" + parameterName + "]";
		}
	}

	@Override
	public String getSetName() {
		return setName;
	}

	@Override
	public String getLocalTestName() {
		return localName;
	}

	@Override
	public String getParameterName() {
		return parameterName;
	}

	@Override
	public String getTestCategories() {
		return categories;
	}

	@Override
	public T getTestSuiteObject() {
		return testObject;
	}

	boolean isFastFail() {
		return fastFail;
	}

	void setFastFail(boolean fastFail) {
		this.fastFail = fastFail;
	}

	void addAssertionError(AssertionError e) {
		errors.add(e);
	}

	boolean hasError() {
		return !errors.isEmpty();
	}

	void fail() {
		StringBuilder sb = new StringBuilder("Multiple failures : \n");
		for (AssertionError e : errors) {
			sb.append("\tError : ").append(e.getMessage()).append("\n");
		}
		sb.append("\nOriginal Stack Traces\n");
		for (AssertionError e : errors) {
			sb.append("\t").append(e.getMessage()).append("\n");
			for (StackTraceElement ste : e.getStackTrace()) {
				sb.append("\t\t").append(ste.toString()).append("\n");
			}
		}

		throw new AssertionError(sb.toString());
	}
}