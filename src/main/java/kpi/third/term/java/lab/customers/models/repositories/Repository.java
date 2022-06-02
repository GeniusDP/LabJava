package kpi.third.term.java.lab.customers.models.repositories;

import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.exceptions.JsonParserException;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Setter
@NoArgsConstructor
public abstract class Repository {
    protected File dataSource;

    public Repository(File dataSource) {
        this.dataSource = dataSource;
    }


    public abstract List<Customer> findAll() throws IOException, JsonParserException;

    public abstract void saveAll(File fileToSave, List<Customer> customers) throws IOException, JsonParserException;

}
