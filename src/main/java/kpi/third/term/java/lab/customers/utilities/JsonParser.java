package kpi.third.term.java.lab.customers.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kpi.third.term.java.lab.customers.controllers.MainController;
import kpi.third.term.java.lab.customers.exceptions.JsonParserException;
import kpi.third.term.java.lab.customers.models.entities.Customer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(JsonParser.class);

    public String convertListOfCustomersToJson(List<Customer> customers) throws JsonParserException {
        String s = "";
        try{
            s = objectMapper.writeValueAsString( customers );
        }catch(JsonProcessingException e){
            logger.info("Cannot cast list to json " +  e.getMessage());
            throw new JsonParserException("Cannot cast list to json");
        }
        return s;
    }


    public List<Customer> jsonToListOfCustomers(String json) throws JsonParserException {
        List<Customer> result;
        try {
            result = objectMapper.readValue( json, new TypeReference<>(){} );
        }catch(JsonProcessingException e){
            logger.info("Cannot cast from json to list" +  e.getMessage());
            throw new JsonParserException("Cannot cast from json to list");
        }
        return result;
    }


}
