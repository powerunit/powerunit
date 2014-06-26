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

import org.powerunit.Rule;
import org.powerunit.Test;
import org.powerunit.TestRule;
import org.powerunit.TestSuite;
import org.powerunit.impl.DefaultPowerUnitRunnerImpl;

public class SystemPropertiesRuleTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<SystemPropertiesRuleTest> runner = new DefaultPowerUnitRunnerImpl<>(
				SystemPropertiesRuleTest.class);
		runner.addListener(new BootstrapTestListener<SystemPropertiesRuleTest>());
		runner.run();

	}

	public static class SystemPropertiesRuleTest implements TestSuite {

		@Rule
		public final TestRule chain = before(() -> {
			System.setProperty("testProps.1", "x");
			System.clearProperty("testProps.2");
			System.clearProperty("testProps.3");
		}).around(after(() -> {
			assertThat(System.getProperty("testProps.1")).is("x");
			assertThat(System.getProperty("testProps.2")).isNull();
			assertThat(System.getProperty("testProps.3")).isNull();
		})).around(systemPropertiesRule("testProps.1", "testProps.2"))
				.around(after(() -> {
					assertThat(System.getProperty("testProps.1")).is("y");
					assertThat(System.getProperty("testProps.2")).is("z");
				})).around(systemProperty("testProps.3", "t"));

		@Test
		public void changeSystemProperty1() {
			System.setProperty("testProps.1", "y");
			System.setProperty("testProps.2", "z");
			System.setProperty("testProps.3", "t");
		}

		@Test
		public void changeSystemProperty2() {
			System.setProperty("testProps.1", "y");
			System.setProperty("testProps.2", "z");
			System.setProperty("testProps.3", "t");
		}
	}
}
