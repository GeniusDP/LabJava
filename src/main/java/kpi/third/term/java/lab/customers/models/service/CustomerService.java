package kpi.third.term.java.lab.customers.models.service;

import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.exceptions.JsonParserException;
import kpi.third.term.java.lab.customers.models.repositories.Repository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomerService {

    private final Repository repository;

    public CustomerService(Repository repository) {
        this.repository = repository;
    }

    public List<Customer> getCustomersInAlphabeticOrder() throws IOException, JsonParserException {
        List<Customer> customers = repository.findAll();
        return customers.stream().sorted( Customer::compareTo ).collect( Collectors.toList() );
    }

    public List<Customer> getCustomersByCardNumberInRange(long leftBound, long rightBound) throws IOException, JsonParserException{
        List<Customer> customers = repository.findAll();
        Predicate<Customer> filteringPredicate = customer -> {
            return customer.getBankCardNumber() >= leftBound && customer.getBankCardNumber() <= rightBound;
        };
        return customers.stream().filter( filteringPredicate ).collect( Collectors.toList() );
    }

    public List<Customer> findAll()throws IOException, JsonParserException{
        return repository.findAll();
    }

    public void saveAll(File fileToSave, List<Customer> currentCustomersList) throws IOException, JsonParserException{
        repository.saveAll(fileToSave, currentCustomersList);
    }


}
