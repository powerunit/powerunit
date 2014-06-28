package org.powerunit.examples.demo;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

import org.powerunit.Categories;
import org.powerunit.Parameter;
import org.powerunit.Parameters;
import org.powerunit.Test;
import org.powerunit.TestSuite;

@Categories({ "example", "demo" })
public class FunctionParameterTest<T, R> implements TestSuite {

	@Parameters("{0} on {1} expecting {2}")
	public static Stream<Object[]> getDatas() {
		return Arrays.stream(new Object[][] { {
				(Function<String, Integer>) Integer::valueOf, "1", 1 } });
	}

	@Parameter(0)
	public Function<T, R> function;

	@Parameter(1)
	public T input;

	@Parameter(2)
	public R expected;

	@Test
	public void testAFunction() {
		assertThatFunction(function, input).is(expected);
	}
}
