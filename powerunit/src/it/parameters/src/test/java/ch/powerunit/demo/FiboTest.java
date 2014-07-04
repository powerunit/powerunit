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
import java.util.stream.Stream;

import ch.powerunit.Categories;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;

@Categories({ "example", "demo" })
public class FiboTest implements TestSuite {

    @Parameters("{0}->{1} ; Exception = {2}")
    public static Stream<Object[]> getDatas() {
        return Arrays.stream(new Object[][] { { 0, 0, null }, { 1, 1, null },
                { 2, 1, null }, { 3, 2, null }, { 4, 3, null },
                { 10, 55, null }, { -1, -1, IllegalArgumentException.class } });
    }

    @Parameter(0)
    public int x;

    @Parameter(1)
    public int y;

    @Parameter(2)
    public Class<?> expectedException;

    @Test(name = "validate the fib suite - no exception expected")
    public void testFib() {
        assumeThat(expectedException).isNull();
        assertThatFunction(Fibo::fibo, x).is(y);
    }

    @Test(name = "validate the fib suite - exception expected")
    public void testFibException() {
        assumeThat(expectedException).isNotNull();
        assertWhen((p) -> Fibo.fibo(p), x).throwException(
                instanceOf(expectedException));
    }

}
