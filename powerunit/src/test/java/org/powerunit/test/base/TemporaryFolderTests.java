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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

		private final TemporaryFolder temporaryFolder = temporaryFolderBuilder()
				.file("toto").folder("tito").file("pipo").end()
				.file("tata", new byte[] { 'x' }).build();

		private Path f;

		private Path f2;

		private Path f3;

		private Path d1;

		@Rule
		public final TestRule chain = after(this::postCheck).around(
				temporaryFolder).around(before(this::createOther));

		@Test
		public void emptyTest() throws IOException {
			assertThat(temporaryFolder.getRootFolder().toFile().exists()).is(
					true);
			f = temporaryFolder.newFile();
			assertThat(Files.exists(f)).is(true);
			assertThat(Files.exists(f2)).is(true);
			assertThat(Files.exists(f3)).is(true);
			assertThat(f3.toFile().isDirectory()).is(true);
			assertThat(Files.exists(d1)).is(true);
			assertThat(Files.readAllBytes(d1)).is(new byte[] { 'a' });
			assertThat(
					new File(temporaryFolder.getRootFolder().toFile(), "toto")
							.exists()).is(true);
			assertThat(
					new File(temporaryFolder.getRootFolder().toFile(), "tito")
							.exists()).is(true);
			assertThat(
					new File(temporaryFolder.getRootFolder().toFile(),
							"tito/pipo").exists()).is(true);
			assertThat(
					new File(temporaryFolder.getRootFolder().toFile(), "tata")
							.exists()).is(true);
			assertThat(
					Files.readAllBytes(new File(temporaryFolder.getRootFolder()
							.toFile(), "tata").toPath()))
					.is(new byte[] { 'x' });
		}

		public void postCheck() {
			assertThat(Files.exists(f)).is(false);
			assertThat(Files.exists(f2)).is(false);
			assertThat(Files.exists(f3)).is(false);
			assertThat(Files.exists(d1)).is(false);
			assertThat(f2.toFile().getName()).is("myName");
			assertThat(temporaryFolder.getRootFolder().toFile().exists()).is(
					false);
		}

		public void createOther() {
			try {
				f2 = temporaryFolder.newFile("myName");
				f3 = temporaryFolder.newFolder("myFolder");
				d1 = temporaryFolder.newFile("myName2", new byte[] { 'a' });
			} catch (IOException e) {
				fail("Unable to create the new file/folder name "
						+ e.getMessage(), e);
			}
		}
	}

}
