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
 * Specify the value that are equals than another one.
 * 
 * @author borettim
 * @since 0.3.0
 *
 * @param <O>
 *            The object of the comparator
 * @param <C>
 *            The comparator undertest
 */
public interface ComparatorTesterDSLEquals<O, C extends Comparator<O>> {
	/**
	 * Specify the values that are equals.
	 * 
	 * @param equalSamples
	 *            all values that are equals between them. All values must be
	 *            greater than the one specified before.
	 * 
	 * @return {@link ComparatorTesterDSLGreater the next step of the DSL}
	 */
	ComparatorTesterDSLGreater<O, C> withEqualSamples(O... equalSamples);

	/**
	 * Specify the values that are equals.
	 * 
	 * @param first
	 *            a single value that will be checked against itself. All values
	 *            must be greater than the one specified before.
	 * 
	 * @return {@link ComparatorTesterDSLGreater the next step of the DSL}
	 */
	ComparatorTesterDSLGreater<O, C> withEqualSamples(O first);

	/**
	 * Specify the values that are equals.
	 * 
	 * @param first
	 *            a first equals value. All values must be greater than the one
	 *            specified before.
	 * 
	 * @param second
	 *            a second equals value. All values must be greater than the one
	 *            specified before.
	 * 
	 * @return {@link ComparatorTesterDSLGreater the next step of the DSL}
	 */
	ComparatorTesterDSLGreater<O, C> withEqualSamples(O first, O second);

	/**
	 * Specify the values that are equals.
	 * 
	 * @param first
	 *            a first equals value. All values must be greater than the one
	 *            specified before.
	 * 
	 * @param second
	 *            a second equals value. All values must be greater than the one
	 *            specified before.
	 * 
	 * @param third
	 *            a third equals value. All values must be greater than the one
	 *            specified before.
	 * 
	 * @return {@link ComparatorTesterDSLGreater the next step of the DSL}
	 */
	ComparatorTesterDSLGreater<O, C> withEqualSamples(O first, O second, O third);

	/**
	 * Specify the values that are equals.
	 * 
	 * @param first
	 *            a first equals value. All values must be greater than the one
	 *            specified before.
	 * @param second
	 *            a second equals value. All values must be greater than the one
	 *            specified before.
	 * @param third
	 *            a third equals value. All values must be greater than the one
	 *            specified before.
	 * @param fourth
	 *            a fourth equals value. All values must be greater than the one
	 *            specified before.
	 * @return {@link ComparatorTesterDSLGreater the next step of the DSL}
	 */
	ComparatorTesterDSLGreater<O, C> withEqualSamples(O first, O second,
			O third, O fourth);
}
