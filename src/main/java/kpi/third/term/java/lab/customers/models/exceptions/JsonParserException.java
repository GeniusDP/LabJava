package kpi.third.term.java.lab.customers.models.exceptions;

public class JsonParserException extends Exception {

    public JsonParserException(String message) {
        super( "My own exception: " + message );
    }

}
