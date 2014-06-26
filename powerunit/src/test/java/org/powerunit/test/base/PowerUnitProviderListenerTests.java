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

import org.apache.maven.surefire.report.CategorizedReportEntry;
import org.apache.maven.surefire.report.ConsoleLogger;
import org.apache.maven.surefire.report.ReportEntry;
import org.apache.maven.surefire.report.RunListener;
import org.apache.maven.surefire.report.SimpleReportEntry;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powerunit.Rule;
import org.powerunit.Test;
import org.powerunit.TestContext;
import org.powerunit.TestRule;
import org.powerunit.TestSuite;
import org.powerunit.impl.DefaultPowerUnitRunnerImpl;
import org.powerunit.surefire.PowerUnitProviderListener;

public class PowerUnitProviderListenerTests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<PowerUnitProviderListenerTest> runner = new DefaultPowerUnitRunnerImpl<>(
				PowerUnitProviderListenerTest.class);
		runner.addListener(new BootstrapTestListener<PowerUnitProviderListenerTest>());
		runner.run();
	}

	public static class PowerUnitProviderListenerTest implements TestSuite {
		@Mock
		private RunListener rl;

		private Class<?> underTest = Object.class;

		@Mock
		private ConsoleLogger consoleLogger;

		@Mock
		private TestContext<Object> textContext;

		@Rule
		public final TestRule testRule = mockitoRule().around(
				before(this::generateSetup));

		private PowerUnitProviderListener<Object> listener;

		public void generateSetup() {
			listener = new PowerUnitProviderListener<>(consoleLogger, rl,
					underTest);
			Mockito.when(textContext.getFullTestName()).thenReturn("name");
			Mockito.when(textContext.getTestCategories()).thenReturn("parent");

		}

		@Test
		public void testNotifyStart() {
			listener.notifyStart(textContext);
			Mockito.verify(rl).testStarting(Matchers.any(ReportEntry.class));
		}

		@Test
		public void testNotifySuccess() {
			listener.notifySuccess(textContext);
			Mockito.verify(rl).testSucceeded(Matchers.any(ReportEntry.class));
		}

		@Test
		public void testNotifyFailure() {
			listener.notifyFailure(textContext, new Exception());
			Mockito.verify(rl).testFailed(Matchers.any(ReportEntry.class));
		}

		@Test
		public void testNotifyError() {
			listener.notifyError(textContext, new Exception());
			Mockito.verify(rl).testError(Matchers.any(ReportEntry.class));
		}

		@Test
		public void testNotifySetStartNotNull() {
			listener.notifySetStart("name", "parent");
			Mockito.verify(rl).testSetStarting(
					Matchers.any(CategorizedReportEntry.class));
		}

		@Test
		public void testNotifySetStartNull() {
			listener.notifySetStart("name", null);
			Mockito.verify(rl).testSetStarting(
					Matchers.any(SimpleReportEntry.class));
		}

		@Test
		public void testNotifySetEndNotNull() {
			listener.notifySetEnd("name", "parent");
			Mockito.verify(rl).testSetCompleted(
					Matchers.any(CategorizedReportEntry.class));
		}

		@Test
		public void testNotifySetEndNull() {
			listener.notifySetEnd("name", null);
			Mockito.verify(rl).testSetCompleted(
					Matchers.any(SimpleReportEntry.class));
		}

		@Test
		public void testNotifySkipped() {
			listener.notifySkipped(textContext);
			Mockito.verify(rl).testSkipped(Matchers.any(ReportEntry.class));
		}
	}

}
