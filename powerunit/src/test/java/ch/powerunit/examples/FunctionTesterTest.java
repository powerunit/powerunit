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
package ch.powerunit.examples;

import ch.powerunit.Categories;
import ch.powerunit.TestDelegate;
import ch.powerunit.TestSuite;
import ch.powerunit.function.FunctionTester;

/**
 * @author borettim
 *
 */
@Categories({ "example" })
public class FunctionTesterTest implements TestSuite {

	private Integer functionToBeTested(Short x) {
		return x + 1;
	}

	@TestDelegate
	public final FunctionTester<Short, Integer> tester1 = FunctionTester
			.of(this::functionToBeTested).passingAsParameter((short) 1)
			.thenExpectingResult(2).testNamed("tested")
			.passingAsParameter((short) 2).thenExpectingResult(3).build();
}
