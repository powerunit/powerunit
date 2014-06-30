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
import java.util.Collection;
import java.util.stream.Stream;

import ch.powerunit.Categories;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;

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

	@Categories("base")
	public static class StreamParametersMapFunctionTest implements TestSuite {
		@Parameters
		public static Stream<Object[]> getDatas() {
			return Arrays
					.stream(new String[][] { { "a", "1", "java.lang.String",
							"3,4", "true,false" } })
					.map(DSL.stringToParameterMap(StreamParametersMapFunctionTest.class))
					.filter(DSL.parametersFilterUsingMatcher(DSL
							.arrayWithSize(5)));
		}

		@Parameter(0)
		public String field1;

		@Parameter(1)
		public int field2;

		@Parameter(2)
		public Class<?> field3;

		@Parameter(3)
		public String field4[];

		@Parameter(4)
		public Collection<Boolean> field5;

		@Test
		public void test() {
			assertThat(field1).is("a");
			assertThat(field2).is(1);
			assertThat(field3).is(equalTo(String.class));
			assertThat(field4).is(new String[] { "3", "4" });
			assertThatIterable(field5).contains(true, false);
		}
	}
}
