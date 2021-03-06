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
import java.util.stream.Stream;

import ch.powerunit.Categories;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;
import ch.powerunit.impl.validator.ParameterValidator;
import ch.powerunit.impl.validator.ParametersValidator;

public class RuntimeTestValidatorTests {
    public static void main(String[] args) {
        DefaultPowerUnitRunnerImpl<Tester> runner = new DefaultPowerUnitRunnerImpl<>(
                Tester.class);
        runner.addListener(new BootstrapTestListener<Tester>());
        runner.run();
    }

    @Categories("base")
    public static class Tester<T> implements TestSuite, ParametersValidator,
            ParameterValidator {
        @Parameters
        public static Stream<Object> getDatas() {
            return Arrays.stream(new Object[] { TestClass2.class,
                    TestClass3.class, TestClass4.class, TestClass5.class, });
        }

        @Parameter(0)
        public Class<T> clazz;

        @Test
        public void testExpectedError() {
            assertWhen(this::executeWithError).throwException(
                    exceptionMessage(containsString("@Test")));
        }

        public void executeWithError(Object anyParameter) throws Throwable {
            new DefaultPowerUnitRunnerImpl<T>(clazz);
        }
    }

    public static class TestClass2 {

        @Test
        public static void staticAnnotatedMethod() {
        }

    }

    public static class TestClass3 {

        @Test
        public int returningMethod() {
            return 1;
        }

    }

    public static class TestClass4 {

        @Test
        public void parameterMethod(String argv) {
        }

    }

    public static class TestClass5 {

        @Test
        private void testPrivateMethod() {
        }

    }
}
