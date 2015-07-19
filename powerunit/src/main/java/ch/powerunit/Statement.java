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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.function.Consumer;

import ch.powerunit.exception.InternalError;

/**
 * Definition of a statement (piece of code that can thrown Throwable).
 * <p>
 * A statement can be used inside test (to isolate a code that must thrown an
 * exception) and are used internally by the framework to compose and execute
 * test sequence element.
 *
 * @author borettim
 * @param <P>
 *            The type of the parameter
 * @param <T>
 *            the exception type
 */
@FunctionalInterface
public interface Statement<P, T extends Throwable> {

	/**
	 * Executable code.
	 * 
	 * @param parameter
	 *            A parameter for the statement
	 * @throws Throwable
	 *             in case of error.
	 */
	void run(P parameter) throws Throwable;// should be T, but T seem to produce
											// a bug in the compiler

	/**
	 * Used to provide a name (for internal use purpose).
	 * 
	 * @return the string, by default null.
	 * @since 0.1.0
	 */
	default String getName() {
		return null;
	}

	/**
	 * Aggregate this statement and then the following. The second statement is
	 * done, even in case of exception in the first one.
	 * 
	 * @param after
	 *            the next statement
	 * @return the new statement
	 */
	default Statement<P, T> andThenAlways(Statement<P, T> after) {
		Objects.requireNonNull(after);
		return (p) -> {
			try {
				run(p);
			} finally {
				after.run(p);
			}
		};
	}

	/**
	 * Aggregate this statement and then the following. The second statement is
	 * done except in case of exception in the first one.
	 * 
	 * @param after
	 *            the next statement
	 * @return the new statement
	 */
	default Statement<P, T> andThenOnlySuccess(Statement<P, T> after) {
		Objects.requireNonNull(after);
		return (p) -> {
			run(p);
			after.run(p);
		};
	}

	/**
	 * Build a around statement (do something, then something others, and after
	 * one a third statement, event in case of exception.
	 * 
	 * @param internal
	 *            the internal part
	 * @param before
	 *            the first statement
	 * @param after
	 *            the last statement, done event in case of exception.
	 * @return the new statement.
	 * @param <P>
	 *            The type of the parameter
	 * @param <T>
	 *            the exception type
	 */
	static <P, T extends Throwable> Statement<P, T> around(
			Statement<P, T> internal, Statement<P, T> before,
			Statement<P, T> after) {
		Objects.requireNonNull(internal);
		Objects.requireNonNull(before);
		Objects.requireNonNull(after);
		return before.andThenOnlySuccess(internal).andThenAlways(after);
	}

	/**
	 * Build a statement based on a method-
	 * 
	 * @param target
	 *            the target object
	 * @param method
	 *            the method
	 * @return the new statement.
	 * @param <P>
	 *            The type of the parameter
	 * @param <T>
	 *            the exception type
	 */
	static <P, T extends Throwable> Statement<P, T> reflectionMethod(
			Object target, Method method) {
		Objects.requireNonNull(target);
		Objects.requireNonNull(method);
		return new Statement<P, T>() {

			@Override
			public void run(P parameter) throws Throwable {
				try {
					method.invoke(target);
				} catch (InvocationTargetException e) {
					throw e.getCause();
				} catch (IllegalAccessException | IllegalArgumentException e) {
					throw new InternalError("Unexpected error "
							+ e.getMessage(), e);
				}
			}

			@Override
			public String getName() {
				return method.getName();
			}
		};
	}

	/**
	 * Build a statement based on a method-
	 * 
	 * @param method
	 *            the method
	 * @param param
	 *            the param
	 * @return the new statement.
	 * @param <P>
	 *            The type of the parameter
	 * @param <T>
	 *            the exception type
	 * @since 0.2.0
	 */
	static <P, T extends Throwable> Statement<P, T> reflectionMethod(
			Consumer<Object> method, Object param) {
		Objects.requireNonNull(method);
		return new Statement<P, T>() {

			@Override
			public void run(P parameter) throws Throwable {
				method.accept(param);
			}

			@Override
			public String getName() {
				return "N/A";
			}
		};
	}

}
