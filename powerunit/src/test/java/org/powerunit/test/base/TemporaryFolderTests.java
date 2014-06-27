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
package org.powerunit.test.base;

import java.io.IOException;
import java.nio.file.Path;

import org.powerunit.Categories;
import org.powerunit.Rule;
import org.powerunit.Test;
import org.powerunit.TestRule;
import org.powerunit.TestSuite;
import org.powerunit.impl.DefaultPowerUnitRunnerImpl;
import org.powerunit.rules.TemporaryFolder;

/**
 * @author borettim
 *
 */
public class TemporaryFolderTests {
	public static void main(String[] args) {
		DefaultPowerUnitRunnerImpl<TemporaryFolderTest> runner = new DefaultPowerUnitRunnerImpl<>(
				TemporaryFolderTest.class);
		runner.addListener(new BootstrapTestListener<TemporaryFolderTest>());
		runner.run();

	}

	@Categories("base")
	public static class TemporaryFolderTest implements TestSuite {

		private final TemporaryFolder temporaryFolder = temporaryFolder();

		private Path f;

		@Rule
		public final TestRule chain = after(this::postCheck).around(
				temporaryFolder);

		@Test
		public void emptyTest() throws IOException {
			assertThat(temporaryFolder.getRootFolder().toFile().exists()).is(
					true);
			f = temporaryFolder.newFile();
			assertThat(f.toFile().exists()).is(true);

		}

		public void postCheck() {
			assertThat(f.toFile().exists()).is(false);
			assertThat(temporaryFolder.getRootFolder().toFile().exists()).is(
					false);
		}
	}

}
