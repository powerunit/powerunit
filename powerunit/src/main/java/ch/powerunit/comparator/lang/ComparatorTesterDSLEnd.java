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
package ch.powerunit.comparator.lang;

import java.util.Comparator;

import ch.powerunit.comparator.ComparatorTester;

/**
 * End of the DSL for Comparator test framework.
 * 
 * @author borettim
 * @since 0.3.0
 * 
 * @param <O>
 *            The object of the comparator
 * @param <C>
 *            The comparator undertest
 */
public interface ComparatorTesterDSLEnd<O, C extends Comparator<O>> {
	/**
	 * End the creation of the tester.
	 * 
	 * @return {@link ComparatorTester the test framework}. The
	 *         {@link ch.powerunit.TestDelegate annotation TestDelegate}
	 *         describe how to use the result.
	 */
	ComparatorTester<O, C> build();
}
