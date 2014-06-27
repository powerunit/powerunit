powerunit
=========

This is a unit test framework for java 8. [Please check the site for more information](http://powerunit.github.io/powerunit/).

```java
package org.powerunit.examples;

import java.util.Arrays;
import java.util.stream.Stream;

import org.powerunit.Parameter;
import org.powerunit.Parameters;
import org.powerunit.Test;
import org.powerunit.TestSuite;

public class HelloWorldParameterTest implements TestSuite {
  @Parameters("Input string is {0}, subString idx is {1}, expected result is {2}")
  public static Stream<Object[]> getDatas() {
    return Arrays.stream(new Object[][] { { "ab", 0, "ab" }, { "ab", 1, "b" } });
  }

  @Parameter(0)
  public String inputString;

  @Parameter(1)
  public int inputIndex;

  @Parameter(2)
  public String expectedString;

  @Test
  public void testSubString() {
    assertThat(inputString.substring(inputIndex)).is(expectedString);
  }
}
```