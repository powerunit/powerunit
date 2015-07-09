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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

import ch.powerunit.Categories;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Rule;
import ch.powerunit.Test;
import ch.powerunit.TestDelegate;
import ch.powerunit.TestDelegator;
import ch.powerunit.TestInterface;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;
import ch.powerunit.rules.SystemStreamRule;

/**
 * @author borettim
 *
 */
public class SystemStreamRuleTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<SystemStreamRuleTest> runner = new DefaultPowerUnitRunnerImpl<>(
				SystemStreamRuleTest.class);
		runner.addListener(new BootstrapTestListener<SystemStreamRuleTest>());
		runner.run();

	}

	@Categories("base")
	public static class SystemStreamRuleTest implements TestSuite {

		private ByteArrayOutputStream out = new ByteArrayOutputStream();

		private ByteArrayOutputStream err = new ByteArrayOutputStream();

		@Rule
		public final SystemStreamRule rule = replaceBothStream(new PrintStream(
				out), new PrintStream(err));

		@Test
		public void testOut() {
			System.out.println("testout");
			System.out.flush();
			assertThat(out.toString()).is(String.format("testout%n"));
		}
		
		@Test
		public void testErr() {
			System.err.println("testerr");
			System.err.flush();
			assertThat(err.toString()).is(String.format("testerr%n"));
		}
		
		@Test
		public void testDiff() {
			assertThat(System.out).isNot(rule.getRealSystemOut());
			assertThat(System.err).isNot(rule.getRealSystemErr());
		}

	}

}
