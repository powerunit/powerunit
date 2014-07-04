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

/**
 * @author borettim
 *
 */
interface HamcrestMatchers {

    /**
     * Creates a matcher that matches if the examined object matches <b>ALL</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, allOf(startsWith(&quot;my&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param matchers
     *            the list of matcher to be applied
     * @return the allOf Matcher
     */
    default <T> org.hamcrest.Matcher<T> allOf(
            java.lang.Iterable<org.hamcrest.Matcher<? super T>> matchers) {
        return org.hamcrest.core.AllOf.<T> allOf(matchers);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ALL</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, allOf(startsWith(&quot;my&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param matchers
     *            the list of matcher to be applied
     * @return the allOf Matcher
     */
    default <T> org.hamcrest.Matcher<T> allOf(
            org.hamcrest.Matcher<? super T>... matchers) {
        return org.hamcrest.core.AllOf.<T> allOf(matchers);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ALL</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, allOf(startsWith(&quot;my&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param first
     *            the first Matcher
     * @param second
     *            the second Matcher
     * @return the allOf Matcher
     */
    default <T> org.hamcrest.Matcher<T> allOf(
            org.hamcrest.Matcher<? super T> first,
            org.hamcrest.Matcher<? super T> second) {
        return org.hamcrest.core.AllOf.<T> allOf(first, second);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ALL</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, allOf(startsWith(&quot;my&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param first
     *            the first Matcher
     * @param second
     *            the second Matcher
     * @param third
     *            the third Matcher
     * @return the allOf Matcher
     */
    default <T> org.hamcrest.Matcher<T> allOf(
            org.hamcrest.Matcher<? super T> first,
            org.hamcrest.Matcher<? super T> second,
            org.hamcrest.Matcher<? super T> third) {
        return org.hamcrest.core.AllOf.<T> allOf(first, second, third);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ALL</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, allOf(startsWith(&quot;my&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param first
     *            the first Matcher
     * @param second
     *            the second Matcher
     * @param third
     *            the third Matcher
     * @param fourth
     *            the fourth Matcher
     * @return the allOf Matcher
     */
    default <T> org.hamcrest.Matcher<T> allOf(
            org.hamcrest.Matcher<? super T> first,
            org.hamcrest.Matcher<? super T> second,
            org.hamcrest.Matcher<? super T> third,
            org.hamcrest.Matcher<? super T> fourth) {
        return org.hamcrest.core.AllOf.<T> allOf(first, second, third, fourth);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ALL</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, allOf(startsWith(&quot;my&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param first
     *            the first Matcher
     * @param second
     *            the second Matcher
     * @param third
     *            the third Matcher
     * @param fourth
     *            the fourth Matcher
     * @param fifth
     *            the fifth Matcher
     * @return the allOf Matcher
     */
    default <T> org.hamcrest.Matcher<T> allOf(
            org.hamcrest.Matcher<? super T> first,
            org.hamcrest.Matcher<? super T> second,
            org.hamcrest.Matcher<? super T> third,
            org.hamcrest.Matcher<? super T> fourth,
            org.hamcrest.Matcher<? super T> fifth) {
        return org.hamcrest.core.AllOf.<T> allOf(first, second, third, fourth,
                fifth);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ALL</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, allOf(startsWith(&quot;my&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param first
     *            the first Matcher
     * @param second
     *            the second Matcher
     * @param third
     *            the third Matcher
     * @param fourth
     *            the fourth Matcher
     * @param fifth
     *            the fifth Matcher
     * @param sixth
     *            the sixth Matcher
     * @return the allOf Matcher
     */
    default <T> org.hamcrest.Matcher<T> allOf(
            org.hamcrest.Matcher<? super T> first,
            org.hamcrest.Matcher<? super T> second,
            org.hamcrest.Matcher<? super T> third,
            org.hamcrest.Matcher<? super T> fourth,
            org.hamcrest.Matcher<? super T> fifth,
            org.hamcrest.Matcher<? super T> sixth) {
        return org.hamcrest.core.AllOf.<T> allOf(first, second, third, fourth,
                fifth, sixth);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ANY</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, anyOf(startsWith(&quot;foo&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param matchers
     *            the list of matcher to be applied
     * @return the anyOf Matcher
     */
    default <T> org.hamcrest.core.AnyOf<T> anyOf(
            java.lang.Iterable<org.hamcrest.Matcher<? super T>> matchers) {
        return org.hamcrest.core.AnyOf.<T> anyOf(matchers);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ANY</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, anyOf(startsWith(&quot;foo&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param first
     *            the first Matcher
     * @param second
     *            the second Matcher
     * @param third
     *            the third Matcher
     * @return the anyOf Matcher
     */
    default <T> org.hamcrest.core.AnyOf<T> anyOf(org.hamcrest.Matcher<T> first,
            org.hamcrest.Matcher<? super T> second,
            org.hamcrest.Matcher<? super T> third) {
        return org.hamcrest.core.AnyOf.<T> anyOf(first, second, third);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ANY</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, anyOf(startsWith(&quot;foo&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param first
     *            the first Matcher
     * @param second
     *            the second Matcher
     * @param third
     *            the third Matcher
     * @param fourth
     *            the fourth Matcher
     * @return the anyOf Matcher
     */
    default <T> org.hamcrest.core.AnyOf<T> anyOf(org.hamcrest.Matcher<T> first,
            org.hamcrest.Matcher<? super T> second,
            org.hamcrest.Matcher<? super T> third,
            org.hamcrest.Matcher<? super T> fourth) {
        return org.hamcrest.core.AnyOf.<T> anyOf(first, second, third, fourth);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ANY</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, anyOf(startsWith(&quot;foo&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param first
     *            the first Matcher
     * @param second
     *            the second Matcher
     * @param third
     *            the third Matcher
     * @param fourth
     *            the fourth Matcher
     * @param fifth
     *            the fifth Matcher
     * @return the anyOf Matcher
     */
    default <T> org.hamcrest.core.AnyOf<T> anyOf(org.hamcrest.Matcher<T> first,
            org.hamcrest.Matcher<? super T> second,
            org.hamcrest.Matcher<? super T> third,
            org.hamcrest.Matcher<? super T> fourth,
            org.hamcrest.Matcher<? super T> fifth) {
        return org.hamcrest.core.AnyOf.<T> anyOf(first, second, third, fourth,
                fifth);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ANY</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, anyOf(startsWith(&quot;foo&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param first
     *            the first Matcher
     * @param second
     *            the second Matcher
     * @param third
     *            the third Matcher
     * @param fourth
     *            the fourth Matcher
     * @param fifth
     *            the fifth Matcher
     * @param sixth
     *            the sixth Matcher
     * @return the anyOf Matcher
     */
    default <T> org.hamcrest.core.AnyOf<T> anyOf(org.hamcrest.Matcher<T> first,
            org.hamcrest.Matcher<? super T> second,
            org.hamcrest.Matcher<? super T> third,
            org.hamcrest.Matcher<? super T> fourth,
            org.hamcrest.Matcher<? super T> fifth,
            org.hamcrest.Matcher<? super T> sixth) {
        return org.hamcrest.core.AnyOf.<T> anyOf(first, second, third, fourth,
                fifth, sixth);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ANY</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, anyOf(startsWith(&quot;foo&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param first
     *            the first Matcher
     * @param second
     *            the second Matcher
     * @return the anyof matcher
     */
    default <T> org.hamcrest.core.AnyOf<T> anyOf(org.hamcrest.Matcher<T> first,
            org.hamcrest.Matcher<? super T> second) {
        return org.hamcrest.core.AnyOf.<T> anyOf(first, second);
    }

    /**
     * Creates a matcher that matches if the examined object matches <b>ANY</b>
     * of the specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myValue&quot;, anyOf(startsWith(&quot;foo&quot;), containsString(&quot;Val&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The type of the object
     * @param matchers
     *            the matchers
     * @return the anyOf Matcher
     */
    default <T> org.hamcrest.core.AnyOf<T> anyOf(
            org.hamcrest.Matcher<? super T>... matchers) {
        return org.hamcrest.core.AnyOf.<T> anyOf(matchers);
    }

    /**
     * Creates a matcher that matches when both of the specified matchers match
     * the examined object.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;fab&quot;, both(containsString(&quot;a&quot;)).and(containsString(&quot;b&quot;)))
     * </pre>
     * 
     * @param <LHS>
     *            Left Hand Side
     * @param matcher
     *            the second matcher
     * @return the combined matcher
     */
    default <LHS> org.hamcrest.core.CombinableMatcher.CombinableBothMatcher<LHS> both(
            org.hamcrest.Matcher<? super LHS> matcher) {
        return org.hamcrest.core.CombinableMatcher.<LHS> both(matcher);
    }

    /**
     * Creates a matcher that matches when either of the specified matchers
     * match the examined object.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;fan&quot;, either(containsString(&quot;a&quot;)).and(containsString(&quot;b&quot;)))
     * </pre>
     * 
     * @param <LHS>
     *            Left Hand Side
     * @param matcher
     *            the second matcher
     * @return the combined matcher
     */
    default <LHS> org.hamcrest.core.CombinableMatcher.CombinableEitherMatcher<LHS> either(
            org.hamcrest.Matcher<? super LHS> matcher) {
        return org.hamcrest.core.CombinableMatcher.<LHS> either(matcher);
    }

    /**
     * Wraps an existing matcher, overriding its description with that
     * specified. All other functions are delegated to the decorated matcher,
     * including its mismatch description.
     * <p>
     * For example:
     * 
     * <pre>
     * describedAs(&quot;a big decimal equal to %0&quot;, equalTo(myBigDecimal),
     *         myBigDecimal.toPlainString())
     * </pre>
     * 
     * @param <T>
     *            the type of the object
     * @param description
     *            the new description for the wrapped matcher
     * @param matcher
     *            the matcher to wrap
     * @param values
     *            optional values to insert into the tokenised description
     * @return the matcher with description
     */
    default <T> org.hamcrest.Matcher<T> describedAs(
            java.lang.String description, org.hamcrest.Matcher<T> matcher,
            java.lang.Object... values) {
        return org.hamcrest.core.DescribedAs.<T> describedAs(description,
                matcher, values);
    }

    /**
     * Creates a matcher for {@link Iterable}s that only matches when a single
     * pass over the examined {@link Iterable} yields items that are all matched
     * by the specified <code>itemMatcher</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;bar&quot;, &quot;baz&quot;), everyItem(startsWith(&quot;ba&quot;)))
     * </pre>
     * 
     * @param <U>
     *            The item type
     * @param itemMatcher
     *            the matcher to apply to every item provided by the examined
     *            {@link Iterable}
     * @return the matcher on iterable
     */
    default <U> org.hamcrest.Matcher<java.lang.Iterable<U>> everyItem(
            org.hamcrest.Matcher<U> itemMatcher) {
        return org.hamcrest.core.Every.<U> everyItem(itemMatcher);
    }

    /**
     * A shortcut to the frequently used <code>is(equalTo(x))</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(cheese, is(smelly))
     * </pre>
     * 
     * instead of:
     * 
     * <pre>
     * assertThat(cheese, is(equalTo(smelly)))
     * </pre>
     * 
     * @param <T>
     *            The type of data.
     * @param value
     *            the data
     * @return the matcher on data
     */
    default <T> org.hamcrest.Matcher<T> is(T value) {
        return org.hamcrest.core.Is.<T> is(value);
    }

    /**
     * Decorates another Matcher, retaining its behaviour, but allowing tests to
     * be slightly more expressive.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(cheese, is(equalTo(smelly)))
     * </pre>
     * 
     * instead of:
     * 
     * <pre>
     * assertThat(cheese, equalTo(smelly))
     * </pre>
     * 
     * @param <T>
     *            The type of data.
     * @param matcher
     *            the matcher on data
     * @return the matcher on data
     */
    default <T> org.hamcrest.Matcher<T> is(org.hamcrest.Matcher<T> matcher) {
        return org.hamcrest.core.Is.<T> is(matcher);
    }

    /**
     * A shortcut to the frequently used
     * <code>is(instanceOf(SomeClass.class))</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(cheese, isA(Cheddar.class))
     * </pre>
     * 
     * instead of:
     * 
     * <pre>
     * assertThat(cheese, is(instanceOf(Cheddar.class)))
     * </pre>
     * 
     * @param <T>
     *            The type of data.
     * @param type
     *            the expected data type
     * @return the matcher on data
     */
    default <T> org.hamcrest.Matcher<T> isA(java.lang.Class<T> type) {
        return org.hamcrest.core.Is.<T> isA(type);
    }

    /**
     * Creates a matcher that always matches, regardless of the examined object.
     * 
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.Object> anything() {
        return org.hamcrest.core.IsAnything.anything();
    }

    /**
     * Creates a matcher that always matches, regardless of the examined object,
     * but describes itself with the specified {@link String}.
     * 
     * @param description
     *            a meaningful {@link String} used when describing itself
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.Object> anything(
            java.lang.String description) {
        return org.hamcrest.core.IsAnything.anything(description);
    }

    /**
     * Creates a matcher for {@link Iterable}s that only matches when a single
     * pass over the examined {@link Iterable} yields at least one item that is
     * equal to the specified <code>item</code>. Whilst matching, the traversal
     * of the examined {@link Iterable} will stop as soon as a matching item is
     * found.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;), hasItem(&quot;bar&quot;))
     * </pre>
     * 
     * @param <T>
     *            element Type
     * @param item
     *            the item to compare against the items provided by the examined
     *            {@link Iterable}
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<java.lang.Iterable<? super T>> hasItem(
            T item) {
        return org.hamcrest.core.IsCollectionContaining.<T> hasItem(item);
    }

    /**
     * Creates a matcher for {@link Iterable}s that only matches when a single
     * pass over the examined {@link Iterable} yields at least one item that is
     * matched by the specified <code>itemMatcher</code>. Whilst matching, the
     * traversal of the examined {@link Iterable} will stop as soon as a
     * matching item is found.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;), hasItem(startsWith(&quot;ba&quot;)))
     * </pre>
     * 
     * @param <T>
     *            the element type
     * @param itemMatcher
     *            the matcher to apply to items provided by the examined
     *            {@link Iterable}
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<java.lang.Iterable<? super T>> hasItem(
            org.hamcrest.Matcher<? super T> itemMatcher) {
        return org.hamcrest.core.IsCollectionContaining
                .<T> hasItem(itemMatcher);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when consecutive
     * passes over the examined {@link Iterable} yield at least one item that is
     * equal to the corresponding item from the specified <code>items</code>.
     * Whilst matching, each traversal of the examined {@link Iterable} will
     * stop as soon as a matching item is found.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;, &quot;baz&quot;), hasItems(&quot;baz&quot;, &quot;foo&quot;))
     * </pre>
     * 
     * @param <T>
     *            the element type.
     * @param items
     *            the items to compare against the items provided by the
     *            examined {@link Iterable}
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<java.lang.Iterable<T>> hasItems(T... items) {
        return org.hamcrest.core.IsCollectionContaining.<T> hasItems(items);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when consecutive
     * passes over the examined {@link Iterable} yield at least one item that is
     * matched by the corresponding matcher from the specified
     * <code>itemMatchers</code>. Whilst matching, each traversal of the
     * examined {@link Iterable} will stop as soon as a matching item is found.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;, &quot;baz&quot;),
     *         hasItems(endsWith(&quot;z&quot;), endsWith(&quot;o&quot;)))
     * </pre>
     * 
     * @param <T>
     *            the element type
     * @param itemMatchers
     *            the matchers to apply to items provided by the examined
     *            {@link Iterable}
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<java.lang.Iterable<T>> hasItems(
            org.hamcrest.Matcher<? super T>... itemMatchers) {
        return org.hamcrest.core.IsCollectionContaining
                .<T> hasItems(itemMatchers);
    }

    /**
     * Creates a matcher that matches when the examined object is logically
     * equal to the specified <code>operand</code>, as determined by calling the
     * {@link java.lang.Object#equals} method on the <b>examined</b> object.
     * 
     * <p>
     * If the specified operand is <code>null</code> then the created matcher
     * will only match if the examined object's <code>equals</code> method
     * returns <code>true</code> when passed a <code>null</code> (which would be
     * a violation of the <code>equals</code> contract), unless the examined
     * object itself is <code>null</code>, in which case the matcher will return
     * a positive match.
     * 
     * <p>
     * The created matcher provides a special behaviour when examining
     * <code>Array</code>s, whereby it will match if both the operand and the
     * examined object are arrays of the same length and contain items that are
     * equal to each other (according to the above rules) <b>in the same
     * indexes</b>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;foo&quot;, equalTo(&quot;foo&quot;));
     * assertThat(new String[] { &quot;foo&quot;, &quot;bar&quot; },
     *         equalTo(new String[] { &quot;foo&quot;, &quot;bar&quot; }));
     * </pre>
     * 
     * @param <T>
     *            The type
     * @param operand
     *            the target value.
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> equalTo(T operand) {
        return org.hamcrest.core.IsEqual.<T> equalTo(operand);
    }

    /**
     * Creates a matcher that matches when the examined object is an instance of
     * the specified <code>type</code>, as determined by calling the
     * {@link java.lang.Class#isInstance(Object)} method on that type, passing
     * the the examined object.
     * 
     * <p>
     * The created matcher forces a relationship between specified type and the
     * examined object, and should be used when it is necessary to make generics
     * conform, for example in the JMock clause
     * <code>with(any(Thing.class))</code>
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new Canoe(), instanceOf(Canoe.class));
     * </pre>
     * 
     * @param <T>
     *            The type
     * @param type
     *            the type for the instanceOf
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> any(java.lang.Class<T> type) {
        return org.hamcrest.core.IsInstanceOf.<T> any(type);
    }

    /**
     * Creates a matcher that matches when the examined object is an instance of
     * the specified <code>type</code>, as determined by calling the
     * {@link java.lang.Class#isInstance(Object)} method on that type, passing
     * the the examined object.
     * 
     * <p>
     * The created matcher assumes no relationship between specified type and
     * the examined object.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new Canoe(), instanceOf(Paddlable.class));
     * </pre>
     * 
     * @param <T>
     *            The type
     * @param type
     *            the type for the instanceof
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> instanceOf(java.lang.Class<?> type) {
        return org.hamcrest.core.IsInstanceOf.<T> instanceOf(type);
    }

    /**
     * Creates a matcher that wraps an existing matcher, but inverts the logic
     * by which it will match.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(cheese, is(not(equalTo(smelly))))
     * </pre>
     * 
     * @param <T>
     *            The type
     * @param matcher
     *            the matcher whose sense should be inverted
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> not(org.hamcrest.Matcher<T> matcher) {
        return org.hamcrest.core.IsNot.<T> not(matcher);
    }

    /**
     * A shortcut to the frequently used <code>not(equalTo(x))</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(cheese, is(not(smelly)))
     * </pre>
     * 
     * instead of:
     * 
     * <pre>
     * assertThat(cheese, is(not(equalTo(smelly))))
     * </pre>
     * 
     * @param <T>
     *            The type
     * @param value
     *            the value that any examined object should <b>not</b> equal
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> not(T value) {
        return org.hamcrest.core.IsNot.<T> not(value);
    }

    /**
     * Creates a matcher that matches if examined object is <code>null</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(cheese, is(nullValue())
     * </pre>
     * 
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.Object> nullValue() {
        return org.hamcrest.core.IsNull.nullValue();
    }

    /**
     * Creates a matcher that matches if examined object is <code>null</code>.
     * Accepts a single dummy argument to facilitate type inference.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(cheese, is(nullValue(Cheese.class))
     * </pre>
     * 
     * @param <T>
     *            The type
     * @param type
     *            dummy parameter used to infer the generic type of the returned
     *            matcher
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> nullValue(java.lang.Class<T> type) {
        return org.hamcrest.core.IsNull.<T> nullValue(type);
    }

    /**
     * A shortcut to the frequently used <code>not(nullValue())</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(cheese, is(notNullValue()))
     * </pre>
     * 
     * instead of:
     * 
     * <pre>
     * assertThat(cheese, is(not(nullValue())))
     * </pre>
     * 
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.Object> notNullValue() {
        return org.hamcrest.core.IsNull.notNullValue();
    }

    /**
     * A shortcut to the frequently used
     * <code>not(nullValue(X.class)). Accepts a
     * single dummy argument to facilitate type inference.</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(cheese, is(notNullValue(X.class)))
     * </pre>
     * 
     * instead of:
     * 
     * <pre>
     * assertThat(cheese, is(not(nullValue(X.class))))
     * </pre>
     * 
     * @param <T>
     *            The type
     * @param type
     *            dummy parameter used to infer the generic type of the returned
     *            matcher
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> notNullValue(java.lang.Class<T> type) {
        return org.hamcrest.core.IsNull.<T> notNullValue(type);
    }

    /**
     * Creates a matcher that matches only when the examined object is the same
     * instance as the specified target object.
     * 
     * @param <T>
     *            The type
     * @param target
     *            the target instance against which others should be assessed
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> sameInstance(T target) {
        return org.hamcrest.core.IsSame.<T> sameInstance(target);
    }

    /**
     * Creates a matcher that matches only when the examined object is the same
     * instance as the specified target object.
     * 
     * @param <T>
     *            The type
     * @param target
     *            the target instance against which others should be assessed
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> theInstance(T target) {
        return org.hamcrest.core.IsSame.<T> theInstance(target);
    }

    /**
     * Creates a matcher that matches if the examined {@link String} contains
     * the specified {@link String} anywhere.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myStringOfNote&quot;, containsString(&quot;ring&quot;))
     * </pre>
     * 
     * @param substring
     *            the substring that the returned matcher will expect to find
     *            within any examined string
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.String> containsString(
            java.lang.String substring) {
        return org.hamcrest.core.StringContains.containsString(substring);
    }

    /**
     * Creates a matcher that matches if the examined {@link String} starts with
     * the specified {@link String}.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myStringOfNote&quot;, startsWith(&quot;my&quot;))
     * </pre>
     * 
     * @param prefix
     *            the substring that the returned matcher will expect at the
     *            start of any examined string
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.String> startsWith(
            java.lang.String prefix) {
        return org.hamcrest.core.StringStartsWith.startsWith(prefix);
    }

    /**
     * Creates a matcher that matches if the examined {@link String} ends with
     * the specified {@link String}.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myStringOfNote&quot;, endsWith(&quot;Note&quot;))
     * </pre>
     * 
     * @param suffix
     *            the substring that the returned matcher will expect at the end
     *            of any examined string
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.String> endsWith(
            java.lang.String suffix) {
        return org.hamcrest.core.StringEndsWith.endsWith(suffix);
    }

    /**
     * Creates a matcher that matches arrays whose elements are satisfied by the
     * specified matchers. Matches positively only if the number of matchers
     * specified is equal to the length of the examined array and each
     * matcher[i] is satisfied by array[i].
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new Integer[] { 1, 2, 3 },
     *         is(array(equalTo(1), equalTo(2), equalTo(3))))
     * </pre>
     * 
     * @param <T>
     *            The element type
     * @param elementMatchers
     *            the matchers that the elements of examined arrays should
     *            satisfy
     * @return the matcher
     */
    default <T> org.hamcrest.collection.IsArray<T> array(
            org.hamcrest.Matcher<? super T>... elementMatchers) {
        return org.hamcrest.collection.IsArray.<T> array(elementMatchers);
    }

    /**
     * A shortcut to the frequently used <code>hasItemInArray(equalTo(x))</code>
     * .
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(hasItemInArray(x))
     * </pre>
     * 
     * instead of:
     * 
     * <pre>
     * assertThat(hasItemInArray(equalTo(x)))
     * </pre>
     * 
     * @param <T>
     *            The element type
     * @param element
     *            the element that should be present in examined arrays
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T[]> hasItemInArray(T element) {
        return org.hamcrest.collection.IsArrayContaining
                .<T> hasItemInArray(element);
    }

    /**
     * Creates a matcher for arrays that matches when the examined array
     * contains at least one item that is matched by the specified
     * <code>elementMatcher</code>. Whilst matching, the traversal of the
     * examined array will stop as soon as a matching element is found.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new String[] { &quot;foo&quot;, &quot;bar&quot; }, hasItemInArray(startsWith(&quot;ba&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The element type
     * @param elementMatcher
     *            the matcher to apply to elements in examined arrays
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T[]> hasItemInArray(
            org.hamcrest.Matcher<? super T> elementMatcher) {
        return org.hamcrest.collection.IsArrayContaining
                .<T> hasItemInArray(elementMatcher);
    }

    /**
     * Creates a matcher for arrays that matches when each item in the examined
     * array satisfies the corresponding matcher in the specified list of
     * matchers. For a positive match, the examined array must be of the same
     * length as the specified list of matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new String[] { &quot;foo&quot;, &quot;bar&quot; },
     *         contains(Arrays.asList(equalTo(&quot;foo&quot;), equalTo(&quot;bar&quot;))))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param itemMatchers
     *            a list of matchers, each of which must be satisfied by the
     *            corresponding item in an examined array
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<E[]> arrayContaining(
            java.util.List<org.hamcrest.Matcher<? super E>> itemMatchers) {
        return org.hamcrest.collection.IsArrayContainingInOrder
                .<E> arrayContaining(itemMatchers);
    }

    /**
     * Creates a matcher for arrays that matcheswhen each item in the examined
     * array is logically equal to the corresponding item in the specified
     * items. For a positive match, the examined array must be of the same
     * length as the number of specified items.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new String[] { &quot;foo&quot;, &quot;bar&quot; }, contains(&quot;foo&quot;, &quot;bar&quot;))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param items
     *            the items that must equal the items within an examined array
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<E[]> arrayContaining(E... items) {
        return org.hamcrest.collection.IsArrayContainingInOrder
                .<E> arrayContaining(items);
    }

    /**
     * Creates a matcher for arrays that matches when each item in the examined
     * array satisfies the corresponding matcher in the specified matchers. For
     * a positive match, the examined array must be of the same length as the
     * number of specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new String[] { &quot;foo&quot;, &quot;bar&quot; },
     *         contains(equalTo(&quot;foo&quot;), equalTo(&quot;bar&quot;)))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param itemMatchers
     *            the matchers that must be satisfied by the items in the
     *            examined array
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<E[]> arrayContaining(
            org.hamcrest.Matcher<? super E>... itemMatchers) {
        return org.hamcrest.collection.IsArrayContainingInOrder
                .<E> arrayContaining(itemMatchers);
    }

    /**
     * Creates an order agnostic matcher for arrays that matches when each item
     * in the examined array is logically equal to one item anywhere in the
     * specified items. For a positive match, the examined array must be of the
     * same length as the number of specified items.
     * <p>
     * N.B. each of the specified items will only be used once during a given
     * examination, so be careful when specifying items that may be equal to
     * more than one entry in an examined array.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new String[] { &quot;foo&quot;, &quot;bar&quot; }, containsInAnyOrder(&quot;bar&quot;, &quot;foo&quot;))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param items
     *            the items that must equal the entries of an examined array, in
     *            any order
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<E[]> arrayContainingInAnyOrder(E... items) {
        return org.hamcrest.collection.IsArrayContainingInAnyOrder
                .<E> arrayContainingInAnyOrder(items);
    }

    /**
     * Creates an order agnostic matcher for arrays that matches when each item
     * in the examined array satisfies one matcher anywhere in the specified
     * matchers. For a positive match, the examined array must be of the same
     * length as the number of specified matchers.
     * <p>
     * N.B. each of the specified matchers will only be used once during a given
     * examination, so be careful when specifying matchers that may be satisfied
     * by more than one entry in an examined array.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new String[] { &quot;foo&quot;, &quot;bar&quot; },
     *         arrayContainingInAnyOrder(equalTo(&quot;bar&quot;), equalTo(&quot;foo&quot;)))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param itemMatchers
     *            a list of matchers, each of which must be satisfied by an
     *            entry in an examined array
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<E[]> arrayContainingInAnyOrder(
            org.hamcrest.Matcher<? super E>... itemMatchers) {
        return org.hamcrest.collection.IsArrayContainingInAnyOrder
                .<E> arrayContainingInAnyOrder(itemMatchers);
    }

    /**
     * Creates an order agnostic matcher for arrays that matches when each item
     * in the examined array satisfies one matcher anywhere in the specified
     * collection of matchers. For a positive match, the examined array must be
     * of the same length as the specified collection of matchers.
     * <p>
     * N.B. each matcher in the specified collection will only be used once
     * during a given examination, so be careful when specifying matchers that
     * may be satisfied by more than one entry in an examined array.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(
     *         new String[] { &quot;foo&quot;, &quot;bar&quot; },
     *         arrayContainingInAnyOrder(Arrays.asList(equalTo(&quot;bar&quot;), equalTo(&quot;foo&quot;))))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param itemMatchers
     *            a list of matchers, each of which must be satisfied by an item
     *            provided by an examined array
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<E[]> arrayContainingInAnyOrder(
            java.util.Collection<org.hamcrest.Matcher<? super E>> itemMatchers) {
        return org.hamcrest.collection.IsArrayContainingInAnyOrder
                .<E> arrayContainingInAnyOrder(itemMatchers);
    }

    /**
     * Creates a matcher for arrays that matches when the <code>length</code> of
     * the array satisfies the specified matcher.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new String[] { &quot;foo&quot;, &quot;bar&quot; }, arrayWithSize(equalTo(2)))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param sizeMatcher
     *            a matcher for the length of an examined array
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<E[]> arrayWithSize(
            org.hamcrest.Matcher<? super java.lang.Integer> sizeMatcher) {
        return org.hamcrest.collection.IsArrayWithSize
                .<E> arrayWithSize(sizeMatcher);
    }

    /**
     * Creates a matcher for arrays that matches when the <code>length</code> of
     * the array equals the specified <code>size</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new String[] { &quot;foo&quot;, &quot;bar&quot; }, arrayWithSize(2))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param size
     *            the length that an examined array must have for a positive
     *            match
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<E[]> arrayWithSize(int size) {
        return org.hamcrest.collection.IsArrayWithSize.<E> arrayWithSize(size);
    }

    /**
     * Creates a matcher for arrays that matches when the <code>length</code> of
     * the array is zero.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new String[0], emptyArray())
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<E[]> emptyArray() {
        return org.hamcrest.collection.IsArrayWithSize.<E> emptyArray();
    }

    /**
     * Creates a matcher for {@link java.util.Collection}s that matches when the
     * <code>size()</code> method returns a value that satisfies the specified
     * matcher.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;), hasSize(equalTo(2)))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param sizeMatcher
     *            a matcher for the size of an examined
     *            {@link java.util.Collection}
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.util.Collection<? extends E>> hasSize(
            org.hamcrest.Matcher<? super java.lang.Integer> sizeMatcher) {
        return org.hamcrest.collection.IsCollectionWithSize
                .<E> hasSize(sizeMatcher);
    }

    /**
     * Creates a matcher for {@link java.util.Collection}s that matches when the
     * <code>size()</code> method returns a value equal to the specified
     * <code>size</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;), hasSize(2))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param size
     *            the expected size of an examined {@link java.util.Collection}
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.util.Collection<? extends E>> hasSize(
            int size) {
        return org.hamcrest.collection.IsCollectionWithSize.<E> hasSize(size);
    }

    /**
     * Creates a matcher for {@link java.util.Collection}s matching examined
     * collections whose <code>isEmpty</code> method returns <code>true</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new ArrayList&lt;String&gt;(), is(empty()))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.util.Collection<? extends E>> empty() {
        return org.hamcrest.collection.IsEmptyCollection.<E> empty();
    }

    /**
     * Creates a matcher for {@link java.util.Collection}s matching examined
     * collections whose <code>isEmpty</code> method returns <code>true</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new ArrayList&lt;String&gt;(), is(emptyCollectionOf(String.class)))
     * </pre>
     * 
     * @param <E>
     *            The element Type
     * @param type
     *            the type of the collection's content
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.util.Collection<E>> emptyCollectionOf(
            java.lang.Class<E> type) {
        return org.hamcrest.collection.IsEmptyCollection
                .<E> emptyCollectionOf(type);
    }

    /**
     * Creates a matcher for {@link Iterable}s matching examined iterables that
     * yield no items.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new ArrayList&lt;String&gt;(), is(emptyIterable()))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.lang.Iterable<? extends E>> emptyIterable() {
        return org.hamcrest.collection.IsEmptyIterable.<E> emptyIterable();
    }

    /**
     * Creates a matcher for {@link Iterable}s matching examined iterables that
     * yield no items.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new ArrayList&lt;String&gt;(), is(emptyIterableOf(String.class)))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param type
     *            the type of the iterable's content
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.lang.Iterable<E>> emptyIterableOf(
            java.lang.Class<E> type) {
        return org.hamcrest.collection.IsEmptyIterable
                .<E> emptyIterableOf(type);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when a single pass
     * over the examined {@link Iterable} yields a series of items, each
     * satisfying the corresponding matcher in the specified matchers. For a
     * positive match, the examined iterable must be of the same length as the
     * number of specified matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;),
     *         contains(equalTo(&quot;foo&quot;), equalTo(&quot;bar&quot;)))
     * </pre>
     * 
     * @param <E>
     *            The element type
     * @param itemMatchers
     *            the matchers that must be satisfied by the items provided by
     *            an examined {@link Iterable}
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.lang.Iterable<? extends E>> contains(
            org.hamcrest.Matcher<? super E>... itemMatchers) {
        return org.hamcrest.collection.IsIterableContainingInOrder
                .<E> contains(itemMatchers);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when a single pass
     * over the examined {@link Iterable} yields a series of items, each
     * logically equal to the corresponding item in the specified items. For a
     * positive match, the examined iterable must be of the same length as the
     * number of specified items.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;), contains(&quot;foo&quot;, &quot;bar&quot;))
     * </pre>
     * 
     * @param <E>
     *            the element type
     * @param items
     *            the items that must equal the items provided by an examined
     *            {@link Iterable}
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.lang.Iterable<? extends E>> contains(
            E... items) {
        return org.hamcrest.collection.IsIterableContainingInOrder
                .<E> contains(items);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when a single pass
     * over the examined {@link Iterable} yields a single item that satisfies
     * the specified matcher. For a positive match, the examined iterable must
     * only yield one item.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;), contains(equalTo(&quot;foo&quot;)))
     * </pre>
     * 
     * @param <E>
     *            the element type
     * @param itemMatcher
     *            the matcher that must be satisfied by the single item provided
     *            by an examined {@link Iterable}
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.lang.Iterable<? extends E>> contains(
            org.hamcrest.Matcher<? super E> itemMatcher) {
        return org.hamcrest.collection.IsIterableContainingInOrder
                .<E> contains(itemMatcher);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when a single pass
     * over the examined {@link Iterable} yields a series of items, each
     * satisfying the corresponding matcher in the specified list of matchers.
     * For a positive match, the examined iterable must be of the same length as
     * the specified list of matchers.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;),
     *         contains(Arrays.asList(equalTo(&quot;foo&quot;), equalTo(&quot;bar&quot;))))
     * </pre>
     * 
     * @param <E>
     *            the element type
     * @param itemMatchers
     *            a list of matchers, each of which must be satisfied by the
     *            corresponding item provided by an examined {@link Iterable}
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.lang.Iterable<? extends E>> contains(
            java.util.List<org.hamcrest.Matcher<? super E>> itemMatchers) {
        return org.hamcrest.collection.IsIterableContainingInOrder
                .<E> contains(itemMatchers);
    }

    /**
     * Creates an order agnostic matcher for {@link Iterable}s that matches when
     * a single pass over the examined {@link Iterable} yields a series of
     * items, each logically equal to one item anywhere in the specified items.
     * For a positive match, the examined iterable must be of the same length as
     * the number of specified items.
     * <p>
     * N.B. each of the specified items will only be used once during a given
     * examination, so be careful when specifying items that may be equal to
     * more than one entry in an examined iterable.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;), containsInAnyOrder(&quot;bar&quot;, &quot;foo&quot;))
     * </pre>
     * 
     * @param <T>
     *            the element type
     * @param items
     *            the items that must equal the items provided by an examined
     *            {@link Iterable} in any order
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<java.lang.Iterable<? extends T>> containsInAnyOrder(
            T... items) {
        return org.hamcrest.collection.IsIterableContainingInAnyOrder
                .<T> containsInAnyOrder(items);
    }

    /**
     * Creates an order agnostic matcher for {@link Iterable}s that matches when
     * a single pass over the examined {@link Iterable} yields a series of
     * items, each satisfying one matcher anywhere in the specified collection
     * of matchers. For a positive match, the examined iterable must be of the
     * same length as the specified collection of matchers.
     * <p>
     * N.B. each matcher in the specified collection will only be used once
     * during a given examination, so be careful when specifying matchers that
     * may be satisfied by more than one entry in an examined iterable.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;),
     *         containsInAnyOrder(Arrays.asList(equalTo(&quot;bar&quot;), equalTo(&quot;foo&quot;))))
     * </pre>
     * 
     * @param <T>
     *            the type element
     * @param itemMatchers
     *            a list of matchers, each of which must be satisfied by an item
     *            provided by an examined {@link Iterable}
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<java.lang.Iterable<? extends T>> containsInAnyOrder(
            java.util.Collection<org.hamcrest.Matcher<? super T>> itemMatchers) {
        return org.hamcrest.collection.IsIterableContainingInAnyOrder
                .<T> containsInAnyOrder(itemMatchers);
    }

    /**
     * Creates an order agnostic matcher for {@link Iterable}s that matches when
     * a single pass over the examined {@link Iterable} yields a series of
     * items, each satisfying one matcher anywhere in the specified matchers.
     * For a positive match, the examined iterable must be of the same length as
     * the number of specified matchers.
     * <p>
     * N.B. each of the specified matchers will only be used once during a given
     * examination, so be careful when specifying matchers that may be satisfied
     * by more than one entry in an examined iterable.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;),
     *         containsInAnyOrder(equalTo(&quot;bar&quot;), equalTo(&quot;foo&quot;)))
     * </pre>
     * 
     * @param <T>
     *            the type element
     * @param itemMatchers
     *            a list of matchers, each of which must be satisfied by an item
     *            provided by an examined {@link Iterable}
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<java.lang.Iterable<? extends T>> containsInAnyOrder(
            org.hamcrest.Matcher<? super T>... itemMatchers) {
        return org.hamcrest.collection.IsIterableContainingInAnyOrder
                .<T> containsInAnyOrder(itemMatchers);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when a single pass
     * over the examined {@link Iterable} yields an item count that satisfies
     * the specified matcher.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;), iterableWithSize(equalTo(2)))
     * </pre>
     * 
     * @param <E>
     *            the element type
     * @param sizeMatcher
     *            a matcher for the number of items that should be yielded by an
     *            examined {@link Iterable}
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.lang.Iterable<E>> iterableWithSize(
            org.hamcrest.Matcher<? super java.lang.Integer> sizeMatcher) {
        return org.hamcrest.collection.IsIterableWithSize
                .<E> iterableWithSize(sizeMatcher);
    }

    /**
     * Creates a matcher for {@link Iterable}s that matches when a single pass
     * over the examined {@link Iterable} yields an item count that is equal to
     * the specified <code>size</code> argument.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Arrays.asList(&quot;foo&quot;, &quot;bar&quot;), iterableWithSize(2))
     * </pre>
     * 
     * @param <E>
     *            the element type
     * @param size
     *            the number of items that should be yielded by an examined
     *            {@link Iterable}
     * @return the matcher
     */
    default <E> org.hamcrest.Matcher<java.lang.Iterable<E>> iterableWithSize(
            int size) {
        return org.hamcrest.collection.IsIterableWithSize
                .<E> iterableWithSize(size);
    }

    /**
     * Creates a matcher for {@link java.util.Map}s matching when the examined
     * {@link java.util.Map} contains at least one entry whose key equals the
     * specified <code>key</code> <b>and</b> whose value equals the specified
     * <code>value</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(myMap, hasEntry(&quot;bar&quot;, &quot;foo&quot;))
     * </pre>
     * 
     * @param <K>
     *            The key type
     * @param <V>
     *            The value type
     * @param key
     *            the key that, in combination with the value, must be describe
     *            at least one entry
     * @param value
     *            the value that, in combination with the key, must be describe
     *            at least one entry
     * @return the matcher
     */
    default <K, V> org.hamcrest.Matcher<java.util.Map<? extends K, ? extends V>> hasEntry(
            K key, V value) {
        return org.hamcrest.collection.IsMapContaining.<K, V> hasEntry(key,
                value);
    }

    /**
     * Creates a matcher for {@link java.util.Map}s matching when the examined
     * {@link java.util.Map} contains at least one entry whose key satisfies the
     * specified <code>keyMatcher</code> <b>and</b> whose value satisfies the
     * specified <code>valueMatcher</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(myMap, hasEntry(equalTo(&quot;bar&quot;), equalTo(&quot;foo&quot;)))
     * </pre>
     * 
     * @param <K>
     *            the key type
     * @param <V>
     *            the value type
     * @param keyMatcher
     *            the key matcher that, in combination with the valueMatcher,
     *            must be satisfied by at least one entry
     * @param valueMatcher
     *            the value matcher that, in combination with the keyMatcher,
     *            must be satisfied by at least one entry
     * @return the matcher
     */
    default <K, V> org.hamcrest.Matcher<java.util.Map<? extends K, ? extends V>> hasEntry(
            org.hamcrest.Matcher<? super K> keyMatcher,
            org.hamcrest.Matcher<? super V> valueMatcher) {
        return org.hamcrest.collection.IsMapContaining.<K, V> hasEntry(
                keyMatcher, valueMatcher);
    }

    /**
     * Creates a matcher for {@link java.util.Map}s matching when the examined
     * {@link java.util.Map} contains at least one key that satisfies the
     * specified matcher.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(myMap, hasKey(equalTo(&quot;bar&quot;)))
     * </pre>
     * 
     * @param <K>
     *            the key type
     * @param keyMatcher
     *            the matcher that must be satisfied by at least one key
     * @return the matcher
     */
    default <K> org.hamcrest.Matcher<java.util.Map<? extends K, ?>> hasKey(
            org.hamcrest.Matcher<? super K> keyMatcher) {
        return org.hamcrest.collection.IsMapContaining.<K> hasKey(keyMatcher);
    }

    /**
     * Creates a matcher for {@link java.util.Map}s matching when the examined
     * {@link java.util.Map} contains at least one key that is equal to the
     * specified key.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(myMap, hasKey(&quot;bar&quot;))
     * </pre>
     * 
     * @param <K>
     *            the key type
     * @param key
     *            the key that satisfying maps must contain
     * @return the matcher
     */
    default <K> org.hamcrest.Matcher<java.util.Map<? extends K, ?>> hasKey(K key) {
        return org.hamcrest.collection.IsMapContaining.<K> hasKey(key);
    }

    /**
     * Creates a matcher for {@link java.util.Map}s matching when the examined
     * {@link java.util.Map} contains at least one value that is equal to the
     * specified value.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(myMap, hasValue(&quot;foo&quot;))
     * </pre>
     * 
     * @param <V>
     *            the value type
     * @param value
     *            the value that satisfying maps must contain
     * @return the matcher
     */
    default <V> org.hamcrest.Matcher<java.util.Map<?, ? extends V>> hasValue(
            V value) {
        return org.hamcrest.collection.IsMapContaining.<V> hasValue(value);
    }

    /**
     * Creates a matcher for {@link java.util.Map}s matching when the examined
     * {@link java.util.Map} contains at least one value that satisfies the
     * specified valueMatcher.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(myMap, hasValue(equalTo(&quot;foo&quot;)))
     * </pre>
     * 
     * @param <V>
     *            the value type
     * @param valueMatcher
     *            the matcher that must be satisfied by at least one value
     * @return the matcher
     */
    default <V> org.hamcrest.Matcher<java.util.Map<?, ? extends V>> hasValue(
            org.hamcrest.Matcher<? super V> valueMatcher) {
        return org.hamcrest.collection.IsMapContaining
                .<V> hasValue(valueMatcher);
    }

    /**
     * Creates a matcher that matches when the examined object is found within
     * the specified collection.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;foo&quot;, isIn(Arrays.asList(&quot;bar&quot;, &quot;foo&quot;)))
     * </pre>
     * 
     * @param <T>
     *            the element type
     * @param collection
     *            the collection in which matching items must be found
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> isIn(java.util.Collection<T> collection) {
        return org.hamcrest.collection.IsIn.<T> isIn(collection);
    }

    default <T> org.hamcrest.Matcher<T> isIn(T[] param1) {
        return org.hamcrest.collection.IsIn.<T> isIn(param1);
    }

    /**
     * Creates a matcher that matches when the examined object is equal to one
     * of the specified elements.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;foo&quot;, isIn(&quot;bar&quot;, &quot;foo&quot;))
     * </pre>
     * 
     * @param <T>
     *            the type
     * @param elements
     *            the elements amongst which matching items will be found
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> isOneOf(T... elements) {
        return org.hamcrest.collection.IsIn.<T> isOneOf(elements);
    }

    /**
     * Creates a matcher of {@link Double}s that matches when an examined double
     * is equal to the specified <code>operand</code>, within a range of +/-
     * <code>error</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(1.03, is(closeTo(1.0, 0.03)))
     * </pre>
     * 
     * @param operand
     *            the expected value of matching doubles
     * @param error
     *            the delta (+/-) within which matches will be allowed
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.Double> closeTo(double operand,
            double error) {
        return org.hamcrest.number.IsCloseTo.closeTo(operand, error);
    }

    /**
     * Creates a matcher of {@link java.math.BigDecimal}s that matches when an
     * examined BigDecimal is equal to the specified <code>operand</code>,
     * within a range of +/- <code>error</code>. The comparison for equality is
     * done by BigDecimals
     * {@link java.math.BigDecimal#compareTo(java.math.BigDecimal)} method.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(new BigDecimal(&quot;1.03&quot;),
     *         is(closeTo(new BigDecimal(&quot;1.0&quot;), new BigDecimal(&quot;0.03&quot;))))
     * </pre>
     * 
     * @param operand
     *            the expected value of matching BigDecimals
     * @param error
     *            the delta (+/-) within which matches will be allowed
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.math.BigDecimal> closeTo(
            java.math.BigDecimal operand, java.math.BigDecimal error) {
        return org.hamcrest.number.BigDecimalCloseTo.closeTo(operand, error);
    }

    /**
     * Creates a matcher of {@link Comparable} object that matches when the
     * examined object is equal to the specified value, as reported by the
     * <code>compareTo</code> method of the <b>examined</b> object.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(1, comparesEqualTo(1))
     * </pre>
     * 
     * @param <T>
     *            the type
     * @param value
     *            the value which, when passed to the compareTo method of the
     *            examined object, should return zero
     * @return the matcher
     */
    default <T extends java.lang.Comparable<T>> org.hamcrest.Matcher<T> comparesEqualTo(
            T value) {
        return org.hamcrest.number.OrderingComparison
                .<T> comparesEqualTo(value);
    }

    /**
     * Creates a matcher of {@link Comparable} object that matches when the
     * examined object is greater than the specified value, as reported by the
     * <code>compareTo</code> method of the <b>examined</b> object.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(2, greaterThan(1))
     * </pre>
     * 
     * @param <T>
     *            the type
     * @param value
     *            the value which, when passed to the compareTo method of the
     *            examined object, should return greater than zero
     * @return the matcher
     */
    default <T extends java.lang.Comparable<T>> org.hamcrest.Matcher<T> greaterThan(
            T value) {
        return org.hamcrest.number.OrderingComparison.<T> greaterThan(value);
    }

    /**
     * Creates a matcher of {@link Comparable} object that matches when the
     * examined object is greater than or equal to the specified value, as
     * reported by the <code>compareTo</code> method of the <b>examined</b>
     * object.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(1, greaterThanOrEqualTo(1))
     * </pre>
     * 
     * @param <T>
     *            The Type
     * @param value
     *            the value which, when passed to the compareTo method of the
     *            examined object, should return greater than or equal to zero
     * @return the matcher
     */
    default <T extends java.lang.Comparable<T>> org.hamcrest.Matcher<T> greaterThanOrEqualTo(
            T value) {
        return org.hamcrest.number.OrderingComparison
                .<T> greaterThanOrEqualTo(value);
    }

    /**
     * Creates a matcher of {@link Comparable} object that matches when the
     * examined object is less than the specified value, as reported by the
     * <code>compareTo</code> method of the <b>examined</b> object.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(1, lessThan(2))
     * </pre>
     * 
     * @param <T>
     *            The Type
     * @param value
     *            the value which, when passed to the compareTo method of the
     *            examined object, should return less than zero
     * @return the matcher
     */
    default <T extends java.lang.Comparable<T>> org.hamcrest.Matcher<T> lessThan(
            T value) {
        return org.hamcrest.number.OrderingComparison.<T> lessThan(value);
    }

    /**
     * Creates a matcher of {@link Comparable} object that matches when the
     * examined object is less than or equal to the specified value, as reported
     * by the <code>compareTo</code> method of the <b>examined</b> object.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(1, lessThanOrEqualTo(1))
     * </pre>
     * 
     * @param <T>
     *            The Type
     * @param value
     *            the value which, when passed to the compareTo method of the
     *            examined object, should return less than or equal to zero
     * @return the matcher
     */
    default <T extends java.lang.Comparable<T>> org.hamcrest.Matcher<T> lessThanOrEqualTo(
            T value) {
        return org.hamcrest.number.OrderingComparison
                .<T> lessThanOrEqualTo(value);
    }

    /**
     * Creates a matcher of {@link String} that matches when the examined string
     * is equal to the specified expectedString, ignoring case.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;Foo&quot;, equalToIgnoringCase(&quot;FOO&quot;))
     * </pre>
     * 
     * @param expectedString
     *            the expected value of matched strings
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.String> equalToIgnoringCase(
            java.lang.String expectedString) {
        return org.hamcrest.text.IsEqualIgnoringCase
                .equalToIgnoringCase(expectedString);
    }

    /**
     * Creates a matcher of {@link String} that matches when the examined string
     * is equal to the specified expectedString, when whitespace differences are
     * (mostly) ignored. To be exact, the following whitespace rules are
     * applied:
     * <ul>
     * <li>all leading and trailing whitespace of both the expectedString and
     * the examined string are ignored</li>
     * <li>any remaining whitespace, appearing within either string, is
     * collapsed to a single space before comparison</li>
     * </ul>
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;   my\tfoo  bar &quot;, equalToIgnoringWhiteSpace(&quot; my  foo bar&quot;))
     * </pre>
     * 
     * @param expectedString
     *            the expected value of matched strings
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.String> equalToIgnoringWhiteSpace(
            java.lang.String expectedString) {
        return org.hamcrest.text.IsEqualIgnoringWhiteSpace
                .equalToIgnoringWhiteSpace(expectedString);
    }

    /**
     * Creates a matcher of {@link String} that matches when the examined string
     * has zero length.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;&quot;, isEmptyString())
     * </pre>
     * 
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.String> isEmptyString() {
        return org.hamcrest.text.IsEmptyString.isEmptyString();
    }

    /**
     * Creates a matcher of {@link String} that matches when the examined string
     * is <code>null</code>, or has zero length.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(((String) null), isEmptyString())
     * </pre>
     * 
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.String> isEmptyOrNullString() {
        return org.hamcrest.text.IsEmptyString.isEmptyOrNullString();
    }

    /**
     * Creates a matcher of {@link String} that matches when the examined string
     * contains all of the specified substrings, regardless of the order of
     * their appearance.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(&quot;myfoobarbaz&quot;, stringContainsInOrder(Arrays.asList(&quot;bar&quot;, &quot;foo&quot;)))
     * </pre>
     * 
     * @param substrings
     *            the substrings that must be contained within matching strings
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.lang.String> stringContainsInOrder(
            java.lang.Iterable<java.lang.String> substrings) {
        return org.hamcrest.text.StringContainsInOrder
                .stringContainsInOrder(substrings);
    }

    /**
     * Creates a matcher that matches any examined object whose
     * <code>toString</code> method returns a value that satisfies the specified
     * matcher.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(true, hasToString(equalTo(&quot;TRUE&quot;)))
     * </pre>
     * 
     * @param <T>
     *            The Type
     * @param toStringMatcher
     *            the matcher used to verify the toString result
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> hasToString(
            org.hamcrest.Matcher<? super java.lang.String> toStringMatcher) {
        return org.hamcrest.object.HasToString.<T> hasToString(toStringMatcher);
    }

    /**
     * Creates a matcher that matches any examined object whose
     * <code>toString</code> method returns a value equalTo the specified
     * string.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(true, hasToString(&quot;TRUE&quot;))
     * </pre>
     * 
     * @param <T>
     *            The Type
     * @param expectedToString
     *            the expected toString result
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> hasToString(
            java.lang.String expectedToString) {
        return org.hamcrest.object.HasToString
                .<T> hasToString(expectedToString);
    }

    /**
     * Creates a matcher of {@link Class} that matches when the specified
     * baseType is assignable from the examined class.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(Integer.class, typeCompatibleWith(Number.class))
     * </pre>
     * 
     * @param <T>
     *            The Type
     * @param baseType
     *            the base class to examine classes against
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<java.lang.Class<?>> typeCompatibleWith(
            java.lang.Class<T> baseType) {
        return org.hamcrest.object.IsCompatibleType
                .<T> typeCompatibleWith(baseType);
    }

    /**
     * Creates a matcher of {@link java.util.EventObject} that matches any
     * object derived from <var>eventClass</var> announced by <var>source</var>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(myEvent, is(eventFrom(PropertyChangeEvent.class, myBean)))
     * </pre>
     * 
     * @param eventClass
     *            the class of the event to match on
     * @param source
     *            the source of the event
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.util.EventObject> eventFrom(
            java.lang.Class<? extends java.util.EventObject> eventClass,
            java.lang.Object source) {
        return org.hamcrest.object.IsEventFrom.eventFrom(eventClass, source);
    }

    /**
     * Creates a matcher of {@link java.util.EventObject} that matches any
     * EventObject announced by <var>source</var>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(myEvent, is(eventFrom(myBean)))
     * </pre>
     * 
     * @param source
     *            the source of the event
     * @return the matcher
     */
    default org.hamcrest.Matcher<java.util.EventObject> eventFrom(
            java.lang.Object source) {
        return org.hamcrest.object.IsEventFrom.eventFrom(source);
    }

    /**
     * Creates a matcher that matches when the examined object has a JavaBean
     * property with the specified name.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(myBean, hasProperty(&quot;foo&quot;))
     * </pre>
     * 
     * @param <T>
     *            The Type
     * @param propertyName
     *            the name of the JavaBean property that examined beans should
     *            possess
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> hasProperty(
            java.lang.String propertyName) {
        return org.hamcrest.beans.HasProperty.<T> hasProperty(propertyName);
    }

    /**
     * Creates a matcher that matches when the examined object has a JavaBean
     * property with the specified name whose value satisfies the specified
     * matcher.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(myBean, hasProperty("foo", equalTo("bar"))
     * </pre>
     * 
     * @param <T>
     *            The Type
     * @param propertyName
     *            the name of the JavaBean property that examined beans should
     *            possess
     * @param valueMatcher
     *            a matcher for the value of the specified property of the
     *            examined bean
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> hasProperty(
            java.lang.String propertyName, org.hamcrest.Matcher<?> valueMatcher) {
        return org.hamcrest.beans.HasPropertyWithValue.<T> hasProperty(
                propertyName, valueMatcher);
    }

    /**
     * Creates a matcher that matches when the examined object has values for
     * all of its JavaBean properties that are equal to the corresponding values
     * of the specified bean.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(myBean, samePropertyValuesAs(myExpectedBean))
     * </pre>
     * 
     * @param <T>
     *            The Type
     * @param expectedBean
     *            the bean against which examined beans are compared
     * @return the matcher
     */
    default <T> org.hamcrest.Matcher<T> samePropertyValuesAs(T expectedBean) {
        return org.hamcrest.beans.SamePropertyValuesAs
                .<T> samePropertyValuesAs(expectedBean);
    }

    /**
     * Creates a matcher of {@link org.w3c.dom.Node}s that matches when the
     * examined node contains a node at the specified <code>xPath</code> within
     * the specified namespace context, with any content.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(xml, hasXPath(&quot;/root/something[2]/cheese&quot;, myNs))
     * </pre>
     * 
     * @param xPath
     *            the target xpath
     * @param namespaceContext
     *            the namespace for matching nodes
     * @return the matcher
     */
    default org.hamcrest.Matcher<org.w3c.dom.Node> hasXPath(
            java.lang.String xPath,
            javax.xml.namespace.NamespaceContext namespaceContext) {
        return org.hamcrest.xml.HasXPath.hasXPath(xPath, namespaceContext);
    }

    /**
     * Creates a matcher of {@link org.w3c.dom.Node}s that matches when the
     * examined node contains a node at the specified <code>xPath</code>, with
     * any content.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(xml, hasXPath(&quot;/root/something[2]/cheese&quot;))
     * </pre>
     * 
     * @param xPath
     *            the target xpath
     * @return the matcher
     */
    default org.hamcrest.Matcher<org.w3c.dom.Node> hasXPath(
            java.lang.String xPath) {
        return org.hamcrest.xml.HasXPath.hasXPath(xPath);
    }

    /**
     * Creates a matcher of {@link org.w3c.dom.Node}s that matches when the
     * examined node has a value at the specified <code>xPath</code>, within the
     * specified <code>namespaceContext</code>, that satisfies the specified
     * <code>valueMatcher</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(xml, hasXPath(&quot;/root/something[2]/cheese&quot;, myNs, equalTo(&quot;Cheddar&quot;)))
     * </pre>
     * 
     * @param xPath
     *            the target xpath
     * @param namespaceContext
     *            the namespace for matching nodes
     * @param valueMatcher
     *            matcher for the value at the specified xpath
     * @return the matcher
     */
    default org.hamcrest.Matcher<org.w3c.dom.Node> hasXPath(
            java.lang.String xPath,
            javax.xml.namespace.NamespaceContext namespaceContext,
            org.hamcrest.Matcher<java.lang.String> valueMatcher) {
        return org.hamcrest.xml.HasXPath.hasXPath(xPath, namespaceContext,
                valueMatcher);
    }

    /**
     * Creates a matcher of {@link org.w3c.dom.Node}s that matches when the
     * examined node has a value at the specified <code>xPath</code> that
     * satisfies the specified <code>valueMatcher</code>.
     * <p>
     * For example:
     * 
     * <pre>
     * assertThat(xml, hasXPath(&quot;/root/something[2]/cheese&quot;, equalTo(&quot;Cheddar&quot;)))
     * </pre>
     * 
     * @param xPath
     *            the target xpath
     * @param valueMatcher
     *            matcher for the value at the specified xpath
     * @return the matcher
     */
    default org.hamcrest.Matcher<org.w3c.dom.Node> hasXPath(
            java.lang.String xPath,
            org.hamcrest.Matcher<java.lang.String> valueMatcher) {
        return org.hamcrest.xml.HasXPath.hasXPath(xPath, valueMatcher);
    }
}
