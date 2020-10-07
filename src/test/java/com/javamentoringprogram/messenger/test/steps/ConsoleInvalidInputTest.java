package com.javamentoringprogram.messenger.test.steps;

import com.javamentoringprogram.messenger.consolemode.ReadAttributesFromConsole;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class ConsoleInvalidInputTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    private BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
    private ReadAttributesFromConsole consoleReader = spy(new ReadAttributesFromConsole());

    @Before
    public void setUp() {
        doReturn(bufferedReader).when(consoleReader).getReader();
        //doCallRealMethod().when(consoleReader).getFilteredInputFromConsole();
    }

    @Test
    public void testEmptyAttributeInputFromConsole() throws IOException {
        String testInput = "Test #{TestSubject} and and #{TestSenderName} and #{TestSenderPosition}";
        Mockito.when(bufferedReader.readLine()).thenReturn(testInput);
        exceptionRule.expect(NullPointerException.class);
    }

    @Test
    public void testMoreThanRequiredAttributesFromConsole() throws IOException {
        String testInput = "Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition} but one more #{TestRedundantat} test";
        Mockito.when(bufferedReader.readLine()).thenReturn(testInput);
        exceptionRule.expect(IndexOutOfBoundsException.class);
    }

}
