package kpi.third.term.java.lab.customers.models.utilities;

import kpi.third.term.java.lab.customers.models.exceptions.JsonParserException;
import kpi.third.term.java.lab.customers.models.entities.Customer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {

    private static final Logger logger = LogManager.getLogger(FileProcessor.class);

    public List<Customer> getAllCustomersFromFile(File file) {
        JsonParser jsonParser = new JsonParser();
        List<Customer> lst = new ArrayList<>();
        try{
            String wholeFile = Files.lines( file.toPath() )
                    .reduce( (accumulator, currentValue) -> accumulator + currentValue )
                    .orElse( "" );

            lst = jsonParser.jsonToListOfCustomers( wholeFile );
        } catch (IOException e){
            logger.fatal( "Error in FileProcessor.getAllCustomersFromFile : " + e.getMessage() );
            System.exit(1337);
        } catch (JsonParserException e) {
            logger.fatal( "Error in FileProcessor.getAllCustomersFromFile :" + e.getMessage() );
            System.exit(1337);
        }
        return lst;
    }


    public void saveListOfCustomersToFile(File fileToSave, List<Customer> customers) {

        try( PrintWriter printWriter = new PrintWriter( fileToSave ) ){
            JsonParser jsonParser = new JsonParser();
            String json = jsonParser.convertListOfCustomersToJson( customers );
            printWriter.print( json );
        }catch(IOException e){
            String msg = "Error during saving result into file " + fileToSave.getAbsolutePath() + " : " + e.getMessage();
            logger.fatal( msg );
            System.exit(1337);
        } catch (JsonParserException e) {
            logger.fatal( e.getMessage() );
            System.exit(1337);
        }

    }
}
