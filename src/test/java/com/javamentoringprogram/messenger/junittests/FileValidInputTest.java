package com.javamentoringprogram.messenger.junittests;

import com.javamentoringprogram.messenger.filemode.ReadAttributesFromFile;
import com.javamentoringprogram.messenger.junittests.extensions.DisableOnDemo;
import com.javamentoringprogram.messenger.junittests.extensions.TemporaryDirectoryExtension;
import com.javamentoringprogram.messenger.utils.FileHelper;
import org.jetbrains.annotations.NotNull;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;


public class FileValidInputTest {
    final String testInput = "Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition}";
    final static String FILE_NAME = "input-from-test";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private BufferedWriter bufferedWriter = Mockito.mock(BufferedWriter.class);
    private ReadAttributesFromFile fileReader = spy(new ReadAttributesFromFile());
    FileHelper fileHelperMock = Mockito.mock(FileHelper.class);
    //PrintStream out = mock(PrintStream.class);

    @Test
    public void testValidInputWrittenToFile() throws IOException {
        fileReader.writeInputToFile("input-from-test", testInput);
        assertEquals(Paths.get("input-from-test").getFileName().toString(), FILE_NAME);
    }

    @Test
    @ExtendWith(TemporaryDirectoryExtension.class)
    public void testValidInputWrittenAndReadFromFile(@NotNull Path tempDir) throws Exception {
        Path testFile = tempDir.resolve("test.txt");
        Files.write(testFile, asList("foo", "bar"));

        List<String> actualLines = Files.readAllLines(testFile);
        assertEquals(asList("foo", "bar"), actualLines);

    }




    @Test
    public void testValidInputFromFileAndFiltering() throws IOException {
        List<String> testListOfAttributes = fileReader.getListOfAttributesFromFile("input-from-test");
        List<String> expectedListOfAttributes = new ArrayList<>(asList("TestSubject","TestReceiverName", "TestSenderName", "TestSenderPosition"));
        assertEquals(expectedListOfAttributes, testListOfAttributes);
    }

    @Test
    @DisableOnDemo
    public void testValidInputFromFileAndOutput() throws IOException {

    }

}


