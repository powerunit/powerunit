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
package ch.powerunit.demo;

import ch.powerunit.Test;
import ch.powerunit.TestRule;
import ch.powerunit.TestSuite;

public class DemoAssertThatExceptionTest implements TestSuite {

	public static void method1() throws Exception {
		throw new Exception("demo1");
	}

	public static boolean method2() throws Exception {
		throw new Exception("demo2");
	}

	public static void method3(Integer p1) throws Exception {
		throw new Exception("demo3");
	}

	@Test
	public void testNoArgNoReturn() {
		assertWhen(asStatement(DemoAssertThatExceptionTest::method1))
				.throwException(exceptionMessage("demo1"));
	}

	@Test
	public void testNoArgNoReturn2() {
		assertWhen(asStatement(()->{throw new Exception("demo4");}))
				.throwException(exceptionMessage("demo4"));
	}

	@Test
	public void testCallable() {
		assertWhen(DemoAssertThatExceptionTest::method2).throwException(
				exceptionMessage("demo2"));
	}

	@Test
	public void testStatement2() {
		assertWhen(DemoAssertThatExceptionTest::method3, 12).throwException(
				exceptionMessage("demo3"));
	}
}