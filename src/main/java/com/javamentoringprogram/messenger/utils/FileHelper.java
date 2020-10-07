package com.javamentoringprogram.messenger.utils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {
    public Path createDirectory(String directoryName) throws IOException {
        return Files.createDirectory(Paths.get(directoryName));
    }

    public boolean exists(String name) throws IOException {
        return Files.exists(Paths.get(name), LinkOption.NOFOLLOW_LINKS);
    }

}