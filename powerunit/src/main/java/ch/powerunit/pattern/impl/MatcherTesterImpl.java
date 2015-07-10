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
package ch.powerunit.pattern.impl;

import java.util.regex.Matcher;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.hamcrest.StringDescription;

import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestDelegator;
import ch.powerunit.TestSuite;

/**
 * @author borettim
 *
 */
@TestDelegator
public class MatcherTesterImpl implements TestSuite {
	@Parameters
	public static Stream<Object[]> getParameter(MatcherTester input) {
		Builder<Object[]> builder = Stream.builder();
		for (int i = 0; i < input.getExpectedGroup().size(); i++) {
			StringDescription sd = new StringDescription();
			input.getExpectedGroup().get(i).describeTo(sd);
			builder.add(new Object[] { input.getMatcher(),
					input.getMatcher().pattern().pattern(), input.getInput(),
					input.getHavingGroup().get(i),
					input.getExpectedGroup().get(i), sd.toString() });
		}
		return builder.build();
	}

	@Parameter(0)
	public Matcher underTest;

	@Parameter(1)
	public String patternAsString;

	@Parameter(2)
	public String inputString;

	@Parameter(3)
	public int number;

	@Parameter(4)
	public org.hamcrest.Matcher<String> expected;

	@Parameter(5)
	public String expectedDescription;

	@Test(name = "Validate that group `%4$s` matches %6$s for Pattern `%2$s` with input string `%3$s`")
	public void testGroupN() {
		try {
			assertThat(underTest.group(number)).is(expected);
		} catch (IndexOutOfBoundsException e) {
			fail("Unable to find group " + number,e);
		} catch (IllegalStateException e) {
			fail("No in a good state "+e.getMessage(),e);
		}
	}
}
