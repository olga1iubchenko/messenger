package com.javamentoringprogram.messenger;

import com.javamentoringprogram.messenger.enums.TemplateAttributeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.HashMap;
import java.util.Map;

import static com.javamentoringprogram.messenger.enums.TemplateAttributeEnum.*;

@Getter
@Setter
@Log4j2
public class EmailTextGenerator {

    public String getEmailText(Map <TemplateAttributeEnum, String> inputData) {
        Map<String, String> valuesMap = new HashMap();
        try {
            valuesMap.put("email.subject", inputData.get(EMAIL_SUBJECT));
            valuesMap.put("receiver.name", inputData.get(RECEIVER_NAME));
            valuesMap.put("sender.name", inputData.get(SENDER_NAME));
            valuesMap.put("sender.position", inputData.get(SENDER_POSITION));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String EmailTextString = sub.replace(EmailTextTemplate.EMAIL_TEXT_TEMPLATE);
        System.out.println(EmailTextString);
        return EmailTextString;
    }

    public static String getEmailSubject(Map <TemplateAttributeEnum, String> inputData) {
        Map<String, String> valuesMap = new HashMap();
        try {
            valuesMap.put("email.subject", inputData.get(EMAIL_SUBJECT));
        }
        catch (Exception e) {
            log.error("Cant retrieve required attribute. Please review input and try again");
            e.printStackTrace();
        }

        StrSubstitutor sub = new StrSubstitutor(valuesMap);
        String EmailSubjectString = sub.replace(EmailTextTemplate.EMAIL_SUBJECT_TEMPLATE);
        return EmailSubjectString;
    }
}
