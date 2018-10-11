package ee.ut.math.tvt.salessystem.ui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryController implements Initializable {

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


    private void resetDateField(){

    }

    private void showPurchaseHistory(){

    }

    private void showTransactionHistory(){

    }

    private void start(Stage stage){

    }
}
