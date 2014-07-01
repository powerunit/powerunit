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
package ch.powerunit;

import java.io.File;
import java.nio.file.Path;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;

import ch.powerunit.matchers.file.FileCanExecuteMatcher;
import ch.powerunit.matchers.file.FileCanReadMatcher;
import ch.powerunit.matchers.file.FileCanWriteMatcher;
import ch.powerunit.matchers.file.FileExistsMatcher;
import ch.powerunit.matchers.file.FileIsAbsoluteMatcher;
import ch.powerunit.matchers.file.FileIsDirectoryMatcher;
import ch.powerunit.matchers.file.FileListMatcher;
import ch.powerunit.matchers.file.FileNameMatcher;
import ch.powerunit.matchers.file.Path2FileMatcher;

/**
 * @author borettim
 *
 */
interface FileMatchers {
	/**
	 * Validate that a file can execute.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileCanExecute(is(true)));
	 * </pre>
	 * 
	 * @param matcher
	 *            the matcher on the can execute flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileCanExecute(Matcher<? super Boolean> matcher) {
		return new FileCanExecuteMatcher(matcher);
	}

	/**
	 * Validate that a file can execute.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileCanExecute(true);
	 * </pre>
	 * 
	 * @param canExecute
	 *            the expected value for the can execute flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileCanExecute(boolean canExecute) {
		return fileCanExecute(CoreMatchers.is(canExecute));
	}

	/**
	 * Validate that a file can read.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileCanRead(is(true)));
	 * </pre>
	 * 
	 * @param matcher
	 *            the matcher on the can read flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileCanRead(Matcher<? super Boolean> matcher) {
		return new FileCanReadMatcher(matcher);
	}

	/**
	 * Validate that a file can read.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileCanRead(true);
	 * </pre>
	 * 
	 * @param canRead
	 *            the expected value for the can read flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileCanRead(boolean canRead) {
		return fileCanRead(CoreMatchers.is(canRead));
	}

	/**
	 * Validate that a file can write.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileCanWrite(is(true)));
	 * </pre>
	 * 
	 * @param matcher
	 *            the matcher on the can write flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileCanWrite(Matcher<? super Boolean> matcher) {
		return new FileCanWriteMatcher(matcher);
	}

	/**
	 * Validate that a file can write.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileCanWrite(true);
	 * </pre>
	 * 
	 * @param canWrite
	 *            the expected value for the can write flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileCanWrite(boolean canWrite) {
		return fileCanWrite(CoreMatchers.is(canWrite));
	}

	/**
	 * Validate that a file is a directory.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileIsDirectory(is(true)));
	 * </pre>
	 * 
	 * @param matcher
	 *            the matcher on the is directory flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileIsDirectory(Matcher<? super Boolean> matcher) {
		return new FileIsDirectoryMatcher(matcher);
	}

	/**
	 * Validate that a file is a directory.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileIsDirectory(true);
	 * </pre>
	 * 
	 * @param isDirectory
	 *            the expected value for the is directory flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileIsDirectory(boolean isDirectory) {
		return fileIsDirectory(CoreMatchers.is(isDirectory));
	}

	/**
	 * Validate that a file exists.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileExists(is(true)));
	 * </pre>
	 * 
	 * @param matcher
	 *            the matcher on the exists flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileExists(Matcher<? super Boolean> matcher) {
		return new FileExistsMatcher(matcher);
	}

	/**
	 * Validate that a file exists.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileExists(true);
	 * </pre>
	 * 
	 * @param exists
	 *            the expected value for the exists flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileExists(boolean exists) {
		return fileExists(CoreMatchers.is(exists));
	}

	/**
	 * Validate that a file is absolute.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileIsAbsolute(is(true)));
	 * </pre>
	 * 
	 * @param matcher
	 *            the matcher on the absolute flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileIsAbsolute(Matcher<? super Boolean> matcher) {
		return new FileIsAbsoluteMatcher(matcher);
	}

	/**
	 * Validate that a file is absolute.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileIsAbsolute(true);
	 * </pre>
	 * 
	 * @param absolute
	 *            the expected value for the absolute flag.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileIsAbsolute(boolean absolute) {
		return fileIsAbsolute(CoreMatchers.is(absolute));
	}

	/**
	 * Validate that a file contains something.
	 * <p>
	 * The returned String[] is the one produced by list().
	 * 
	 * @param matcher
	 *            the matcher on the list() result.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileContains(Matcher<String[]> matcher) {
		return new FileListMatcher(matcher);
	}

	/**
	 * Validate that a file contains some other file name.
	 * 
	 * @param atLeastThisName
	 *            the file name.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileContains(String atLeastThisName) {
		return fileContains(org.hamcrest.Matchers
				.hasItemInArray(atLeastThisName));
	}

	/**
	 * Validate that a file has some name.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileNamed(is(&quot;x&quot;)));
	 * </pre>
	 * 
	 * @param matcher
	 *            the matcher on the name.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileNamed(Matcher<? super String> matcher) {
		return new FileNameMatcher(matcher);
	}

	/**
	 * Validate that a file has some name.
	 * <p>
	 * For example :
	 * 
	 * <pre>
	 * assertThat(myFile).is(fileNamed(&quot;x&quot;));
	 * </pre>
	 * 
	 * @param name
	 *            the expected value for the name.
	 * @return the matcher on the file.
	 */
	default Matcher<File> fileNamed(String name) {
		return fileNamed(CoreMatchers.is(name));
	}

	/**
	 * This is a special matcher that apply the toFile() method to the in
	 * matching path, and then apply the specified matcher.
	 * 
	 * @param matcher
	 *            the matcher on File.
	 * @return the matcher on Path.
	 */
	default Matcher<Path> pathMatchedAsFile(Matcher<? super File> matcher) {
		return new Path2FileMatcher(matcher);
	}

}
