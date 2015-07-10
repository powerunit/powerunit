package ch.powerunit.pattern.impl;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

import ch.powerunit.TestInterface;

@TestInterface(MatcherTesterImpl.class)
final class MatcherTester {
	private final Matcher matcher;

	private final String input;

	private final List<Integer> havingGroup;

	private final List<org.hamcrest.Matcher<String>> expectedGroup;

	/**
	 * @param matcher
	 * @param input
	 * @param havingGroup
	 * @param expectedGroup
	 */
	public MatcherTester(Matcher matcher, String input,
			List<Integer> havingGroup,
			List<org.hamcrest.Matcher<String>> expectedGroup) {
		this.matcher = matcher;
		this.input = input;
		this.havingGroup = havingGroup;
		this.expectedGroup = expectedGroup;
	}

	/**
	 * @return the matcher
	 */
	public Matcher getMatcher() {
		return matcher;
	}

	/**
	 * @return the havingGroup
	 */
	public List<Integer> getHavingGroup() {
		return Collections.unmodifiableList(havingGroup);
	}

	/**
	 * @return the expectedGroup
	 */
	public List<org.hamcrest.Matcher<String>> getExpectedGroup() {
		return Collections.unmodifiableList(expectedGroup);
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

}
