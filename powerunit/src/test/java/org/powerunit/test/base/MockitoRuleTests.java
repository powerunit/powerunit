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

import org.mockito.Mock;
import org.powerunit.Categories;
import org.powerunit.Rule;
import org.powerunit.Test;
import org.powerunit.TestRule;
import org.powerunit.TestSuite;
import org.powerunit.impl.DefaultPowerUnitRunnerImpl;

public class MockitoRuleTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<TestMockito> runner = new DefaultPowerUnitRunnerImpl<>(
				TestMockito.class);
		runner.addListener(new BootstrapTestListener<TestMockito>());
		runner.run();

	}

	@Categories("base")
	public static class TestMockito implements TestSuite {
		@Mock
		private Object mock;

		@Rule
		public final TestRule rule = mockitoRule();

		@Test
		public void test() {
			assertThat(mock).isNotNull();
		}
	}
}
