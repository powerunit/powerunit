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

/**
 * This interface define the execution test context.
 * 
 * @author borettim
 * @param <T>
 *            the test object type.
 */
public interface TestContext<T> {
	/**
	 * Return the current testName
	 * 
	 * @return the testName
	 */
	String getFullTestName();

	/**
	 * Return the current SetName
	 * 
	 * @return the setName
	 */
	String getSetName();

	/**
	 * Return the local test name
	 * 
	 * @return the localTestName
	 */
	String getLocalTestName();

	/**
	 * Return the ParameterName
	 * 
	 * @return the parameterName or null if not applicable
	 */
	String getParameterName();

	/**
	 * Return the test categories
	 * 
	 * @return the test categories
	 */
	String getTestCategories();

	/**
	 * Return the object used for the test suite.
	 * 
	 * @return the test object.
	 */
	T getTestSuiteObject();
}
