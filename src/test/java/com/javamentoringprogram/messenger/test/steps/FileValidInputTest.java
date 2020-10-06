package com.javamentoringprogram.messenger.test.steps;

import com.javamentoringprogram.messenger.filemode.ReadAttributesFromFile;
import org.junit.Rule;
import org.junit.Test;
import org.junit.gen5.api.Disabled;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

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
    //private BufferedWriter bufferedWriter = Mockito.mock(BufferedWriter.class);
    private ReadAttributesFromFile fileReader = spy(new ReadAttributesFromFile());
    //PrintStream out = mock(PrintStream.class);
//
//    @Before
//    public void setUp() {
//        doReturn("files/test-input").when(fileReader).writeInputToFile("test-input", testInput);
//       // doCallRealMethod().when(fileReader).writeInputToFile();
//    }

    @Test
    public void testValidInputWrittenToFile() throws IOException {
        verify(fileReader).writeInputToFile("test", testInput);
    }

    @Test
    public void testValidInputFromFileAndFiltering() throws IOException {
        List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject","TestReceiverName", "TestSenderName", "TestSenderPosition"));

        List<String> testInputFilteredResult = fileReader.getListOfAttributesFromFile("file-input", testInput);
        verify(fileReader).writeInputToFile("test", testInput);
        //Assert.assertEquals(expectedListOfAttributes, testInputFilteredResult);
    }
    @Test
    @Disabled("Disabled until issue with writer to map is fixed")
    public void testValidInputFromFileAndOutput() throws IOException {

    }
}


