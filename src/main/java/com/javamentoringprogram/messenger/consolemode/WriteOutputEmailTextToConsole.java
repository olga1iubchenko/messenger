package com.javamentoringprogram.messenger.consolemode;

import com.javamentoringprogram.messenger.EmailTextGenerator;
import com.javamentoringprogram.messenger.enums.TemplateAttributeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
@Getter
@Setter
public class WriteOutputEmailTextToConsole {
    private final ReadAttributesFromConsole readAttributesFromConsoleMode = new ReadAttributesFromConsole();
    private final EmailTextGenerator emailTextGenerator = new EmailTextGenerator();
    private final Map<TemplateAttributeEnum, String> listOfInputAttributes = readAttributesFromConsoleMode.createMapOfInputData(readAttributesFromConsoleMode.getFilteredInputFromConsole());


    public String getEmailTextOutputToConsole(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        return emailTextGenerator.getEmailText(emailTextGenerator.getEmailTextMapper(listOfInputAttributes));
    }

    public String getEmailSubjectOutputToConsole(Map<TemplateAttributeEnum, String> listOfInputAttributes) {
        return emailTextGenerator.getEmailSubject(emailTextGenerator.getEmailTextMapper(listOfInputAttributes));
    }

    public void printGeneratedEmailToConsole(Map<TemplateAttributeEnum, String> listOfInputAttributes){
        System.out.println(String.format("%s \n %s", getEmailSubjectOutputToConsole(listOfInputAttributes), getEmailTextOutputToConsole(listOfInputAttributes)));
    }
}
