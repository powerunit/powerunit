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
package ch.powerunit.matchers.impl;

import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestDelegator;
import ch.powerunit.TestSuite;
import ch.powerunit.matchers.MatcherTester;
import ch.powerunit.matchers.lang.MatcherAssertion;

/**
 * @author borettim
 * @since 0.3.0
 */
@TestDelegator
public final class MatcherTesterImpl<T extends Matcher<?>> implements TestSuite {
	@Parameters
	public static <T extends Matcher<?>> Stream<Object[]> getParameter(MatcherTester<T> input) {
		Builder<Object[]> b = Stream.builder();
		for (MatcherAssertion<?> ma : input.getAssertions()) {
			MatcherAssertionImpl<?> c = (MatcherAssertionImpl<?>) ma;
			b.accept(new Object[] { ma, c.getMatcher(), c.getExpected(),
					descriptionAsString(c.getExpected()), c.getNullRejected(),
					c.getNullRejectedMessage(),
					descriptionAsString(c.getNullRejectedMessage()), null,
					null, null, null });
			if (c.getAcceptedValues() != null) {
				for (Object o : c.getAcceptedValues()) {
					b.accept(new Object[] { ma, c.getMatcher(), null, null,
							null, null, null, o, null, null, null });
				}
			}
			if (c.getRejectedValue() != null) {
				for (MatcherAssertion.Reject o : c.getRejectedValue()) {
					RejectImpl r = (RejectImpl) o;
					b.accept(new Object[] { ma, c.getMatcher(), null, null,
							null, null, null, null, r.getValue(),
							r.getMessageValidation(),
							descriptionAsString(r.getMessageValidation()) });
				}
			}
		}
		return b.build()
				.map(TestSuite.DSL.addFieldToEachEntry(input.getMatcherClass()))
				.map(TestSuite.DSL
						.<BiFunction<String, Object[], Boolean>> addFieldToEachEntry(MatcherTesterImpl::filter));
	}

	@Parameter(0)
	public MatcherAssertionImpl<T> assertion;

	@Parameter(1)
	public Matcher<T> target;

	@Parameter(2)
	public Matcher<String> expectedDescription;

	@Parameter(3)
	public String expectedDescriptionAsString;

	@Parameter(4)
	public Boolean nullRejected;

	@Parameter(5)
	public Matcher<String> expectedRejectDescription;

	@Parameter(6)
	public String expectedRejectDescriptionAsString;

	@Parameter(7)
	public Object acceptedValue;

	@Parameter(8)
	public Object rejectedValue;

	@Parameter(9)
	public Matcher<String> rejectMessage;

	@Parameter(10)
	public String rejectMesageAsString;

	@Parameter(11)
	public Class<T> matcherClass;

	@Parameter(value = 12, filter = true)
	public BiFunction<String, Object[], Boolean> filter;

	private static String descriptionAsString(Matcher<?> input) {
		if (input == null) {
			return null;
		}
		StringDescription description = new StringDescription();
		input.describeTo(description);
		return description.toString();
	}

	private static boolean filter(String methodName, Object parameter[]) {
		if ("testMatcherForExpectedValue".equals(methodName)
				&& parameter[2] == null) {
			return false;
		}
		if ("testMatcherForNullIsRejected".equals(methodName)
				&& (parameter[4] == null || (Boolean) parameter[4] == false)) {
			return false;
		}
		if ("testMatcherForNullIsAccepted".equals(methodName)
				&& (parameter[4] == null || (Boolean) parameter[4] == true)) {
			return false;
		}
		if ("testMatcherForValueIsAccepted".equals(methodName)
				&& parameter[7] == null) {
			return false;
		}
		if ("testMatcherForValueIsRejected".equals(methodName)
				&& parameter[8] == null) {
			return false;
		}
		return true;
	}

	@Test(name = "Validate that for the matcher of class %12$s the expectedDescription is correct (%2$s then %4$s)")
	public void testMatcherForExpectedValue() {
		StringDescription d = new StringDescription();
		assertion.getMatcher().describeTo(d);
		assertThat("Validate the description of the matcher ", d.toString())
				.is(expectedDescription);
	}

	@Test(name = "Validate that null is rejected for the matcher of class %12$s (%2$s then %7$s)")
	public void testMatcherForNullIsRejected() {
		assertThat("Validate null is rejected by this matcher",
				assertion.getMatcher().matches(null)).is(false);
		StringDescription d = new StringDescription();
		assertion.getMatcher().describeMismatch(null, d);
		assertThat("Validate the mismatch description for this matcher",
				d.toString()).is(expectedRejectDescription);

	}

	@Test(name = "Validate that null is accepted for the matcher of class %12$s (%2$s)")
	public void testMatcherForNullIsAccepted() {
		assertThat("Validate null is accepted by this matcher",
				assertion.getMatcher().matches(null)).is(true);
	}

	@Test(name = "Validate that a value (%8$s) is accepted for the matcher of class %12$s (%2$s)")
	public void testMatcherForValueIsAccepted() {
		assertThat(
				"Validate " + acceptedValue + " is accepted by this matcher",
				assertion.getMatcher().matches(acceptedValue)).is(true);
	}

	@Test(name = "Validate that a value (%9$s) is rejected for the matcher of class %12$s (%2$s then %11$s)")
	public void testMatcherForValueIsRejected() {
		assertThat(
				"Validate " + rejectedValue + " is rejected by this matcher",
				assertion.getMatcher().matches(rejectedValue)).is(false);
		StringDescription d = new StringDescription();
		assertion.getMatcher().describeMismatch(rejectedValue, d);
		assertThat("Validate the mismatch description for this matcher",
				d.toString()).is(rejectMessage);

	}
}
