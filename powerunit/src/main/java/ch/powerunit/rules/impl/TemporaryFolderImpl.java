package ch.powerunit.rules.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import ch.powerunit.TestSuite;
import ch.powerunit.rules.TemporaryFolder;

public class TemporaryFolderImpl implements TemporaryFolder {

    public TemporaryFolderImpl(InitialFolderEntry root) {
        this.root = root;
    }

    private final InitialFolderEntry root;

    private Path rootFolder;

    @Override
    public void before() {
        try {
            rootFolder = Files.createTempDirectory("powerunit");
            if (root != null) {
                addInitial(rootFolder, root);
            }
        } catch (IOException e) {
            TestSuite.DSL.fail("Unable to create the rootFolder because of "
                    + e.getMessage(), e);
        }
    }

    private void addInitial(Path current, InitialFolderEntry init)
            throws IOException {
        for (InitialFileEntry f : init.getFiles()) {
            Path p = Files.createFile(new File(current.toFile(), f.getName())
                    .toPath());
            if (f.getData() != null) {
                Files.write(p, f.getData());
            }
        }
        for (InitialFolderEntry f : init.getFolders()) {
            Path p = Files.createDirectories(new File(current.toFile(), f
                    .getName()).toPath());
            addInitial(p, f);
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

    @Override
    public InitialFolderEntry getInitial() {
        return root;
    }

    public static class TemporaryFolderBuilderImpl implements
            TemporaryFolderBuilder {

        private final InitialFolderEntryImpl root = new InitialFolderEntryImpl(
                null, null);

        private InitialFolderEntryImpl current = root;

        @Override
        public TemporaryFolderBuilder file(String fileName) {
            return file(fileName, null);
        }

        @Override
        public TemporaryFolderBuilder file(String fileName, byte[] data) {
            current.addFile(new InitialFileEntryImpl(fileName, data));
            return this;
        }

        @Override
        public TemporaryFolderBuilder folder(String folderName) {
            InitialFolderEntryImpl n = new InitialFolderEntryImpl(current,
                    folderName);
            current.addFolder(n);
            current = n;
            return this;
        }

        @Override
        public TemporaryFolderBuilder end() {
            current = current.getParent();
            return this;
        }

        @Override
        public TemporaryFolder build() {
            return new TemporaryFolderImpl(root);
        }

    }

    private static class InitialFolderEntryImpl implements InitialFolderEntry {

        public InitialFolderEntryImpl(InitialFolderEntryImpl parent, String name) {
            this.name = name;
            this.parent = parent;
        }

        private final String name;

        private final InitialFolderEntryImpl parent;

        private final Collection<InitialFileEntry> files = new ArrayList<>();

        private final Collection<InitialFolderEntry> folders = new ArrayList<>();

        @Override
        public String getName() {
            return name;
        }

        @Override
        public Collection<InitialFileEntry> getFiles() {
            return Collections.unmodifiableCollection(files);
        }

        @Override
        public Collection<InitialFolderEntry> getFolders() {
            return Collections.unmodifiableCollection(folders);
        }

        public void addFile(InitialFileEntry file) {
            files.add(file);
        }

        public void addFolder(InitialFolderEntry folder) {
            folders.add(folder);
        }

        public InitialFolderEntryImpl getParent() {
            return parent;
        }

    }

    private static class InitialFileEntryImpl implements InitialFileEntry {

        public InitialFileEntryImpl(String name, byte[] data) {
            this.name = name;
            this.data = data;
        }

        private final String name;

        private final byte[] data;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public byte[] getData() {
            return data;
        }

    }

}
