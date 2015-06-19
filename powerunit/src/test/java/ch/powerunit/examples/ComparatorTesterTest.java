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
package ch.powerunit.examples;

import java.util.Comparator;

import ch.powerunit.Categories;
import ch.powerunit.TestDelegate;
import ch.powerunit.TestSuite;
import ch.powerunit.comparator.ComparatorTester;

/**
 * @author borettim
 *
 */
@Categories({ "example" })
public class ComparatorTesterTest implements TestSuite {

	public static class MyComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o1.compareTo(o2);
		}

	}

	@TestDelegate
	public final ComparatorTester<Integer, MyComparator> automaticTester = ComparatorTester
			.of(MyComparator.class).build();

	@TestDelegate
	public final ComparatorTester<Integer, MyComparator> automaticTesterWithManualInstance = ComparatorTester
			.of(MyComparator.class).usingInstance(new MyComparator()).build();

	@TestDelegate
	public final ComparatorTester<Integer, MyComparator> providedSampler = ComparatorTester
			.of(MyComparator.class).withLessSamples(-6, -4)
			.withEqualSamples(12).withGreaterSamples(16, 18).build();

	@TestDelegate
	public final ComparatorTester<Integer, MyComparator> providedSampler2 = ComparatorTester
			.of(MyComparator.class).withLessSamples(-6).withEqualSamples(12)
			.withGreaterSamples(16).build();
}
