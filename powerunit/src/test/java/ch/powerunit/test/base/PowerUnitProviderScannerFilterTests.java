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

import java.util.HashMap;
import java.util.Properties;

import ch.powerunit.Categories;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;
import ch.powerunit.surefire.PowerUnitProviderScannerFilter;

public class PowerUnitProviderScannerFilterTests {

	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<PowerUnitProviderScannerFilterTest> runner = new DefaultPowerUnitRunnerImpl<>(
				PowerUnitProviderScannerFilterTest.class);
		runner.addListener(new BootstrapTestListener<PowerUnitProviderScannerFilterTest>());
		runner.run();
	}

	@Categories("base")
	public static class PowerUnitProviderScannerFilterTest implements TestSuite {
		@Test
		public void testRejectedClass() {
			assertThat(new PowerUnitProviderScannerFilter(new HashMap<String, String>()).accept(Object.class))
					.is(false);
		}

		@Test
		public void testAcceptedClass() {
			assertThat(new PowerUnitProviderScannerFilter(new HashMap<String, String>())
					.accept(PowerUnitProviderScannerFilterTest.class)).is(true);
		}
	}

}
