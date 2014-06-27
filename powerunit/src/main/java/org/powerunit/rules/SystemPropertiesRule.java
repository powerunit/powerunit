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
package org.powerunit.rules;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.powerunit.TestRule;

/**
 * Rule that restore system properties after test.
 * <p>
 * This class is exposed by the
 * {@link org.powerunit.TestSuite#systemPropertiesRule(String...)
 * systemPropertiesRule()} method of the {@link org.powerunit.TestSuite
 * TestSuite} interface. Direct instantiation of this class should be avoided.
 * Other methods are also available in the TestSuite interface to set one
 * property.
 * 
 * @author borettim
 *
 */
public final class SystemPropertiesRule implements ExternalResource {

	private final String propertiesName[];

	private Map<String, String> values;

	/**
	 * Default constructor.
	 * 
	 * @param propertiesName
	 *            the property names
	 * @see org.powerunit.TestSuite#systemPropertiesRule(String...) Alternate
	 *      method to have the new construction in favor of a more DSL language.
	 */
	public SystemPropertiesRule(String... propertiesName) {
		this.propertiesName = propertiesName;
	}

	@Override
	public void before() {
		values = new HashMap<>();
		for (String n : propertiesName) {
			String old = System.getProperty(n);
			values.put(n, old);
		}
	}

	@Override
	public void after() {
		for (String n : propertiesName) {
			String old = values.get(n);
			if (old == null) {
				System.clearProperty(n);
			} else {
				System.setProperty(n, old);
			}
		}
	}

	/**
	 * Set a system property before a test and ensure the correct restore.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param propertyValue
	 *            the property value
	 * @return the test rule to do so
	 */
	public static TestRule setSystemPropertyBeforeTestAndRestoreAfter(
			String propertyName, Supplier<String> propertyValue) {
		return new SystemPropertiesRule(propertyName).around(TestRule
				.before(() -> System.setProperty(propertyName,
						propertyValue.get())));
	}

	/**
	 * Set a system property before a test and ensure the correct restore.
	 * 
	 * @param propertyName
	 *            the property name
	 * @param propertyValue
	 *            the property value
	 * @return the test rule to do so
	 */
	public static TestRule setSystemPropertyBeforeTestAndRestoreAfter(
			String propertyName, String propertyValue) {
		return setSystemPropertyBeforeTestAndRestoreAfter(propertyName,
				() -> propertyValue);
	}

}
