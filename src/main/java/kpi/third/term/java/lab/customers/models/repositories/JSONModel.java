package kpi.third.term.java.lab.customers.models.repositories;

import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.utilities.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONModel implements ModelLayer {

    @Override
    public List<Customer> findAll(File file) {
        JsonParser jsonParser = new JsonParser();


        List<Customer> lst = new ArrayList<>();
        try( FileReader reader = new FileReader( file );
                Scanner scanner = new Scanner( reader ) ) {
            String wholeFile = Files.lines( file.toPath() )
                    .reduce( (accumulator, currentValue) -> accumulator + currentValue )
                    .orElse( "" );

            lst = jsonParser.jsonToListOfCustomers( wholeFile );
        } catch (IOException e){
            System.out.println( e );
        }

        return lst;
    }


}
