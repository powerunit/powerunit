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
import ch.powerunit.bifunction.BiFunctionTester;

/**
 * @author borettim
 *
 */
@Categories({ "example" })
public class BiFunctionTesterTest implements TestSuite {

	private Long bifunctionToBeTested(Short x, Integer y) {
		return (long) (x + y);
	}

	@TestDelegate
	public final BiFunctionTester<Short, Integer, Long> tester1 = BiFunctionTester
			.of(this::bifunctionToBeTested).passingAsParameter((short) 1, 2)
			.thenExpectingResult(3l).testNamed("tested")
			.passingAsParameter((short) 2, 4).thenExpectingResult(6l).build();
}
