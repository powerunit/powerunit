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

public class AssumeThatTests {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static void main(String[] args) {
        AllTests.testNoException("testAssumeThatIsObjectOKWithoutMessage",
                () -> TestSuite.DSL.assumeThat("x").is("x"));

        AllTests.testException("testAssumeThatIsObjectKOWithoutMessage",
                () -> TestSuite.DSL.assumeThat("x").is("xy"),
                "expecting \"xy\" but was \"x\"");

        AllTests.testNoException("testAssumeThatIsObjectOKWithMessage",
                () -> TestSuite.DSL.assumeThat("msg", "x").is("x"));

        AllTests.testException("testAssumeThatIsObjectKOWithMessage",
                () -> TestSuite.DSL.assumeThat("msg", "x").is("xy"),
                "msg\nexpecting \"xy\" but was \"x\"");

        AllTests.testNoException("testAssumeThatIsNotObjectOKWithoutMessage",
                () -> TestSuite.DSL.assumeThat("x").isNot("y"));

        AllTests.testException("testAssumeThatIsNotObjectKOWithoutMessage",
                () -> TestSuite.DSL.assumeThat("x").isNot("x"),
                "expecting not \"x\" but was \"x\"");

        AllTests.testNoException("testAssumeThatIsNotObjectOKWithMessage",
                () -> TestSuite.DSL.assumeThat("msg", "x").isNot("y"));

        AllTests.testException("testAssumeThatIsNotObjectKOWithMessage",
                () -> TestSuite.DSL.assumeThat("msg", "x").isNot("x"),
                "msg\nexpecting not \"x\" but was \"x\"");

        AllTests.testNoException("testAssumeThatIsNullOKWithoutMesage",
                () -> TestSuite.DSL.assumeThat((Object) null).isNull());

        AllTests.testException("testAssumeThatIsNullKOWithoutMesage",
                () -> TestSuite.DSL.assumeThat("x").isNull(),
                "expecting null but was \"x\"");

        AllTests.testNoException("testAssumeThatIsNullOKWithMesage",
                () -> TestSuite.DSL.assumeThat("msg", (Object) null).isNull());

        AllTests.testException("testAssumeThatIsNullKOWithMesage",
                () -> TestSuite.DSL.assumeThat("msg", "x").isNull(),
                "msg\nexpecting null but was \"x\"");

        AllTests.testNoException("testAssumeThatIsNotNullOKWithoutMesage",
                () -> TestSuite.DSL.assumeThat("x").isNotNull());

        AllTests.testException("testAssumeThatIsNotNullKOWithoutMesage",
                () -> TestSuite.DSL.assumeThat((Object) null).isNotNull(),
                "expecting not null but was null");

        AllTests.testNoException("testAssumeThatIsNotNullOKWithMesage",
                () -> TestSuite.DSL.assumeThat("msg", "x").isNotNull());

        AllTests.testException("testAssumeThatIsNotNullKOWithMesage",
                () -> TestSuite.DSL.assumeThat("msg", (Object) null)
                        .isNotNull(), "msg\nexpecting not null but was null");

        //
        AllTests.testNoException("testAssumeThatIsAOKWithoutMesage",
                () -> TestSuite.DSL.assumeThat("x").isA(String.class));

        AllTests.testException("testAssumeThatIsAKOWithoutMesage",
                () -> TestSuite.DSL.assumeThat("x").isA((Class) Integer.class),
                "expecting is an instance of java.lang.Integer but \"x\" is a java.lang.String");

        AllTests.testNoException("testAssumeThatIsAOKWithMesage",
                () -> TestSuite.DSL.assumeThat("msg", "x").isA(String.class));

        AllTests.testException(
                "testAssumeThatIIsAKOWithMesage",
                () -> TestSuite.DSL.assumeThat("msg", "x").isA(
                        (Class) Integer.class),
                "msg\nexpecting is an instance of java.lang.Integer but \"x\" is a java.lang.String");
    }

}
