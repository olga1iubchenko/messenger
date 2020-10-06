package com.javamentoringprogram.messenger.test.steps;//package com.javamentoringprogram.messenger.testStepDefinishions;

import com.javamentoringprogram.messenger.consolemode.ReadAttributesFromConsole;
import cucumber.annotation.Before;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Mockito.*;

@Log4j2
@AllArgsConstructor

public class BeforeHook {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    private BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
    private ReadAttributesFromConsole consoleReader = spy(new ReadAttributesFromConsole());

    @Before("@setUpConsoleMockAndConsoleReaderSpy")
    public void setUp() throws IOException {
        doReturn(bufferedReader).when(consoleReader).getReader();
        doCallRealMethod().when(consoleReader).getFilteredInput();
    }
}
