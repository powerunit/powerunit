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
package ch.powerunit.collector.lang;

import ch.powerunit.collector.CollectorTester;

/**
 * DSL step of the builder of {@link java.util.stream.Collector Collector
 * tester}.
 * 
 * @author borettim
 * @param <T>
 *            the input type of the {@link java.util.stream.Collector Collector}
 *            .
 * @param <A>
 *            the accumulator type of the {@link java.util.stream.Collector
 *            Collector}.
 * @param <R>
 *            the return type of the {@link java.util.stream.Collector
 *            Collector}.
 * @since 0.4.0
 */
public interface CollectorTesterDSL1<T, A, R> extends
		CollectorTesterDSL3<T, A, R> {
	/**
	 * Build the instance of the tester.
	 * 
	 * @return {@link CollectorTester the tester to be used}
	 */
	CollectorTester<T, A, R> build();
}
