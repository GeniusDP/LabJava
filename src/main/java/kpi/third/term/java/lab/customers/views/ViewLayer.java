package kpi.third.term.java.lab.customers.views;

import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.utilities.OperationType;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class ViewLayer {

    public static final String HELLO_MESSAGE = "Hello, this is program.";
    public static final String CUSTOMERS_LIST_FETCHED_MESSAGE = "Those are all customers, fetched from storage:";
    public static final String EVERYTHING_SAVED = "Data has been saved successfully!";

    private static final String REWRITE_PLEASE = "Rewrite please!";
    private static final String RESOURCES_FILES_PREFIX = "src/main/resources/";
    private final Scanner scanner = new Scanner( System.in );

    public void printMessage(String message) {
        System.out.println( message );
    }


    public void printCustomerList(List<Customer> customers) {
        System.out.println( "This is a list of customers you needed:" );
        customers.forEach( System.out::println );
    }


    public OperationType getOperationType() {
        while( true ){
            System.out.println("Write please type of operation needed.");
            System.out.println("Write 'range' (without quotes) - customers in range by card number");
            System.out.println("Write 'alphabetic' (without quotes) - customers in alphabetic order");

            String value = scanner.nextLine();
            boolean cycleCanStop = false;


            switch( value ){
                case "range", "alphabetic" -> cycleCanStop = true;
                default -> {
                    System.out.println("Wrong! You have written '" + value + "' ");
                    System.out.println("You should choose either 'range' or 'alphabetic'.");
                    System.out.println(REWRITE_PLEASE);
                }
            }

            if( cycleCanStop ){
                System.out.println("OK. You have chosen operation type " + value + "!");
                return value.equals( "range" ) ? OperationType.CARD_NUMBER_RANGE : OperationType.ALPHABETIC_ORDER;
            }

        }
    }


    public boolean performAnotherOperation() {
        while( true ){
            System.out.println("Write, please, 'yes' if you want to perform another " +
                    "operation to start list or 'no' in another case.");
            String value = scanner.nextLine();

            boolean cycleCanStop = false;


            switch( value ){
                case "no", "yes" -> {
                    cycleCanStop = true;
                }
                default -> {
                    System.out.println("Wrong! You have written '" + value + "' ");
                    System.out.println("You should choose either 'yes' or 'no' no continue or not respectively.");
                    System.out.println(REWRITE_PLEASE);
                }
            }

            if( cycleCanStop ){
                System.out.println("OK. You have chosen operation type '" + value + "'!");
                return value.equals( "yes" );
            }
        }

    }


    public long getLeftBoundOfRange() {

        System.out.println("Write, please, left bound of range.");

        while( !scanner.hasNextInt() ){
            System.out.println("You have to insert a long-type number!");
            scanner.next();
        }

        return scanner.nextInt();
    }


    public long getRightBoundOfRange() {

        System.out.println("Write, please, right bound of range.");

        while( !scanner.hasNextInt() ){
            System.out.println("You have to insert a long-type number!");
            scanner.next();
        }

        return scanner.nextInt();
    }


    public boolean saveDialog() {

        while( true ){
            System.out.println( "Do you want to save the result of your computation? " +
                    "Print 'yes' or 'no' (without quotes)." );
            String value = scanner.nextLine();

            boolean cycleCanStop = false;


            switch( value ){
                case "no", "yes" -> {
                    cycleCanStop = true;
                }
                default -> {
                    System.out.println("Wrong! You have written '" + value + "' ");
                    System.out.println("You should choose either 'yes' or 'no' no continue or not respectively.");
                    System.out.println(REWRITE_PLEASE);
                }
            }

            if( cycleCanStop ){
                System.out.println("OK. You have chosen operation type '" + value + "'!");
                return value.equals( "yes" );
            }
        }

    }


    public File saveFileGetting() {
        File fileToSave = null;
        while( true ){
            System.out.println( "You desired to save data into the file. " +
                    "Are you still desired? Write 'yes' or 'no' (without quotes)." );
            String value = scanner.nextLine();

            boolean cycleCanStop = false;

            switch( value ){
                case "no", "yes" -> {
                    cycleCanStop = true;
                }
                default -> {
                    System.out.println("Wrong! You have written '" + value + "' ");
                    System.out.println("You should choose either 'yes' or 'no' no continue or not respectively.");
                    System.out.println(REWRITE_PLEASE);
                }
            }

            if( cycleCanStop ){
                if( value.equals( "yes" ) ){
                    System.out.println( "OK. Now you need to type in name of the file." );
                    fileToSave = inputFileNameToSaveCustomerList();
                }else{
                    System.out.println( "Dismissing of saving current data to file was done successfully" );
                }
                break;
            }
        }
        return fileToSave;
    }


    private File inputFileNameToSaveCustomerList(){
        System.out.println( "Type in name of the file and press enter " +
                "( file will be placed in src/main/resources directory ) : " );
        System.out.println("!!!Notice: write name without extension. It will be automatically set to .json.");
        String fileName = scanner.nextLine();
        return new File(RESOURCES_FILES_PREFIX + fileName + ".json");
    }


}
