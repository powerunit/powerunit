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
import java.util.function.BiFunction;
import java.util.stream.Stream;

import ch.powerunit.Categories;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;

@Categories({ "example", "demo" })
public class FiboTestUsingFiltering implements TestSuite {

    @Parameters()
    public static Stream<Object[]> getDatas() {
        return Arrays
                .stream(new Object[][] { { 0, 0, null }, { 1, 1, null },
                        { 2, 1, null }, { 3, 2, null }, { 4, 3, null },
                        { 10, 55, null },
                        { -1, -1, IllegalArgumentException.class } })
                .map(TestSuite.DSL
                        .<BiFunction<String, Object[], Boolean>> addFieldToEachEntry(FiboTestUsingFiltering::validateTestMethod));
    }

    private static boolean validateTestMethod(String name, Object parameters[]) {
        if (parameters[2] == null) {
            return "testFib".equals(name);
        }
        return "testFibException".equals(name);
    }

    @Parameter(0)
    public int x;

    @Parameter(1)
    public int y;

    @Parameter(2)
    public Class<?> expectedException;

    @Parameter(value = 3, filter = true)
    public BiFunction<String, Object[], Boolean> filter;

    @Test(name = "validate the fib suite : {0}->{1}")
    public void testFib() {
        assertThatFunction(Fibo::fibo, x).is(y);
    }

    @Test(name = "Validate exception is {2}")
    public void testFibException() {
        assertWhen((p) -> Fibo.fibo(p), x).throwException(
                instanceOf(expectedException));
    }

}
