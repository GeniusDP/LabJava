package kpi.third.term.java.lab.customers.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kpi.third.term.java.lab.customers.models.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private final ObjectMapper objectMapper = new ObjectMapper();


    public String convertListOfCustomersToJson(List<Customer> customers) {
        String s = "";
        try{
            s = objectMapper.writeValueAsString( customers );
        }catch(JsonProcessingException e){
            System.out.println("Cannot cast from object to json " +  e.getMessage());
            System.exit( -1 );
        }
        return s;
    }


    public List<Customer> jsonToListOfCustomers(String json) {
        List<Customer> result = new ArrayList<>();
        try {
            result = objectMapper.readValue( json, new TypeReference<List<Customer>>(){} );
        }catch(JsonProcessingException e){
            System.out.println("Cannot cast from json to object" +  e.getMessage());
            System.exit( -1 );
        }
        return result;
    }


}
