package kpi.third.term.java.lab.customers.models.service;

import kpi.third.term.java.lab.customers.models.entities.Customer;

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

}
