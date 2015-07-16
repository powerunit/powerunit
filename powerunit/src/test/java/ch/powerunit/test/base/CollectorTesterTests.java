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
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Collectors;

import ch.powerunit.Categories;
import ch.powerunit.TestDelegate;
import ch.powerunit.TestSuite;
import ch.powerunit.collector.CollectorTester;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;

/**
 * @author borettim
 * 
 */
public class CollectorTesterTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<CollectorTesterTest> runner = new DefaultPowerUnitRunnerImpl<>(
				CollectorTesterTest.class);
		runner.addListener(new BootstrapTestListener<CollectorTesterTest>());
		runner.run();

	}

	@Categories("base")
	public static class CollectorTesterTest implements TestSuite {

		@TestDelegate
		public final CollectorTester<CharSequence, ?, String> sample1 = CollectorTester
				.of(Collectors.joining(","))
				.withInput(Arrays.<CharSequence> asList("a", "b").stream())
				.expecting("a,b")
				.withInput(
						Arrays.<CharSequence> asList("a", "b", "c")
								.parallelStream()).expecting("a,b,c").build();

		@TestDelegate
		public final CollectorTester<CharSequence, ?, String> sample2 = testerOfCollector(
				Collectors.joining(","))
				.withStreamFromList(Arrays.<CharSequence> asList("a", "b"))
				.expecting("a,b")
				.withParallelStreamFromList(
						Arrays.<CharSequence> asList("a", "b", "c"))
				.expecting("a,b,c").build();

		@TestDelegate
		public final CollectorTester<String, ?, TreeSet<String>> sample3 = testerOfCollector(
				Collector.<String, TreeSet<String>> of(TreeSet::new,
						TreeSet::add, (left, right) -> {
							left.addAll(right);
							return left;
						}))
				.havingCharacteristics(Characteristics.IDENTITY_FINISH)
				.withStreamFromList(Arrays.asList())
				.expecting(iterableWithSize(0)).withStreamFromArray("a", "b")
				.expecting(containsInAnyOrder("a", "b")).build();

	}

}
