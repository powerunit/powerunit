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

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.function.Function;
import java.util.regex.Pattern;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

import ch.powerunit.matchers.ExceptionMessageMatcher;
import ch.powerunit.matchers.StringPatternMatcher;
import ch.powerunit.matchers.optional.OptionalDoubleMatcher;
import ch.powerunit.matchers.optional.OptionalDoublePresentMatcher;
import ch.powerunit.matchers.optional.OptionalIntMatcher;
import ch.powerunit.matchers.optional.OptionalIntPresentMatcher;
import ch.powerunit.matchers.optional.OptionalLongMatcher;
import ch.powerunit.matchers.optional.OptionalLongPresentMatcher;
import ch.powerunit.matchers.optional.OptionalMatcher;
import ch.powerunit.matchers.optional.OptionalPresentMatcher;

/**
 * @author borettim
 *
 */
interface Matchers extends HamcrestMatchers, FileMatchers {

	/**
	 * Validate the message of an exception
	 * 
	 * @param matching
	 *            the matcher on the message
	 * @return the matcher on the exception
	 */
	default org.hamcrest.Matcher<Throwable> exceptionMessage(
			org.hamcrest.Matcher<? super String> matching) {
		return new ExceptionMessageMatcher(matching);
	}

	/**
	 * Validate the message of an exception
	 * 
	 * @param message
	 *            the expected Message
	 * @return the matcher on the exception
	 */
	default org.hamcrest.Matcher<Throwable> exceptionMessage(String message) {
		return exceptionMessage(equalTo(message));
	}

	/**
	 * Check that the passed optional is present.
	 * 
	 * @return the matcher on the optional
	 * @param <T>
	 *            the type for what is inside the optional
	 */
	default <T> org.hamcrest.Matcher<Optional<T>> optionalIsPresent() {
		return new OptionalPresentMatcher<>(is(true));
	}

	/**
	 * Check that the passed optional is not present.
	 * 
	 * @return the matcher on the optional
	 * @param <T>
	 *            the type for what is inside the optional
	 */
	default <T> org.hamcrest.Matcher<Optional<T>> optionalIsNotPresent() {
		return new OptionalPresentMatcher<>(not(true));
	}

	/**
	 * Check that the passed optional is accepted by some matcher.
	 * 
	 * @param subMatcher
	 *            the matcher on the data
	 * @return the matcher on the optional
	 * @param <T>
	 *            the type for what is inside the optional
	 */
	default <T> org.hamcrest.Matcher<Optional<? super T>> optionalIs(
			org.hamcrest.Matcher<? super T> subMatcher) {
		return both((org.hamcrest.Matcher) optionalIsPresent()).and(
				new OptionalMatcher<T>(subMatcher));
	}

	/**
	 * Check that the passed optional is some target value.
	 * 
	 * @param target
	 *            the target value
	 * @return the matcher on the optional
	 * @param <T>
	 *            the type for what is inside the optional
	 */
	default <T> org.hamcrest.Matcher<Optional<? super T>> optionalIs(T target) {
		return optionalIs(is(target));
	}

	/**
	 * Check that the passed optional is present.
	 * 
	 * @return the matcher on the optional
	 */
	default org.hamcrest.Matcher<OptionalDouble> optionalDoubleIsPresent() {
		return new OptionalDoublePresentMatcher(is(true));
	}

	/**
	 * Check that the passed optional is not present.
	 * 
	 * @return the matcher on the optional
	 */
	default org.hamcrest.Matcher<OptionalDouble> optionalDoubleIsNotPresent() {
		return new OptionalDoublePresentMatcher(not(true));
	}

	/**
	 * Check that the passed optional is accepted by some matcher.
	 * 
	 * @param subMatcher
	 *            the matcher on the data
	 * @return the matcher on the optional
	 */
	default org.hamcrest.Matcher<OptionalDouble> optionalDoubleIs(
			org.hamcrest.Matcher<? super Double> subMatcher) {
		return both((org.hamcrest.Matcher) optionalDoubleIsPresent()).and(
				new OptionalDoubleMatcher(subMatcher));
	}

	/**
	 * Check that the passed optional is present.
	 * 
	 * @return the matcher on the optional
	 */
	default org.hamcrest.Matcher<OptionalInt> optionalIntIsPresent() {
		return new OptionalIntPresentMatcher(is(true));
	}

