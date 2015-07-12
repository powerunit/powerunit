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

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.jws.Oneway;

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

	/**
	 * Build a {@link TestListenerRule} based on the various method.
	 * 
	 * @param onStart
	 *            {@link #onStart(TestContext) the action to be done before the
	 *            test start}. If null, nothing is done.
	 * @param onEnd
	 *            {@link #onEnd(TestContext) the action to be done after the
	 *            test end}. If null, nothing is done.
	 * @param onFailure
	 *            {@link #onFailure(TestContext, AssertionError) the action to
	 *            be done in case of failure}. If null, nothing is done.
	 * @param onError
	 *            {@link #onError(TestContext, Throwable) the action to be done
	 *            in case of error}. If null, nothing is done.
	 * @param onAssumptionSkip
	 *            {@link #onAssumptionSkip(TestContext, AssumptionError) the
	 *            action to be done in case of assumption skipped}. If null
	 *            nothing is done.
	 * @return the Test Rule.
	 * @since 0.4.0
	 */
	static TestListenerRule of(Consumer<TestContext<Object>> onStart,
			Consumer<TestContext<Object>> onEnd,
			BiConsumer<TestContext<Object>, AssertionError> onFailure,
			BiConsumer<TestContext<Object>, Throwable> onError,
			BiConsumer<TestContext<Object>, AssumptionError> onAssumptionSkip) {
		return new TestListenerRule() {

			@Override
			public void onStart(TestContext<Object> context) {
				if (onStart != null) {
					onStart.accept(context);
				}
			}

			@Override
			public void onFailure(TestContext<Object> context, AssertionError af) {
				if (onFailure != null) {
					onFailure.accept(context, af);
				}
			}

			@Override
			public void onError(TestContext<Object> context, Throwable error) {
				if (onError != null) {
					onError.accept(context, error);
				}
			}

			@Override
			public void onAssumptionSkip(TestContext<Object> context,
					AssumptionError error) {
				if (onAssumptionSkip != null) {
					onAssumptionSkip.accept(context, error);
				}
			}

			@Override
			public void onEnd(TestContext<Object> context) {
				if (onEnd != null) {
					onEnd.accept(context);
				}
			}

		};
	}

	/**
	 * Build a {@link TestListenerRule} with only an action at start.
	 * 
	 * @param onStart
	 *            {@link #onStart(TestContext) the action to be done before the
	 *            test start}.
	 * @return the Test Rule.
	 * @since 0.4.0
	 */
	static TestListenerRule onStart(Consumer<TestContext<Object>> onStart) {
		return of(Objects.requireNonNull(onStart, "onStart can't be null"),
				null, null, null, null);
	}

	/**
	 * Build a {@link TestListenerRule} with only an action at end.
	 * 
	 * @param onEnd
	 *            {@link #onEnd(TestContext) the action to be done after the
	 *            test end}.
	 * @return the Test Rule.
	 * @since 0.4.0
	 */
	static TestListenerRule onEnd(Consumer<TestContext<Object>> onEnd) {
		return of(null, Objects.requireNonNull(onEnd, "onEnd can't be null"),
				null, null, null);
	}
}
