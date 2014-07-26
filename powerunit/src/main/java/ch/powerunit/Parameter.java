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
package ch.powerunit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Field marker (public non static field), for parameterized test.
 * <p>
 * The field must be numbered (using {@link #value()}) from 0 to n, in a
 * continuous way.
 * <p>
 * For example, a parameter field can be defined in the following way:
 *
 * <pre>
 * &#064;Parameter(0)
 * public String fieldName;
 * </pre>
 *
 * @author borettim
 * @see Parameters
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Parameter {
    /**
     * Define the parameter position.
     * 
     * @return the parameter position (0-based).
     */
    int value();

    /**
     * This attribute can be used to indicate that this parameter is to be used
     * as a predicate to accept (or not) a test for execution with this
     * parameter set.
     * <p>
     * This is false by default and when set to true, only one field can it.
     * This can only be used on field of type :
     * 
     * <pre>
     * BiFunction&lt;String,Object[],Boolean&gt;
     * </pre>
     * 
     * This predicate will receive as parameter the test method name and the
     * parameter array and should return true if and only if the test method
     * should be run.
     * 
     * @return the filtering status.
     * @since 0.1.0
     * @see java.util.function.BiFunction
     */
    boolean filter() default false;
}
