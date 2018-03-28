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
package ch.powerunit.surefire;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.maven.surefire.providerapi.AbstractProvider;
import org.apache.maven.surefire.providerapi.ProviderParameters;
import org.apache.maven.surefire.report.ConsoleStream;
import org.apache.maven.surefire.report.ReporterException;
import org.apache.maven.surefire.report.ReporterFactory;
import org.apache.maven.surefire.report.RunListener;
import org.apache.maven.surefire.suite.RunResult;
import org.apache.maven.surefire.testset.TestSetFailedException;
import org.apache.maven.surefire.util.RunOrderCalculator;
import org.apache.maven.surefire.util.ScanResult;
import org.apache.maven.surefire.util.TestsToRun;

import ch.powerunit.TestResultListener;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;

/**
 * @author borettim
 *
 */
public class PowerUnitProvider<T> extends AbstractProvider {

	private final ClassLoader testClassLoader;

	private final ProviderParameters providerParameters;

	private final RunOrderCalculator runOrderCalculator;

	private final ScanResult scanResult;

	private final Map<String, String> parameters;

	public PowerUnitProvider(ProviderParameters providerParameters) {
		this.providerParameters = providerParameters;
		this.testClassLoader = providerParameters.getTestClassLoader();
		this.scanResult = providerParameters.getScanResult();
		this.runOrderCalculator = providerParameters.getRunOrderCalculator();
		this.parameters = providerParameters.getProviderProperties();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.maven.surefire.providerapi.SurefireProvider#getSuites()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Iterable<Class<?>> getSuites() {
		TestsToRun scanned = scanResult.applyFilter(new PowerUnitProviderScannerFilter(parameters), testClassLoader);
		return runOrderCalculator.orderTestClasses(scanned);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.maven.surefire.providerapi.SurefireProvider#invoke(java.lang
	 * .Object)
	 */
	@Override
	public RunResult invoke(Object forkTestSet)
			throws TestSetFailedException, ReporterException, InvocationTargetException {
		if (forkTestSet == null) {
			RunResult r = RunResult.noTestsRun();
			@SuppressWarnings("rawtypes")
			Iterable<Class<?>> i = getSuites();
			for (Class<?> c : i) {
				r = r.aggregate(invoke(c));
			}
			return r;
		}
		ReporterFactory reporterFactory = providerParameters.getReporterFactory();

		ConsoleStream consoleLogger = providerParameters.getConsoleLogger();
		RunListener rl = reporterFactory.createReporter();

		if (!(forkTestSet instanceof Class)) {
			throw new TestSetFailedException("Unexpected error. Received parameter is not a class");
		}
		@SuppressWarnings("unchecked")
		Class<T> underTest = (Class<T>) forkTestSet;
		TestResultListener<T> listener = new PowerUnitProviderListener<>(consoleLogger, rl, underTest);

		DefaultPowerUnitRunnerImpl<T> runner = new DefaultPowerUnitRunnerImpl<>(underTest);
		runner.addListener(listener);
		runner.run();
		return reporterFactory.close();

	}
}
