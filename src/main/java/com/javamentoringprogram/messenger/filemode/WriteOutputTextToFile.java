package com.javamentoringprogram.messenger.filemode;

import com.javamentoringprogram.messenger.EmailTextGenerator;
import com.javamentoringprogram.messenger.consolemode.ReadAttributesFromConsole;
import com.javamentoringprogram.messenger.enums.TemplateAttributeEnum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteOutputTextToFile {
    private final ReadAttributesFromConsole readAttributesFromConsoleMode = new ReadAttributesFromConsole();
    private final EmailTextGenerator emailTextGenerator = new EmailTextGenerator();
    private final Map<TemplateAttributeEnum, String> listOfInputAttributes = readAttributesFromConsoleMode.createMapOfInputData(readAttributesFromConsoleMode.getFilteredInputFromConsole());

    public String getEmailTextOutputToFile(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        return emailTextGenerator.getEmailText(emailTextGenerator.getEmailTextMapper(listOfInputAttributes));
    }

    public String getEmailSubjectOutputToFile(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        return emailTextGenerator.getEmailSubject(emailTextGenerator.getEmailTextMapper(listOfInputAttributes));
    }

    public void writeGeneratedEmailToFile(Map<TemplateAttributeEnum, String> listOfInputAttributes, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(String.format("%s \n %s", getEmailSubjectOutputToFile(listOfInputAttributes), getEmailTextOutputToFile(listOfInputAttributes)));
    }
}
