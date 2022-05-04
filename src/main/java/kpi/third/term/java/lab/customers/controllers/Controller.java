package kpi.third.term.java.lab.customers.controllers;

import kpi.third.term.java.lab.customers.controllers.service.CustomerService;
import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.repositories.JSONModel;
import kpi.third.term.java.lab.customers.models.repositories.ModelLayer;
import kpi.third.term.java.lab.customers.utilities.OperationType;
import kpi.third.term.java.lab.customers.views.ViewLayer;

import java.io.File;
import java.util.List;

public class Controller {

    private ModelLayer model;
    private ViewLayer view;
    private CustomerService service;


    public Controller(){
        view = new ViewLayer();
        model = new JSONModel();
        service = new CustomerService();
    }


    public void start() {
        view.printMessage( ViewLayer.HELLO_MESSAGE );

        File inputFile = view.resolveInputFile();

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
                    yield service.getCustomersByCardNumberInRange(customers, leftBound, rightBound);
                }
            };
            view.printCustomerList( currentCustomersList );

            boolean savingDesired = view.saveDialog();

            if( savingDesired ){
                File fileToSave = view.saveFileGetting();
                if( fileToSave != null ){
                    model.saveListOfCustomersToFile(fileToSave, currentCustomersList);
                    view.printMessage( ViewLayer.EVERYTHING_SAVED  );
                }
            }

        }while( view.performAnotherOperation() );

    }


}
