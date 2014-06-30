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
package ch.powerunit;

import org.hamcrest.Matcher;

/**
 * DSL for assertion on exception result.
 * <p>
 * This interface is returned by the various methods
 * {@link TestSuite#assertWhen(Statement) assertWhen} exposed by
 * {@link TestSuite}.
 * 
 * @author borettim
 *
 * @param <T>
 *            The exception type
 */
public interface AssertThatException<T extends Throwable> {

	/**
	 * Define the matcher on the exception and execute the matcher validation.
	 * 
	 * @param matching
	 *            the matcher.
	 */
	void throwException(Matcher<T> matching);
}
