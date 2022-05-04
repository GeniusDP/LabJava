package kpi.third.term.java.lab.customers.models.repositories;

import kpi.third.term.java.lab.customers.exceptions.JsonParserException;
import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.utilities.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONModel implements ModelLayer {

    @Override
    public List<Customer> findAll(File file) {
        JsonParser jsonParser = new JsonParser();


        List<Customer> lst = new ArrayList<>();
        try(
            FileReader reader = new FileReader( file );
            Scanner scanner = new Scanner( reader );
        ){
            String wholeFile = Files.lines( file.toPath() )
                    .reduce( (accumulator, currentValue) -> accumulator + currentValue )
                    .orElse( "" );

            lst = jsonParser.jsonToListOfCustomers( wholeFile );
        } catch (IOException e){
            System.out.println( e );
        } catch (JsonParserException e) {
            System.out.println( e.getMessage() );
        }

        return lst;
    }

    @Override
    public void saveListOfCustomersToFile(File fileToSave, List<Customer> customers) {

        try( PrintWriter printWriter = new PrintWriter( fileToSave ) ){
            JsonParser jsonParser = new JsonParser();
            String json = jsonParser.convertListOfCustomersToJson( customers );
            printWriter.print( json );
        }catch(IOException e){
            String msg = "Error during saving result into file " + fileToSave.getAbsolutePath() + " : " + e.getMessage();
            System.out.println( msg );
        } catch (JsonParserException e) {
            System.out.println( e.getMessage() );
        }

    }


}
