package kpi.third.term.java.lab.customers.controllers;

import kpi.third.term.java.lab.customers.models.exceptions.JsonParserException;
import kpi.third.term.java.lab.customers.models.service.CustomerService;
import kpi.third.term.java.lab.customers.models.entities.Customer;
import kpi.third.term.java.lab.customers.models.repositories.JSONRepository;
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

    private final ViewLayer view;
    private final CustomerService service;
    private static final Logger logger = LogManager.getLogger(Controller.class);

    public Controller(){
        logger.debug("Initialization of project started.");

        this.view = new ViewLayer();

        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            view.printMessage( ViewLayer.ERROR_DURING_LOADING_DATA );
            logger.fatal( "Error during accessing config file " + e );
            System.exit(1337);
        }

        service = new CustomerService( new JSONRepository( new File( properties.getProperty( "main-file-path" ) ) ) );
    }


    public void start() {
        logger.info("Application has been started.");
        view.printMessage( ViewLayer.HELLO_MESSAGE );
        view.printMessage( ViewLayer.CUSTOMERS_LIST_FETCHED_MESSAGE );

        try{

            mainControllerJob();

        }catch (IOException e){
            view.printMessage(ViewLayer.ERROR_DURING_LOADING_DATA);
            logger.fatal( "Error during working with storage." );
            System.exit(1337);
        } catch (JsonParserException e) {
            view.printMessage(ViewLayer.ERROR_DURING_PARSING_LOADED_DATA);
            logger.fatal( "Error during parsing data." );
            System.exit(1337);
        }

        logger.info("Application has been finished successfully.");
    }

    private void mainControllerJob() throws IOException, JsonParserException{
        view.printCustomerList( service.findAll( ) );
        do{
            OperationType operationType = view.getOperationType();
            List<Customer> currentCustomersList = switch( operationType ){
                case ALPHABETIC_ORDER -> service.getCustomersInAlphabeticOrder();
                case CARD_NUMBER_RANGE -> {
                    long leftBound = view.getLeftBoundOfRange();
                    long rightBound = view.getRightBoundOfRange();
                    yield service.getCustomersByCardNumberInRange(leftBound, rightBound);
                }
            };
            view.printCustomerList( currentCustomersList );

            boolean savingDesired = view.saveDialog();

            if( savingDesired ){
                File fileToSave = view.saveFileGetting();
                if( fileToSave != null ){
                    logger.info("Result of current computation saved.");
                    service.saveAll(fileToSave, currentCustomersList);
                    view.printMessage( ViewLayer.EVERYTHING_SAVED  );
                }
            }

        }while( view.performAnotherOperation() );
    }


}
