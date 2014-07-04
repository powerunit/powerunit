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
package ch.powerunit.rules;

import ch.powerunit.TestContext;

/**
 * This rule can be used to have access to the {@link TestContext test execution
 * context} from inside a test.
 *
 * @author borettim
 *
 */
public final class TestContextRule implements TestListenerRule {

    private TestContext<Object> testContext;

    /*
     * (non-Javadoc)
     * 
     * @see
     * ch.powerunit.rules.ExternalResource#onStart(ch.powerunit.TestContext)
     */
    @Override
    public void onStart(TestContext<Object> context) {
        this.testContext = context;
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
