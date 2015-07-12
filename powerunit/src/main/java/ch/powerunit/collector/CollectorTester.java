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
package ch.powerunit.collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import java.util.stream.Stream;

import org.hamcrest.Matcher;

import ch.powerunit.TestInterface;
import ch.powerunit.TestSuite;
import ch.powerunit.collector.impl.CollectorTesterImpl;
import ch.powerunit.collector.lang.CollectorTesterDSL0;
import ch.powerunit.collector.lang.CollectorTesterDSL1;
import ch.powerunit.collector.lang.CollectorTesterDSL2;
import ch.powerunit.collector.lang.CollectorTesterDSL3;

/**
 * This is a tester for {@link Collector}.
 * <p>
 * The goal of this tester is to validate a {@link Collector}. The following
 * tests are done :
 * <ol>
 * <li><code>{@link Collector#accumulator() accumulator()}</code> must return a
 * not null result.</li>
 * <li><code>{@link Collector#combiner() combiner()}</code> must return a not
 * null result.</li>
 * <li><code>{@link Collector#finisher() finisher()}</code> must return a not
 * null result.</li>
 * <li><code>{@link Collector#supplier() supplier()}</code> must return a not
 * null result.</li>
 * <li><code>{@link Collector#characteristics() characteristics()}</code> must
 * return a not null result and be the same list that is specified in this
 * tester.</li>
 * <li>For each received sample either use it -
 * {@link ch.powerunit.collector.lang.CollectorTesterDSL0#withInput(Stream)
 * withInput} - or create a stream -
 * {@link ch.powerunit.collector.lang.CollectorTesterDSL0#withStreamFromList(List)
 * withStreamFromList} or
 * {@link ch.powerunit.collector.lang.CollectorTesterDSL0#withParallelStreamFromList(List)
 * withParallelStreamFromList} - and then execute the method
 * {@link java.util.stream.Stream#collect(Collector) collect} with the
 * {@link Collector} under test and validate the returned result.</li>
 * </ol>
 * 
 * @author borettim
 * @since 0.4.0
 * @param <T>
 *            the input type of the {@link java.util.stream.Collector Collector}
 *            .
 * @param <A>
 *            the accumulator type of the {@link java.util.stream.Collector
 *            Collector}.
 * @param <R>
 *            the return type of the {@link java.util.stream.Collector
 *            Collector}.
 */
@TestInterface(CollectorTesterImpl.class)
public final class CollectorTester<T, A, R> {
	private final Collector<T, A, R> collectorToTest;

	private final List<Stream<T>> inputs;

	private final List<Matcher<? super R>> results;

	private final Matcher<Iterable<? extends Characteristics>> expectedCharacteristics;

	private CollectorTester(Collector<T, A, R> collectorToTest,
			List<Stream<T>> inputs, List<Matcher<? super R>> results,
			Matcher<Iterable<? extends Characteristics>> expectedCharacteristics) {
		this.collectorToTest = collectorToTest;
		this.inputs = inputs;
		this.results = results;
		this.expectedCharacteristics = expectedCharacteristics;
	}

