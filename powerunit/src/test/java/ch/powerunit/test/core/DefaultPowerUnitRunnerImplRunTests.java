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
package ch.powerunit.test.core;

import java.util.Arrays;
import java.util.stream.Stream;

import ch.powerunit.Ignore;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Rule;
import ch.powerunit.Test;
import ch.powerunit.TestContext;
import ch.powerunit.TestResultListener;
import ch.powerunit.TestRule;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;
import ch.powerunit.rules.ExternalResource;

public class DefaultPowerUnitRunnerImplRunTests {
	public static void main(String[] args) {
		testRun();
		testRunError();
		testRunClassIgnore();
	}

	public static void testRun() {
		AllTests.testNoException(
				"testRun",
				() -> {
					counter = 0;
					DefaultPowerUnitRunnerImpl<TestClass1> underTest = new DefaultPowerUnitRunnerImpl<>(
							TestClass1.class);
					underTest.addListener(new Listener<TestClass1>());
					underTest.run();
					TestSuite.DSL.assertThat(TestClass1.counter1).is(1);
					TestSuite.DSL.assertThat(TestClass1.counter2).is(1);
					TestSuite.DSL.assertThat(TestClass1.counter3).is(2);
					TestSuite.DSL.assertThat(TestClass1.counter4).is(2);
					TestSuite.DSL.assertThat(TestClass1.counter5).is(4);
					TestSuite.DSL.assertThat(TestClass1.counter6).is(4);
					TestSuite.DSL.assertThat(TestClass1.bigCounter).is(0);
					TestSuite.DSL.assertThat(counter).is(
							1000 + 10000 + 100000 + 100000 + 1000000 + 1000000);

				});
		AllTests.testNoException(
				"testRun2",
				() -> {
					counter = 0;
					DefaultPowerUnitRunnerImpl<TestClass4> underTest = new DefaultPowerUnitRunnerImpl<>(
							TestClass4.class);
					underTest.addListener(new Listener<TestClass4>());
					underTest.run();
					TestSuite.DSL.assertThat(TestClass1.counter1).is(1);
					TestSuite.DSL.assertThat(TestClass1.counter2).is(1);
					TestSuite.DSL.assertThat(TestClass1.counter3).is(2);
					TestSuite.DSL.assertThat(TestClass1.counter4).is(2);
					TestSuite.DSL.assertThat(TestClass1.counter5).is(4);
					TestSuite.DSL.assertThat(TestClass1.counter6).is(4);
					TestSuite.DSL.assertThat(TestClass1.bigCounter).is(0);
					TestSuite.DSL.assertThat(counter).is(
							1000 + 10000 + 100000 + 100000 + 1000000 + 1000000);

				});
	}

	public static void testRunError() {
		AllTests.testException("testRunCantBuildTestClass",
				() -> new DefaultPowerUnitRunnerImpl<TestClass2>(
						TestClass2.class).run(), "Unexpected error");
	}

	public static void testRunClassIgnore() {
		AllTests.testNoException(
				"testRunClassIgnore",
				() -> {
					counter = 0;
					DefaultPowerUnitRunnerImpl<TestClass3> underTest = new DefaultPowerUnitRunnerImpl<>(
							TestClass3.class);
					underTest.addListener(new Listener<TestClass3>());
					underTest.run();
					TestSuite.DSL.assertThat(counter).is(
							1000 + 10000 + 100000 + 100000000);
				});
	}

	private static class Listener<T> implements TestResultListener<T> {

		@Override
		public void notifySetStart(String setName, String parameters) {
			counter += 1000;
		}

		@Override
		public void notifySetEnd(String setName, String parameters) {
			counter += 10000;
		}

		@Override
		public void notifyStart(TestContext<T> context) {
			counter += 100000;
		}

		@Override
		public void notifySuccess(TestContext<T> context) {
			counter += 1000000;
		}

		@Override
		public void notifyFailure(TestContext<T> context, Throwable cause) {
			counter += 10000000;
		}

		@Override
		public void notifySkipped(TestContext<T> context) {
			counter += 100000000;
		}

