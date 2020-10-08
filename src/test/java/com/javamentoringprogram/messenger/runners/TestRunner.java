package com.javamentoringprogram.messenger.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        monochrome = true,
        plugin = {"json:target/cucumber-report/[CUCABLE:RUNNER].json","pretty"},
        glue = {"src/test/java/com/javamentoringprogram/messenger/cucmberstepdefinitions"},
        dryRun = false )

public class TestRunner {

}