	/**
	 * Check that the passed optional is not present.
	 * 
	 * @return the matcher on the optional
	 */
	default org.hamcrest.Matcher<OptionalInt> optionalIntIsNotPresent() {
		return new OptionalIntPresentMatcher(not(true));
	}

	/**
	 * Check that the passed optional is accepted by some matcher.
	 * 
	 * @param subMatcher
	 *            the matcher on the data
	 * @return the matcher on the optional
	 */
	default org.hamcrest.Matcher<OptionalInt> optionalIntIs(
			org.hamcrest.Matcher<? super Integer> subMatcher) {
		return both((org.hamcrest.Matcher) optionalIntIsPresent()).and(
				new OptionalIntMatcher(subMatcher));
	}

	/**
	 * Check that the passed optional is some target value.
	 * 
	 * @param target
	 *            the target value
	 * @return the matcher on the optional
	 */
	default org.hamcrest.Matcher<OptionalInt> optionalIntIs(Integer target) {
		return optionalIntIs(is(target));
	}

	/**
	 * Check that the passed optional is present.
	 * 
	 * @return the matcher on the optional
	 */
	default org.hamcrest.Matcher<OptionalLong> optionalLongIsPresent() {
		return new OptionalLongPresentMatcher(is(true));
	}

	/**
	 * Check that the passed optional is not present.
	 * 
	 * @return the matcher on the optional
	 */
	default org.hamcrest.Matcher<OptionalLong> optionalLongIsNotPresent() {
		return new OptionalLongPresentMatcher(not(true));
	}

	/**
	 * Check that the passed optional is accepted by some matcher.
	 * 
	 * @param subMatcher
	 *            the matcher on the data
	 * @return the matcher on the optional
	 */
	default org.hamcrest.Matcher<OptionalLong> optionalLongIs(
			org.hamcrest.Matcher<? super Long> subMatcher) {
		return both((org.hamcrest.Matcher) optionalLongIsPresent()).and(
				new OptionalLongMatcher(subMatcher));
	}

	/**
	 * Check that the passed optional is some target value.
	 * 
	 * @param target
	 *            the target value
	 * @return the matcher on the optional
	 */
	default org.hamcrest.Matcher<OptionalLong> optionalLongIs(Long target) {
		return optionalLongIs(is(target));
	}

	/**
	 * Validate a string with a {@link java.util.regex.Pattern}.
	 * 
	 * <pre>
	 * assertThat(&quot;abc&quot;).is(matchesRegex(Pattern.compile(&quot;&circ;[a-z]$&quot;));
	 * </pre>
	 * 
	 * @param pattern
	 *            the pattern to be used.
	 * @return The matcher.
	 */
	default Matcher<String> matchesRegex(Pattern pattern) {
		return new StringPatternMatcher(pattern);
	}

	/**
	 * Validate a string with a regex.
	 * 
	 * <pre>
	 * assertThat(&quot;abc&quot;).is matchesRegex(&quot;&circ;[a-z]+$&quot;));
	 * </pre>
	 * 
	 * @param regex
	 *            The regex to be used for the validation.
	 * @return The matcher.
	 */
	default Matcher<String> matchesRegex(String regex) {
		return matchesRegex(Pattern.compile(regex));
	}

	/**
	 * Create a feature matcher, on the fly.
	 * 
	 * @param clazz
	 *            the clazz of the object supported by the Matcher.
	 * @param featureClazz
	 *            the clazz of the feature.
	 * @param featureName
	 *            the name of the feature.
	 * @param featureExtractor
	 *            the {@link Function} to extract the feature.
	 * @param subMatcher
	 *            the matcher to be applied on the feature.
	 * @return the matcher.
	 * @param <T>
	 *            the type for the matcher.
	 * @param <F>
	 *            the feature returned by the extractor.
	 * @since 0.4.0
	 */
	default <T, F> Matcher<T> featureMatcher(Class<T> clazz,
			Class<F> featureClazz, String featureName,
			Function<T, F> featureExtractor, Matcher<? super F> subMatcher) {
		return new FeatureMatcher<T, F>(subMatcher, featureName, featureName) {

			@Override
			protected F featureValueOf(T actual) {
				return featureExtractor.apply(actual);
			}
		};
	}
}
