package com.javamentoringprogram.messenger.junittests;

import com.javamentoringprogram.messenger.filemode.ReadAttributesFromFile;
import com.javamentoringprogram.messenger.junittests.extensions.DisableOnDemo;
import com.javamentoringprogram.messenger.junittests.extensions.TemporaryDirectory;
import com.javamentoringprogram.messenger.junittests.extensions.TemporaryDirectoryExtension;
import com.javamentoringprogram.messenger.junittests.extensions.TemporaryDirectoryExtension_1;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.mockito.Mockito.spy;


public class FileValidInputTest {
    final String testInput = "Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition}";
    final static String FILE_NAME = "input-from-test";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private BufferedWriter bufferedWriter = Mockito.mock(BufferedWriter.class);
    private ReadAttributesFromFile fileReader = spy(new ReadAttributesFromFile());

    @TempDir
    File anotherTempDir;

    @Test
    public void testValidInputWrittenToFile() throws IOException {
        fileReader.writeInputToFile("input-from-test", testInput);
        assertEquals(Paths.get("input-from-test").getFileName().toString(), FILE_NAME);
    }

    @Test
    @ExtendWith(TemporaryDirectoryExtension_1.class)
    @Disabled("Test to run with already backed @TempDir extension doesn't work. Returned method should have no parameters error. So disabled")
    public void testValidInputWrittenAndReadFromFile(Path tempDir) throws Exception {
        Path testFile = tempDir.resolve("input-from-test");
        Files.write(testFile, Collections.singleton(testInput));
        String actualLines = Files.readString(Paths.get("test-input"));
        assertEquals(testFile.toFile().toString(), actualLines);
    }

    @Test
    @ExtendWith(TemporaryDirectoryExtension.class)
    @DisableOnDemo
    @Disabled("Test to run with Temporary directory as separate and put to parameter doesn't work. Returned method should have no parameters error. So disabled")
    public void testUseMethodParameter(TemporaryDirectory temporaryDirectory) throws Exception {
        Path path = temporaryDirectory.newFile();
        Files.write(path, "input-from-test".getBytes());
        String actual = new String(Files.readAllBytes(path));
        Assertions.assertEquals(FILE_NAME, actual);
    }

    @Test
    @Disabled("Test to run with already backed @TempDir extension doesn't work. Returned method should have no parameters error. So disabled")
    public void givenFieldWithTempDirectoryFile_whenWriteToFile_thenContentIsCorrect() throws IOException {
        assertTrue("Should be a directory ", this.anotherTempDir.isDirectory());
        File letters = new File(anotherTempDir, "input-from-test.txt");
        List<String> lines = Files.readAllLines(Paths.get("test-input"));
        Files.write(letters.toPath(), lines);
        assertAll(
                () -> assertTrue("File should exist", Files.exists(letters.toPath())),
                () -> assertLinesMatch(lines, Files.readAllLines(letters.toPath())));
    }


    @Test
    public void testValidInputFromFileAndFiltering()  {
        List<String> testListOfAttributes = fileReader.getListOfAttributesFromFile("test-file", "input-from-test");
        List<String> expectedListOfAttributes = new ArrayList<>(asList("TestSubject","TestReceiverName", "TestSenderName", "TestSenderPosition"));
        assertEquals(expectedListOfAttributes, testListOfAttributes);
    }


}


