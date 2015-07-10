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

import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.hamcrest.Matcher;

import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Rule;
import ch.powerunit.Test;
import ch.powerunit.TestDelegate;
import ch.powerunit.TestDelegator;
import ch.powerunit.TestRule;
import ch.powerunit.TestSuite;
import ch.powerunit.pattern.PatternTester;

/**
 * @author borettim
 *
 */
@TestDelegator
public class PatternTesterImpl implements TestSuite {
	@Parameters
	public static Stream<Object[]> getParameters(PatternTester input) {
		Builder<Object[]> builder = Stream.builder();
		for (int i = 0; i < input.getInputs().size(); i++) {
			builder.add(new Object[] { input.getUnderTest(),
					input.getInputs().get(i), input.getExpectedResult().get(i),
					input.getHavingGroup().get(i),
					input.getExpectedGroup().get(i),
					input.getUnderTest().pattern() });
		}
		return builder.build();
	}

	@Parameter(0)
	public Pattern underTest;

	@Parameter(1)
	public String receiving;

	@Parameter(2)
	public boolean expectedResult;

	@Parameter(3)
	public List<Integer> groups;

	@Parameter(4)
	public List<Matcher<String>> expectings;

	@Parameter(5)
	public String patternAsString;

	private java.util.regex.Matcher result;

	private boolean matches;

	@Rule
	public final TestRule rules = before(this::prepare);

	private void prepare() {
		result = underTest.matcher(receiving);
		matches = result.matches();
	}

	@TestDelegate
	public final Supplier<MatcherTester> matcherTester = () -> new MatcherTester(
			result, receiving, groups, expectings);

	@Test(name = "Validate that Pattern `%6$s` matches `%2$s` is `%3$s`")
	public void testMatches() {
		assertThat(matches).is(expectedResult);
	}
}
