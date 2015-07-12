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
package ch.powerunit.collector.impl;

import java.util.function.BiFunction;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Stream.Builder;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestDelegator;
import ch.powerunit.TestSuite;
import ch.powerunit.collector.CollectorTester;

/**
 * @author borettim
 *
 */
@TestDelegator
public class CollectorTesterImpl<T, A, R> implements TestSuite {
	@Parameters
	public static <T, A, R> Stream<Object[]> getDatas(
			CollectorTester<T, A, R> input) {
		StringDescription sdChar = new StringDescription();
		input.getExpectedCharacteristics().describeTo(sdChar);
		Builder<Object[]> builder = Stream.builder();
		for (int i = 0; i < input.getInputs().size(); i++) {
			StringDescription sd = new StringDescription();
			input.getResults().get(i).describeTo(sd);
			builder.add(new Object[] {
					input.getCollectorToTest(),
					i == 0,
					input.getInputs().get(i),
					input.getResults().get(i),
					sd.toString(),
					input.getExpectedCharacteristics(),
					sdChar.toString(),
					(BiFunction<String, Object[], Boolean>) CollectorTesterImpl::filter });
		}
		return builder.build();
	}

	@Parameter(0)
	public Collector<T, A, R> underTest;

	@Parameter(1)
	public boolean generalTest;

	@Parameter(2)
	public Stream<T> input;

	@Parameter(3)
	public Matcher<R> expecting;

	@Parameter(4)
	public String expectingDescription;

	@Parameter(5)
	public Matcher<Iterable<? extends Characteristics>> expectingCharacteristics;

	@Parameter(6)
	public String expectingCharacteristicsDescription;

	@Parameter(value = 7, filter = true)
	public BiFunction<String, Object[], Boolean> filter;

	private static boolean filter(String methodName, Object parameter[]) {
		if (!(Boolean) parameter[1]
				&& (methodName.equals("testAccumulatorIsNotNull")
						|| methodName.equals("testCombinerIsNotNull")
						|| methodName.equals("testFinisherIsNotNull")
						|| methodName.equals("testSupplierIsNotNull") || methodName
							.equals("testCharacteristicsIsNotNull"))) {
			return false;
		}
		return true;
	}

	@Test(name = "Validate that accumulator() is not null for `%1$s`")
	public void testAccumulatorIsNotNull() {
		assertThat(underTest.accumulator()).isNotNull();
	}

	@Test(name = "Validate that combiner() is not null for `%1$s`")
	public void testCombinerIsNotNull() {
		assertThat(underTest.combiner()).isNotNull();
	}

	@Test(name = "Validate that finisher() is not null for `%1$s`")
	public void testFinisherIsNotNull() {
		assertThat(underTest.finisher()).isNotNull();
	}

	@Test(name = "Validate that supplier() is not null for `%1$s`")
	public void testSupplierIsNotNull() {
		assertThat(underTest.supplier()).isNotNull();
	}

	@Test(name = "Validate that characteristics() is not null for `%1$s`")
	public void testCharacteristicsIsNotNull() {
		assertThat(underTest.characteristics()).isNotNull();
	}

	@Test(name = "Validate that characteristics() is %7$s")
	public void testCharacteristicsIsASetOfValue() {
		assertThat(underTest.characteristics()).is(expectingCharacteristics);
	}

	@Test(name = "Validate that the stream `%3$s` with the collector under test result matches %5$s for `%1$s`")
	public void testValidateFunction() {
		assertThat(input.collect(underTest)).is(expecting);
	}
}
