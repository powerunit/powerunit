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
package ch.powerunit.test.core;

import ch.powerunit.Ignore;
import ch.powerunit.Test;
import ch.powerunit.TestContext;
import ch.powerunit.TestResultListener;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;

public class TestResultListenerTests {
    public static void main(String[] args) {
        AllTests.testNoException(
                "testResultListenerOK",
                () -> {
                    DefaultPowerUnitRunnerImpl<TestClass> runner = new DefaultPowerUnitRunnerImpl<>(
                            TestClass.class);
                    runner.addListener(new Listener());
                    runner.run();
                    TestSuite.DSL.assertThat(counter).is(
                            1000l + 10000 + 100000 + 100000 + 1000000
                                    + 10000000 + 100000 + 100000000
                                    + 1000000000 + 100000 + 100000 + 100000000);
                });
    }

    private static class Listener implements TestResultListener<TestClass> {

        @Override
        public void notifySetStart(String setName, String parameters) {
            counter += 1000;
        }

        @Override
        public void notifySetEnd(String setName, String parameters) {
            counter += 10000;
        }

        @Override
        public void notifyStart(TestContext<TestClass> context) {
            counter += 100000;
        }

        @Override
        public void notifySuccess(TestContext<TestClass> context) {
            counter += 1000000;
        }

        @Override
        public void notifyFailure(TestContext<TestClass> context,
                Throwable cause) {
            counter += 10000000;
        }

        @Override
        public void notifySkipped(TestContext<TestClass> context) {
            counter += 100000000;
        }

        @Override
        public void notifyError(TestContext<TestClass> context, Throwable cause) {
            counter += 1000000000;
        }

        @Override
        public void notifyParameterStart(String setName, String parameterName) {
            counter += 10000000000l;
        }

        @Override
        public void notifyParameterEnd(String setName, String parameterName) {
            counter += 100000000000l;
        }

    }

    private static long counter = 0;

    public static class TestClass implements TestSuite {
        @Test
        public void success() {
        }

        @Test
        public void failure() {
            fail();
        }

        @Test
        public void error() {
            throw new IllegalArgumentException("xxx");
        }

        @Override
        @Test
        @Ignore
        public void skip() {

        }

        @Test
        public void skipOnAssume() {
            assumeThat(true).is(false);
        }
    }
}
