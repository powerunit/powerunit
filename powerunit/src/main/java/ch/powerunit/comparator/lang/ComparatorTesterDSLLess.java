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
 * Specify the value that are less than another one.
 * 
 * @author borettim
 * @since 0.3.0
 * 
 * @param <O>
 *            The object of the comparator
 * @param <C>
 *            The comparator undertest
 */
public interface ComparatorTesterDSLLess<O, C extends Comparator<O>> extends
		ComparatorTesterDSLEnd<O, C> {
	/**
	 * Specify the values that are lesser.
	 * 
	 * @param lessSamples
	 *            the samples that are all less than all the ones specified
	 *            after. <b>All the passed samples must be ordered from the
	 *            smaller to the bigger</b>.
	 * @return {@link ComparatorTesterDSLEquals the next step of the DSL}
	 */
	ComparatorTesterDSLEquals<O, C> withLessSamples(O... lessSamples);

	/**
	 * Specify the values that are lesser.
	 * 
	 * @param first
	 *            the smaller value of all
	 * @return {@link ComparatorTesterDSLEquals the next step of the DSL}
	 */
	ComparatorTesterDSLEquals<O, C> withLessSamples(O first);

	/**
	 * Specify the values that are lesser.
	 * 
	 * @param first
	 *            the smaller value of all
	 * @param second
	 *            a value bigger than the previous
	 * @return {@link ComparatorTesterDSLEquals the next step of the DSL}
	 */
	ComparatorTesterDSLEquals<O, C> withLessSamples(O first, O second);

	/**
	 * Specify the values that are lesser.
	 * 
	 * @param first
	 *            the smaller value of all
	 * @param second
	 *            a value bigger than the previous
	 * @param third
	 *            a value bigger than the previous
	 * @return {@link ComparatorTesterDSLEquals the next step of the DSL}
	 */
	ComparatorTesterDSLEquals<O, C> withLessSamples(O first, O second, O third);

	/**
	 * Specify the values that are lesser.
	 * 
	 * @param first
	 *            the smaller value of all
	 * @param second
	 *            a value bigger than the previous
	 * @param third
	 *            a value bigger than the previous
	 * @param fourth
	 *            a value bigger than the previous
	 * @return {@link ComparatorTesterDSLEquals the next step of the DSL}
	 */
	ComparatorTesterDSLEquals<O, C> withLessSamples(O first, O second, O third,
			O fourth);
}
