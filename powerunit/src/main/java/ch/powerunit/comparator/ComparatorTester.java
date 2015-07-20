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
package ch.powerunit.comparator;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Supplier;

import ch.powerunit.TestInterface;
import ch.powerunit.comparator.impl.ComparatorTesterImpl;
import ch.powerunit.comparator.impl.SampleProvider;
import ch.powerunit.comparator.lang.ComparatorTesterDSLEnd;
import ch.powerunit.comparator.lang.ComparatorTesterDSLEquals;
import ch.powerunit.comparator.lang.ComparatorTesterDSLGreater;
import ch.powerunit.comparator.lang.ComparatorTesterDSLLess;
import ch.powerunit.comparator.lang.ComparatorTesterDSLStart;

/**
 * This is a framework to simplify the testing of {@link Comparator}.
 * 
 * @author borettim
 * @since 0.3.0
 * @param <O>
 *            The object of the comparator
 * @param <C>
 *            The comparator undertest
 */
@TestInterface(ComparatorTesterImpl.class)
public final class ComparatorTester<O, C extends Comparator<O>> {
	private static class ComparatorTesterDSL<O, C extends Comparator<O>>
			implements ComparatorTesterDSLStart<O, C>,
			ComparatorTesterDSLLess<O, C>, ComparatorTesterDSLEquals<O, C>,
			ComparatorTesterDSLGreater<O, C>, ComparatorTesterDSLEnd<O, C> {
		private final Class<C> clazzUnderTest;
		private O lessSamples[];
		private O equalSamples[];
		private O greaterSamples[];
		private C underTest;

		public ComparatorTesterDSL(Class<C> clazzUnderTest) {
			this.clazzUnderTest = clazzUnderTest;
		}

		@Override
		public ComparatorTesterDSLEquals<O, C> withLessSamples(O... lessSamples) {
			this.lessSamples = lessSamples;
			return this;
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLEquals<O, C> withLessSamples(O first) {
			return withLessSamples((O[]) new Object[] { first });
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLEquals<O, C> withLessSamples(O first, O second) {
			return withLessSamples((O[]) new Object[] { first, second });
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLEquals<O, C> withLessSamples(O first,
				O second, O third) {
			return withLessSamples((O[]) new Object[] { first, second, third });
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLEquals<O, C> withLessSamples(O first,
				O second, O third, O fourth) {
			return withLessSamples((O[]) new Object[] { first, second, third,
					fourth });
		}

		@Override
		public ComparatorTesterDSLGreater<O, C> withEqualSamples(
				O... equalSamples) {
			this.equalSamples = equalSamples;
			return this;
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLGreater<O, C> withEqualSamples(O first) {
			return withEqualSamples((O[]) new Object[] { first });
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLGreater<O, C> withEqualSamples(O first,
				O second) {
			return withEqualSamples((O[]) new Object[] { first, second });
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLGreater<O, C> withEqualSamples(O first,
				O second, O third) {
			return withEqualSamples((O[]) new Object[] { first, second, third });
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLGreater<O, C> withEqualSamples(O first,
				O second, O third, O fourth) {
			return withEqualSamples((O[]) new Object[] { first, second, third,
					fourth });
		}

		@Override
		public ComparatorTesterDSLEnd<O, C> withGreaterSamples(
				O... greaterSamples) {
			this.greaterSamples = greaterSamples;
			return this;
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLEnd<O, C> withGreaterSamples(O first) {
			return withGreaterSamples((O[]) new Object[] { first });
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLEnd<O, C> withGreaterSamples(O first, O second) {
			return withGreaterSamples((O[]) new Object[] { first, second });
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLEnd<O, C> withGreaterSamples(O first,
				O second, O third) {
			return withGreaterSamples((O[]) new Object[] { first, second, third });
		}

		@SuppressWarnings("unchecked")
		@Override
		public ComparatorTesterDSLEnd<O, C> withGreaterSamples(O first,
				O second, O third, O fourth) {
			return withGreaterSamples((O[]) new Object[] { first, second,
					third, fourth });
		}

		@Override
		public ComparatorTesterDSLLess<O, C> usingInstance(C instance) {
			Objects.requireNonNull(instance, "instance can't be null");
			this.underTest = instance;
			return this;
		}

		@Override
		public ComparatorTester<O, C> build() {
			Supplier<C> comparatorSupplier;
			if (underTest == null) {
				comparatorSupplier = () -> {
					try {
						return clazzUnderTest.newInstance();
					} catch (InstantiationException | IllegalAccessException e) {
						throw new IllegalArgumentException(
								"Unable to instanciate the class underTest using the default constructor. Use the usingInstance method to provide an instance",
								e);
					}
				};
			} else {
				comparatorSupplier = () -> underTest;
			}
			Supplier<O[]> lessSupplier;
			Supplier<O[]> equalsSupplier;
			Supplier<O[]> greatSupplier;
			if (lessSamples == null) {
				SampleProvider<O, C> providers = new SampleProvider<O, C>(
						clazzUnderTest, comparatorSupplier);
				lessSupplier = providers::getLessSamples;
				equalsSupplier = providers::getEqualSamples;
				greatSupplier = providers::getGreaterSamples;
			} else {
				lessSupplier = () -> lessSamples;
				equalsSupplier = () -> equalSamples;
				greatSupplier = () -> greaterSamples;
			}
			return new ComparatorTester<O, C>(clazzUnderTest, lessSupplier,
					equalsSupplier, greatSupplier, comparatorSupplier);
		}
	}

	private final Class<C> comparatorClass;

	private final Supplier<O[]> lessSamples;

	private final Supplier<O[]> equalSamples;

	private final Supplier<O[]> greaterSamples;

	private final Supplier<C> underTest;

	private ComparatorTester(Class<C> comparatorClass,
			Supplier<O[]> lessSamples, Supplier<O[]> equalSamples,
			Supplier<O[]> greaterSamples, Supplier<C> underTest) {
		this.comparatorClass = comparatorClass;
		this.lessSamples = lessSamples;
		this.equalSamples = equalSamples;
		this.greaterSamples = greaterSamples;
		this.underTest = underTest;
	}

	/**
	 * Use this method to start the DSL to test a comparator.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * &#064;TestDelegate
	 * public final ComparatorTester&lt;Integer, MyComparator&gt; direct = ComparatorTester.of(
	 * 		MyComparator.class).withLessSamples(-6).withEqualSamples(12)
	 * 		.withGreaterSamples(16).build();
	 * </pre>
	 * 
	 * @param clazzUnderTest
	 *            The class of the comparator to be tested.
	 * @return {@link ComparatorTesterDSLStart the following of the DSL}
	 * @throws NullPointerException
	 *             when clazzUnderTest is null
	 * @param <O>
	 *            The object of the comparator
	 * @param <C>
	 *            The comparator undertest
	 * @see ch.powerunit.TestDelegate
	 */
	public static <O, C extends Comparator<O>> ComparatorTesterDSLStart<O, C> of(
			final Class<C> clazzUnderTest) {
		Objects.requireNonNull(clazzUnderTest, "clazzUnderTest can't be null");
		return new ComparatorTesterDSL<O, C>(clazzUnderTest);
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the comparatorClass
	 */
	public Class<C> getComparatorClass() {
		return comparatorClass;
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the lessSamples
	 */
	public Supplier<O[]> getLessSamples() {
		return lessSamples;
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the equalSamples
	 */
	public Supplier<O[]> getEqualSamples() {
		return equalSamples;
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the greaterSamples
	 */
	public Supplier<O[]> getGreaterSamples() {
		return greaterSamples;
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the underTest
	 */
	public Supplier<C> getUnderTest() {
		return underTest;
	}
}
