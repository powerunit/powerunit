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

import ch.powerunit.Categories;
import ch.powerunit.TestDelegate;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;
import ch.powerunit.pattern.PatternTester;

/**
 * @author borettim
 *
 */
public class PatternTesterTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<PatternTesterTest> runner = new DefaultPowerUnitRunnerImpl<>(
				PatternTesterTest.class);
		runner.addListener(new BootstrapTestListener<PatternTesterTest>());
		runner.run();

	}

	@Categories("base")
	public static class PatternTesterTest implements TestSuite {

		@TestDelegate
		public final PatternTester sample1 = PatternTester.of("a+")
				.receiving("b").thenNoMatching().receiving("aa").thenMatching()
				.build();

		@TestDelegate
		public final PatternTester sample2 = PatternTester.of("a+")
				.receiving("b").thenNoMatching().receiving("aa").thenMatching()
				.havingGroup(0).equalTo("aa").build();

		@TestDelegate
		public final PatternTester sample3 = PatternTester.of("a+(b*)")
				.receiving("b").thenNoMatching().receiving("aa").thenMatching()
				.havingGroup(0).equalTo("aa").havingGroup(1).equalTo("")
				.build();

		@TestDelegate
		public final PatternTester sample4 = testerOfPattern("a+(b*)")
				.receiving("b").thenNoMatching().receiving("aa").thenMatching()
				.havingGroup(0).equalTo("aa").havingGroup(1).equalTo("")
				.receiving("aab").thenMatching().havingGroup(0).equalTo("aab")
				.havingGroup(1).equalTo("b").build();
	}

}
