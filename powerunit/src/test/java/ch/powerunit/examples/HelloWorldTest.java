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
import ch.powerunit.Test;
import ch.powerunit.TestSuite;

@Categories({ "example" })
public class HelloWorldTest implements TestSuite {
	@Test()
	public void testHelloWorld1() {
		assertThat("Hello world").is(containsString(" "));
	}

	@Test(name = "Test Hello World alternate test name")
	public void testHelloWorld2() {
		assertThat("Hello world").is(containsString(" "));
	}

	@Test(name = "exception Hello world")
	public void testHelloWorld3() {
		assertWhen((p) -> {
			throw new Throwable("tst");
		}).throwException(exceptionMessage("tst"));
	}
}
