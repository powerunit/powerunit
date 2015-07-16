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
package ch.powerunit.bifunction.impl;

import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.hamcrest.Matcher;

import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestDelegator;
import ch.powerunit.TestSuite;
import ch.powerunit.bifunction.BiFunctionTester;

/**
 * @author borettim
 *
 */
@TestDelegator
public class BiFunctionTesterImpl<T, U, R> implements TestSuite {
	@Parameters
	public static <T, U, R> Stream<Object[]> getParameters(
			BiFunctionTester<T, U, R> input) {
		Builder<Object[]> b = Stream.builder();
		for (int i = 0; i < input.getInput1().size(); i++) {
			Matcher<? super R> result = input.getResult().get(i).get();
			T param1 = input.getInput1().get(i).get();
			U param2 = input.getInput2().get(i).get();
			String name = input.getName().get(i).get();
			if ("".equals(name)) {
				name = "Passing `" + param1 + "` and `" + param2 + "`"
						+ " then " + result + " is expected";
			}
			b.add(new Object[] { name, param1, param2, result,
					input.getUnderTest() });
		}
		return b.build();
	}

	@Parameter(0)
	public String name;

	@Parameter(1)
	public T input1;

	@Parameter(2)
	public U input2;

	@Parameter(3)
	public Matcher<? super R> expectedResult;

	@Parameter(4)
	public BiFunction<T, U, R> function;

	@Test(name = "%1$s")
	public void testFunction() {
		assertThatBiFunction(function, input1, input2).is(expectedResult);
	}
}
