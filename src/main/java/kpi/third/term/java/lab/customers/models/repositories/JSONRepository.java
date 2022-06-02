package kpi.third.term.java.lab.customers.models.repositories;

import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.exceptions.JsonParserException;
import kpi.third.term.java.lab.customers.models.utilities.FileProcessor;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONRepository extends Repository {

    private final FileProcessor fileProcessor;

    public JSONRepository(File dataSource){
        this.dataSource = dataSource;
        fileProcessor = new FileProcessor();
    }

    @Override
    public List<Customer> findAll() throws IOException, JsonParserException {
        return fileProcessor.getAllCustomersFromFile( this.dataSource );
    }

    @Override
    public void saveAll(File fileToSave, List<Customer> customers) throws IOException, JsonParserException{
        fileProcessor.saveListOfCustomersToFile( fileToSave, customers );
    }


}
