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
 * Mark a method as being a test method.
 * <p>
 * This method must be public, non static, 0-arg, void return.
 *
 * @author borettim
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {
	/**
	 * Define an alternative name for the test.
	 * <p>
	 * If not used, the test name will be based on the method name. In case of
	 * parameterized test, the name will be formatted in the same way that the
	 * {@link Parameters @Parameters} annotation will be.
	 * 
	 * @return the name
	 * @see Parameters#value() For the exact syntax of the name model.
	 * @see java.text.MessageFormat#format(String, Object...) The formatter used
	 *      for parameterized test. For version before 0.4.0.
	 * @see java.lang.String#format(String, Object...) The formatter used for
	 *      parameterized test, since version 0.4.0.
	 */
	String name() default "";

	/**
	 * Define if the test must fail at the first assertion/failure in error
	 * (default) or only at the end (and accumulate all expected error).
	 * 
	 * @return true by default.
	 * @since 0.4.0
	 */
	boolean fastFail() default true;
}
