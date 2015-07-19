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

import java.util.Arrays;
import java.util.Collection;

import ch.powerunit.Rule;
import ch.powerunit.Test;
import ch.powerunit.TestContext;
import ch.powerunit.TestRule;
import ch.powerunit.TestSuite;

public class DemoTestSuiteTest implements TestSuite {

	@Rule
	public final TestRule rules = beforeContextAware(this::beforeTestContext);

	private void beforeTestContext(TestContext<Object> ctx) {
		System.out.println(">>>" + ctx.getClass());
		System.out.println(">>>" + ctx.getFullTestName());
		System.out.println(">>>" + this);
	}

	@Test
	public void test() {

	}
}