	/**
	 * Return a builder to create a tester of {@link java.util.stream.Collector
	 * Collector}.
	 * 
	 * @param collectorToTest
	 *            the {@link java.util.stream.Collector Collector} to be tested.
	 * @param <T>
	 *            the input type of the {@link java.util.stream.Collector
	 *            Collector} .
	 * 
	 * @param <R>
	 *            the return type of the {@link java.util.stream.Collector
	 *            Collector}.
	 * @return {@link CollectorTesterDSL0 the DSL to build the tester}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T, R> CollectorTesterDSL0<T, ?, R> of(
			Collector<T, ?, R> collectorToTest) {
		return new CollectorTesterDSL(collectorToTest);
	}

	/**
	 * Return a builder to create a tester of {@link java.util.stream.Collector
	 * Collector}.
	 * 
	 * @param inputClass
	 *            the class of the input of the
	 *            {@link java.util.stream.Collector Collector}.
	 * @param outputClass
	 *            the class of the output of the
	 *            {@link java.util.stream.Collector Collector}.
	 * @param collectorToTest
	 *            the {@link java.util.stream.Collector Collector} to be tested.
	 * @param <T>
	 *            the input type of the {@link java.util.stream.Collector
	 *            Collector}.
	 * 
	 * @param <R>
	 *            the return type of the {@link java.util.stream.Collector
	 *            Collector}.
	 * @return {@link CollectorTesterDSL0 the DSL to build the tester}
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T, R> CollectorTesterDSL0<T, ?, R> of(Class<T> inputClass,
			Class<T> outputClass, Collector<T, ?, R> collectorToTest) {
		return new CollectorTesterDSL(collectorToTest);
	}

	private static class CollectorTesterDSL<T, A, R> implements
			CollectorTesterDSL0<T, A, R>, CollectorTesterDSL1<T, A, R>,
			CollectorTesterDSL2<T, A, R> {
		private final Collector<T, A, R> collectorToTest;

		private final List<Stream<T>> inputs = new ArrayList<>();

		private final List<Matcher<? super R>> results = new ArrayList<>();

		private Matcher<Iterable<? extends Characteristics>> expectedCharacteristics;

		public CollectorTesterDSL(Collector<T, A, R> collectorToTest) {
			this.collectorToTest = collectorToTest;
		}

		@Override
		public CollectorTesterDSL3<T, A, R> havingCharacteristics(
				Characteristics... expectedCharacteristics) {
			this.expectedCharacteristics = TestSuite.DSL
					.<Collector.Characteristics> containsInAnyOrder(expectedCharacteristics);
			return this;
		}

		@Override
		public CollectorTesterDSL2<T, A, R> withInput(Stream<T> input) {
			inputs.add(Objects.requireNonNull(input, "input can't be null"));
			return this;
		}

		@Override
		public CollectorTesterDSL2<T, A, R> withStreamFromList(List<T> input) {
			return withInput(Objects.requireNonNull(input,
					"input can't be null").stream());
		}

		@Override
		public CollectorTesterDSL2<T, A, R> withParallelStreamFromList(
				List<T> input) {
			return withInput(Objects.requireNonNull(input,
					"input can't be null").parallelStream());
		}

		@Override
		public CollectorTesterDSL2<T, A, R> withStreamFromArray(
				@SuppressWarnings("unchecked") T... input) {
			return withInput(Arrays.stream(input));
		}

		@SuppressWarnings("unchecked")
		@Override
		public CollectorTesterDSL2<T, A, R> withStreamFromArray(T first) {
			return withStreamFromArray((T[]) new Object[] { first });
		}

		@SuppressWarnings("unchecked")
		@Override
		public CollectorTesterDSL2<T, A, R> withStreamFromArray(T first,
				T second) {
			return withStreamFromArray((T[]) new Object[] { first, second });
		}

		@SuppressWarnings("unchecked")
		@Override
		public CollectorTesterDSL2<T, A, R> withStreamFromArray(T first,
				T second, T third) {
			return withStreamFromArray((T[]) new Object[] { first, second,
					third });
		}

		@SuppressWarnings("unchecked")
		@Override
		public CollectorTesterDSL2<T, A, R> withStreamFromArray(T first,
				T second, T third, T fourth) {
			return withStreamFromArray((T[]) new Object[] { first, second,
					third, fourth });
		}

		@SuppressWarnings("unchecked")
		@Override
		public CollectorTesterDSL2<T, A, R> withStreamFromArray(T first,
				T second, T third, T fourth, T fifth) {
			return withStreamFromArray((T[]) new Object[] { first, second,
					third, fourth, fifth });
		}

		@Override
		public CollectorTesterDSL1<T, A, R> expecting(
				Matcher<? super R> matching) {
			results.add(Objects.requireNonNull(matching,
					"matching can't be null"));
			return this;
		}

		@Override
		public CollectorTesterDSL1<T, A, R> expecting(R value) {
			return expecting(TestSuite.DSL.equalTo(value));
		}

		@Override
		public CollectorTesterDSL1<T, A, R> expectingNull() {
			return expecting(TestSuite.DSL.nullValue());
		}

		@Override
		public CollectorTester<T, A, R> build() {
			return new CollectorTester<T, A, R>(collectorToTest, inputs,
					results,
					expectedCharacteristics == null ? TestSuite.DSL
							.emptyIterable() : expectedCharacteristics);
		}

	}

	/**
	 * Used by the framework.
	 * 
	 * @return the collectorToTest
	 */
	public Collector<T, A, R> getCollectorToTest() {
		return collectorToTest;
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the inputs
	 */
	public List<Stream<T>> getInputs() {
		return Collections.unmodifiableList(inputs);
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the results
	 */
	public List<Matcher<? super R>> getResults() {
		return Collections.unmodifiableList(results);
	}

	/**
	 * Used by the framework.
	 * 
	 * @return the expectedCharacteristics
	 */
	public Matcher<Iterable<? extends Characteristics>> getExpectedCharacteristics() {
		return expectedCharacteristics;
	}

}
