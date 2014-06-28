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
 * Used to mark a method (public static, returning Stream&lt;Object[]&gt;,
 * 0-args) as method to provide test parameter.
 * <p>
 * This method will be run once only.
 * <p>
 * For instance, we may wrote this code
 * 
 * <pre>
 * import java.util.Arrays;
 * import java.util.function.Function;
 * import java.util.stream.Stream;
 * 
 * import org.powerunit.Categories;
 * import org.powerunit.Parameter;
 * import org.powerunit.Parameters;
 * import org.powerunit.Test;
 * import org.powerunit.TestSuite;
 * 
 * &#064;Categories({ &quot;example&quot;, &quot;demo&quot; })
 * public class FunctionParameterTest&lt;T, R&gt; implements TestSuite {
 * 
 * 	&#064;Parameters(&quot;{0} on {1} expecting {2}&quot;)
 * 	public static Stream&lt;Object[]&gt; getDatas() {
 * 		return Arrays.stream(new Object[][] { {
 * 				(Function&lt;String, Integer&gt;) Integer::valueOf, &quot;1&quot;, 1 } });
 * 	}
 * 
 * 	&#064;Parameter(0)
 * 	public Function&lt;T, R&gt; function;
 * 
 * 	&#064;Parameter(1)
 * 	public T input;
 * 
 * 	&#064;Parameter(2)
 * 	public R expected;
 * 
 * 	&#064;Test
 * 	public void testAFunction() {
 * 		assertThatFunction(function, input).is(expected);
 * 	}
 * }
 * 
 * </pre>
 * 
 * @author borettim
 * @see java.util.stream.Stream
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Parameters {
	/**
	 * Define an optional name of the test parameters. use {n} to refer to the
	 * parameter.
	 * 
	 * @return the name.
	 */
	String value() default "";
}
