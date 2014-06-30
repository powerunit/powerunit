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
package ch.powerunit.test.core;

import ch.powerunit.TestSuite;

public class AssertThatTests {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		AllTests.testNoException("testAssertThatIsObjectOKWithoutMessage",
				() -> TestSuite.DSL.assertThat(1).is(1));

		AllTests.testException("testAssertThatIsObjectKOWithoutMessage",
				() -> TestSuite.DSL.assertThat("x").is("xy"),
				"expecting \"xy\" but was \"x\"");

		AllTests.testNoException("testAssertThatIsObjectOKWithMessage",
				() -> TestSuite.DSL.assertThat("msg", "x").is("x"));

		AllTests.testException("testAssertThatIsObjectKOWithMessage",
				() -> TestSuite.DSL.assertThat("msg", "x").is("xy"),
				"msg\nexpecting \"xy\" but was \"x\"");

		AllTests.testNoException("testAssertThatIsNotObjectOKWithoutMessage",
				() -> TestSuite.DSL.assertThat("x").isNot("y"));

		AllTests.testException("testAssertThatIsNotObjectKOWithoutMessage",
				() -> TestSuite.DSL.assertThat("x").isNot("x"),
				"expecting not \"x\" but was \"x\"");

		AllTests.testNoException("testAssertThatIsNotObjectOKWithMessage",
				() -> TestSuite.DSL.assertThat("msg", "x").isNot("y"));

		AllTests.testException("testAssertThatIsNotObjectKOWithMessage",
				() -> TestSuite.DSL.assertThat("msg", "x").isNot("x"),
				"msg\nexpecting not \"x\" but was \"x\"");

		AllTests.testNoException("testAssertThatIsNullOKWithoutMesage",
				() -> TestSuite.DSL.assertThat((Object) null).isNull());

		AllTests.testException("testAssertThatIsNullKOWithoutMesage",
				() -> TestSuite.DSL.assertThat("x").isNull(),
				"expecting null but was \"x\"");

		AllTests.testNoException("testAssertThatIsNullOKWithMesage",
				() -> TestSuite.DSL.assertThat("msg", (Object) null).isNull());

		AllTests.testException("testAssertThatIsNullKOWithMesage",
				() -> TestSuite.DSL.assertThat("msg", "x").isNull(),
				"msg\nexpecting null but was \"x\"");

		AllTests.testNoException("testAssertThatIsNotNullOKWithoutMesage",
				() -> TestSuite.DSL.assertThat("x").isNotNull());

		AllTests.testException("testAssertThatIsNotNullKOWithoutMesage",
				() -> TestSuite.DSL.assertThat((Object) null).isNotNull(),
				"expecting not null but was null");

		AllTests.testNoException("testAssertThatIsNotNullOKWithMesage",
				() -> TestSuite.DSL.assertThat("msg", "x").isNotNull());

		AllTests.testException("testAssertThatIsNotNullKOWithMesage",
				() -> TestSuite.DSL.assertThat("msg", (Object) null)
						.isNotNull(), "msg\nexpecting not null but was null");

		AllTests.testNoException("testAssertThatIsAOKWithoutMesage",
				() -> TestSuite.DSL.assertThat("x").isA(String.class));

		AllTests.testException("testAssertThatIsAKOWithoutMesage",
				() -> TestSuite.DSL.assertThat("x").isA((Class) Integer.class),
				"expecting is an instance of java.lang.Integer but \"x\" is a java.lang.String");

		AllTests.testNoException("testAssertThatIsAOKWithMesage",
				() -> TestSuite.DSL.assertThat("msg", "x").isA(String.class));

		AllTests.testException(
				"testAssertThatIIsAKOWithMesage",
				() -> TestSuite.DSL.assertThat("msg", "x").isA(
						(Class) Integer.class),
				"msg\nexpecting is an instance of java.lang.Integer but \"x\" is a java.lang.String");

		AllTests.testNoException("testAssertThatOnStringContainsOKWithMessage",
				() -> TestSuite.DSL.assertThat("abc").containsString("b"));

		AllTests.testNoException("testAssertThatOnStringPrefixOKWithMessage",
				() -> TestSuite.DSL.assertThat("abc").startsWith("a"));

		AllTests.testNoException(
				"testAssertThatOnStringEndPrefixOKWithMessage",
				() -> TestSuite.DSL.assertThat("abc").endsWith("c"));
	}

}
