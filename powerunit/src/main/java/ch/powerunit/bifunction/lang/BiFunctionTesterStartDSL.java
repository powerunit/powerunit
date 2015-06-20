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
package ch.powerunit.bifunction.lang;

import java.util.function.Supplier;

/**
 * Specification of an argument for the test.
 * 
 * @author borettim
 * @since 0.3.0
 */
public interface BiFunctionTesterStartDSL<T, U, R> {
	/**
	 * Specify the input argument.
	 * 
	 * @param input1
	 *            the first argument to be passed.
	 * @param input2
	 *            the second argument to be passed.
	 * @return the {@link BiFunctionTesterDefineDSL the next step of the DSL}
	 */
	BiFunctionTesterDefineDSL<T, U, R> passingAsParameter(T input1, U input2);

	/**
	 * Specify the input argument.
	 * 
	 * @param input1
	 *            the first argument to be passed.
	 * @param input2
	 *            the second argument to be passed.
	 * @return the {@link BiFunctionTesterDefineDSL the next step of the DSL}
	 */
	BiFunctionTesterDefineDSL<T, U, R> passingAsParameter(Supplier<T> input1,
			Supplier<U> input2);
}
