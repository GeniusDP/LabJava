package kpi.third.term.java.lab.customers.views;

import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.utilities.OperationType;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ViewLayer {

    public static final String HELLO_MESSAGE = "Hello, this is program.";
    public static final String CUSTOMERS_LIST_FETCHED_MESSAGE = "Those are all customers, fetched from storage:";
    public static final String EVERYTHING_SAVED = "Data has been saved successfully!";
    public static final String WRITE_PLS_TYPE_OF_OPERATION = "Write please type of operation needed.";
    public static final String WRITE_RANGE = "Write 'range' (without quotes) - customers in range by card number";
    public static final String WRITE_ALPHABETIC = "Write 'alphabetic' (without quotes) - customers in alphabetic order";
    public static final String ERROR_DURING_LOADING_DATA = "Loading data from the storage finished with an error";
    public static final String ERROR_DURING_PARSING_LOADED_DATA = "Parsing loaded from the storage finished with an error";


    private static final String REWRITE_PLEASE = "Rewrite please!";
    private static final String RESOURCES_FILES_PREFIX = "src/main/resources/";
    private final Scanner scanner = new Scanner( System.in );

    public void printMessage(String message) {
        System.out.println( message );
    }


    public void printCustomerList(List<Customer> customers) {
        if(customers == null || customers.isEmpty()){
            System.out.println("The list of customers is empty: []");
            return;
        }
        printMessage( "This is a list of customers you needed:" );
        customers.forEach( System.out::println );
    }


    public OperationType getOperationType() {
        while( true ){
            printMessage( WRITE_PLS_TYPE_OF_OPERATION );
            printMessage( WRITE_RANGE );
            printMessage( WRITE_ALPHABETIC );

            String value = scanner.nextLine();
            boolean cycleCanStop = false;


            switch( value ){
                case "range", "alphabetic" -> cycleCanStop = true;
                default -> {
                    printMessage("Wrong! You have written '" + value + "' ");
                    printMessage("You should choose either 'range' or 'alphabetic'.");
                    printMessage(REWRITE_PLEASE);
                }
            }

            if( cycleCanStop ){
                printMessage("OK. You have chosen operation type " + value + "!");
                return value.equals( "range" ) ? OperationType.CARD_NUMBER_RANGE : OperationType.ALPHABETIC_ORDER;
            }

        }
    }


    public boolean performAnotherOperation() {
        while( true ){
            printMessage("Write, please, 'yes' if you want to perform another " +
                    "operation to start list or 'no' in another case.");
            String value = scanner.nextLine();

            boolean cycleCanStop = false;


            switch( value ){
                case "no", "yes" -> {
                    cycleCanStop = true;
                }
                default -> {
                    printMessage("Wrong! You have written '" + value + "' ");
                    printMessage("You should choose either 'yes' or 'no' no continue or not respectively.");
                    printMessage(REWRITE_PLEASE);
                }
            }

            if( cycleCanStop ){
                printMessage("OK. You have chosen operation type '" + value + "'!");
                return value.equals( "yes" );
            }
        }

    }


    public long getLeftBoundOfRange() {

        printMessage("Write, please, left bound of range.");

        while( !scanner.hasNextInt() ){
            printMessage("You have to insert a long-type number!");
            scanner.next();
        }

        return scanner.nextInt();
    }


    public long getRightBoundOfRange() {

        printMessage("Write, please, right bound of range.");

        while( !scanner.hasNextInt() ){
            printMessage("You have to insert a long-type number!");
            scanner.next();
        }
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }


    public boolean saveDialog() {

        while( true ){
            printMessage( "Do you want to save the result of your computation? " +
                    "Print 'yes' or 'no' (without quotes)." );
            String value = scanner.nextLine();

            boolean cycleCanStop = false;


            switch( value ){
                case "no", "yes" -> {
                    cycleCanStop = true;
                }
                default -> {
                    printMessage("Wrong! You have written '" + value + "' ");
                    printMessage("You should choose either 'yes' or 'no' no continue or not respectively.");
                    printMessage(REWRITE_PLEASE);
                }
            }

            if( cycleCanStop ){
                printMessage("OK. You have chosen operation type '" + value + "'!");
                return value.equals( "yes" );
            }
        }

    }


    public File saveFileGetting() {
        File fileToSave = null;
        while( true ){
            printMessage( "You desired to save data into the file. " +
                    "Are you still desired? Write 'yes' or 'no' (without quotes)." );
            String value = scanner.nextLine();

            boolean cycleCanStop = false;

            switch( value ){
                case "no", "yes" -> {
                    cycleCanStop = true;
                }
                default -> {
                    printMessage("Wrong! You have written '" + value + "' ");
                    printMessage("You should choose either 'yes' or 'no' no continue or not respectively.");
                    printMessage(REWRITE_PLEASE);
                }
            }

            if( cycleCanStop ){
                if( value.equals( "yes" ) ){
                    printMessage( "OK. Now you need to type in name of the file." );
                    fileToSave = inputFileNameToSaveCustomerList();
                }else{
                    printMessage( "Dismissing of saving current data to file was done successfully" );
                }
                break;
            }
        }
        return fileToSave;
    }


    private File inputFileNameToSaveCustomerList(){
        printMessage( "Type in name of the file and press enter " +
                "( file will be placed in src/main/resources directory ) : " );
        printMessage("!!!Notice: write name without extension. It will be automatically set to .json.");
        String fileName = scanner.nextLine();
        return new File(RESOURCES_FILES_PREFIX + fileName + ".json");
    }


}
