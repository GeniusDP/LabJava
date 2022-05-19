package kpi.third.term.java.lab.customers.controllers;

import kpi.third.term.java.lab.customers.models.service.CustomerService;
import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.repositories.JSONRepository;
import kpi.third.term.java.lab.customers.models.repositories.Repository;
import kpi.third.term.java.lab.customers.models.utilities.OperationType;
import kpi.third.term.java.lab.customers.views.ViewLayer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Controller {

    private final Repository repository;
    private final ViewLayer view;
    private final CustomerService service;
    private static final Logger logger = LogManager.getLogger(Controller.class);

    public Controller(){
        logger.debug("Initialization of project started.");
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            logger.fatal( "File reading exception ( application.properties ) : " + e );
            System.exit(1337);
        }
        view = new ViewLayer();
        repository = new JSONRepository( new File( properties.getProperty("main-file-path") ) );
        service = new CustomerService();
    }


    public void start() {
        logger.info("Application has been started.");
        view.printMessage( ViewLayer.HELLO_MESSAGE );

        view.printMessage( ViewLayer.CUSTOMERS_LIST_FETCHED_MESSAGE );
        view.printCustomerList( repository.findAll( ) );

        do{
            OperationType operationType = view.getOperationType();
            List<Customer> currentCustomersList = switch( operationType ){
                case ALPHABETIC_ORDER -> service.getCustomersInAlphabeticOrder(repository.findAll( ));
                case CARD_NUMBER_RANGE -> {
                    long leftBound = view.getLeftBoundOfRange();
                    long rightBound = view.getRightBoundOfRange();
                    yield service.getCustomersByCardNumberInRange(repository.findAll( ), leftBound, rightBound);
                }
            };
            view.printCustomerList( currentCustomersList );

            boolean savingDesired = view.saveDialog();

            if( savingDesired ){
                //засунуть в сервис
                File fileToSave = view.saveFileGetting();
                if( fileToSave != null ){
                    logger.info("Result of current computation saved.");
                    repository.saveAll(fileToSave, currentCustomersList);
                    view.printMessage( ViewLayer.EVERYTHING_SAVED  );
                }
            }

        }while( view.performAnotherOperation() );
        logger.info("Application has been finished successfully.");
    }


}
