package com.javamentoringprogram.messenger.filemode;

import com.javamentoringprogram.messenger.enums.TemplateAttributeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.javamentoringprogram.messenger.enums.TemplateAttributeEnum.*;

@Log4j2
@Getter
@Setter
public class ReadAttributesFromFile {

    public void writeInputToFile(final String inputFileName, final String emailTextWithAttributes) {
        BufferedWriter inputFileWriter = null;
        try {
            File file = new File(inputFileName);
            if(!file.exists()){
                file.createNewFile();
            }

            inputFileWriter = new BufferedWriter(new FileWriter(file));
            inputFileWriter.write(emailTextWithAttributes);
            log.info("File written Successfully");

        }catch(IOException e){
        System.out.println(String.format("An error occurred while reading file: %s", e));
        e.printStackTrace();
    }finally {
            try {
                if (inputFileWriter != null)
                    inputFileWriter.close();
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }

     private List<String> getListOfAttributesFromFile(String inputFileName, String emailTextWithAttributes) {
         List<String> listOfAttributes = new ArrayList<>();
         writeInputToFile(inputFileName, emailTextWithAttributes);
         try (Stream<String> stream = Files.lines(Paths.get(inputFileName))) {
             stream
                     .filter(line -> line != null && line.length() > 0)
                     .map(line -> line.split("\\s+"))
                     .forEach(word -> {
                         for (String str : word)
                             if (str.contains("#{")) {
                                 String filteredWord = str.substring(str.indexOf("{"), str.indexOf("}")).replace("{", "");
                                 listOfAttributes.add(filteredWord);
                             }
                     });
         } catch (IOException e) {
             e.printStackTrace();
         }
         return listOfAttributes;
     }


     public Map<TemplateAttributeEnum, String> createMapOfInputDataFromFile(final String inputFileName, final String emailTextWithAttributes){
        Map<TemplateAttributeEnum, String> inputMap = new HashMap();
        List<String> attributesForEmailTemplate = getListOfAttributesFromFile(inputFileName, emailTextWithAttributes);
        try {
            System.out.println("Dear user, please enter following required attributes for email generation");
            System.out.println("Please enter email subject");
            inputMap.put(EMAIL_SUBJECT, attributesForEmailTemplate.get(0));
            System.out.println("Please enter receiver's name");
            inputMap.put(RECEIVER_NAME, attributesForEmailTemplate.get(1));
            System.out.println("Please enter sender's name");
            inputMap.put(SENDER_NAME, attributesForEmailTemplate.get(3));
            System.out.println("Please enter sender's position");
            inputMap.put(SENDER_POSITION, attributesForEmailTemplate.get(4));
        }catch(Exception e){
            log.error("Cant retrieve required attribute. Please review file content");
            e.printStackTrace();
        }
        return inputMap;
    }

}
