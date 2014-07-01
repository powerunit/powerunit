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
package ch.powerunit.test.base;

import java.io.IOException;
import java.nio.file.Path;

import ch.powerunit.Categories;
import ch.powerunit.Rule;
import ch.powerunit.Test;
import ch.powerunit.TestRule;
import ch.powerunit.TestSuite;
import ch.powerunit.impl.DefaultPowerUnitRunnerImpl;
import ch.powerunit.rules.TemporaryFolder;

/**
 * @author borettim
 *
 */
public class FileMatchersTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<FileMatchersTest> runner = new DefaultPowerUnitRunnerImpl<>(
				FileMatchersTest.class);
		runner.addListener(new BootstrapTestListener<FileMatchersTest>());
		runner.run();

	}

	@Categories("base")
	public static class FileMatchersTest implements TestSuite {

		private final TemporaryFolder temporaryFolder = temporaryFolder();

		@Rule
		public final TestRule chain = temporaryFolder;

		@Test
		public void emptyTest() throws IOException {
			Path p = temporaryFolder.newFile("toto");
			assertThat(fileExists(true).matches(p.toFile())).is(true);
			assertThat(fileIsDirectory(false).matches(p.toFile())).is(true);
			p = temporaryFolder.newFolder("titi");
			assertThat(fileExists(true).matches(p.toFile())).is(true);
			assertThat(fileIsDirectory(true).matches(p.toFile())).is(true);
			assertThat(
					fileContains("toto").matches(
							temporaryFolder.getRootFolder().toFile())).is(true);
			assertThat(fileNamed("titi").matches(p.toFile())).is(true);
			assertThat(pathMatchedAsFile(fileNamed("titi")).matches(p))
					.is(true);
		}

	}

}
