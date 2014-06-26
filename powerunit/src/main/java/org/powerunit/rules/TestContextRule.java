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
package org.powerunit.rules;

import org.powerunit.Statement;
import org.powerunit.TestContext;
import org.powerunit.TestRule;

/**
 * This rule can be used to have access to the {@link TestContext test execution
 * context} from inside a test.
 * 
 * @author borettim
 *
 */
public final class TestContextRule implements TestRule {

	private TestContext<Object> testContext;

	@Override
	public Statement<TestContext<Object>, Throwable> computeStatement(
			Statement<TestContext<Object>, Throwable> inner) {
		return Statement.around(inner, (p) -> testContext = p, (p) -> {
		});
	}

	/**
	 * Retrieve the test context object.
	 * 
	 * @return the testContext.
	 */
	public TestContext<Object> getTestContext() {
		return testContext;
	}

}