		@Override
		public void notifyError(TestContext<T> context, Throwable cause) {
			counter += 1000000000;
		}

	}

	private static int counter = 0;

	public static class TestClass1 implements TestSuite {

		public static int counter1 = 0;

		public static int counter2 = 0;

		public static int counter3 = 0;

		public static int counter4 = 0;

		public static int counter5 = 0;

		public static int counter6 = 0;

		public static int bigCounter = 0;

		@Parameters
		public static Stream<Object[]> getParameters() {
			return Arrays.stream(new Object[][] { { "name1" } });
		}

		@Parameter(0)
		public String name1;

		public void nonAnnotatedMethod() {
		}

		@Rule
		public final TestRule rule = before(() -> {
			assertThat(bigCounter).is(0);
			bigCounter++;
		}).around(new ExternalResource() {

			@Override
			public void before() {
				assertThat(bigCounter).is(1);
				bigCounter++;
				counter5++;
			}

			@Override
			public void after() {
				assertThat(bigCounter).is(8);
				counter6++;
				bigCounter = 0;

			}

		}).around(() -> {
			assertThat(bigCounter).is(2);
			bigCounter++;
			return new ExternalResource() {

				@Override
				public void before() {
					assertThat(bigCounter).is(3);
					bigCounter++;
					counter5++;
				}

				@Override
				public void after() {
					assertThat(bigCounter).is(7);
					bigCounter++;
					counter6++;
				}

			};
		}).around(before(this::beforeMethod)).around(after(this::afterMethod));

		@Test
		public void testMethod() {
			assertThat(bigCounter).is(5);
			bigCounter++;
			counter1++;
		}

		@Test(name = "otherName")
		public void testMethod2() {
			assertThat(bigCounter).is(5);
			bigCounter++;
			counter2++;
		}

		public void beforeMethod() {
			assertThat(bigCounter).is(4);
			bigCounter++;
			counter3++;
		}

		public void afterMethod() {
			counter4++;
			assertThat(bigCounter).is(6);
			bigCounter++;
		}

	}

	public static class TestClass2 {
		private TestClass2(int a) {
		}
	}

	@Ignore
	public static class TestClass3 {

	}

	public static class TestClass4 implements TestSuite {

		public static int counter1 = 0;

		public static int counter2 = 0;

		public static int counter3 = 0;

		public static int counter4 = 0;

		public static int counter5 = 0;

		public static int counter6 = 0;

		public static int bigCounter = 0;

		@Parameters
		public static Stream<Object[]> getParameters() {
			return Arrays.stream(new Object[][] { { "name1" } });
		}

		@Parameter(0)
		public String name1;

		public void nonAnnotatedMethod() {
		}

		public void before1() {
			assertThat(bigCounter).is(1);
			bigCounter++;
			counter5++;
		}

		public void after1() {
			assertThat(bigCounter).is(8);
			counter6++;
			bigCounter = 0;
		}

		@Rule
		public final TestRule rule = before(() -> {
			assertThat(bigCounter).is(0);
			bigCounter++;
		}).around(before(this::before1)).around(after(this::after1))
				.around(() -> {
					assertThat(bigCounter).is(2);
					bigCounter++;
					return new ExternalResource() {

						@Override
						public void before() {
							assertThat(bigCounter).is(3);
							bigCounter++;
							counter5++;
						}

						@Override
						public void after() {
							assertThat(bigCounter).is(7);
							bigCounter++;
							counter6++;
						}

					};
				}).around(before(this::beforeMethod))
				.around(after(this::afterMethod));

		@Test
		public void testMethod() {
			assertThat(bigCounter).is(5);
			bigCounter++;
			counter1++;
		}

		@Test(name = "otherName")
		public void testMethod2() {
			assertThat(bigCounter).is(5);
			bigCounter++;
			counter2++;
		}

		public void beforeMethod() {
			assertThat(bigCounter).is(4);
			bigCounter++;
			counter3++;
		}

		public void afterMethod() {
			counter4++;
			assertThat(bigCounter).is(6);
			bigCounter++;
		}

	}

}
