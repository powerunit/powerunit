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

import org.powerunit.Rule;
import org.powerunit.TestRule;
import org.powerunit.TestSuite;
import org.powerunit.rules.TestContextRule;

public abstract class ParentTest implements TestSuite {
	protected final TestContextRule context = new TestContextRule();

	@Rule
	public final TestRule level1 = context.around(before(this::prepare1)
			.around(after(this::clean1)));

	public final void prepare1() {
		System.out.println(context.getTestContext().getFullTestName()
				+ ":prepare1");
	}

	public final void clean1() {
		System.out.println(context.getTestContext().getFullTestName()
				+ ":clean1");
	}
}
