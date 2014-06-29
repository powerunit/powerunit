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
import java.util.Collection;

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
	 * Create a new file with generated name.
	 * 
	 * @return the file
	 * @throws IOException
	 *             in case of error
	 */
	Path newFile() throws IOException;

	/**
	 * Create a new file with specified name.
	 * 
	 * @param fileName
	 *            the file name
	 * @return the file
	 * @throws IOException
	 *             in case of error
	 */
	Path newFile(String fileName) throws IOException;

	/**
	 * Create a new file with specified name and data.
	 * 
	 * @param fileName
	 *            the file name
	 * @param data
	 *            the byte that must be wrote into the file
	 * @return the file
	 * @throws IOException
	 *             in case of error
	 */
	Path newFile(String fileName, byte data[]) throws IOException;

	/**
	 * Create a new folder with generated name.
	 * 
	 * @return the folder
	 * @throws IOException
	 *             in case of error
	 */
	Path newFolder() throws IOException;

	/**
	 * Create a new folder with specified name.
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

	/**
	 * Get the initial temporary folder info
	 * 
	 * @return the initial data
	 */
	InitialFolderEntry getInitial();

	/**
	 * This is a builder for temporary folder.
	 * 
	 * @author borettim
	 *
	 */
	interface TemporaryFolderBuilder {

		/**
		 * Create a new file, in the current folder.
		 * 
		 * @param fileName
		 *            the file name
		 * @return the temporary folder builder
		 */
		TemporaryFolderBuilder file(String fileName);

		/**
		 * Create a new file, in the current folder.
		 * 
		 * @param fileName
		 *            the file name
		 * @param data
		 *            the data to be wrote in the file
		 * @return the temporary folder builder
		 */
		TemporaryFolderBuilder file(String fileName, byte data[]);

		/**
		 * Create a new folder, in the current folder.
		 * 
		 * @param folderName
		 *            the folder name
		 * @return the temporary folder builder (moved in this folder)
		 */
		TemporaryFolderBuilder folder(String folderName);

		/**
		 * Move up from this folder.
		 * 
		 * @return the builder
		 */
		TemporaryFolderBuilder end();

		/**
		 * Build the temporary folder.
		 * 
		 * @return The temporary folder rule
		 */
		TemporaryFolder build();
	}

	interface InitialEntry {
		String getName();
	}

	interface InitialFolderEntry extends InitialEntry {

		Collection<InitialFileEntry> getFiles();

		Collection<InitialFolderEntry> getFolders();
	}

	interface InitialFileEntry extends InitialEntry {
		byte[] getData();
	}

}
