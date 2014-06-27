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

import org.powerunit.Categories;
import org.powerunit.Parameter;
import org.powerunit.Parameters;
import org.powerunit.Test;
import org.powerunit.TestSuite;
import org.powerunit.impl.DefaultPowerUnitRunnerImpl;
import org.powerunit.impl.validator.ParameterValidator;
import org.powerunit.impl.validator.ParametersValidator;

public class RuntimeParametersValidatorTests {
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
					TestClass3.class, TestClass4.class, TestClass5.class,
					TestClass6.class, TestClass7.class, TestClass8.class,
					TestClass9.class, TestClass10.class, TestClass11.class,
					TestClass12.class });
		}

		@Parameter(0)
		public Class<T> clazz;

		@Test
		public void testExpectedError() {
			this.<Throwable> assertWhen(this::executeWithError).throwException(
					exceptionMessage(containsString("@Parameter")));
		}

		public void executeWithError(Object anyParameter) {
			new DefaultPowerUnitRunnerImpl<T>(clazz);
		}
	}

	public static class TestClass2 {
		@Parameters
		public static Stream<Object[]> getDatas() {
			return null;
		}

	}

	public static class TestClass3 {

		@Parameter(0)
		public String p1;
	}

	public static class TestClass4 {
		@Parameters
		public Stream<Object[]> getDatas() {
			return null;
		}

		@Parameter(0)
		public String p1;
	}

	public static class TestClass5 {
		@Parameters
		private static Stream<Object[]> getDatas() {
			return null;
		}

		@Parameter(0)
		public String p1;
	}

	public static class TestClass6 {
		@Parameters
		public static Object getDatas() {
			return null;
		}

		@Parameter(0)
		public String p1;
	}

	public static class TestClass7 {
		@Parameters
		public static Stream<Object[]> getDatas(int p) {
			return null;
		}

		@Parameter(0)
		public String p1;
	}

	public static class TestClass8 {
		@Parameters
		public static Stream<Object[]> getDatas() {
			return null;
		}

		@Parameters
		public static Stream<Object[]> getDatas2() {
			return null;
		}

		@Parameter(0)
		public String p1;
	}

	public static class TestClass9 {
		@Parameters
		public static Stream<Object[]> getDatas() {
			return null;
		}

		@Parameter(1)
		public String p1;
	}

	public static class TestClass10 {
		@Parameters
		public static Stream<Object[]> getDatas() {
			return null;
		}

		@Parameter(0)
		public static String p1;
	}

	public static class TestClass11 {
		@Parameters
		public static Stream<Object[]> getDatas() {
			return null;
		}

		@Parameter(0)
		private String p1;
	}

	public static class TestClass12 {
		@Parameters
		public static Stream<Object[]> getDatas() {
			return null;
		}

		@Parameter(-1)
		public String p1;
	}
}
