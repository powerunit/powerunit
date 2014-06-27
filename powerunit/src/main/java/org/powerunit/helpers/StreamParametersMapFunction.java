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
package org.powerunit.helpers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.powerunit.Parameter;

/**
 * This is a class that provide a way to transform stream of String to Object
 * based on the @Parameter field type.
 * <p>
 * Access to this class is available from the {@link org.powerunit.TestSuite
 * TestSuite} interface.
 * 
 * @author borettim
 * @param <T>
 *            the input type
 */
public final class StreamParametersMapFunction<T> implements
		Function<T[], Object[]> {

	private StreamParametersMapFunction() {
	}

	private Map<Integer, Function<Object, Object>> mapper = new HashMap<>();

	@Override
	public Object[] apply(T[] input) {
		Object output[] = new Object[input.length];
		for (int i = 0; i < input.length; i++) {
			Function<Object, Object> f = mapper.getOrDefault(i,
					Function.identity());
			output[i] = f.apply(input[i]);
		}
		return output;
	}

	/**
	 * Start building a Parameter Mapper function, with an initial converter.
	 * <p>
	 * Not specified index are considered transformed by identity function.
	 * 
	 * @param idx
	 *            The parameter index
	 * @param mapFunction
	 *            the function to be applied
	 * @return the function on the parameter array
	 * @param <T>
	 *            The input type for the function
	 * @param <R>
	 *            the result type for the function
	 */
	public static <T, R> StreamParametersMapFunction<T> map(int idx,
			Function<T, R> mapFunction) {
		if (idx < 0) {
			throw new IllegalArgumentException("idx can't be negative");
		}
		return new StreamParametersMapFunction<T>().andMap(idx, mapFunction);
	}

	/**
	 * Start building a Parameter Mapper function, assuming that the input are
	 * String, and using the type of the {@link Parameter &#64;Parameter} field.
	 * <p>
	 * Fields not supported will not be mapped and must be handled manually,
	 * using {@link StreamParametersMapFunction#andMap(int, Function) andMap}
	 * method.
	 * 
	 * @param testClass
	 *            the testClass with the annotation
	 * @return the function on the parameter array
	 */
	public static StreamParametersMapFunction<String> stringToParameterMap(
			Class<?> testClass) {
		StreamParametersMapFunction<String> map = new StreamParametersMapFunction<>();
		Arrays.stream(testClass.getDeclaredFields())
				.filter(f -> f.isAnnotationPresent(Parameter.class))
				.forEach(
						f -> {
							if (f.getGenericType() instanceof Class) {
								int pid = f.getAnnotation(Parameter.class)
										.value();
								Class<?> c = (Class<?>) f.getGenericType();
								if (int.class.equals(c)
										|| Integer.class.equals(c)) {
									map.<Integer> andMap(pid, Integer::valueOf);
								} else if (float.class.equals(c)
										|| Float.class.equals(c)) {
									map.<Float> andMap(pid, Float::valueOf);
								} else if (short.class.equals(c)
										|| Short.class.equals(c)) {
									map.<Short> andMap(pid, Short::valueOf);
								} else if (double.class.equals(c)
										|| Double.class.equals(c)) {
									map.<Double> andMap(pid, Double::valueOf);
								} else if (long.class.equals(c)
										|| Long.class.equals(c)) {
									map.<Long> andMap(pid, Long::valueOf);
								} else if (char.class.equals(c)
										|| Character.class.equals(c)) {
									map.<Character> andMap(pid,
											s -> s.charAt(0));
								} else if (String.class.equals(c)) {
									map.<String> andMap(pid,
											Function.identity());
								} else if (boolean.class.equals(c)
										|| Boolean.class.equals(c)) {
									map.<Boolean> andMap(pid, Boolean::valueOf);
								} else if (Class.class.equals(c)) {
									map.<Class<?>> andMap(
											pid,
											s -> {
												try {
													return Class.forName(s);
												} catch (ClassNotFoundException e) {
													throw new IllegalArgumentException(
															"Unexpected error "
																	+ e.getMessage(),
															e);
												}
											});
								}
							}
						});
		return map;
	}

	/**
	 * Defines an additional Parameter Mapper function.
	 * <p>
	 * Not specified index are considered transformed by identity function.
	 * 
	 * @param idx
	 *            The parameter index
	 * @param mapFunction
	 *            the function to be applied
	 * @return the function on the parameter array
	 * @param <R>
	 *            The return type for the function
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <R> StreamParametersMapFunction<T> andMap(int idx,
			Function<T, R> mapFunction) {
		if (idx < 0) {
			throw new IllegalArgumentException("idx can't be negative");
		}
		Objects.requireNonNull(mapFunction);
		mapper.put(idx, (Function) mapFunction);
		return this;
	}
}
