/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryManager implements Initializable {
    @FXML private MenuItem importMenuItem;
    @FXML private MenuItem exportMenuItem;
    @FXML private TableColumn<Item, String> nameColumn;
    @FXML private TableColumn<Item, String> serialNumberColumn;
    @FXML private TableColumn<Item, BigDecimal> valueColumn;
    @FXML private Button addItemButton;
    @FXML private Button modifyItemButton;
    @FXML private Button searchItemButton;
    @FXML private Button deleteItemButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void importMenuItemPressed(ActionEvent event) {
    }

    public void exportMenuItemPressed(ActionEvent event) {
    }

    public void addItemButtonPressed(ActionEvent event) {
    }

    public void modifyItemButtonPressed(ActionEvent event) {
    }

    public void searchItemButtonPressed(ActionEvent event) {
    }

    public void deleteItemButtonPressed(ActionEvent event) {
    }
}
