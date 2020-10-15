import com.javamentoringprogram.messenger.consolemode.ReadAttributesFromConsole;
import com.javamentoringprogram.messenger.consolemode.WriteOutputEmailTextToConsole;
import com.javamentoringprogram.messenger.enums.TemplateAttributeEnum;
import com.javamentoringprogram.messenger.filemode.ReadAttributesFromFile;
import com.javamentoringprogram.messenger.filemode.WriteOutputTextToFile;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;


@Getter
@Log4j2
class MessengerApp {
    public static void main(String[] args) throws IOException {

        try {
            // set up new properties object from file "/resources/file.properties"
            FileInputStream propFile = new FileInputStream("src/main/resources/file.properties");
            Properties p = new Properties(System.getProperties());
            p.load(propFile);

            // set the system properties
            System.setProperties(p);
            System.getProperties().list(System.out);    // display new properties
            System.out.println(System.getProperty("file.name").length());
        } catch (java.io.FileNotFoundException e) {
            System.err.println("Can't find file.properties");
        } catch (java.io.IOException e) {
            System.err.println("I/O failed.");
        }

        Map<TemplateAttributeEnum, String> listOfInputAttributes;

        if ( System.getProperty("file.name").length() != 0 && System.getProperty("file.emailTemplateText").length() != 0 ){
       ReadAttributesFromConsole readAttributesFromConsoleMode = new ReadAttributesFromConsole();
        listOfInputAttributes = readAttributesFromConsoleMode.createMapOfInputData(readAttributesFromConsoleMode.getFilteredInputFromConsole());
        WriteOutputEmailTextToConsole writeOutputEmailTextToConsole = new WriteOutputEmailTextToConsole();
        writeOutputEmailTextToConsole.printGeneratedEmailToConsole(listOfInputAttributes);
        }else{
            ReadAttributesFromFile readAttributesFromFileMode = new ReadAttributesFromFile();
            readAttributesFromFileMode.writeInputToFile(System.getProperty("file.name"), System.getProperty("file.emailTemplateText"));
            List<String> filteredList = readAttributesFromFileMode.getListOfAttributesFromFile(System.getProperty("file.name"), "Test #{TestSubject} and  #{TestReceiverName} and #{TestSenderName} and #{TestSenderPosition}");
            listOfInputAttributes = readAttributesFromFileMode.createMapOfInputDataFromFile(filteredList);
            WriteOutputTextToFile writeGeneratedEmailToFile = new WriteOutputTextToFile();
            writeGeneratedEmailToFile.writeGeneratedEmailToFile(listOfInputAttributes, System.getProperty("file.name"));
        }
    }
}