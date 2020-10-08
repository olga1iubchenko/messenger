package com.javamentoringprogram.messenger.junittests;

import com.javamentoringprogram.messenger.filemode.ReadAttributesFromFile;
import com.javamentoringprogram.messenger.utils.FileHelper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.gen5.api.Disabled;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


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
    public void testValidInputFromFileAndFiltering() throws IOException {
        List<String> testListOfAttributes = fileReader.getListOfAttributesFromFile("input-from-test");
        List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject","TestReceiverName", "TestSenderName", "TestSenderPosition"));
        assertEquals(expectedListOfAttributes, testListOfAttributes);
    }
    @Test
    @Disabled("test")
    public void testValidInputFromFileAndOutput() throws IOException {

    }

}


