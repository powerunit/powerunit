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
package ch.powerunit.test.base;

import ch.powerunit.Categories;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;

/**
 * @author borettim
 * 
 */
public class LaterFailureTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<LaterFailureTest> runner = new DefaultPowerUnitRunnerImpl<>(
				LaterFailureTest.class);
		runner.addListener(new BootstrapTestListener<LaterFailureTest>());
		try {
			runner.run();
		} catch (AssertionError e) {
			if (!e.getMessage().contains("Multiple failures")) {
				throw e;
			}
		}

	}

	@Categories("exampleko")
	public static class LaterFailureTest implements TestSuite {

		@Test(fastFail = false)
		public void test() {
			boolean tmp = true;
			tmp = assertThat(true).is(false);
			if (tmp) {
				throw new IllegalArgumentException("Not false");
			}
			tmp = assertWhen((p) -> {
			}).throwException(any(Throwable.class));
			if (tmp) {
				throw new IllegalArgumentException("Not false");
			}
			tmp = fail("demo");
			if (tmp) {
				throw new IllegalArgumentException("Not false");
			}
		}

	}

}
