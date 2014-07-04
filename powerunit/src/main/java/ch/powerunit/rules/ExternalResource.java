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
package ch.powerunit.rules;

import ch.powerunit.TestContext;

/**
 * Default Rule to do some stuff before the test, and after (always).
 * <p>
 * This interface is provided here as a facility for rule provider, which will
 * implement the {@link #before()} and {@link #after()} methods. The idea is to
 * support use case that access some external system which need preparation and
 * cleanup between each test.
 * <p>
 * To use this interface, implementer should :
 * <ul>
 * <li>Implement the method {@link #before()} with the code that prepare the
 * external resource (creation of folder, start of server, etc).</li>
 * <li>Implement the method {@link #after()} with the code that cleanup the
 * external resource (destruction of folder, shutdown of server, etc). This
 * method is used even in case the test is in failure or error.</li>
 * </ul>
 *
 * @author borettim
 */
public interface ExternalResource extends TestListenerRule {

    @Override
    default void onStart(TestContext<Object> context) {
        before();
    }

    @Override
    default void onEnd(TestContext<Object> context) {
        after();
    }

    /**
     * Code to be done before
     */
    default void before() {
        // Do nothing as default
    }

    /**
     * Code to be done after.
     */
    default void after() {
        // Do nothing as default
    }
}
