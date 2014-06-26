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
import org.powerunit.Test;
import org.powerunit.TestSuite;
import org.powerunit.impl.DefaultPowerUnitRunnerImpl;

/**
 * @author borettim
 *
 */
public class StreamParametersMapFunctionTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<StreamParametersMapFunctionTest> runner = new DefaultPowerUnitRunnerImpl<>(
				StreamParametersMapFunctionTest.class);
		runner.addListener(new BootstrapTestListener<StreamParametersMapFunctionTest>());
		runner.run();

	}

	public static class StreamParametersMapFunctionTest implements TestSuite {
		@Parameters
		public static Stream<Object[]> getDatas() {
			return Arrays
					.stream(new String[][] { { "a", "1", "java.lang.String" } })
					.map(DSL.stringToParameterMap(StreamParametersMapFunctionTest.class))
					.filter(DSL.parametersFilterUsingMatcher(DSL
							.arrayWithSize(2)));
		}

		@Parameter(0)
		public String field1;

		@Parameter(1)
		public int field2;

		@Parameter(2)
		public Class<?> field3;

		@Test
		public void test() {
			assertThat(field1).is("a");
			assertThat(field2).is(1);
			assertThat(field3).is(equalTo(String.class));
		}
	}
}
