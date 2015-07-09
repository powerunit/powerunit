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

import java.io.PrintStream;

import ch.powerunit.rules.impl.NullOutputStream;
import ch.powerunit.rules.impl.SystemStreamRuleImpl;

/**
 * This is a test rule to change the system {@link java.lang.System#out out}/
 * {@link java.lang.System#err err} stream.
 * <p>
 * This rule will replace the stream with the passed one ; In case it is not
 * possible to change the stream (because of a
 * {@link java.lang.SecurityException}) no change is done and the exception is
 * ignored.
 * 
 * @author borettim
 * @since 0.4.0
 */
public interface SystemStreamRule extends TestListenerRule {
	/**
	 * Return the {@link java.lang.System#out system out} that was set before
	 * entering the rule.
	 * 
	 * @return the original {@link java.lang.System#out system out}.
	 */
	PrintStream getRealSystemOut();

	/**
	 * Return the {@link java.lang.System#err system err} that was set before
	 * entering the rule.
	 * 
	 * @return the original {@link java.lang.System#err system err}.
	 */
	PrintStream getRealSystemErr();

	/**
	 * Provide a test rule that suppress both {@link java.lang.System#err system
	 * err} and {@link java.lang.System#out system out}.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 */
	static SystemStreamRule disableBothStreams() {
		return replaceBothStream(new PrintStream(new NullOutputStream()),
				new PrintStream(new NullOutputStream()));
	}

	/**
	 * Provide a test rule that replace both {@link java.lang.System#err system
	 * err} and {@link java.lang.System#out system out} with the provided one.
	 * 
	 * @param outReplacement
	 *            the replacement of the {@link java.lang.System#out system out}
	 *            stream.
	 * @param errRemplacement
	 *            the replacement of the {@link java.lang.System#err system err}
	 *            stream.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 */
	static SystemStreamRule replaceBothStream(PrintStream outReplacement,
			PrintStream errRemplacement) {
		return new SystemStreamRuleImpl(outReplacement, errRemplacement);
	}

	/**
	 * Provide a test rule that suppress the {@link java.lang.System#out system
	 * out} stream.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 */
	static SystemStreamRule disableOutStream() {
		return replaceOutStream(new PrintStream(new NullOutputStream()));
	}

	/**
	 * Provide a test rule that suppress the {@link java.lang.System#err system
	 * err} stream.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 */
	static SystemStreamRule disableErrStream() {
		return replaceErrStream(new PrintStream(new NullOutputStream()));
	}

	/**
	 * Privde a test rule that replace the {@link java.lang.System#out system
	 * out} stream with the provided one
	 * 
	 * @param outReplacement
	 *            the replacement of the {@link java.lang.System#out system out}
	 *            stream.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 */
	static SystemStreamRule replaceOutStream(PrintStream outReplacement) {
		return replaceBothStream(outReplacement, System.err);
	}

	/**
	 * Privde a test rule that replace the {@link java.lang.System#err system
	 * err} stream with the provided one
	 * 
	 * @param errReplacement
	 *            the replacement of the {@link java.lang.System#err system err}
	 *            stream.
	 * 
	 * @see ch.powerunit.rules.SystemStreamRule The complete description of the
	 *      functionnality of the rule.
	 * 
	 * @return the test rule.
	 */
	static SystemStreamRule replaceErrStream(PrintStream errReplacement) {
		return replaceBothStream(System.out, errReplacement);
	}

}
