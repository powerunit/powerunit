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

import org.mockito.Mock;
import org.mockito.Mockito;

import ch.powerunit.Categories;
import ch.powerunit.Ignore;
import ch.powerunit.Rule;
import ch.powerunit.Test;
import ch.powerunit.TestRule;
import ch.powerunit.TestSuite;

@Categories({ "example", "demo" })
public class MockitoRuleTest implements TestSuite {
    public interface Mockable {
        void run();
    }

    @Mock
    private Mockable mock;

    @Rule
    public final TestRule testRule = mockitoRule()
            .around(before(this::prepare));

    public void prepare() {
        Mockito.doThrow(new RuntimeException("test")).when(mock).run();
    }

    @Test
    public void testException() {
        assertWhen((p) -> mock.run()).throwException(
                instanceOf(RuntimeException.class));
    }

    @Test
    @Ignore
    public void testIgnore() {

    }
}
