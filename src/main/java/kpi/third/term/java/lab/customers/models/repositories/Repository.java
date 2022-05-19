package kpi.third.term.java.lab.customers.models.repositories;

import kpi.third.term.java.lab.customers.models.entities.Customer;

import java.io.File;
import java.util.List;

public abstract class Repository {
    protected File dataSource;

    public Repository(File dataSource) {
        this.dataSource = dataSource;
    }

    public abstract List<Customer> findAll();

    public abstract void saveAll(File fileToSave, List<Customer> customers);

}
