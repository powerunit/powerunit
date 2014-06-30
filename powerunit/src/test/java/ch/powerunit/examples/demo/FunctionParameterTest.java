package ch.powerunit.examples.demo;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

import ch.powerunit.Categories;
import ch.powerunit.Parameter;
import ch.powerunit.Parameters;
import ch.powerunit.Test;
import ch.powerunit.TestSuite;

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
