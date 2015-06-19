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

/**
 * First step of the DSL - Set the instance under test if necessary.
 * 
 * @author borettim
 * @since 0.3.0
 * 
 * @param <O>
 *            The object of the comparator
 * @param <C>
 *            The comparator undertest
 */
public interface ComparatorTesterDSLStart<O, C extends Comparator<O>> extends
		ComparatorTesterDSLLess<O, C> {
	/**
	 * This method may be use to specify the instance to be used for the test of
	 * the comparator.
	 * <p>
	 * When not used, the framework try to create itself an instance of the
	 * comparator.
	 * 
	 * @param instance
	 *            The instance to be used for the test.
	 * @return {@link ComparatorTesterDSLEnd the continuation of the DSL}
	 * @throws NullPointerException
	 *             when instance is null.
	 */
	ComparatorTesterDSLLess<O, C> usingInstance(C instance);
}
