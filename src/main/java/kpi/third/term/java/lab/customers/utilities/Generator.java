package kpi.third.term.java.lab.customers.utilities;


import kpi.third.term.java.lab.customers.exceptions.JsonParserException;
import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.repositories.JSONModel;
import kpi.third.term.java.lab.customers.models.repositories.ModelLayer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Generator {

    public static void main(String[] args) throws JsonParserException {
        JsonParser jsonParser = new JsonParser();
        ModelLayer modelLayer = new JSONModel();
        File file = new File( "src/main/resources/input.json" );

        System.out.println( file.getName() );

        List<Customer> allCustomers = modelLayer.findAll( new File( "" ) );
        String json = jsonParser.convertListOfCustomersToJson( allCustomers );

        try( PrintWriter pw = new PrintWriter( file ) ) {
            pw.println( json );
        } catch (IOException e){
            System.out.println( e );
        }

    }


}
