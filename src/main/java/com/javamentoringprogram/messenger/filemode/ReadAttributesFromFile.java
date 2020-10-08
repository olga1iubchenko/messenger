package com.javamentoringprogram.messenger.filemode;

import com.javamentoringprogram.messenger.enums.TemplateAttributeEnum;
import io.cucumber.messages.internal.com.google.common.collect.ImmutableMap;
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

    public List<String> getListOfAttributesFromFile(String inputFileName) {
        List<String> listOfAttributesFromFile = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(inputFileName))) {
            stream
                    .filter(line -> line != null && line.length() > 0)
                    .map(line -> line.split("\\s+"))
                    .forEach(word -> {
                        for (String str : word)
                            if (str.contains("#{")) {
                                String filteredWord = str.substring(str.indexOf("{"), str.indexOf("}")).replace("{", "");
                                listOfAttributesFromFile.add(filteredWord);
                            }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfAttributesFromFile;
    }


    public Map<TemplateAttributeEnum, String> createMapOfInputDataFromFile(List<String> listOfAttributes){
            return ImmutableMap.<TemplateAttributeEnum, String>builder()
                    .put(EMAIL_SUBJECT, listOfAttributes.get(0))
                    .put(RECEIVER_NAME, listOfAttributes.get(1))
                    .put(SENDER_NAME, listOfAttributes.get(2))
                    .put(SENDER_POSITION, listOfAttributes.get(3))
                    .build();
        }
    }

