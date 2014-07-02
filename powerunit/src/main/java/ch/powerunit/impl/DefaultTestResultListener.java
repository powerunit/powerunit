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
package ch.powerunit.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import ch.powerunit.TestContext;
import ch.powerunit.TestResultListener;
import ch.powerunit.report.Error;
import ch.powerunit.report.Failure;
import ch.powerunit.report.Testcase;
import ch.powerunit.report.Testsuite;
import ch.powerunit.report.Testsuites;

/**
 * @author borettim
 *
 */
public class DefaultTestResultListener<T> implements TestResultListener<T> {

	public DefaultTestResultListener(String targetFolder) {
		this.targetFolder = new File(targetFolder);
		this.targetFolder.mkdirs();
	}

	private static final JAXBContext JAXB_CONTEXT;

	static {
		try {
			JAXB_CONTEXT = JAXBContext.newInstance(Testsuites.class);
		} catch (JAXBException e) {
			throw new IllegalArgumentException("Unable to setup jaxb "
					+ e.getMessage(), e);
		}
	}

	private boolean error = false;

	private Map<String, Testsuite> result = new HashMap<>();

	private Map<String, Testsuites> results = new HashMap<>();

	private Map<String, Map<String, Testcase>> resultcase = new HashMap<>();

	private File targetFolder;

	@Override
	public void notifySetStart(String setName, String parameters) {
		Testsuites tss = new Testsuites();
		tss.setDisabled(0);
		tss.setErrors(0);
		tss.setFailures(0);
		tss.setName(setName);
		tss.setTests(0);
		tss.setTime(0l);
		results.put(setName, tss);

		Testsuite ts = new Testsuite();
		result.put(setName, ts);
		ts.setName(setName.replace('$', '.'));
		ts.setDisabled(0);
		ts.setErrors(0);
		ts.setFailures(0);
		ts.setTests(0);
		ts.setTime(0l);
		resultcase.put(setName, new HashMap<>());

	}

	@Override
	public void notifySetEnd(String setName, String parameters) {
		try {
			File target = new File(targetFolder, setName + ".xml");
			Object o = results.get(setName);
			if (results.get(setName).getTestsuite().size() == 0) {
				o = result.get(setName);
			}
			JAXB_CONTEXT.createMarshaller().marshal(o, target);
		} catch (JAXBException e) {
			throw new IllegalArgumentException("Unable to setup jaxb "
					+ e.getMessage(), e);
		}
	}

	@Override
	public void notifyStart(TestContext<T> context) {
		String setName = context.getSetName()
				+ (context.getParameterName() == null ? "" : context
						.getParameterName());
		Testsuite ts = result.get(setName);
		Testcase tc = new Testcase();
		tc.setName(context.getLocalTestName()
				+ (context.getParameterName() == null ? "" : ("["
						+ context.getParameterName() + "]")));
		tc.setClassname(context.getTestSuiteObject().getClass()
				.getCanonicalName());
		resultcase.get(setName).put(context.getFullTestName(), tc);
		ts.getTestcase().add(tc);
		tc.setTime(System.currentTimeMillis());

	}

	@Override
	public void notifySuccess(TestContext<T> context) {
		String setName = context.getSetName()
				+ (context.getParameterName() == null ? "" : context
						.getParameterName());
		long end = System.currentTimeMillis();
		Testsuite ts = result.get(setName);
		Testcase tc = resultcase.get(setName).get(context.getFullTestName());
		tc.setTime((end - tc.getTime()) / 1000);
		ts.setTime(ts.getTime() + tc.getTime());
		ts.setTests(ts.getTests() + 1);
	}

	@Override
	public void notifyFailure(TestContext<T> context, Throwable cause) {
		String setName = context.getSetName()
				+ (context.getParameterName() == null ? "" : context
						.getParameterName());
		long end = System.currentTimeMillis();
		Testsuite ts = result.get(setName);
		Testcase tc = resultcase.get(setName).get(context.getFullTestName());
		tc.setTime((end - tc.getTime()) / 1000);
		ts.setTime(ts.getTime() + tc.getTime());
		error = true;
		ts.setFailures(ts.getFailures() + 1);

		Failure f = new Failure();
		tc.getFailure().add(f);
		f.setType(cause.getClass().getCanonicalName());
		f.setMessage(cause.getMessage());
		StringBuffer stack = new StringBuffer(cause.getMessage()).append('\n');
		for (StackTraceElement ste : cause.getStackTrace()) {
			stack.append(ste.toString()).append('\n');
		}
		f.setContent(stack.toString());
	}

	@Override
	public void notifySkipped(TestContext<T> context) {
		String setName = context.getSetName()
				+ (context.getParameterName() == null ? "" : context
						.getParameterName());
		long end = System.currentTimeMillis();
		Testsuite ts = result.get(setName);
		Testcase tc = resultcase.get(setName).get(context.getFullTestName());
		tc.setTime((end - tc.getTime()) / 1000);
		ts.setTime(ts.getTime() + tc.getTime());
		ts.setDisabled(ts.getDisabled() + 1);

		tc.setSkipped("Skipped");
	}

	@Override
	public void notifyError(TestContext<T> context, Throwable cause) {
		String setName = context.getSetName()
				+ (context.getParameterName() == null ? "" : context
						.getParameterName());
		long end = System.currentTimeMillis();
		Testsuite ts = result.get(setName);
		Testcase tc = resultcase.get(setName).get(context.getFullTestName());
		tc.setTime((end - tc.getTime()) / 1000);
		ts.setTime(ts.getTime() + tc.getTime());
		error = true;
		ts.setErrors(ts.getErrors() + 1);

		Error e = new Error();
		tc.getError().add(e);
		e.setType(cause.getClass().getCanonicalName());
		e.setMessage(cause.getMessage());
		StringBuffer stack = new StringBuffer(cause.getMessage()).append('\n');
		for (StackTraceElement ste : cause.getStackTrace()) {
			stack.append(ste.toString()).append('\n');
		}
		e.setContent(stack.toString());
	}

	@Override
	public void notifyParameterStart(String setName, String parameterName) {
		Testsuites tss = results.get(setName);
		Testsuite ts = new Testsuite();
		result.put(setName + parameterName, ts);
		ts.setName(setName + "[" + parameterName + "]");
		ts.setDisabled(0);
		ts.setErrors(0);
		ts.setFailures(0);
		ts.setTests(0);
		ts.setTime(0l);
		resultcase.put(setName + parameterName, new HashMap<>());
		tss.getTestsuite().add(ts);
	}

	@Override
	public void notifyParameterEnd(String setName, String parameterName) {
		Testsuites tss = results.get(setName);
		Testsuite ts = result.get(setName + parameterName);
		tss.setDisabled(tss.getDisabled() + ts.getDisabled());
		tss.setErrors(tss.getErrors() + ts.getErrors());
		tss.setFailures(tss.getFailures() + ts.getFailures());
		tss.setTests(tss.getTests() + ts.getTests());
		tss.setTime(tss.getTime() + ts.getTime());
	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

}