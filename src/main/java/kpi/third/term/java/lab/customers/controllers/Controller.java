package kpi.third.term.java.lab.customers.controllers;

import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.repositories.JSONModel;
import kpi.third.term.java.lab.customers.models.repositories.ModelLayer;
import kpi.third.term.java.lab.customers.models.repositories.SerializationModel;
import kpi.third.term.java.lab.customers.utilities.FileNameHelper;
import kpi.third.term.java.lab.customers.utilities.OperationType;
import kpi.third.term.java.lab.customers.views.ViewLayer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private ModelLayer model;
    private ViewLayer view;
    private CustomerService service;


    public Controller(){
        view = new ViewLayer();
        service = new CustomerService();
    }


    public void start() {
        view.printMessage( ViewLayer.HELLO_MESSAGE );

        File inputFile = view.resolveInputFile();
        System.out.println( inputFile.getAbsolutePath() );

        String fileExtension = FileNameHelper.getFileExtension( inputFile.getName() );
        modelInitialization( fileExtension );

        view.printMessage( ViewLayer.CUSTOMERS_LIST_FETCHED_MESSAGE );
        List<Customer> customers = model.findAll( inputFile );
        view.printCustomerList( customers );

        do{
            OperationType operationType = view.getOperationType();
            List<Customer> currentCustomersList = switch( operationType ){
                case ALPHABETIC_ORDER -> service.getCustomersInAlphabeticOrder(customers);
                case CARD_NUMBER_RANGE -> {
                    long leftBound = view.getLeftBoundOfRange();
                    long rightBound = view.getRightBoundOfRange();
                    System.out.println(leftBound);
                    System.out.println(rightBound);
                    yield service.getCustomersByCardNumberInRange(customers, leftBound, rightBound);
                }
            };
            view.printCustomerList( currentCustomersList );

        }while( view.performAnotherOperation() );

    }


    private void modelInitialization(String fileExtension){
        model = switch ( fileExtension ){
            case "ser" -> new SerializationModel();
            default -> new JSONModel();
        };
    }


}
