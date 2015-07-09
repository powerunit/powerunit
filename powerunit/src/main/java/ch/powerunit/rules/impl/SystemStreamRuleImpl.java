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
package ch.powerunit.rules.impl;

import java.io.PrintStream;
import java.util.Objects;

import ch.powerunit.TestContext;
import ch.powerunit.rules.SystemStreamRule;

/**
 * Implementation
 * @author borettim
 * @since 0.4.0
 */
public final class SystemStreamRuleImpl implements SystemStreamRule {

	private PrintStream oldErr;

	private PrintStream oldOut;
	
	private final PrintStream remplacementOut;
	
	private final PrintStream remplacementErr;

	/**
	 * @param remplacementOut
	 * @param remplacementErr
	 */
	public SystemStreamRuleImpl(PrintStream remplacementOut,
			PrintStream remplacementErr) {
		this.remplacementOut = Objects.requireNonNull(remplacementOut,"remplacementOut can't be null");
		this.remplacementErr = Objects.requireNonNull(remplacementErr,"remplacementErr can't be null");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ch.powerunit.rules.TestListenerRule#onStart(ch.powerunit.TestContext)
	 */
	@Override
	public void onStart(TestContext<Object> context) {
		oldErr = System.err;
		oldOut = System.out;
		try {
			System.setErr(remplacementErr);
			System.setOut(remplacementOut);
		} catch (SecurityException e) {
			// ignored
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ch.powerunit.rules.TestListenerRule#onEnd(ch.powerunit.TestContext)
	 */
	@Override
	public void onEnd(TestContext<Object> context) {
		try {
			System.setErr(oldErr);
			System.setOut(oldOut);
		} catch (SecurityException e) {
			// ignored
		}
	}

	@Override
	public PrintStream getRealSystemOut() {
		return oldOut;
	}

	@Override
	public PrintStream getRealSystemErr() {
		return oldErr;
	}

}
