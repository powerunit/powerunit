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
package ch.powerunit.comparator.impl;

import java.util.Comparator;
import java.util.function.Supplier;

import org.hamcrest.internal.ReflectiveTypeFinder;

/**
 * @author borettim
 * @since 0.3.0
 */
public class SampleProvider<O, C extends Comparator<O>> {

	private static final ReflectiveTypeFinder TARGET_FINDER = new ReflectiveTypeFinder(
			"compare", 2, 0);

	private final Class<O> targetClass;

	private O[] less;

	private O[] equal;

	private O[] greater;

	@SuppressWarnings("unchecked")
	public SampleProvider(Class<C> clazzUnderTest,
			Supplier<C> comparatorSupplier) {
		this.targetClass = (Class<O>) TARGET_FINDER
				.findExpectedType(clazzUnderTest);
	}

	@SuppressWarnings("unchecked")
	private void computeSample() {
		if (Integer.class.equals(targetClass)) {
			less = (O[]) new Integer[] { -3, -2, -1 };
			equal = (O[]) new Integer[] { 0 };
			greater = (O[]) new Integer[] { 1, 2, 3 };
		} else if (Short.class.equals(targetClass)) {
			less = (O[]) new Short[] { -3, -2, -1 };
			equal = (O[]) new Short[] { 0 };
			greater = (O[]) new Short[] { 1, 2, 3 };
		} else if (Byte.class.equals(targetClass)) {
			less = (O[]) new Byte[] { 'a', 'b', 'c' };
			equal = (O[]) new Byte[] { 'd' };
			greater = (O[]) new Byte[] { 'e', 'e', 'f' };
		} else if (Long.class.equals(targetClass)) {
			less = (O[]) new Long[] { -3l, -2l, -1l };
			equal = (O[]) new Long[] { 0l };
			greater = (O[]) new Long[] { 1l, 2l, 3l };
		} else if (Character.class.equals(targetClass)) {
			less = (O[]) new Character[] { 'a', 'b', 'c' };
			equal = (O[]) new Character[] { 'd' };
			greater = (O[]) new Character[] { 'e', 'e', 'f' };
		} else if (Float.class.equals(targetClass)) {
			less = (O[]) new Float[] { -3f, -2f, -1f };
			equal = (O[]) new Float[] { 0f };
			greater = (O[]) new Float[] { 1f, 2f, 3f };
		} else if (Double.class.equals(targetClass)) {
			less = (O[]) new Double[] { -3d, -2d, -1d };
			equal = (O[]) new Double[] { 0d };
			greater = (O[]) new Double[] { 1d, 2d, 3d };
		} else {
			throw new IllegalArgumentException(
					"No way to create sample for the class "
							+ targetClass
							+ " please use the method withLessSamples and the following to provide samples");
		}
	}

	public O[] getLessSamples() {
		if (less == null) {
			computeSample();
		}
		return less;
	}

	public O[] getEqualSamples() {
		if (less == null) {
			computeSample();
		}
		return equal;
	}

	public O[] getGreaterSamples() {
		if (less == null) {
			computeSample();
		}
		return greater;
	}
}
