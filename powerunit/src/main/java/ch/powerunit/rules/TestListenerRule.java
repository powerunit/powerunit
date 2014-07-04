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
package ch.powerunit.rules;

import ch.powerunit.Statement;
import ch.powerunit.TestContext;
import ch.powerunit.TestRule;
import ch.powerunit.exception.AssumptionError;

/**
 * This can be use to support taking action depending on the issue of the test.
 * <p>
 * The purpose is here to provide an interface that can be implemented by rule
 * implementor to do action, before, after and on some condition.
 * <p>
 * Rule implementer should implements this interface and then implement the
 * required methods (which are do-nothing method by default).
 *
 * The order of execution is the following :
 * <ul>
 * <li>Before the test, the method {@link #onStart(TestContext)} is executed.</li>
 * <li>Then the test is executed.</li>
 * <li>In case of error/failure, one (and only one) of the next methods is
 * executed
 * <ol>
 * <li>{@link #onFailure(TestContext, AssertionError)} in case of test failure.</li>
 * <li>{@link #onError(TestContext, Throwable)} in case of test error.</li>
 * <li>{@link #onAssumptionSkip(TestContext, AssumptionError)} in case of test
 * skip.</li>
 * </ol>
 * </li>
 * <li>In all case, after the test, and after the previous method in case of
 * error/failure, the method {@link #onEnd(TestContext)} is executed.</li>
 * </ul>
 *
 * The {@link ExternalResource} rule implements this interface to provide simple
 * use case (action before and always after test).
 *
 * @author borettim
 * @see ExternalResource
 */
public interface TestListenerRule extends TestRule {

    /**
     * Method used at the start of the test.
     * <p>
     * Default implementation is to do nothing.
     * 
     * @param context
     *            the test context
     */
    default void onStart(TestContext<Object> context) {
        // Do nothing as default
    }

    /**
     * Method used when a failure happened.
     * <p>
     * Default implementation is to do nothing.
     * 
     * @param context
     *            the test context
     * @param af
     *            the failure
     */
    default void onFailure(TestContext<Object> context, AssertionError af) {
        // Do nothing as default
    }

    /**
     * Method used when an error happened.
     * <p>
     * Default implementation is to do nothing.
     * 
     * @param context
     *            the test context
     * @param error
     *            the error
     */
    default void onError(TestContext<Object> context, Throwable error) {
        // Do nothing as default
    }

    /**
     * Method used when an assumption error happened.
     * <p>
     * Default implementation is to do nothing.
     * 
     * @param context
     *            the test context
     * @param error
     *            the assumption error
     */
    default void onAssumptionSkip(TestContext<Object> context,
            AssumptionError error) {
        // Do nothing as default
    }

    /**
     * Method used at the end of the test.
     * <p>
     * Default implementation is to do nothing.
     * 
     * @param context
     *            the test context
     */
    default void onEnd(TestContext<Object> context) {
        // Do nothing as default
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
