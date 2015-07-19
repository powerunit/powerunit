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
	 * <p>
	 * The goal is here to change the fail assertion and failure work ; When set
	 * to <code>false</code>. Assertion/failure will not stop the test, but
	 * return false. It is up to the test itself to define what to be done based
	 * on this return value (continue, return, etc). At the end of the test, if
	 * any failure happened, a general failure will be produced, with detail
	 * regarding all failures. <br>
	 * For example, a test may look like :
	 * 
	 * <pre>
	 * public class LaterFailureTest implements TestSuite {
	 * 	&#064;Test(fastFail = false)
	 * 	public void test() {
	 * 		assertThat(true).is(false);
	 * 		assertWhen((p) -&gt; {
	 * 		}).throwException(any(Throwable.class));
	 * 		fail(&quot;demo&quot;);
	 * 	}
	 * }
	 * </pre>
	 * 
	 * And the result like:
	 * 
	 * <pre>
	 * Multiple failures : 
	 * 	Error : expecting &lt;false&gt; but was &lt;true&gt;
	 * 	Error : An exception was expected, but none was thrown
	 * 	Error : demo
	 * 
	 * Original Stack Traces
	 * 	expecting &lt;false&gt; but was &lt;true&gt;
	 * 		ch.powerunit.impl.AssertThatObjectImpl.is(AssertThatObjectImpl.java:63)
	 * 		...
	 * 	An exception was expected, but none was thrown
	 * 		ch.powerunit.impl.AssertThatExceptionImpl.throwException(AssertThatExceptionImpl.java:64)
	 * 		...
	 * 	demo
	 * 		ch.powerunit.Assert.fail(Assert.java:578)
	 * 		...
	 * 
	 * ch.powerunit.impl.TestContextImpl.fail(TestContextImpl.java:115)
	 * ch.powerunit.impl.DefaultPowerUnitRunnerImpl.lambda$null$104(DefaultPowerUnitRunnerImpl.java:505)
	 * ch.powerunit.impl.DefaultPowerUnitRunnerImpl$$Lambda$38/665188480.run(Unknown Source)
	 * ch.powerunit.impl.DefaultPowerUnitRunnerImpl.lambda$runOne$73(DefaultPowerUnitRunnerImpl.java:226)
	 * ch.powerunit.impl.DefaultPowerUnitRunnerImpl$$Lambda$42/520022247.accept(Unknown Source)
	 * java.util.HashMap$EntrySet.forEach(HashMap.java:1035)
	 * ch.powerunit.impl.DefaultPowerUnitRunnerImpl.runOne(DefaultPowerUnitRunnerImpl.java:210)
	 * ch.powerunit.impl.DefaultPowerUnitRunnerImpl.run(DefaultPowerUnitRunnerImpl.java:144)
	 * ch.powerunit.PowerUnitMainRunner.main(PowerUnitMainRunner.java:82)
	 * ch.powerunit.suite.Suites.main(Suites.java:73)
	 * </pre>
	 * 
	 * <br>
	 * This function will only function in the following case :
	 * <ul>
	 * <li>The assertion method is used from the same thread that the test
	 * itself (it is the standard case).</li>
	 * <li>The assertion method is used directement from the object test, and
	 * this class implements {@link TestSuite}.</li>
	 * </ul>
	 * 
	 * <h3>Examples</h3>
	 * <h4>Using two threads without fastFail</h4>
	 * Let's assume the following test class :
	 * 
	 * <pre>
	 * import ch.powerunit.Rule;
	 * import ch.powerunit.Test;
	 * import ch.powerunit.TestRule;
	 * import ch.powerunit.TestSuite;
	 * 
	 * public class MyBean4Test implements TestSuite {
	 * 	&#064;Rule
	 * 	public final TestRule rule = before(this::prepare);
	 * 
	 * 	private MyBean bean;
	 * 
	 * 	private void prepare() {
	 * 		bean = new MyBean();
	 * 	}
	 * 
	 * 	&#064;Test()
	 * 	public void testSimple() throws Exception {
	 * 		Thread t = new Thread(() -&gt; {
	 * 			bean.setField1(&quot;y&quot;);
	 * 			assertThat(bean.getField1()).is(&quot;x&quot;);
	 * 		});
	 * 		t.start();
	 * 		t.join();
	 * 	}
	 * }
	 * </pre>
	 * 
	 * <b>This test will not fail!</b>. Why ? because simply the assertion
	 * failure happend in another thread, so this thread receive an assertion
	 * failure, not the once supporting the test.
	 * 
	 * <h4>Using two threads inside the same class, with fastFail</h4>
	 * Let's assume the following test class
	 * 
	 * <pre>
	 * import ch.powerunit.Rule;
	 * import ch.powerunit.Test;
	 * import ch.powerunit.TestRule;
	 * import ch.powerunit.TestSuite;
	 * 
	 * public class MyBean3Test implements TestSuite {
	 * 	&#064;Rule
	 * 	public final TestRule rule = before(this::prepare);
	 * 
	 * 	private MyBean bean;
	 * 
	 * 	private void prepare() {
	 * 		bean = new MyBean();
	 * 	}
	 * 
	 * 	&#064;Test(fastFail = false)
	 * 	public void testSimple() throws Exception {
	 * 		Thread t = new Thread(() -&gt; {
	 * 			bean.setField1(&quot;y&quot;);
	 * 			assertThat(bean.getField1()).is(&quot;x&quot;);
	 * 		});
	 * 		t.start();
	 * 		t.join();
	 * 	}
	 * }
	 * </pre>
	 * 
	 * <b>This test will fail!</b>. Why ? because the fastFail mode will make
	 * the <code>assertThat</code> method only register the failure, and produce
	 * an error in the test thread. the link between the two threads is found
	 * because the <code>assertThat</code> method used is the one from the test
	 * class itself.
	 * 
	 * @return true by default.
	 * @since 0.4.0
	 */
	boolean fastFail() default true;
}
