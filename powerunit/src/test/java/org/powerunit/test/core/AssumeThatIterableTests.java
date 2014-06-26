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
package org.powerunit.test.core;

import java.util.Collections;

import org.hamcrest.Matchers;
import org.powerunit.TestSuite;

public class AssumeThatIterableTests {
	public static void main(String[] args) {

		AllTests.testNoException(
				"testAssumeThatIsIterableHasSizeOKWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1")).hasSize(1));

		AllTests.testNoException(
				"testAssumeThatIsIterableHasSizeOKWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1")).hasSize(1));

		AllTests.testException(
				"testAssumeThatIsIterableHasSizeKOWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1")).hasSize(2),
				"expecting a collection with size <2> but collection size was <1>");

		AllTests.testException(
				"testAssumeThatIsIterableHasSizeKOWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1")).hasSize(2),
				"msg\nexpecting a collection with size <2> but collection size was <1>");

		AllTests.testNoException(
				"testAssumeThatIsIterableHasNotSizeOKWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1")).hasNotSize(2));

		AllTests.testNoException(
				"testAssumeThatIsIterableHasNotSizeOKWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1")).hasNotSize(2));

		AllTests.testException(
				"testAssumeThatIsIterableHasNotSizeKOWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1")).hasNotSize(1),
				"expecting not a collection with size <1> but was <[1]>");

		AllTests.testException(
				"testAssumeThatIsIterableHasNotSizeKOWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1")).hasNotSize(1),
				"msg\nexpecting not a collection with size <1> but was <[1]>");

		AllTests.testNoException(
				"testAssumeThatIsIterableContainsOKWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1")).contains("1"));

		AllTests.testException(
				"testAssumeThatIsIterableContainsKOWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1")).contains("1", "2"),
				"expecting iterable containing [\"1\", \"2\"] but No item matched: \"2\"");

		AllTests.testNoException(
				"testAssumeThatIsIterableContainsOKWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1")).contains("1"));

		AllTests.testException(
				"testAssumeThatIsIterableContainsKOWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1")).contains("1", "2"),
				"msg\nexpecting iterable containing [\"1\", \"2\"] but No item matched: \"2\"");

		AllTests.testNoException(
				"testAssumeThatIsIterableContainsInAnyOrderOKWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1")).containsInAnyOrder("1"));

		AllTests.testException(
				"testAssumeThatIsIterableContainsInAnyOrderKOWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1"))
						.containsInAnyOrder("1", "2"),
				"expecting iterable over [\"1\", \"2\"] in any order but No item matches: \"2\" in [\"1\"]");

		AllTests.testNoException(
				"testAssumeThatIsIterableContainsInAnyOrderOKWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1")).containsInAnyOrder("1"));

		AllTests.testException(
				"testAssumeThatIsIterableContainsInAnyOrderKOWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1"))
						.containsInAnyOrder("1", "2"),
				"msg\nexpecting iterable over [\"1\", \"2\"] in any order but No item matches: \"2\" in [\"1\"]");

		AllTests.testNoException(
				"testAssumeThatIsIterableContainsInAnyOrderMatcherOKWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1")).containsInAnyOrderMatching(
						Matchers.equalTo("1")));

		AllTests.testException(
				"testAssumeThatIsIterableContainsInAnyOrderMatcherKOWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1")).containsInAnyOrderMatching(
						Matchers.equalTo("1"), Matchers.equalTo("2")),
				"expecting iterable over [\"1\", \"2\"] in any order but No item matches: \"2\" in [\"1\"]");

		AllTests.testNoException(
				"testAssumeThatIsIterableContainsInAnyOrderMatcherOKWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1")).containsInAnyOrderMatching(
						Matchers.equalTo("1")));

		AllTests.testException(
				"testAssumeThatIsIterableContainsInAnyOrderMatcherKOWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1")).containsInAnyOrderMatching(
						Matchers.equalTo("1"), Matchers.equalTo("2")),
				"msg\nexpecting iterable over [\"1\", \"2\"] in any order but No item matches: \"2\" in [\"1\"]");

		AllTests.testNoException(
				"testAssumeThatIsIterableContainsMatcherOKWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1")).containsMatching(
						Matchers.equalTo("1")));

		AllTests.testException(
				"testAssumeThatIsIterableContainsMatcherKOWithoutMessage",
				() -> TestSuite.DSL.assumeThatIterable(
						Collections.singleton("1")).containsMatching(
						Matchers.equalTo("1"), Matchers.equalTo("2")),
				"expecting iterable containing [\"1\", \"2\"] but No item matched: \"2\"");

		AllTests.testNoException(
				"testAssumeThatIsIterableContainsMatcherOKWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1")).containsMatching(
						Matchers.equalTo("1")));

		AllTests.testException(
				"testAssumeThatIsIterableContainsMatcherKOWithMessage",
				() -> TestSuite.DSL.assumeThatIterable("msg",
						Collections.singleton("1")).containsMatching(
						Matchers.equalTo("1"), Matchers.equalTo("2")),
				"msg\nexpecting iterable containing [\"1\", \"2\"] but No item matched: \"2\"");
	}

}
