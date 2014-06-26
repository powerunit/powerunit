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
package org.powerunit.test.base;

import java.util.Arrays;
import java.util.stream.Stream;

import org.powerunit.Parameter;
import org.powerunit.Parameters;
import org.powerunit.Rule;
import org.powerunit.Test;
import org.powerunit.TestRule;
import org.powerunit.TestSuite;
import org.powerunit.impl.DefaultPowerUnitRunnerImpl;
import org.powerunit.impl.validator.ParameterValidator;
import org.powerunit.impl.validator.ParametersValidator;

public class RuntimeRuleValidatorTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<Tester> runner = new DefaultPowerUnitRunnerImpl<>(
				Tester.class);
		runner.addListener(new BootstrapTestListener<Tester>());
		runner.run();
	}

	public static class Tester<T> implements TestSuite, ParametersValidator,
			ParameterValidator {
		@Parameters
		public static Stream<Object> getDatas() {
			return Arrays.stream(new Object[] { TestClass1.class,
					TestClass2.class, TestClass3.class, TestClass4.class,
					TestClass5.class, TestClass6.class });
		}

		@Parameter(0)
		public Class<T> clazz;

		@Test
		public void testExpectedError() {
			assertWhen(this::executeWithError).throwException(
					exceptionMessage(containsString("@Rule")));
		}

		public void executeWithError(Object anyParameter) throws Throwable {
			new DefaultPowerUnitRunnerImpl<T>(clazz);
		}
	}

	public static class TestClass1 implements TestSuite {
		@Rule
		public static final TestRule field1 = TestRule.before(() -> {
		});
	}

	public static class TestClass2 implements TestSuite {
		@Rule
		public TestRule field1 = before(() -> {
		});
	}

	public static class TestClass3 implements TestSuite {
		@Rule
		public final int field1 = 0;
	}

	public static class TestClass4 implements TestSuite {
		@Rule
		private final TestRule field1 = before(() -> {
		});
	}

	public static class TestClass5 implements TestSuite {
		@Rule
		public final TestRule field1 = before(() -> {
		});

		@Rule
		public final TestRule field2 = before(() -> {
		});
	}

	public static class TestClass6 implements TestSuite {
		@Rule
		public final TestRule field1 = null;
	}
}
