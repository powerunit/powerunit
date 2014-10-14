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
 * This annotation can be used on a non static, final field in a test class as
 * providing access to a test framework.
 * <p>
 * This annotation can be use to delegate test execution to a provided test
 * framework. For example, assuming that the exposed interface for the framework
 * is a class <code>TestFrameworkInterface</code> (which will be required to be
 * annotated with <code>{@link TestInterface}</code>), this framework can be
 * used in the following ways :
 * 
 * <pre>
 * &#064;TestDelegate
 * public final TestFrameworkInterface myDelegation = new TestFrameworkInterface(...);
 * </pre>
 * 
 * or
 * 
 * <pre>
 * &#064;TestDelegate
 * public final Supplier&lt;TestFrameworkInterface&gt; myDelegation = ()-&gt;new TestFrameworkInterface(...);
 * </pre>
 * 
 * @author borettim
 * @since 1.0.2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TestDelegate {

}
