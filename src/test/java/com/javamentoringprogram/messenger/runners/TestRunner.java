package com.javamentoringprogram.messenger.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "",
        monochrome = true,
        plugin = {"pretty", "com.epam.reportportal.cucumber.ScenarioReporter"})
public class TestRunner {

}
