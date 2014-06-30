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
package ch.powerunit.test.base;

import ch.powerunit.TestContext;
import ch.powerunit.TestResultListener;
import ch.powerunit.TestSuite;

class BootstrapTestListener<T> implements TestResultListener<T>, TestSuite {// package
																			// private

	@Override
	public void notifySetStart(String setName, String parameters) {
		System.out.println("Starting " + setName + ":" + parameters);
	}

	@Override
	public void notifySetEnd(String setName, String parameters) {
		System.out.println("Ending " + setName + ":" + parameters);
	}

	@Override
	public void notifyStart(TestContext<T> context) {
		System.out.println("Starting " + context.getFullTestName() + ":"
				+ context.getTestCategories());
	}

	@Override
	public void notifySuccess(TestContext<T> context) {
		System.out.println("Successs " + context.getFullTestName() + ":"
				+ context.getTestCategories());
	}

	@Override
	public void notifyFailure(TestContext<T> context, Throwable cause) {
		System.out.println("Failed " + context.getFullTestName() + ":"
				+ context.getTestCategories());
		fail("Unexpected error " + cause.getMessage(), cause);
	}

	@Override
	public void notifySkipped(TestContext<T> context) {
		System.out.println("Skipped " + context.getFullTestName() + ":"
				+ context.getTestCategories());
	}

	@Override
	public void notifyError(TestContext<T> context, Throwable cause) {
		System.out.println("Failed " + context.getFullTestName() + ":"
				+ context.getTestCategories());
		fail("Unexpected error " + cause.getMessage(), cause);
	}

}