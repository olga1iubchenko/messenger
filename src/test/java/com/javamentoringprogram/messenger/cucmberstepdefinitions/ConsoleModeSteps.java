package com.javamentoringprogram.messenger.cucmberstepdefinitions;

import com.javamentoringprogram.messenger.consolemode.ReadAttributesFromConsole;
import com.javamentoringprogram.messenger.contexts.ScenarioContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.opentest4j.IncompleteExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.javamentoringprogram.messenger.contexts.ScenarioContext.Context.FILTERED_TEST_INPUT;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

;

@Log4j2
@AllArgsConstructor
public class ConsoleModeSteps {

    private final ScenarioContext scenarioContext;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    public ExpectedException exceptionRule = ExpectedException.none();

    @Mock
    private BufferedReader bufferedReader = Mockito.mock(BufferedReader.class);
    private ReadAttributesFromConsole consoleReader = spy(new ReadAttributesFromConsole());



    @Given("^console up and running$")
    public void setUpConsoleMock(){
        doReturn(bufferedReader).when(consoleReader).getReader();
    }

    @When("^user enter '(.*)' to filter it$")
    public void setUserInput(String testInput) throws IOException {
        Mockito.when(bufferedReader.readLine()).thenReturn(testInput);
        scenarioContext.setContext(FILTERED_TEST_INPUT, consoleReader.getFilteredInputFromConsole());
    }


    @Then("^filtered input are the same as '(.*)'$")
     public void verifyUserInput(String expectedInput) throws IOException{
        List<String> expectedListOfAttributes = Arrays.asList(expectedInput);
        Assert.assertEquals(expectedListOfAttributes, scenarioContext.getContext(FILTERED_TEST_INPUT));
    }

    @Then("^user enter '(.*)' and '(.*)' occurs$")
    public void verifyInvalidUserInput(String testInput, String providedExpectedException) throws IOException, IncompleteExecutionException {
        Throwable expectedException;
        switch(providedExpectedException) {
            case "NullPointerException": expectedException = new NullPointerException();
            break;
            case "IndexOutOfBoundsException":
                expectedException = new IndexOutOfBoundsException();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + providedExpectedException);
        }
        Mockito.when(bufferedReader.readLine()).thenReturn(testInput);
        exceptionRule.expect(expectedException.getClass());
        }

}
