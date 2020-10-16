package com.javamentoringprogram.messenger.junittests.extensions;

import org.junit.gen5.api.extension.ConditionEvaluationResult;
import org.junit.gen5.api.extension.TestExecutionCondition;
import org.junit.gen5.api.extension.TestExtensionContext;
import org.junit.jupiter.api.extension.Extension;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DisabledOnDemoExtension implements TestExecutionCondition, Extension {
    @Override
    public ConditionEvaluationResult evaluate(TestExtensionContext testExtensionContext) {
        return LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY
                ? ConditionEvaluationResult.disabled("Today is DEMO for customer. No flaky tests should be shown")
                : ConditionEvaluationResult.enabled("Please lake a look on this test - it's flaky");
    }
}
