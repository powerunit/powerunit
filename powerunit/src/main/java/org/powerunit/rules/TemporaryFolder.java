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

import java.io.IOException;
import java.nio.file.Path;

/**
 * This rule provides a way to support temporary folder.
 * <p>
 * This class is exposed by the
 * {@link org.powerunit.TestSuite#temporaryFolder() temporaryFolder()} method of
 * the {@link org.powerunit.TestSuite TestSuite} interface. Created file and
 * folder by this rule (or created inside the once created by this rule) are
 * removed after test execution.
 * 
 * @author borettim
 *
 */
public interface TemporaryFolder extends ExternalResource {

	/**
	 * Create a new file.
	 * 
	 * @return the file
	 * @throws IOException
	 *             in case of error
	 */
	Path newFile() throws IOException;

	/**
	 * Create a new file.
	 * 
	 * @param fileName
	 *            the file name
	 * @return the file
	 * @throws IOException
	 *             in case of error
	 */
	Path newFile(String fileName) throws IOException;

	/**
	 * Create a new folder.
	 * 
	 * @return the folder
	 * @throws IOException
	 *             in case of error
	 */
	Path newFolder() throws IOException;

	/**
	 * Create a new folder.
	 * 
	 * @param folderName
	 *            the folder name
	 * @return the folder
	 * @throws IOException
	 *             in case of error
	 */
	Path newFolder(String folderName) throws IOException;

	/**
	 * Get the rootFolder.
	 * 
	 * @return the rootFolder
	 */
	Path getRootFolder();

}
