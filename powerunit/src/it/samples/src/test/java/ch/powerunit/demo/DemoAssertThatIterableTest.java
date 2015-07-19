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
package ch.powerunit.demo;

import java.util.Arrays;
import java.util.Collection;

import ch.powerunit.Test;
import ch.powerunit.TestRule;
import ch.powerunit.TestSuite;

public class DemoAssertThatIterableTest implements TestSuite {

	private Collection<Integer> myCollection = Arrays.asList(1, 3, 5);

	@Test
	public void testSize() {
		assertThatIterable(myCollection).hasSize(3);
	}

	@Test
	public void testNotSize() {
		assertThatIterable(myCollection).hasNotSize(4);
	}

	@Test
	public void testContains() {
		assertThatIterable(myCollection).contains(1, 3, 5);
	}

	@Test
	public void testContainsMatching() {
		assertThatIterable(myCollection).containsMatching(equalTo(1),
				equalTo(3), equalTo(5));
	}

	@Test
	public void testContainsInAnyOrder() {
		assertThatIterable(myCollection).containsInAnyOrder(5, 3, 1);
	}

	@Test
	public void testContainsInAnyOrderMatching() {
		assertThatIterable(myCollection).containsInAnyOrderMatching(equalTo(5),
				equalTo(3), equalTo(1));
	}
}