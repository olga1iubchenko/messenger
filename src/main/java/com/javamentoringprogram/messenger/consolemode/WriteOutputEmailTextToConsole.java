package com.javamentoringprogram.messenger.consolemode;
import com.javamentoringprogram.messenger.EmailTextGenerator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
public class WriteOutputEmailTextToConsole {
    private final ReadAttributesFromConsole readAttributesFromConsoleMode = new ReadAttributesFromConsole();
    private final EmailTextGenerator emailTextGenerator = new EmailTextGenerator();
    private String emailText;
    private String emailSubject;

    public String getEmailTextOutputToConsole() {
        emailText = emailTextGenerator.getEmailText(readAttributesFromConsoleMode.createMapOfInputData());
        return emailText;
    }

    public String getEmailSubjectOutputToConsole() {
        emailSubject = emailTextGenerator.getEmailSubject(readAttributesFromConsoleMode.createMapOfInputData());
        return emailText;
    }

    public void printGeneratedEmailToConsole(){
        System.out.println(String.format("%s + \n + %s", getEmailSubjectOutputToConsole(), getEmailTextOutputToConsole()));
    }
}
