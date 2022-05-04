package kpi.third.term.java.lab.customers.models.entities;

import java.io.Serializable;

public class Customer implements Comparable {
    private String firstName;
    private String secondName;
    private String fathersName;
    private String address;
    private long bankCardNumber;
    private String bankAccountNumber;


    public Customer(String firstName,
                    String secondName,
                    String fathersName,
                    String address,
                    long bankCardNumber,
                    String bankAccountNumber) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.fathersName = fathersName;
        this.address = address;
        this.bankCardNumber = bankCardNumber;
        this.bankAccountNumber = bankAccountNumber;
    }


    /*
        needed for Jackson deserialization
     */
    public Customer() {
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getSecondName() {
        return secondName;
    }


    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }


    public String getFathersName() {
        return fathersName;
    }


    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public long getBankCardNumber() {
        return bankCardNumber;
    }


    public void setBankCardNumber(long bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }


    public String getBankAccountNumber() {
        return bankAccountNumber;
    }


    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }


    @Override
    public String toString() {
        String format = "firstName = %10s | secondName = %15s | " +
                " fathersName = %15s | address = %10s | " +
                "bankCardNumber = %5d | accNumber = %29s";
        return String.format(format, firstName, secondName, fathersName, address, bankCardNumber, bankAccountNumber);
    }


    @Override
    public int compareTo(Object o) {
        Customer customer = (Customer) o;
        int firstNamesCompareResult = this.firstName.compareTo( customer.firstName );
        int secondNamesCompareResult = this.secondName.compareTo( customer.secondName );
        int fathersNamesCompareResult = this.fathersName.compareTo( customer.fathersName );

        if( firstNamesCompareResult != 0 ){
            return firstNamesCompareResult;
        }

        if( secondNamesCompareResult != 0 ){
            return secondNamesCompareResult;
        }

        return fathersNamesCompareResult;
    }


}

