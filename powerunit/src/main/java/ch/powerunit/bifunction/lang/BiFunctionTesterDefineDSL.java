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

import org.hamcrest.Matcher;

/**
 * Specify the expected result.
 * 
 * @author borettim
 * @since 0.3.0
 */
public interface BiFunctionTesterDefineDSL<T, U, R> {
	/**
	 * Specify the expected result.
	 * 
	 * @param result
	 *            the expected value.
	 * @return the {@link BiFunctionTesterNextDSL next step of the DSL}
	 */
	BiFunctionTesterNextDSL<T, U, R> thenExpectingResult(R result);

	/**
	 * Specify the expected result.
	 * 
	 * @param result
	 *            a supplier for the expected value
	 * @return the {@link BiFunctionTesterNextDSL next step of the DSL}
	 */
	BiFunctionTesterNextDSL<T, U, R> thenExpectingResult(Supplier<R> result);

	/**
	 * Specify the expected result. Specify the expected result.
	 * 
	 * @param matching
	 *            a matcher on the expected value.
	 * @return the {@link BiFunctionTesterNextDSL next step of the DSL}
	 */
	BiFunctionTesterNextDSL<T, U, R> thenExpectingResultThat(
			Matcher<? super R> matching);

	/**
	 * Specify the expected result.
	 * 
	 * @param matching
	 *            a supplier for the matcher on the expected value
	 * @return the {@link BiFunctionTesterNextDSL next step of the DSL}
	 */
	BiFunctionTesterNextDSL<T, U, R> thenExpectingResultThat(
			Supplier<Matcher<? super R>> matching);
}
