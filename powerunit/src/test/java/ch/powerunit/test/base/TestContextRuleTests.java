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
package ch.powerunit.test.base;

import ch.powerunit.Categories;
import ch.powerunit.Rule;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;
import ch.powerunit.rules.TestContextRule;

public class TestContextRuleTests {
    public static void main(String[] args) {
        DefaultPowerUnitRunnerImpl<TestContextRuleTest> runner = new DefaultPowerUnitRunnerImpl<>(
                TestContextRuleTest.class);
        runner.addListener(new BootstrapTestListener<TestContextRuleTest>());
        runner.run();

    }

    @Categories("base")
    public static class TestContextRuleTest implements TestSuite {

        @Rule
        public final TestContextRule rule = new TestContextRule();

        @Test
        public void test() {
            assertThat(rule).isNotNull();
            assertThat(rule.getTestContext()).isNotNull();
            assertThat(rule.getTestContext().getFullTestName())
                    .is("ch.powerunit.test.base.TestContextRuleTests$TestContextRuleTest:test");
        }
    }
}
