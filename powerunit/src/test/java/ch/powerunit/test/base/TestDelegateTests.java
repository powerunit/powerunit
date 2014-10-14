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

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

import ch.powerunit.Categories;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestDelegate;
import ch.powerunit.TestDelegator;
import ch.powerunit.TestInterface;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;

/**
 * @author borettim
 *
 */
public class TestDelegateTests {
    public static void main(String[] args) {
        DefaultPowerUnitRunnerImpl<TestDelegateTest> runner = new DefaultPowerUnitRunnerImpl<>(
                TestDelegateTest.class);
        runner.addListener(new BootstrapTestListener<TestDelegateTest>());
        runner.run();

    }

    @Categories("base")
    public static class TestDelegateTest implements TestSuite {
        @TestDelegate
        public final Delegator d = new Delegator("text1");

        @TestDelegate
        public final Supplier<Delegator> d2 = () -> new Delegator("text1");
    }

    @TestInterface(RealTest.class)
    public static class Delegator {
        private final String text;

        public Delegator(String text) {
            this.text = text;
        }

        /**
         * @return the text
         */
        public String getText() {
            return text;
        }

    }

    @TestDelegator
    public static class RealTest implements TestSuite {
        @Parameters
        public static Stream<Object[]> getDatas(Delegator input) {
            return Arrays.stream(new Object[][] { { input.text } });
        }

        @Parameter(0)
        public String field1;

        @Test(name = "name={0}")
        public void test() {
            assertThat(field1).is(anything());
        }
    }
}
