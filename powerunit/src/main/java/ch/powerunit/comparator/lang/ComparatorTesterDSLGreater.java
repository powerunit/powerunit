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
 * Specify the value that are greater than another one.
 * 
 * @author borettim
 * @since 0.3.0
 *
 * @param <O>
 *            The object of the comparator
 * @param <C>
 *            The comparator undertest
 */
public interface ComparatorTesterDSLGreater<O, C extends Comparator<O>> {
	/**
	 * Specify the values that are greater.
	 * 
	 * @param greaterSamples
	 *            the samples that are all greater than all the ones specified
	 *            before. <b>All the passed samples must be ordered from the
	 *            smaller to the bigger</b>.
	 * @return {@link ComparatorTesterDSLEnd the next step of the DSL}
	 */
	ComparatorTesterDSLEnd<O, C> withGreaterSamples(O... greaterSamples);

	/**
	 * Specify the values that are greater.
	 * 
	 * @param first
	 *            the sample that is greater than all the ones specified before
	 * @return {@link ComparatorTesterDSLEnd the next step of the DSL}
	 */
	ComparatorTesterDSLEnd<O, C> withGreaterSamples(O first);

	/**
	 * Specify the values that are greater.
	 * 
	 * @param first
	 *            the sample that is greater than all the ones specified before
	 * @param second
	 *            the sample that is greater that the previous
	 * @return {@link ComparatorTesterDSLEnd the next step of the DSL}
	 */
	ComparatorTesterDSLEnd<O, C> withGreaterSamples(O first, O second);

	/**
	 * Specify the values that are greater.
	 * 
	 * @param first
	 *            the sample that is greater than all the ones specified before
	 * @param second
	 *            the sample that is greater that the previous
	 * @param third
	 *            the sample that is greater that the previous
	 * @return {@link ComparatorTesterDSLEnd the next step of the DSL}
	 */
	ComparatorTesterDSLEnd<O, C> withGreaterSamples(O first, O second, O third);

	/**
	 * Specify the values that are greater.
	 * 
	 * @param first
	 *            the sample that is greater than all the ones specified before
	 * @param second
	 *            the sample that is greater that the previous
	 * @param third
	 *            the sample that is greater that the previous
	 * @param fourth
	 *            the sample that is greater that the previous
	 * @return {@link ComparatorTesterDSLEnd the next step of the DSL}
	 */
	ComparatorTesterDSLEnd<O, C> withGreaterSamples(O first, O second, O third,
			O fourth);
}
