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

import java.util.function.Supplier;

import org.mockito.MockitoAnnotations;

/**
 * Definition of a test rule (modification of a test statement).
 * <p>
 * TestRule are the only way (outside of the &#64;Parameters concept) to
 * provides test fixtures, action before and after tests, etc. Only one
 * <code>TestRule</code> chain is allowed for a test class. If a test class
 * extends another one, one chain is allowed for each test class and they will
 * be chained from the upper class to the lower one.
 * <p>
 * Conceptually, a test rule, at execution time, will receive a test statement
 * as parameter and compute another test statement.
 * <h3>Test Rule chain</h3>
 * A test rule chain is public final, non static, field, of type
 * <code>TestRule</code>. Once you have the outer test rule, it is possible to
 * chain them by using the {@link #around(TestRule)} or
 * {@link #around(Supplier)} methods. One single TestRule (which can be used as
 * a start of the chain, or later), can be builded by :
 * <ul>
 * <li>Simply using the constructor (or other way) provided by the rule itself
 * (for instance see the {@link org.powerunit.rules.TestContextRule
 * TestContextRule} rule.</li>
 * <li>Use the <code>before</code> method that will produce a rule to be
 * executed before a test ; The <code>after</code> method will do the same, but
 * with a code to be executed after a test.</li>
 * </ul>
 * 
 * <h3>Example</h3>
 * For instance, for this class:
 * 
 * <pre>
 * import org.mockito.Mock;
 * import org.mockito.Mockito;
 * import org.powerunit.Rule;
 * import org.powerunit.Test;
 * import org.powerunit.TestRule;
 * import org.powerunit.TestSuite;
 * 
 * public class MockitoRuleTest implements TestSuite {
 * 	public interface Mockable {
 * 		void run();
 * 	}
 * 
 * 	&#064;Mock
 * 	private Mockable mock;
 * 
 * 	&#064;Rule
 * 	public final TestRule testRule = mockitoRule()
 * 			.around(before(this::prepare));
 * 
 * 	public void prepare() {
 * 		Mockito.doThrow(new RuntimeException(&quot;test&quot;)).when(mock).run();
 * 	}
 * 
 * 	&#064;Test
 * 	public void testException() {
 * 		assertWhen((p) -&gt; mock.run()).throwException(
 * 				isA(RuntimeException.class));
 * 	}
 * 
 * }
 * </pre>
 * 
 * In this case, the defined chain, will first apply the rule provided by
 * <code>mockitoRule</code>, and then apply the rule included inside the
 * <code>around</code>. The <code>before</code> method usage inside the
 * <code>around</code> will ensure the method <code>prepare</code> is executed
 * before each test.
 * <p>
 * The sequence of execution, will be :
 * <ol>
 * <li>Execute the Mockito initialization.</li>
 * <li>Execute the method <code>prepare</code>.</li>
 * <li>Execute the test method <code>testException</code>.</li>
 * </ol>
 * 
 * @author borettim
 *
 */
@FunctionalInterface
public interface TestRule {
	/**
	 * The default implementation of test rule.
	 * <p>
	 * The goal of this method is to compute another test statement that will be
	 * the one to be runned.
	 * 
	 * @param inner
	 *            the inner statement
	 * @return the new statement, as modified by this rule.
	 */
	Statement<TestContext<Object>, Throwable> computeStatement(
			Statement<TestContext<Object>, Throwable> inner);

	/**
	 * Build a before testrule.
	 * <p>
	 * The passed piece of code (which can for instance an inline definition or
	 * a reference to a method) that must be executed before the test itself.
	 * 
	 * @param before
	 *            the code to be used before
	 * @return the rule chain.
	 */
	static TestRule before(Runnable before) {
		return (i) -> (p) -> {
			before.run();
			i.run(p);
		};
	}

	/**
	 * Build a after testrule.
	 * <p>
	 * The passed piece of code (which can for instance an inline definition or
	 * a reference to a method) that must be executed after the test itself.
	 * 
	 * @param after
	 *            the code to be used after
	 * @return the rule chain.
	 */
	static TestRule after(Runnable after) {
		return (i) -> (p) -> {
			try {
				i.run(p);
			} finally {
				after.run();
			}
		};
	}

	/**
	 * Add an inner rule.
	 * 
	 * @param inner
	 *            the inner rule
	 * @return the rule chain.
	 */
	default TestRule around(TestRule inner) {
		return around(() -> inner);
	}

	/**
	 * Add an inner rule.
	 * <p>
	 * This inner rule is created just before usage, thanks to the
	 * {@link Supplier} object. This can be used for the case when one rule
	 * depend on the outcome of a previous one.
	 * 
	 * @param inner
	 *            the supplier of the inner rule
	 * @return the rule chain.
	 */
	default TestRule around(Supplier<TestRule> inner) {
		return (i) -> computeStatement((p) -> inner.get().computeStatement(i)
				.run(p));
	}

	/**
	 * Create a rule to support mockito.
	 * 
	 * @return the mockitor rule
	 */
	static TestRule mockitoRule() {
		return (i) -> Statement.around(i, (p) -> {
			MockitoAnnotations.initMocks(p.getTestSuiteObject());
		}, (p) -> {
			// Do nothing as default
			});
	}
}
