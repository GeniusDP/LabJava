package kpi.third.term.java.lab.customers.models.repositories;

import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.utilities.FileProcessor;

import java.io.File;
import java.util.List;

public class JSONRepository extends Repository {

    private final FileProcessor fileProcessor;

    public JSONRepository(File dataSource){
        super(dataSource);
        fileProcessor = new FileProcessor();
    }

    @Override
    public List<Customer> findAll() {
        return fileProcessor.getAllCustomersFromFile( this.dataSource );
    }

    @Override
    public void saveAll(File fileToSave, List<Customer> customers) {
        fileProcessor.saveListOfCustomersToFile( fileToSave, customers );
    }


}
