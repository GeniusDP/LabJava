package kpi.third.term.java.lab.customers.models.utilities;

import kpi.third.term.java.lab.customers.models.exceptions.JsonParserException;
import kpi.third.term.java.lab.customers.models.entities.Customer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

public class FileProcessor {

    public List<Customer> getAllCustomersFromFile(File file) throws IOException, JsonParserException{
        JsonParser jsonParser = new JsonParser();
        String wholeFile = Files.lines( file.toPath() )
                .reduce( (accumulator, currentValue) -> accumulator + currentValue )
                .orElse( "" );
        return jsonParser.jsonToListOfCustomers( wholeFile );
    }


    public void saveListOfCustomersToFile(File fileToSave, List<Customer> customers) throws IOException, JsonParserException{
        PrintWriter printWriter = new PrintWriter( fileToSave );
        JsonParser jsonParser = new JsonParser();
        String json = jsonParser.convertListOfCustomersToJson( customers );
        printWriter.print( json );
    }
}
