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
import java.util.function.Function;
import java.util.stream.Stream;

import ch.powerunit.Categories;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;

public class FunctionParameterTest implements TestSuite {

    @Parameters("%1$s expecting %2$s")
    public static Stream<Object[]> getDatas() {
        return Arrays.stream(new Object[][] { { "1", 1 }, { "2", 2} });
    }

    @Parameter(0)
    public String input;

    @Parameter(1)
    public Integer expected;

    @Test
    public void testAFunction() {
        assertThatFunction(Integer::valueOf, input).is(expected);
    }
}