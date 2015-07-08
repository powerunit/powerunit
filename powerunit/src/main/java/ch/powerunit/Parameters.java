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
 * import ch.powerunit.Parameter;
 * import ch.powerunit.Parameters;
 * import ch.powerunit.Test;
 * import ch.powerunit.TestSuite;
 * 
 * public class FunctionParameterTest&lt;T, R&gt; implements TestSuite {
 * 
 * 	&#064;Parameters(&quot;%1$s on %2$s expecting %3$s&quot;)
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
 * It is also possible to indicate that each test parameter set is not
 * applicable for each test method. This is done by using an additional, with
 * the attribute <code>filter=true</code>. This field will be a BiFunction
 * receiving the test method name and the parameters and returning a boolean. (
 * <code>BiFunction&lt;String,Object[],Boolean&gt;</code>). This method will be
 * used to check if the test method accept (or not) the parameter.
 * 
 * <p>
 * In case this annotation is used on a method from a test class annotated with
 * <code>{@link TestDelegator}</code>, this method is used as a way to provide
 * parameter to the test classes. In this case, this method must have exactly
 * one parameter, which will be a class annotated with
 * <code>{@link TestInterface}</code>.
 *
 * @author borettim
 * @see java.util.stream.Stream
 * @see TestSuite#addFieldToEachEntry(Object) This is used on the stream to add
 *      an object at the end of each entry (for instance the BiFunction).
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Parameters {
	/**
	 * Define an optional name of the test parameters. use the syntax defined by
	 * {@link java.lang.String#format(String, Object...)} to refer to the
	 * parameter.
	 * <p>
	 * <b>The syntax for the formatted string has change since the version
	 * 0.4.0.</b>. At the moment, both syntax are supported, but the old one may
	 * be removed in a future release.
	 * <br>
	 * <h3>Normal syntax</h3>
	 * The syntax defined by {@link java.lang.String#format(String, Object...)}
	 * is used. Each parameter is available. It is here required to be careful
	 * with the numbering of the argument ; {@link Parameter} index are numbered
	 * starting from 0, but in the string format the numbering start from 1. In
	 * the easy form, the parameter can be passed with the syntax
	 * <code>%1$s</code> which for instance will use the first parameter (0
	 * parameter), as a String. 
	 * <br>
	 * <h3>Old syntax</h3>
	 * The old syntax is defined by
	 * {@link java.text.MessageFormat#format(String, Object...)} ; This syntax
	 * is enabled when the passed string format contains the regular expression
	 * <code>\{[0-9]+\}</code>.
	 * 
	 * @return the name.
	 * @see java.text.MessageFormat#format(String, Object...) The formatter used
	 *      for parameterized test. For version before 0.4.0.
	 * @see java.lang.String#format(String, Object...) The formatter used for
	 *      parameterized test, since version 0.4.0.
	 */
	String value() default "";
}
