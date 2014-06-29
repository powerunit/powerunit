package org.powerunit.rules.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.powerunit.TestSuite;
import org.powerunit.rules.TemporaryFolder;

public class TemporaryFolderImpl implements TemporaryFolder {

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

	@Override
	public Path newFile() throws IOException {
		return Files.createTempFile(rootFolder, "tmp", ".tmp");
	}

	@Override
	public Path newFile(String fileName) throws IOException {
		return Files.createFile(new File(rootFolder.toFile(), fileName)
				.toPath());
	}

	@Override
	public Path newFile(String fileName, byte[] data) throws IOException {
		Path p = newFile(fileName);
		Files.write(p, data);
		return p;
	}

	@Override
	public Path newFolder() throws IOException {
		return Files.createTempDirectory(rootFolder, "tmp");
	}

	@Override
	public Path newFolder(String folderName) throws IOException {
		return Files.createDirectory(new File(rootFolder.toFile(), folderName)
				.toPath());
	}

	@Override
	public Path getRootFolder() {
		return rootFolder;
	}

}
