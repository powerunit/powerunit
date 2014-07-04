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
package ch.powerunit.helpers;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import ch.powerunit.Parameter;

/**
 * This is a class that provide a way to transform stream of String to Object
 * based on the @Parameter field type.
 * <p>
 * Access to this class is available from the {@link ch.powerunit.TestSuite
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

    /**
     * This is the regex used to support the split in case the input is an
     * array.
     */
    public static final String DEFAULT_REGEX_FOR_ARRAY = "\\s*,\\s*";

    private final Map<Integer, Function<Object, Object>> mapper = new HashMap<>();

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
     * @see <a href="./doc-files/convertedType.html">Supported automated
     *      conversion</a>
     */
    public static StreamParametersMapFunction<String> stringToParameterMap(
            Class<?> testClass) {
        StreamParametersMapFunction<String> map = new StreamParametersMapFunction<>();
        Arrays.stream(testClass.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Parameter.class))
                .forEach(
                        f -> {
                            int pid = f.getAnnotation(Parameter.class).value();
                            Function<String, ?> fct = null;
                            if (f.getGenericType() instanceof Class) {
                                Class<?> c = (Class<?>) f.getGenericType();
                                fct = getEntryClassMapperFunction(c);

                            } else if (f.getGenericType() instanceof ParameterizedType) {
                                ParameterizedType p = (ParameterizedType) f
                                        .getGenericType();
                                fct = getEntryParameterizedTypeFunction(p);
                            }
                            if (fct != null) {
                                map.andMap(pid, fct);
                            }
                        });
        return map;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Function<String, ?> getEntryParameterizedTypeFunction(
            ParameterizedType p) {
        Type raw = p.getRawType();
        if (Collection.class.equals(raw)
                && p.getActualTypeArguments()[0] instanceof Class) {
            Class<?> param = (Class<?>) p.getActualTypeArguments()[0];
            return collectionMapper((Class) param,
                    getEntryClassMapperFunction(param), DEFAULT_REGEX_FOR_ARRAY);
        } else if (Set.class.equals(raw)
                && p.getActualTypeArguments()[0] instanceof Class) {
            Class<?> param = (Class<?>) p.getActualTypeArguments()[0];
            return setMapper((Class) param, getEntryClassMapperFunction(param),
                    DEFAULT_REGEX_FOR_ARRAY);
        } else if (Class.class.equals(raw)) {
            return getEntryClassMapperFunction((Class) raw);
        }
        return null;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static Function<String, ?> getEntryClassMapperFunction(Class<?> c) {
        if (c.isArray()) {
            Function<String, ?> compound = getEntryClassMapperFunction(c
                    .getComponentType());
            if (compound != null) {
                return arrayMapper((Class) c.getComponentType(), compound,
                        DEFAULT_REGEX_FOR_ARRAY);
            }
        } else {
            return getSingleEntryClassMapperFunction(c);
        }
        return null;
    }

    private static Function<String, ?> getSingleEntryClassMapperFunction(
            Class<?> c) {
        if (int.class.equals(c) || Integer.class.equals(c)) {
            return Integer::valueOf;
        } else if (float.class.equals(c) || Float.class.equals(c)) {
            return Float::valueOf;
        } else if (short.class.equals(c) || Short.class.equals(c)) {
            return Short::valueOf;
        } else if (double.class.equals(c) || Double.class.equals(c)) {
            return Double::valueOf;
        } else if (long.class.equals(c) || Long.class.equals(c)) {
            return Long::valueOf;
        } else if (char.class.equals(c) || Character.class.equals(c)) {
            return s -> s.charAt(0);
        } else if (String.class.equals(c)) {
            return Function.identity();
        } else if (boolean.class.equals(c) || Boolean.class.equals(c)) {
            return Boolean::valueOf;
        } else if (Class.class.equals(c)) {
            return s -> {
                try {
                    return Class.forName(s);
                } catch (ClassNotFoundException e) {
                    throw new IllegalArgumentException("Unexpected error "
                            + e.getMessage(), e);
                }
            };
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T> Function<String, T[]> arrayMapper(Class<T> clazz,
            Function<String, T> singleElementMapper, String separator) {
        return (s) -> {
            if (s == null) {
                return null;
            }
            return Arrays.stream(s.split(separator)).map(singleElementMapper)
                    .toArray((i) -> (T[]) Array.newInstance(clazz, i));
        };
    }

    private static <T> Function<String, Collection<T>> collectionMapper(
            Class<T> clazz, Function<String, T> singleElementMapper,
            String separator) {
        return (s) -> {
            if (s == null) {
                return null;
            }
            return Arrays.stream(s.split(separator)).map(singleElementMapper)
                    .collect(Collectors.toList());
        };
    }

    private static <T> Function<String, Set<T>> setMapper(Class<T> clazz,
            Function<String, T> singleElementMapper, String separator) {
        return (s) -> {
            if (s == null) {
                return null;
            }
            return Arrays.stream(s.split(separator)).map(singleElementMapper)
                    .collect(Collectors.toSet());
        };
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
