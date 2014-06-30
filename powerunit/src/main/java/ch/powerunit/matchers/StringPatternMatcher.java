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
package ch.powerunit.matchers;

import java.util.regex.Pattern;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * The matcher on string pattern.
 * 
 * @author borettim
 *
 */
public class StringPatternMatcher extends TypeSafeDiagnosingMatcher<String> {

	/**
	 * @param pattern
	 */
	public StringPatternMatcher(Pattern pattern) {
		this.pattern = pattern;
	}

	private Pattern pattern;

	@Override
	public void describeTo(Description description) {
		description.appendText("a string matching the pattern ").appendValue(
				pattern);
	}

	@Override
	protected boolean matchesSafely(String item, Description mismatchDescription) {
		if (!pattern.matcher(item).matches()) {
			mismatchDescription.appendText("but the string ").appendValue(item)
					.appendText(" was not matching ").appendValue(pattern);
			return false;
		}
		return true;
	}

}