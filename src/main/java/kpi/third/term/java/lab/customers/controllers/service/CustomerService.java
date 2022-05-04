package kpi.third.term.java.lab.customers.controllers.service;

import kpi.third.term.java.lab.customers.exceptions.JsonParserException;
import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.utilities.JsonParser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomerService {

    public List<Customer> getCustomersInAlphabeticOrder(List<Customer> customers) {
        return customers.stream().sorted( Customer::compareTo ).collect( Collectors.toList() );
    }


    public List<Customer> getCustomersByCardNumberInRange(List<Customer> customers, long leftBound, long rightBound) {
        Predicate<Customer> filteringPredicate = customer -> {
            return customer.getBankCardNumber() >= leftBound && customer.getBankCardNumber() <= rightBound;
        };
        return customers.stream().filter( filteringPredicate ).collect( Collectors.toList() );
    }


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
