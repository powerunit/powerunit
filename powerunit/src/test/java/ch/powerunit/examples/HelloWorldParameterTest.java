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
package ch.powerunit.examples;

import java.util.Arrays;
import java.util.stream.Stream;

import ch.powerunit.Categories;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;

@Categories({ "example" })
public class HelloWorldParameterTest implements TestSuite {
    @Parameters("Input string is %1$s, subString idx is %2$s, expected result is %3$s")
    public static Stream<Object[]> getDatas() {
        return Arrays.stream(new Object[][] { { "ab", 0, "ab" },
                { "ab", 1, "b" } });
    }

    @Parameter(0)
    public String inputString;

    @Parameter(1)
    public int inputIndex;

    @Parameter(2)
    public String expectedString;

    @Test(name = "Test substring : Input string is %1$s, subString idx is %2$s, expected result is %3$s")
    public void testSubString() {
        assertThat(inputString.substring(inputIndex)).is(expectedString);
    }
}
