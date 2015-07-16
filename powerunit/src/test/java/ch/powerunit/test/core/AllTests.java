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
package ch.powerunit.test.core;

import ch.powerunit.exception.AssumptionError;
import ch.powerunit.exception.InternalError;

public class AllTests {
	public static void main(String[] args) {
		AssertThatTests.main(args);
		AssertThatIterableTests.main(args);
		AssertThatFunctionTests.main(args);
		AssertThatBiFunctionTests.main(args);
		AssertWhenTests.main(args);
		TestResultListenerTests.main(args);
		DefaultPowerUnitRunnerImplRunTests.main(args);
		AssumeThatFunctionTests.main(args);
		AssumeThatBiFunctionTests.main(args);
		AssumeThatIterableTests.main(args);
		AssumeThatTests.main(args);
		AssumeWhenTests.main(args);
		ConverterTests.main(args);
	}

	static void testNoException(String name, Runnable code) {// package private
		System.out.println("Test : " + name);
		try {
			code.run();
		} catch (AssertionError af) {
			System.err.println("Exception is not expected here, but was "
					+ af.getMessage());
			af.printStackTrace();
			System.exit(-1);
		} catch (AssumptionError af) {
			System.err.println("Exception is not expected here, but was "
					+ af.getMessage());
			af.printStackTrace();
			System.exit(-1);
		}
	}

	static void testException(String name, Runnable code, String expectedMessage) {// package
																					// private
		System.out.println("Test : " + name);
		try {
			code.run();
		} catch (AssertionError af) {
			if (!expectedMessage.equals(af.getMessage())) {
				System.err
						.println("Exception is expected, but wrong message : expected "
								+ expectedMessage
								+ " but was "
								+ af.getMessage());
				af.printStackTrace();
				System.exit(-1);
			}
			System.out.println("\tException attendue : " + af.getMessage());
			return;
		} catch (AssumptionError af) {
			if (!expectedMessage.equals(af.getMessage())) {
				System.err
						.println("Exception is expected, but wrong message : expected "
								+ expectedMessage
								+ " but was "
								+ af.getMessage());
				af.printStackTrace();
				System.exit(-1);
			}
			System.out.println("\tException attendue : " + af.getMessage());
			return;
		} catch (InternalError af) {
			if (!af.getMessage().contains(expectedMessage)) {
				System.err
						.println("Exception is expected, but wrong message : expected "
								+ expectedMessage
								+ " but was "
								+ af.getMessage());
				af.printStackTrace();
				System.exit(-1);
			}
			System.out.println("\tException attendue : " + af.getMessage());
			return;
		}
		System.err.println("Exception is expected, but not received");
		System.exit(-1);
	}
}
