package kpi.third.term.java.lab.customers.models.repositories;

import kpi.third.term.java.lab.customers.models.entities.Customer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface ModelLayer {

    default List<Customer> findAll(File file){
        List<Customer> customers = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            customers.add( CustomerFactory.getCustomerInstance() );
        }
        return customers;
    }


}




