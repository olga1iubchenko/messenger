import com.javamentoringprogram.messenger.enums.TemplateAttributeEnum;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Map;


@Getter
@Log4j2
class MessengerApp {
    public static void main(String[] args) throws IOException {

//        try {
//            // set up new properties object from file "/resources/file.properties"
//            FileInputStream propFile = new FileInputStream("src/main/resources/file.properties");
//            Properties p = new Properties(System.getProperties());
//            p.load(propFile);
//
//            // set the system properties
//            System.setProperties(p);
//            System.getProperties().list(System.out);    // display new properties
//            System.out.println(System.getProperty("file.name").length());
//        } catch (java.io.FileNotFoundException e) {
//            System.err.println("Can't find file.properties");
//        } catch (java.io.IOException e) {
//            System.err.println("I/O failed.");
//        }
//
        Map<TemplateAttributeEnum, String> listOfInputAttributes;
//
//        if ( System.getProperty("file.name").length() != 0 && System.getProperty("file.emailTemplateText").length() != 0 ){
//            ReadAttributesFromConsole readAttributesFromConsoleMode = new ReadAttributesFromConsole();
//            readAttributesFromConsoleMode.getReader();
//            readAttributesFromConsoleMode.getFilteredInput();
//            listOfInputAttributes = readAttributesFromConsoleMode.createMapOfInputData();
//        }else{
//            ReadAttributesFromFile readAttributesFromFileMode = new ReadAttributesFromFile();
//            readAttributesFromFileMode.writeInputToFile(System.getProperty("file.name"), System.getProperty("file.emailTemplateText"));
//            listOfInputAttributes = readAttributesFromFileMode.createMapOfInputDataFromFile(System.getProperty("file.name"), System.getProperty("file.emailTemplateText"));
//        }


//        ReadAttributesFromConsole readAttributesFromConsoleMode = new ReadAttributesFromConsole();
//       // readAttributesFromConsoleMode.closeReader();
//
//
//        //TODO: Update with instanceof
//        EmailTextGenerator emailTextGenerator = new EmailTextGenerator();
//        emailTextGenerator.getEmailSubject(readAttributesFromConsoleMode.createMapOfInputData());
//        emailTextGenerator.getEmailText(readAttributesFromConsoleMode.createMapOfInputData());
//        WriteOutputEmailTextToConsole writeOutputEmailTextToConsole = new WriteOutputEmailTextToConsole();
//        writeOutputEmailTextToConsole.printGeneratedEmailToConsole();

//        Map<TemplateAttributeEnum, String> listOfInputAttributes;
//
//        ReadAttributesFromConsole readAttributesFromConsoleMode = new ReadAttributesFromConsole();
//            readAttributesFromConsoleMode.getFilteredInput();
//            listOfInputAttributes = readAttributesFromConsoleMode.createMapOfInputData();
//        System.out.println(listOfInputAttributes);


    }
}