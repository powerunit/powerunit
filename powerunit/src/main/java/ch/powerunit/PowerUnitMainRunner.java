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
package ch.powerunit;

import java.io.PrintStream;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import ch.powerunit.impl.PowerUnit;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;
import ch.powerunit.impl.DefaultTestResultListener;

/**
 * Main class that can be used to run test.
 * 
 * @author borettim
 * @since 0.1.0
 * @see #main(String[]) The main method to have the description of the
 *      parameters.
 */
public class PowerUnitMainRunner {

	private PowerUnitMainRunner() {
	}

	public static final PrintStream DEFAULT_OUT = System.out;

	/**
	 * The main method.
	 * 
	 * @param args
	 *            The argument syntax is the following :
	 *            <ul>
	 *            <li><code>path className[:methodName],...</code></li>
	 *            </ul>
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			DEFAULT_OUT.printf("The received argument is not valid : %1$s%n",
					Arrays.toString(args));
			System.exit(-1);// NOSONAR
		}
		PowerUnit mbean = new PowerUnit();
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
		ObjectName name;
		try {
			name = new ObjectName("ch.powerunit.jmx:type=PowerUnit");
			mbs.registerMBean(mbean, name);
		} catch (MalformedObjectNameException | InstanceAlreadyExistsException
				| MBeanRegistrationException | NotCompliantMBeanException e1) {
			e1.printStackTrace();
		}
		String outputPath = args[0];
		String classes[] = args[1].split("\\s*,\\s*");
		StringBuilder resumedSucess = new StringBuilder();
		StringBuilder resumedFailure = new StringBuilder();
		StringBuilder resumedSkipped = new StringBuilder();

		boolean success = true;
		for (String c : classes) {
			DEFAULT_OUT.printf("Running test for %1$s%n", c);
			try {
				PowerUnitRunner r = null;
				if (c.contains(":")) {
					String param[] = c.split("\\s*:\\s*");
					Class<?> cls = Class.forName(param[0]);
					Method m = cls.getMethod(param[1]);
					r = new DefaultPowerUnitRunnerImpl(cls, m);
				} else {
					Class<?> cls = Class.forName(c);
					r = new DefaultPowerUnitRunnerImpl(cls);
				}
				DefaultTestResultListener def = new DefaultTestResultListener(
						outputPath, DEFAULT_OUT, mbean);
				r.addListener(def);
				r.run();// NOSONAR
				success &= !def.isError();
				resumedSucess.append(def.getResumedSucess());
				resumedFailure.append(def.getResumedFailure());
				resumedSkipped.append(def.getResumedSkipped());
			} catch (ClassNotFoundException e) {
				DEFAULT_OUT.printf("Unable to create the class %1$s%n", c);
			} catch (NoSuchMethodException e) {
				DEFAULT_OUT.printf(
						"Unable to create the find the method %1$s%n", c);
			} catch (SecurityException e) {
				DEFAULT_OUT
						.printf("Unable to create the test because of security issue %1$s%n",
								c);
			} finally {
				DEFAULT_OUT.printf("End running test for %1$s%n", c);
			}
		}
		DEFAULT_OUT.print("\n\nSuccess tests:\n" + resumedSucess.toString()
				+ "\n\nSkipped tests:\n" + resumedSkipped.toString()
				+ "\n\nFailed tests:\n" + resumedFailure.toString() + "\n");
		if (!success) {
			System.exit(-1);// NOSONAR
		}
		System.exit(0);// NOSONAR
	}

}
