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
package ch.powerunit.examples;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import ch.powerunit.Categories;
import ch.powerunit.TestDelegate;
import ch.powerunit.TestSuite;
import ch.powerunit.matchers.MatcherTester;
import static ch.powerunit.matchers.MatcherTester.*;

/**
 * @author borettim
 *
 */
@Categories({ "example" })
public class MatcherTesterTest implements TestSuite {
	private static class TestMatcher extends BaseMatcher<String> {

		/**
		 * @param target
		 */
		public TestMatcher(String target) {
			super();
			this.target = target;
		}

		private final String target;

		@Override
		public boolean matches(Object item) {
			if (target == null) {
				return item == null;
			}
			if (!(item instanceof String)) {
				return false;
			}
			return target.equals(item);
		}

		@Override
		public void describeTo(Description description) {
			if (target == null) {
				description.appendText("is null");
			} else {
				description.appendText("is ").appendValue(target);
			}
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.hamcrest.BaseMatcher#describeMismatch(java.lang.Object,
		 * org.hamcrest.Description)
		 */
		@Override
		public void describeMismatch(Object item, Description description) {
			description.appendText(" was ").appendValue(item);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "TestMatcher [target=" + target + "]";
		}

	}

	@TestDelegate
	public final MatcherTester<TestMatcher> tester = MatcherTester.of(
			TestMatcher.class).with(
			matcher(new TestMatcher(null)).describedAs("is null")
					.nullAccepted()
					.rejecting(value("x").withMessage(" was \"x\"")),
			matcher(new TestMatcher("x")).describedAs("is \"x\"")
					.nullRejected(" was null").accepting("x")
					.rejecting(value("y").withMessage(" was \"y\"")),
			matcher(new TestMatcher("y")).describedAs("is \"y\"")
					.nullRejected(" was null").accepting("y")
					.rejecting(value("x").withMessage(" was \"x\"")));
}
