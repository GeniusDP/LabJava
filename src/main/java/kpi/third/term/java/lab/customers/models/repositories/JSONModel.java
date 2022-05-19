package kpi.third.term.java.lab.customers.models.repositories;

import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.utilities.FileProcessor;

import java.io.File;
import java.util.List;

public class JSONModel implements ModelLayer {

    private final FileProcessor fileProcessor;

    public JSONModel(){
        fileProcessor = new FileProcessor();
    }

    @Override
    public List<Customer> findAll(File file) {
        return fileProcessor.getAllCustomersFromFile( file );
    }

    @Override
    public void saveAll(File fileToSave, List<Customer> customers) {
        fileProcessor.saveListOfCustomersToFile( fileToSave, customers );
    }


}
