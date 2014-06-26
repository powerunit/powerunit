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
package org.powerunit.examples;

import java.util.Arrays;
import java.util.stream.Stream;

import org.powerunit.Categories;
import org.powerunit.Parameter;
import org.powerunit.Parameters;
import org.powerunit.Rule;
import org.powerunit.Test;
import org.powerunit.TestRule;

/**
 * @author borettim
 *
 */
@Categories({ "example" })
public class ChildrenTest extends ParentTest {

	@Parameters
	public static Stream<Object[]> getDatas() {
		return Arrays.stream(new Object[][] { { "A" }, { "B" } });
	}

	@Parameter(0)
	public String p1;

	@Rule
	public final TestRule level2 = before(this::prepare2).around(
			after(this::clean2)).around(
			() -> {
				System.out.println(context.getTestContext().getFullTestName()
						+ " just before construct");
				return before(this::prepare3);
			});

	public final void prepare3() {
		System.out.println(context.getTestContext().getFullTestName()
				+ ":prepare3");
	}

	public final void prepare2() {
		System.out.println(context.getTestContext().getFullTestName()
				+ ":prepare2");
	}

	public final void clean2() {
		System.out.println(context.getTestContext().getFullTestName()
				+ ":clean2");
	}

	@Test
	public void test1() {
		System.out.println("test1 " + p1);
	}

	@Test
	public void test2() {
		System.out.println("test2 " + p1);
	}
}
