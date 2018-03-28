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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.surefire.booter.ProviderParameterNames;
import org.apache.maven.surefire.util.ScannerFilter;

import ch.powerunit.Test;
import ch.powerunit.TestDelegate;

/**
 * @author borettim
 *
 */
public class PowerUnitProviderScannerFilter implements ScannerFilter {

	private final Set<String> sgroups;

	private final Set<String> sexcludedGroups;

	public PowerUnitProviderScannerFilter(Map<String, String> parameters) {
		String groups = parameters.getOrDefault(ProviderParameterNames.TESTNG_GROUPS_PROP, "");
		String excludedGroups = parameters.getOrDefault(ProviderParameterNames.TESTNG_EXCLUDEDGROUPS_PROP, "");
		Set<String> sgroups = new HashSet<String>();
		Set<String> sexcludedGroups = new HashSet<String>();
		for (String g : groups.split(",")) {
			sgroups.add(g);
		}
		for (String g : excludedGroups.split(",")) {
			sexcludedGroups.add(g);
		}
		this.sgroups = Collections.unmodifiableSet(sgroups);
		this.sexcludedGroups = Collections.unmodifiableSet(sexcludedGroups);
	}

	@Override
	public boolean accept(@SuppressWarnings("rawtypes") Class testClass) {
		for (Method m : testClass.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Test.class)) {
				return true;
			}
		}
		for (Field f : testClass.getDeclaredFields()) {
			if (f.isAnnotationPresent(TestDelegate.class)) {
				return true;
			}
		}
		return false;
	}
}
