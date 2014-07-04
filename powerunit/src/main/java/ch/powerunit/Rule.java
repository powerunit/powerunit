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
 * Mark a field as the test rule chain.
 * <p>
 * This field must be public, final, non static and of type {@link TestRule}.
 * This annotation can be used only once on a class.
 * <p>
 * In case one test class extends another one, it is possible to have one field
 * annotated with this annotation per class. The rule of the upper classes are
 * executed around the once of the lower classes.
 * <p>
 * For example :
 *
 * <pre>
 * &#064;Rule
 * public final TestRule rule = before(this::prepare).around(after(this::clean));
 * </pre>
 *
 * This will define that the method <code>prepare</code> of the test class will
 * be execute before each test and then, the method <code>clean</code> after
 * each test. Of course, thanks to the lambda, it is not required to reference a
 * method of this class, but it is also possible to pass some code directly in
 * line (<code>()-&gt;{}</code>).
 *
 * @author borettim
 * @see TestRule
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface Rule {
}
