package com.javamentoringprogram.messenger.test.steps;

import com.javamentoringprogram.messenger.EmailTextGenerator;
import com.javamentoringprogram.messenger.consolemode.ReadAttributesFromConsole;
import com.javamentoringprogram.messenger.consolemode.WriteOutputEmailTextToConsole;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.gen5.api.Disabled;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ConsoleValidInputTest {

    @Rule
   public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
    private ReadAttributesFromConsole consoleReader = spy(new ReadAttributesFromConsole());
    //PrintStream out = mock(PrintStream.class);

    @Before
    public void setUp() {
        doReturn(bufferedReader).when(consoleReader).getReader();
        doCallRealMethod().when(consoleReader).getFilteredInput();
    }

    @Test
    public void testValidInputFromConsoleAndFiltering() throws IOException {
        List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject","TestReceiverName", "TestSenderName", "TestSenderPosition"));
        String testInput = "Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition}";
        Mockito.when(bufferedReader.readLine()).thenReturn(testInput);
        List<String> testInputFilteredResult = consoleReader.getFilteredInput();
        Assert.assertEquals(expectedListOfAttributes, testInputFilteredResult);
    }
    @Test
    @Disabled("Disabled until issue with writer to map is fixed")
    public void testValidInputFromConsoleAndOutPut() throws IOException {
        EmailTextGenerator emailTextGenerator = new EmailTextGenerator();
        WriteOutputEmailTextToConsole writeOutputEmailTextToConsole = new WriteOutputEmailTextToConsole();
        String expectedEmailText = "The TestSubject needs review+\n" +
                "Dear TestReceiverName\" +\n" +
                "Kindly ask you to to take a look on the issue TestSubject.\" +\n" +
                "We would like to hear yor opinion regarding this matter\" +\n" +
                "Thank you in advance\" +\n" +
                " +\n" +
                " Kind regards\" +\n" +
                "TestSenderName\" +\n" +
                "TestSenderPosition";
        String testInput = "Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition}";
        Mockito.when(bufferedReader.readLine()).thenReturn(testInput);
        List<String> testInputFilteredResult = consoleReader.getFilteredInput();
        emailTextGenerator.getEmailSubject(consoleReader.createMapOfInputData());
        emailTextGenerator.getEmailText(consoleReader.createMapOfInputData());
        String generatedEmailText = (String.format("%s \n %s", writeOutputEmailTextToConsole.getEmailSubject(), writeOutputEmailTextToConsole.getEmailText()));
        Assert.assertEquals(expectedEmailText, generatedEmailText);
    }
}
