package com.javamentoringprogram.messenger.test.steps;

import com.javamentoringprogram.messenger.filemode.ReadAttributesFromFile;
import com.javamentoringprogram.messenger.utils.FileHelper;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.gen5.api.Disabled;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;



public class FileValidInputTest {
    final String testInput = "Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition}";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private BufferedWriter bufferedWriter = Mockito.mock(BufferedWriter.class);
    private ReadAttributesFromFile fileReader = spy(new ReadAttributesFromFile());
    FileHelper fileHelperMock = Mockito.mock(FileHelper.class);
    //PrintStream out = mock(PrintStream.class);

    @Test
    public void testValidInputWrittenToFile() throws IOException {
        fileReader.writeInputToFile("test-input", testInput);
        verify(fileHelperMock).createDirectory("test_input");
    }

    @Test
    public void testValidInputFromFileAndFiltering() throws IOException {
        verify(fileReader).writeInputToFile("test", testInput);
        List<String>testListOfAttributes = fileReader.getListOfAttributesFromFile("file-input");
        List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject","TestReceiverName", "TestSenderName", "TestSenderPosition"));
        Assert.assertEquals(expectedListOfAttributes, testListOfAttributes);
    }
    @Test
    @Disabled("test")
    public void testValidInputFromFileAndOutput() throws IOException {

    }
}


