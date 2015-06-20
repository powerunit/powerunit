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
package ch.powerunit.function.impl;

import java.util.function.Supplier;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/**
 * @author borettim
 * @since 0.3.0
 */
public class SupplierEqualsToMatcher<T> extends BaseMatcher<T> {

	private final Supplier<T> supplier;

	private Matcher<T> cached;

	private Matcher<T> getCached() {
		if (cached == null) {
			cached = Matchers.equalTo(supplier.get());
		}
		return cached;
	}

	/**
	 * @param supplier
	 */
	public SupplierEqualsToMatcher(Supplier<T> supplier) {
		this.supplier = supplier;
	}

	@Override
	public boolean matches(Object item) {
		return getCached().matches(item);
	}

	@Override
	public void describeTo(Description description) {
		getCached().describeTo(description);
	}

	@Override
	public void describeMismatch(Object item, Description description) {
		getCached().describeMismatch(item, description);
	}

	@Override
	public String toString() {
		return getCached().toString();
	}

}
