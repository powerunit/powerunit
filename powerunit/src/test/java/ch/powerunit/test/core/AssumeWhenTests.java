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

import org.hamcrest.Matchers;

import ch.powerunit.TestSuite;

public class AssumeWhenTests {
    public static void main(String[] args) {
        AllTests.testNoException(
                "testAssumeWhenOKWithoutMessage",
                () -> TestSuite.DSL.assumeWhen((p) -> {
                    throw new Exception("test");
                }).throwException(
                        Matchers.hasProperty("message",
                                Matchers.equalTo("test"))));

        AllTests.testException(
                "testAssumeWhenKONoExceptionWithoutMessage",
                () -> TestSuite.DSL.assumeWhen((p) -> {
                }).throwException(
                        Matchers.hasProperty("message",
                                Matchers.equalTo("test"))),
                "An exception was expected, but none was thrown");

        AllTests.testException(
                "testAssumeWhenKOExceptionWithoutMessage",
                () -> TestSuite.DSL.assumeWhen((p) -> {
                    throw new Exception("test");
                }).throwException(
                        Matchers.hasProperty("message",
                                Matchers.equalTo("other"))),
                "expecting hasProperty(\"message\", \"other\") but property 'message' was \"test\"");

        AllTests.testNoException(
                "testAssumeWhenOKWithMessage",
                () -> TestSuite.DSL.assumeWhen("msg", (p) -> {
                    throw new Exception("test");
                }).throwException(
                        Matchers.hasProperty("message",
                                Matchers.equalTo("test"))));

        AllTests.testException(
                "testAssumeWhenKONoExceptionWithMessage",
                () -> TestSuite.DSL.assumeWhen("msg", (p) -> {
                }).throwException(
                        Matchers.hasProperty("message",
                                Matchers.equalTo("test"))),
                "msg\nAn exception was expected, but none was thrown");

        AllTests.testException(
                "testAssumeWhenKOExceptionWithMessage",
                () -> TestSuite.DSL.assumeWhen("msg", (p) -> {
                    throw new Exception("test");
                }).throwException(
                        Matchers.hasProperty("message",
                                Matchers.equalTo("other"))),
                "msg\nexpecting hasProperty(\"message\", \"other\") but property 'message' was \"test\"");
    }

}
