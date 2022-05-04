package kpi.third.term.java.lab.customers.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.repositories.JSONModel;
import kpi.third.term.java.lab.customers.models.repositories.ModelLayer;
import kpi.third.term.java.lab.customers.utilities.JsonParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    public static void main(String[] args) throws JsonProcessingException {
        JsonParser jsonParser = new JsonParser();
        ModelLayer modelLayer = new JSONModel();
        File file = new File( "src/main/resources/input.json" );

        System.out.println( file.getName() );

        List<Customer> allCustomers = modelLayer.findAll( new File( "" ) );
        String json = jsonParser.convertToJson( allCustomers );

        try( PrintWriter pw = new PrintWriter( file ) ) {
            pw.println( json );
        } catch (IOException e){
            System.out.println( e );
        }

    }


}


class Car{

    public int age;
    public String mark;


    public Car(int age, String mark) {
        this.age = age;
        this.mark = mark;
    }


    public Car() {
    }


    @Override
    public String toString() {
        return "Car{" +
                "age=" + age +
                ", mark='" + mark + '\'' +
                '}';
    }


}
