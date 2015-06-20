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
package ch.powerunit.function.lang;

import java.util.function.Supplier;

/**
 * Optionnally specify a name for the test case.
 * 
 * @author borettim
 * @since 0.3.0
 */
public interface FunctionTesterNextDSL<T, R> extends
		FunctionTesterEndDSL<T, R>, FunctionTesterStartDSL<T, R> {
	/**
	 * Optionnally specify a name for the test case.
	 * 
	 * @param name
	 *            the name to used for the test case.
	 * @return the {@link FunctionTesterEndDSL next step of the DSL}
	 */
	FunctionTesterEndDSL<T, R> testNamed(String name);

	/**
	 * Optionnally specify a name for the test case.
	 * 
	 * @param name
	 *            the supplier to be used for the name for the test case
	 * @return the {@link FunctionTesterEndDSL next step of the DSL}
	 */
	FunctionTesterEndDSL<T, R> testNamed(Supplier<String> name);
}
