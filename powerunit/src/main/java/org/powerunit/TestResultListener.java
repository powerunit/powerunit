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
package org.powerunit;

/**
 * Test result listener.
 * 
 * @author borettim
 * @param <T>
 *            Test type
 */
public interface TestResultListener<T> {

	/**
	 * String used a groups parameter when the test doesn't belong to any
	 * groups.
	 */
	String ALL_GROUPS = "<none>";

	/**
	 * Notification of the start of a set of test.
	 * 
	 * @param setName
	 *            the setName
	 * @param groups
	 *            the groups {@link Categories}
	 */
	void notifySetStart(String setName, String groups);

	/**
	 * Notification of the end of a set of test.
	 * 
	 * @param setName
	 *            the setName
	 * @param groups
	 *            the groups {@link Categories}
	 */
	void notifySetEnd(String setName, String groups);

	/**
	 * Notification of the start of one single test.
	 * 
	 * @param context
	 *            the context
	 */
	void notifyStart(TestContext<T> context);

	/**
	 * Notification of the end (success) of one single test.
	 * 
	 * @param context
	 *            the context
	 */
	void notifySuccess(TestContext<T> context);

	/**
	 * Notification of the end (failure) of one single test.
	 * 
	 * @param context
	 *            the context
	 * @param cause
	 *            the cause of the failure
	 */
	void notifyFailure(TestContext<T> context, Throwable cause);

	/**
	 * Notification of the end (error) of one single test.
	 * 
	 * @param context
	 *            the context
	 * @param cause
	 *            the cause of the error
	 */
	void notifyError(TestContext<T> context, Throwable cause);

	/**
	 * Notification of the end (skipped) of one single test.
	 * 
	 * @param context
	 *            the context
	 */
	void notifySkipped(TestContext<T> context);

}
