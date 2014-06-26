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
package org.powerunit.rules;

import org.powerunit.Statement;
import org.powerunit.TestContext;
import org.powerunit.TestRule;
import org.powerunit.exception.AssumptionError;

/**
 * This can be use to support taking action depending on the issue of the test.
 * <p>
 * The purpose is here to provide an interface that can be implemented by rule
 * implementor to do action, before, after and on some condition.
 * 
 * @author borettim
 */
public interface TestListenerRule extends TestRule {

	/**
	 * Method used at the start of the test.
	 * 
	 * @param context
	 *            the test context
	 */
	default void onStart(TestContext<Object> context) {

	}

	/**
	 * Method used when a failure happened.
	 * 
	 * @param context
	 *            the test context
	 * @param af
	 *            the failure
	 */
	default void onFailure(TestContext<Object> context, AssertionError af) {

	}

	/**
	 * Method used when an error happened.
	 * 
	 * @param context
	 *            the test context
	 * @param error
	 *            the error
	 */
	default void onError(TestContext<Object> context, Throwable error) {

	}

	/**
	 * Method used when an assumption error happened.
	 * 
	 * @param context
	 *            the test context
	 * @param error
	 *            the assumption error
	 */
	default void onAssumptionSkip(TestContext<Object> context,
			AssumptionError error) {

	}

	/**
	 * Method used at the end of the test.
	 * 
	 * @param context
	 *            the test context
	 */
	default void onEnd(TestContext<Object> context) {

	}

	@Override
	default Statement<TestContext<Object>, Throwable> computeStatement(
			Statement<TestContext<Object>, Throwable> inner) {
		return (p) -> {
			try {
				onStart(p);
				inner.run(p);
			} catch (AssertionError af) {
				onFailure(p, af);
				throw af;
			} catch (InternalError ie) {
				onError(p, ie);
				throw ie;
			} catch (AssumptionError ie) {
				onAssumptionSkip(p, ie);
				throw ie;
			} catch (Throwable t) {
				onError(p, t);
				throw t;
			} finally {
				onEnd(p);
			}
		};
	}
}
