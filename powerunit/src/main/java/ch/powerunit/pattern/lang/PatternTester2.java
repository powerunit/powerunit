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
package ch.powerunit.pattern.lang;

/**
 * This a step in the process to create a tester of
 * {@link java.util.regex.Pattern Pattern}.
 * 
 * @author borettim
 *
 */
public interface PatternTester2 {
	/**
	 * Specify that the received input String is not accepted by the
	 * {@link java.util.regex.Pattern Pattern} under test.
	 * 
	 * @return {@link PatternTester1 The next step of the DSL.}
	 */
	PatternTester1 thenNoMatching();

	/**
	 * Specify that the received input String is accepted by the
	 * {@link java.util.regex.Pattern Pattern} under test.
	 * 
	 * @return {@link PatternTester3 The next step of the DSL.}
	 */
	PatternTester3 thenMatching();
}
