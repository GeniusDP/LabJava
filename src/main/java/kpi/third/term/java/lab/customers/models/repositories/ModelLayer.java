package kpi.third.term.java.lab.customers.models.repositories;

import kpi.third.term.java.lab.customers.models.entities.Customer;

import java.io.File;
import java.util.List;

public interface ModelLayer {

    List<Customer> findAll(File file);

    void saveListOfCustomersToFile(File fileToSave, List<Customer> customers);

}
