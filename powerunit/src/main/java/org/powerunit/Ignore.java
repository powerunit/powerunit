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
package org.powerunit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to indicate a test to be ignored.
 * <p>
 * This can be used on a class (to ignore completely the class) or on a method
 * (to ignore only the method).
 * <p>
 * Depending on the context, the action is not exactly the same :
 * <ul>
 * <li><b>Usage on class</b>
 * <p>
 * In this case, no check on the class are done. A single pseudo test execution
 * will be done, with a skipped status.</li>
 * <li><b>Usage on method</b>
 * <p>
 * In this case, test rule related of this method are not executed.</li>
 * </ul>
 * 
 * @author borettim
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Ignore {
}
