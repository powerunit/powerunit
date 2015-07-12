package ch.powerunit.collector.lang;

import java.util.List;
import java.util.stream.Stream;

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
public interface CollectorTesterDSL3<T, A, R> {
	/**
	 * Specify a sample input Stream to be used to test the
	 * {@link java.util.stream.Collector Collector}.
	 * 
	 * @param input
	 *            the sample input.
	 * @return {@link CollectorTesterDSL2 the next step of the DSL}
	 */
	CollectorTesterDSL2<T, A, R> withInput(Stream<T> input);

	/**
	 * Specify a sample input List that will be transformed as stream and be
	 * used as a sample to test the {@link java.util.stream.Collector Collector}
	 * .
	 * 
	 * @param input
	 *            the sample input.
	 * @return {@link CollectorTesterDSL2 the next step of the DSL}
	 */
	CollectorTesterDSL2<T, A, R> withStreamFromList(List<T> input);

	/**
	 * Specify a sample input List that will be transformed as parallelStream
	 * and be used as a sample to test the {@link java.util.stream.Collector
	 * Collector}.
	 * 
	 * @param input
	 *            the sample input.
	 * @return {@link CollectorTesterDSL2 the next step of the DSL}
	 */
	CollectorTesterDSL2<T, A, R> withParallelStreamFromList(List<T> input);

	/**
	 * Specify a sample input array that will be transformed as stream and be
	 * used as a sample to test the {@link java.util.stream.Collector Collector}
	 * .
	 * 
	 * @param input
	 *            the sample input.
	 * @return {@link CollectorTesterDSL2 the next step of the DSL}
	 */
	CollectorTesterDSL2<T, A, R> withStreamFromArray(T... input);

	/**
	 * Specify a sample input array that will be transformed as stream and be
	 * used as a sample to test the {@link java.util.stream.Collector Collector}
	 * .
	 * 
	 * @param first
	 *            the first input.
	 * @return {@link CollectorTesterDSL2 the next step of the DSL}
	 */
	CollectorTesterDSL2<T, A, R> withStreamFromArray(T first);

	/**
	 * Specify a sample input array that will be transformed as stream and be
	 * used as a sample to test the {@link java.util.stream.Collector Collector}
	 * .
	 * 
	 * @param first
	 *            the first input.
	 * @param second
	 *            the second input.
	 * @return {@link CollectorTesterDSL2 the next step of the DSL}
	 */
	CollectorTesterDSL2<T, A, R> withStreamFromArray(T first, T second);

	/**
	 * Specify a sample input array that will be transformed as stream and be
	 * used as a sample to test the {@link java.util.stream.Collector Collector}
	 * .
	 * 
	 * @param first
	 *            the first input.
	 * @param second
	 *            the second input.
	 * @param third
	 *            the third input.
	 * @return {@link CollectorTesterDSL2 the next step of the DSL}
	 */
	CollectorTesterDSL2<T, A, R> withStreamFromArray(T first, T second, T third);

	/**
	 * Specify a sample input array that will be transformed as stream and be
	 * used as a sample to test the {@link java.util.stream.Collector Collector}
	 * .
	 * 
	 * @param first
	 *            the first input.
	 * @param second
	 *            the second input.
	 * @param third
	 *            the third input.
	 * @param fourth
	 *            the fourth input.
	 * @return {@link CollectorTesterDSL2 the next step of the DSL}
	 */
	CollectorTesterDSL2<T, A, R> withStreamFromArray(T first, T second,
			T third, T fourth);

	/**
	 * Specify a sample input array that will be transformed as stream and be
	 * used as a sample to test the {@link java.util.stream.Collector Collector}
	 * .
	 * 
	 * @param first
	 *            the first input.
	 * @param second
	 *            the second input.
	 * @param third
	 *            the third input.
	 * @param fourth
	 *            the fourth input.
	 * @param fifth
	 *            the fifth input.
	 * @return {@link CollectorTesterDSL2 the next step of the DSL}
	 */
	CollectorTesterDSL2<T, A, R> withStreamFromArray(T first, T second,
			T third, T fourth, T fifth);

}
