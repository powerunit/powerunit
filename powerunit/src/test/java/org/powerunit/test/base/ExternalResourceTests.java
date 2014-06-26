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
import org.powerunit.rules.ExternalResource;

/**
 * @author borettim
 *
 */
public class ExternalResourceTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<TestExternalResourceEmpty> runner = new DefaultPowerUnitRunnerImpl<>(
				TestExternalResourceEmpty.class);
		runner.addListener(new BootstrapTestListener<TestExternalResourceEmpty>());
		runner.run();

	}

	public static class TestExternalResourceEmpty implements TestSuite {
		@Rule
		public final TestRule rule = new ExternalResource() {
		};

		@Test
		public void test() {
			// Nothing
		}
	}
}
