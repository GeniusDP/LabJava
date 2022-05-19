package kpi.third.term.java.lab.customers.models.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kpi.third.term.java.lab.customers.models.exceptions.JsonParserException;
import kpi.third.term.java.lab.customers.models.entities.Customer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class JsonParser {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LogManager.getLogger(JsonParser.class);

    public String convertListOfCustomersToJson(List<Customer> customers) throws JsonParserException {
        try{
            return objectMapper.writeValueAsString( customers );
        }catch(JsonProcessingException e){
            logger.error("Cannot cast list to json " +  e.getMessage());
            throw new JsonParserException("Cannot cast list to json");
        }
    }


    public List<Customer> jsonToListOfCustomers(String json) throws JsonParserException {
        try {
            return objectMapper.readValue( json, new TypeReference<>(){} );
        }catch(JsonProcessingException e){
            logger.error("Cannot cast from json to list" +  e.getMessage());
            throw new JsonParserException("Cannot cast from json to list");
        }
    }


}
