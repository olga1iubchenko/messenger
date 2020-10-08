package com.javamentoringprogram.messenger.junittests;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.javamentoringprogram.messenger.junittests.FileValidInputTest.FILE_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class FileDynamicTestOfFileEquality {

    @SneakyThrows
    @TestFactory
    public Stream<DynamicTest> testFiles() {
        return Files.walk(Paths.get("src/test/resources"), 1)
                .filter(path -> path.toString().endsWith(".txt"))
                .map((file) -> dynamicTest(
                        "Test for file: " + file,
                        () -> {
                            assertEquals(file, Files.lines(Paths.get(FILE_NAME)));
                        }));
    }
}


