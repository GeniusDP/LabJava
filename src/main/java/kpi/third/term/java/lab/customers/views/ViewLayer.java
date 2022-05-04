package kpi.third.term.java.lab.customers.views;

import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.utilities.FileNameHelper;
import kpi.third.term.java.lab.customers.utilities.OperationType;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class ViewLayer {

    public static final String HELLO_MESSAGE = "Hello, this is program.";
    public static final String CUSTOMERS_LIST_FETCHED_MESSAGE = "Those are all customers, fetched from storage:";

    private static final String REWRITE_PLEASE = "Rewrite please!";
    private static final String RESOURCES_FILES_PREFIX = "src/main/resources/";

    public void printMessage(String message) {
        System.out.println( message );
    }


    public void printCustomerList(List<Customer> customers) {
        System.out.println( "This is a list of customers you needed:" );
        customers.forEach( s -> System.out.println( s ));
    }


    public File resolveInputFile() {
        Scanner scanner = new Scanner( System.in );
        System.out.println( "Firstly, you need to choose the source file." );
        System.out.println( "File should be in resources directory of program." );
        System.out.println( "Type in the name of the file (extension must be either .ser or .json) : " );

        File file = null;
        while(true) {
            String fileName = scanner.nextLine();

            boolean canStop = FileNameHelper.getFileExtension(fileName).equals("ser");
            canStop = canStop || FileNameHelper.getFileExtension(fileName).equals("json");
            canStop = canStop && Files.exists( Path.of(RESOURCES_FILES_PREFIX + fileName) );

            if( canStop ){
                file = new File( RESOURCES_FILES_PREFIX + fileName );
                break;
            }else{
                System.out.println("Extension must be either .ser or .json and file should exist in folder resources!");
                System.out.println(REWRITE_PLEASE);
            }
        }
        System.out.println("OK. File was found! " );
        return file;
    }


    public OperationType getOperationType() {
        Scanner scanner = new Scanner( System.in );
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
        Scanner scanner = new Scanner( System.in );
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
                System.out.println("OK. You have chosen operation type " + value + "!");
                return value.equals( "yes" );
            }
        }

    }


    public long getLeftBoundOfRange() {
        Scanner scanner = new Scanner( System.in );

        System.out.println("Write, please, left bound of range.");

        while( !scanner.hasNextInt() ){
            System.out.println("You have to insert a long number!");
            scanner.next();
        }

        return scanner.nextInt();
    }


    public long getRightBoundOfRange() {
        Scanner scanner = new Scanner( System.in );

        System.out.println("Write, please, right bound of range.");

        while( !scanner.hasNextInt() ){
            System.out.println("You have to insert a long number!");
            scanner.next();
        }

        return scanner.nextInt();
    }


}