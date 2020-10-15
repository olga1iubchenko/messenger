package com.javamentoringprogram.messenger.junittests.extensions;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 *
 */
public class TemporaryDirectory {

    private final Path root_;

    public TemporaryDirectory() {
        try {
            root_ = Files.createTempDirectory("junit");
            Files.deleteIfExists(root_);
            Files.createDirectory(root_);
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public Path newFile() {
        try {
            return Files.createTempFile(root_, "junit", "");
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public Path newFile(String name) {
        try {
            Path file = Paths.get(root_.toString(), name);
            Files.createFile(file);
            return file;
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public Path newDirectory() {
        try {
            Path path = Files.createTempFile(root_, "junit", "");
            Files.delete(path);
            Files.createDirectories(path);
            return path;
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public Path newDirectory(String name) {
        try {
            Path dir = Paths.get(root_.toString(), name);
            Files.createDirectories(dir);
            return dir;
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public void close() {
        try {
            Files.walkFileTree(root_, new DeleteAllFileVisitor());
        } catch (IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    static class DeleteAllFileVisitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Files.delete(file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
            Files.delete(dir);
            return FileVisitResult.CONTINUE;
        }
    }
}