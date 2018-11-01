package ee.ut.math.tvt.salessystem.ui.controllers;

import com.sun.javafx.collections.ObservableListWrapper;
import ee.ut.math.tvt.salessystem.dao.SalesSystemDAO;
import ee.ut.math.tvt.salessystem.dataobjects.SoldItem;
import ee.ut.math.tvt.salessystem.dataobjects.StockItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class StockController implements Initializable {

    private static final Logger log = LogManager.getLogger(StockController.class);

    private final SalesSystemDAO dao;

    @FXML
    private Button addItem;
    @FXML
    private TableView<StockItem> warehouseTableView;

    @FXML
    private TextField barCodeField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;



    public StockController(SalesSystemDAO dao) {
        this.dao = dao;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        log.info("Stock controller is initialized");
        refreshStockItems();
        // TODO refresh view after adding new items
    }

    @FXML
    public void refreshButtonClicked() {
        log.info("Warehouse has been refreshed");
        refreshStockItems();
    }

    private void refreshStockItems() {
        warehouseTableView.setItems(new ObservableListWrapper<>(dao.findStockItems()));
        warehouseTableView.refresh();
    }

    @FXML
    public void addProductButtonClicked() {
        log.info("Product has been added");
        addItemEventHandler();
    }

    public void addItemEventHandler() {
        try {
            Long barCode = Long.valueOf(barCodeField.getText());
            String name = String.valueOf(nameField.getText());
            String description = "";
            double price = Double.valueOf(priceField.getText());
            int quantity = Integer.valueOf(quantityField.getText());
            StockItem stockItem = new StockItem(barCode, name, description, price, quantity);
            dao.saveStockItem(stockItem);
        }
        catch (RuntimeException e) {
            log.error("Wrong input data");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Wrong input data!");
            alert.setContentText("Try to enter correct data!");
            alert.showAndWait();
        }
    }

    private void getInput() {
        System.out.println(barCodeField.getPromptText());
    }

    @FXML
    public void resetProductField() {
    }

    @FXML
    public void refreeshStockItems() {
    }

    @FXML
    public void showWarehouse() {
    }





}
