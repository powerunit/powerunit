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
package org.powerunit.surefire;

import org.apache.maven.surefire.report.CategorizedReportEntry;
import org.apache.maven.surefire.report.ConsoleLogger;
import org.apache.maven.surefire.report.LegacyPojoStackTraceWriter;
import org.apache.maven.surefire.report.RunListener;
import org.powerunit.TestContext;
import org.powerunit.TestResultListener;

/**
 * @author borettim
 *
 */
public class PowerUnitProviderListener<T> implements TestResultListener<T> {
	private final RunListener rl;
	private final Class<?> underTest;
	private final ConsoleLogger consoleLogger;

	public PowerUnitProviderListener(ConsoleLogger consoleLogger,
			RunListener rl, Class<?> underTest) {
		this.rl = rl;
		this.underTest = underTest;
		this.consoleLogger = consoleLogger;
	}

	@Override
	public void notifyStart(TestContext<T> context) {
		rl.testStarting(new CategorizedReportEntry(
				underTest.getCanonicalName(), context.getFullTestName(),
				context.getTestCategories()));
	}

	@Override
	public void notifySuccess(TestContext<T> context) {
		rl.testSucceeded(new CategorizedReportEntry(underTest
				.getCanonicalName(), context.getFullTestName(), context
				.getTestCategories()));
	}

	@Override
	public void notifyFailure(TestContext<T> context, Throwable cause) {
		rl.testFailed(new CategorizedReportEntry(underTest.getCanonicalName(),
				context.getFullTestName(), context.getTestCategories(),
				new LegacyPojoStackTraceWriter(underTest.getCanonicalName(),
						context.getFullTestName(), cause), null));
	}

	@Override
	public void notifySetStart(String setName, String groups) {
		rl.testSetStarting(new CategorizedReportEntry(underTest
				.getCanonicalName(), setName, groups));
	}

	@Override
	public void notifySetEnd(String setName, String groups) {
		rl.testSetCompleted(new CategorizedReportEntry(underTest
				.getCanonicalName(), setName, groups));
	}

	@Override
	public void notifySkipped(TestContext<T> context) {
		rl.testSkipped(new CategorizedReportEntry(underTest.getCanonicalName(),
				context.getFullTestName(), context.getTestCategories()));
	}

	@Override
	public void notifyError(TestContext<T> context, Throwable cause) {
		rl.testError(new CategorizedReportEntry(underTest.getCanonicalName(),
				context.getFullTestName(), context.getTestCategories(),
				new LegacyPojoStackTraceWriter(underTest.getCanonicalName(),
						context.getFullTestName(), cause), null));
	}
}