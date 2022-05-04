package kpi.third.term.java.lab.customers.exceptions;

public class JsonParserException extends Throwable {

    public JsonParserException(String message) {
        super( "My own exception: " + message );
    }

}
