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
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.hamcrest.Matcher;

import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.TestDelegate;
import ch.powerunit.TestDelegator;
import ch.powerunit.TestSuite;
import ch.powerunit.bifunction.BiFunctionTester;
import ch.powerunit.comparator.ComparatorTester;

/**
 * @author borettim
 *
 */
@TestDelegator
public class ComparatorTesterImpl<O, C extends Comparator<O>> implements
		TestSuite {
	@Parameters
	public static <O, C extends Comparator<O>> Stream<Object[]> getParameters(
			ComparatorTester<O, C> input) {
		Builder<Object[]> b = Stream.builder();
		O less[] = input.getLessSamples().get();
		O equal[] = input.getEqualSamples().get();
		O greater[] = input.getGreaterSamples().get();
		Comparator<O> underTest = input.getUnderTest().get();
		int i = 0;
		for (O l : less) {
			b.accept(new Object[] {
					input.getComparatorClass(),
					underTest,
					l,
					l,
					TestSuite.DSL.equalTo(0),
					"Having `"
							+ l
							+ "` = `"
							+ l
							+ "` Then result should be 0 for the Comparator class "
							+ input.getComparatorClass() + " (with instance "
							+ underTest + ")" });
			for (O e : equal) {
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						l,
						e,
						TestSuite.DSL.lessThan(0),
						"Having `"
								+ l
								+ "` < `"
								+ e
								+ "` Then result should be <0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						e,
						l,
						TestSuite.DSL.greaterThan(0),
						"Having `"
								+ e
								+ "` > `"
								+ l
								+ "` Then result should be >0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
			}
			for (O g : greater) {
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						l,
						g,
						TestSuite.DSL.lessThan(0),
						"Having `"
								+ l
								+ "` < `"
								+ g
								+ "` Then result should be <0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						g,
						l,
						TestSuite.DSL.greaterThan(0),
						"Having `"
								+ g
								+ "` > `"
								+ l
								+ "` Then result should be >0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
			}
			int j = 0;
			for (O l2 : less) {
				if (j++ <= i) {
					continue;
				}
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						l,
						l2,
						TestSuite.DSL.lessThan(0),
						"Having `"
								+ l
								+ "` < `"
								+ l2
								+ "` Then result should be <0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						l2,
						l,
						TestSuite.DSL.greaterThan(0),
						"Having `"
								+ l2
								+ "` > `"
								+ l
								+ "` Then result should be >0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
			}
			i++;
		}
		i = 0;
		for (O e : equal) {
			for (O g : greater) {
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						e,
						g,
						TestSuite.DSL.lessThan(0),
						"Having `"
								+ e
								+ "` < `"
								+ g
								+ "` Then result should be <0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						g,
						e,
						TestSuite.DSL.greaterThan(0),
						"Having `"
								+ g
								+ "` > `"
								+ e
								+ "` Then result should be >0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
			}
			for (O e2 : equal) {
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						e,
						e2,
						TestSuite.DSL.equalTo(0),
						"Having `"
								+ e
								+ "` = `"
								+ e2
								+ "` Then result should be 0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						e2,
						e,
						TestSuite.DSL.equalTo(0),
						"Having `"
								+ e2
								+ "` = `"
								+ e
								+ "` Then result should be 0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
			}
		}
		i = 0;
		for (O g : greater) {
			int j = 0;
			for (O g2 : greater) {
				if (j++ <= i) {
					continue;
				}
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						g,
						g2,
						TestSuite.DSL.lessThan(0),
						"Having `"
								+ g
								+ "` < `"
								+ g2
								+ "` Then result should be <0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
				b.accept(new Object[] {
						input.getComparatorClass(),
						underTest,
						g2,
						g,
						TestSuite.DSL.greaterThan(0),
						"Having `"
								+ g2
								+ "` > `"
								+ g
								+ "` Then result should be >0 for the Comparator class "
								+ input.getComparatorClass()
								+ " (with instance " + underTest + ")" });
			}
			i++;
		}

		// TODO add greater
		return b.build();
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
	public Matcher<Integer> expectedResult;

	@Parameter(5)
	public String name;

	@TestDelegate
	public final Supplier<BiFunctionTester<O, O, Integer>> tester = () -> testerOfBiFunction(
			instance::compare).passingAsParameter(obj1, obj2)
			.thenExpectingResultThat(expectedResult).testNamed(name).build();

}
