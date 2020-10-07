package com.javamentoringprogram.messenger.test.steps;

import com.javamentoringprogram.messenger.consolemode.ReadAttributesFromConsole;
import com.javamentoringprogram.messenger.consolemode.WriteOutputEmailTextToConsole;
import com.javamentoringprogram.messenger.enums.TemplateAttributeEnum;
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
import java.util.*;

import static com.javamentoringprogram.messenger.enums.TemplateAttributeEnum.*;
import static org.mockito.Mockito.*;

public class ConsoleValidInputTest {

    @Rule
   public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
    private ReadAttributesFromConsole consoleReader = spy(new ReadAttributesFromConsole());

    @Before
    public void setUp() {
        doReturn(bufferedReader).when(consoleReader).getReader();
        //doCallRealMethod().when(consoleReader).getFilteredInputFromConsole();
    }

    @Test
    public void testValidInputGetFilteredInputFromConsole() throws IOException {
        final List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject","TestReceiverName", "TestSenderName", "TestSenderPosition"));
        final String testInput = "Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition}";
        Mockito.when(bufferedReader.readLine()).thenReturn(testInput);
        final List<String> testInputFilteredResult = consoleReader.getFilteredInputFromConsole();
        Assert.assertEquals(expectedListOfAttributes, testInputFilteredResult);
    }

    @Test
    public void testCreateMapOfInputData(){
        final List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject","TestReceiverName", "TestSenderName", "TestSenderPosition"));
        final Map<TemplateAttributeEnum, String> expectedMap = new HashMap<>() {
            {put(EMAIL_SUBJECT, "TestSubject");
             put(RECEIVER_NAME, "TestReceiverName");
             put(SENDER_NAME,"TestSenderName");
             put(SENDER_POSITION, "TestSenderPosition");
            }};
            final Map<TemplateAttributeEnum, String> inputMap = consoleReader.createMapOfInputData(expectedListOfAttributes);
        Assert.assertTrue(expectedMap.entrySet().stream()
                .allMatch(e -> e.getValue().equals(inputMap.get(e.getKey()))));
    }

    @Test
    @Disabled("Disabled until issue with writer to map is fixed")
    public void testValidInputFromConsoleAndOutPut() throws IOException {
        final List<String> expectedListOfAttributes = new ArrayList<>(Arrays.asList("TestSubject","TestReceiverName", "TestSenderName", "TestSenderPosition"));
        final Map<TemplateAttributeEnum, String> listOfAttributes = consoleReader.createMapOfInputData(expectedListOfAttributes);
        final String expectedEmailText = "The TestSubject needs review+\n" +
                "Dear TestReceiverName \n" +
                "Kindly ask you to to take a look on the issue TestSubject. \n" +
                "We would like to hear yor opinion regarding this matter. " +
                "Thank you in advance \n" +
                "  \n" +
                "Kind regards \n" +
                "TestSenderName \n" +
                "TestSenderPosition ";
        
        final WriteOutputEmailTextToConsole writeOutputEmailTextToConsole = new WriteOutputEmailTextToConsole();
        final String generatedEmailText = (String.format("%s \n %s", writeOutputEmailTextToConsole.getEmailSubjectOutputToConsole(listOfAttributes), writeOutputEmailTextToConsole.getEmailTextOutputToConsole(listOfAttributes)));
        Assert.assertEquals(expectedEmailText, generatedEmailText);
    }
}
