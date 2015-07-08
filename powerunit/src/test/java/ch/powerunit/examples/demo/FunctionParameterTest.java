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
package ch.powerunit.examples.demo;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

import ch.powerunit.Categories;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;

@Categories({ "example", "demo" })
public class FunctionParameterTest<T, R> implements TestSuite {

    @Parameters("%1$s on %2$s expecting %3$s")
    public static Stream<Object[]> getDatas() {
        return Arrays.stream(new Object[][] { {
                (Function<String, Integer>) Integer::valueOf, "1", 1 } });
    }

    @Parameter(0)
    public Function<T, R> function;

    @Parameter(1)
    public T input;

    @Parameter(2)
    public R expected;

    @Test
    public void testAFunction() {
        assertThatFunction(function, input).is(expected);
    }
}
