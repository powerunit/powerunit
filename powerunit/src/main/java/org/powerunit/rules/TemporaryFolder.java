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
package org.powerunit.rules;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.powerunit.TestSuite;

/**
 * This rule provides a way to support temporary folder
 * 
 * @author borettim
 *
 */
public final class TemporaryFolder implements ExternalResource {

	private Path rootFolder;

	@Override
	public void before() {
		try {
			rootFolder = Files.createTempDirectory("powerunit");
		} catch (IOException e) {
			TestSuite.DSL.fail("Unable to create the rootFolder because of "
					+ e.getMessage(), e);
		}
	}

	@Override
	public void after() {
		if (rootFolder != null) {
			recursiveDelete(rootFolder.toFile());
		}
	}

	private void recursiveDelete(File file) {
		File[] files = file.listFiles();
		if (files != null) {
			for (File each : files) {
				recursiveDelete(each);
			}
		}
		file.delete();
	}

	/**
	 * Create a new file.
	 * 
	 * @return the file
	 * @throws IOException
	 *             in case of error
	 */
	public Path newFile() throws IOException {
		return Files.createTempFile(rootFolder, "tmp", ".tmp");
	}

	/**
	 * Create a new folder.
	 * 
	 * @return the folder
	 * @throws IOException
	 *             in case of error
	 */
	public Path newFolder() throws IOException {
		return Files.createTempDirectory(rootFolder, "tmp");
	}

	/**
	 * Get the rootFolder.
	 * 
	 * @return the rootFolder
	 */
	public Path getRootFolder() {
		return rootFolder;
	}

}
