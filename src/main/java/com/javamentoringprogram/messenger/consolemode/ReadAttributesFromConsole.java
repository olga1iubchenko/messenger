package com.javamentoringprogram.messenger.consolemode;

import com.javamentoringprogram.messenger.enums.TemplateAttributeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.javamentoringprogram.messenger.enums.TemplateAttributeEnum.*;

@Log4j2
@Getter
@Setter
public class ReadAttributesFromConsole {
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        public BufferedReader getReader() {
           return reader;
        }

        public void closeReader() throws IOException {
            getReader().close();
        }

        public final List<String> getFilteredInput() {
            System.out.println("Dear user, please enter following required attributes for email generation email subject, receiver's name, sender's name and sender's position");
            List<String> listOfAttributesFromConsole = Arrays.asList(new String[4]);
            try {
                listOfAttributesFromConsole = getReader().readLine()
                        .lines().distinct()
                        .filter(line -> line != null && line.length() > 0)
                        .map(line -> line.split("\\s+")).flatMap(Arrays::stream)
                        .filter(str -> str.contains("#{")).map(str -> str.substring(str.indexOf("{"), str.indexOf("}"))
                         .replace("{", ""))
                        .collect(Collectors.toList());
                closeReader();
            } catch (IOException iOException) {
                log.error("{} incorrect input. There should be 4 input attributes in #{} provided: email subject, receiver name, sender name, sender position", listOfAttributesFromConsole.toString());
                iOException.printStackTrace();
            } catch (IndexOutOfBoundsException indexOutOfBoundException){
                log.error("Incorrect input. There should be 4 input attributes in #{} provided: email subject, receiver name, sender name, sender position");
                indexOutOfBoundException.printStackTrace();
            }
            if(listOfAttributesFromConsole.size() < 4 ){
            for(String attribute: listOfAttributesFromConsole){
                if(attribute == null || attribute == " ") {
                    throw new NullPointerException();
                }
            }}
            System.out.println(listOfAttributesFromConsole);
            return listOfAttributesFromConsole;
        }


    public final Map<TemplateAttributeEnum, String> createMapOfInputData() {
        final Map<TemplateAttributeEnum, String> inputMap = null;
        final List<String> filteredInput = getFilteredInput();
           System.out.println(filteredInput);
                 inputMap.put(EMAIL_SUBJECT, filteredInput.get(0));
                 inputMap.put(RECEIVER_NAME, filteredInput.get(1));
                 inputMap.put(SENDER_NAME, filteredInput.get(2));
                 inputMap.put(SENDER_POSITION, filteredInput.get(3));
        System.out.println(inputMap);
               return inputMap;
    }
}


