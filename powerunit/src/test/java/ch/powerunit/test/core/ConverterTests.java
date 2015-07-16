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

import java.util.function.Function;

import ch.powerunit.TestSuite;

public class ConverterTests {

	public static void main(String[] args) {
		AllTests.testNoException(
				"testNullConverterNullThenNull",
				() -> {
					Function<String, String> fct = TestSuite.DSL
							.nullToNullConverter(Function.identity());
					if (fct.apply(null) != null) {
						throw new AssertionError("null should return null");
					}
				});
		AllTests.testNoException(
				"testNullConverterNotNullThenFunction",
				() -> {
					Function<String, String> fct = TestSuite.DSL
							.nullToNullConverter(Function.identity());
					if (!"x".equals(fct.apply("x"))) {
						throw new AssertionError(
								"not null should return function");
					}
				});
	}
}
