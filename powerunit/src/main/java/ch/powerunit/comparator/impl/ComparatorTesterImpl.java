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
package ch.powerunit.comparator.impl;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestDelegator;
import ch.powerunit.TestSuite;
import ch.powerunit.comparator.ComparatorTester;

/**
 * @author borettim
 *
 */
@TestDelegator
public class ComparatorTesterImpl<O, C extends Comparator<O>> implements
		TestSuite {
	@Parameters
	public static <O, C extends Comparator<O>> Stream<Object[]> getParameter(
			ComparatorTester<O, C> input) {
		Builder<Object[]> b = Stream.builder();
		O less[] = input.getLessSamples().get();
		O equal[] = input.getEqualSamples().get();
		O greater[] = input.getGreaterSamples().get();
		Comparator<O> underTest = input.getUnderTest().get();
		for (O l : less) {
			for (O e : equal) {
				b.accept(new Object[] { input.getComparatorClass(), underTest,
						l, e, -1 });
				b.accept(new Object[] { input.getComparatorClass(), underTest,
						e, l, 1 });
			}
			for (O g : greater) {
				b.accept(new Object[] { input.getComparatorClass(), underTest,
						l, g, -1 });
				b.accept(new Object[] { input.getComparatorClass(), underTest,
						g, l, 1 });
			}
		}
		for (O e : equal) {
			for (O g : greater) {
				b.accept(new Object[] { input.getComparatorClass(), underTest,
						e, g, -1 });
				b.accept(new Object[] { input.getComparatorClass(), underTest,
						g, e, 1 });
			}
			for (O e2 : equal) {
				b.accept(new Object[] { input.getComparatorClass(), underTest,
						e, e2, 0 });
				b.accept(new Object[] { input.getComparatorClass(), underTest,
						e2, e, 0 });
			}
		}
		return b.build()
				.map(TestSuite.DSL
						.<BiFunction<String, Object[], Boolean>> addFieldToEachEntry(ComparatorTesterImpl::filter));
	}

	@Parameter(0)
	public Class<C> target;

	@Parameter(1)
	public Comparator<O> instance;

	@Parameter(2)
	public O obj1;

	@Parameter(3)
	public O obj2;

	@Parameter(4)
	public int expectedResult;

	@Parameter(value = 5, filter = true)
	public BiFunction<String, Object[], Boolean> filter;

	private static boolean filter(String methodName, Object parameter[]) {
		if (methodName == "testLess"
				&& Integer.valueOf(-1).equals(parameter[4])) {
			return true;
		}
		if (methodName == "testEquals"
				&& Integer.valueOf(0).equals(parameter[4])) {
			return true;
		}
		if (methodName == "testGreater"
				&& Integer.valueOf(1).equals(parameter[4])) {
			return true;
		}
		return false;
	}

	@Test(name = "Having `{2}` < `{3}` Then result should be <0 for the Comparator class {0} (with instance {1})}")
	public void testLess() {
		assertThat("Comparaison is negative", instance.compare(obj1, obj2)).is(
				lessThan(0));
	}

	@Test(name = "Having `{2}` = `{3}` Then result should be 0 for the Comparator class {0} (with instance {1})")
	public void testEquals() {
		assertThat("Comparaison is 0", instance.compare(obj1, obj2)).is(
				equalTo(0));
	}

	@Test(name = "Having `{2}` > `{3}` Then result should be >0 for the Comparator class {0} (with instance {1})")
	public void testGreater() {
		assertThat("Comparaison is positive", instance.compare(obj1, obj2)).is(
				greaterThan(0));
	}

}
