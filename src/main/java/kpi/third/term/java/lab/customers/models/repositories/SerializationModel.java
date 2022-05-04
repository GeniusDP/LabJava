package kpi.third.term.java.lab.customers.models.repositories;

import kpi.third.term.java.lab.customers.models.entities.Customer;

import java.io.File;
import java.util.List;

public class SerializationModel implements ModelLayer {

    @Override
    public List<Customer> findAll(File file) {
        return ModelLayer.super.findAll( file );
    }


}
