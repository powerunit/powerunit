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

import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.Properties;

import org.apache.maven.surefire.providerapi.ProviderParameters;
import org.apache.maven.surefire.util.RunOrderCalculator;
import org.apache.maven.surefire.util.ScanResult;
import org.apache.maven.surefire.util.TestsToRun;
import org.mockito.Matchers;
import org.mockito.Mock;

import ch.powerunit.Categories;
import ch.powerunit.Rule;
import ch.powerunit.Test;
import ch.powerunit.TestRule;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;
import ch.powerunit.surefire.PowerUnitProvider;
import ch.powerunit.surefire.PowerUnitProviderScannerFilter;

public class PowerUnitProviderTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<PowerUnitProviderTest> runner = new DefaultPowerUnitRunnerImpl<>(
				PowerUnitProviderTest.class);
		runner.addListener(new BootstrapTestListener<PowerUnitProviderTest>());
		runner.run();

	}

	@Categories("base")
	public static class PowerUnitProviderTest implements TestSuite {

		@Mock
		private ClassLoader classLoader;

		@Mock
		private ProviderParameters providerParameters;

		@Mock
		private RunOrderCalculator runOrderCalculator;

		@Mock
		private ScanResult scanResult;

		@Mock
		private Properties parameters;

		@Mock
		private TestsToRun testToRun;

		@SuppressWarnings("rawtypes")
		@Mock
		private Iterator<Class> iterator;

		private PowerUnitProvider<?> underTest;

		@Rule
		public final TestRule chainRule = mockitoRule().around(
				before(this::prepare));

		public void prepare() {
			when(providerParameters.getTestClassLoader()).thenReturn(
					classLoader);
			when(providerParameters.getRunOrderCalculator()).thenReturn(
					runOrderCalculator);
			when(providerParameters.getScanResult()).thenReturn(scanResult);
			when(providerParameters.getProviderProperties()).thenReturn(
					parameters);
			when(testToRun.iterator()).thenReturn(iterator);
			underTest = new PowerUnitProvider<>(providerParameters);
			when(
					parameters.getProperty(Matchers.anyString(),
							Matchers.anyString())).thenReturn("");
		}

		@Test
		public void testGetSuites() {
			when(
					scanResult.applyFilter(
							Matchers.any(PowerUnitProviderScannerFilter.class),
							Matchers.eq(classLoader))).thenReturn(testToRun);
			when(runOrderCalculator.orderTestClasses(Matchers.eq(testToRun)))
					.thenReturn(testToRun);
			assertThat(underTest.getSuites()).is(sameInstance(iterator));
		}
	}
}
