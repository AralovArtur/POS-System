package ee.ut.math.tvt.salessystem.ui.controllers;

import ee.ut.math.tvt.salessystem.SalesSystemException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryController implements Initializable {

    private static final Logger log = LogManager.getLogger(HistoryController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: implement
    }

    @FXML
    public Button showBetweenDates;

    @FXML
    public Button showLast10;

    @FXML
    public Button showAll;

    @FXML
    public Button startDate;

    @FXML
    public Button endDate;

    @FXML
    protected void showBetweenDatesButtonClicked() {
        log.info("Show between dates has been requested");
    }

    @FXML
    protected void showLast10() {
        log.info("Show last 10 has been requested");
    }

    @FXML
    private void showPurchaseHistory(){
        log.info("Purchase history has been requested");
    }

    private void resetDateField(){

    }

    private void showTransactionHistory(){

    }

    private void start(Stage stage){

    }
}
