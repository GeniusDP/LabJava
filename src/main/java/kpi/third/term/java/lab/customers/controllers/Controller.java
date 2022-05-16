package kpi.third.term.java.lab.customers.controllers;

import kpi.third.term.java.lab.customers.controllers.service.CustomerService;
import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.repositories.JSONModel;
import kpi.third.term.java.lab.customers.models.repositories.ModelLayer;
import kpi.third.term.java.lab.customers.utilities.OperationType;
import kpi.third.term.java.lab.customers.views.ViewLayer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Controller {

    private final ModelLayer model;
    private final ViewLayer view;
    private final CustomerService service;
    private final File inputFile;

    private static final Logger logger = LogManager.getLogger(Controller.class);
    public Controller(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            logger.error( "File reading exception ( application.properties ) : " + e );
            System.exit(1337);
        }
        view = new ViewLayer();
        model = new JSONModel();
        service = new CustomerService();
        inputFile = new File( properties.getProperty("main-file-path") );
    }


    public void start() {
        view.printMessage( ViewLayer.HELLO_MESSAGE );

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
                    model.saveAll(fileToSave, currentCustomersList);
                    view.printMessage( ViewLayer.EVERYTHING_SAVED  );
                }
            }

        }while( view.performAnotherOperation() );

    }


}
