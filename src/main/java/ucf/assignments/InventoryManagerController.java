/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class InventoryManagerController implements Initializable {
    private static ObservableList<Item> itemList;

    @FXML
    private MenuItem importMenuItem;

    @FXML
    private MenuItem exportMenuItem;

    @FXML
    private TableView<Item> itemTable;

    @FXML
    private TableColumn<Item, String> nameColumn;

    @FXML
    private TableColumn<Item, String> serialNumberColumn;

    @FXML
    private TableColumn<Item, BigDecimal> valueColumn;

    @FXML
    private Button addItemButton;

    @FXML
    private Button modifyItemButton;

    @FXML
    private Button deleteItemButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        this.valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        // check if the item list is null
        if (itemList == null) {
            // initialize the item list
            initializeItemList();
        }

        itemList.add(new Item("Hello World!", "123456789A", BigDecimal.TEN));

        // display the items
        this.itemTable.setItems(itemList);
    }

    public void initializeItemList() {
        // initialize the item list
        itemList = FXCollections.observableArrayList();
    }

    @FXML
    public void importMenuItemPressed(ActionEvent event) {
        // create a file handler object
        FileHandler fileHandler = new FileHandler();

        // add all the contents from the file to the item list
        itemList.addAll(fileHandler.importItemsFromFile(this.itemTable));
    }

    @FXML
    public void exportMenuItemPressed(ActionEvent event) {
        // create a file handler object
        FileHandler fileHandler = new FileHandler();

        // export all the contents from the list to a file
        fileHandler.exportItemListToFile(this.itemTable, itemList);
    }

    @FXML
    public void addItemButtonPressed(ActionEvent event) {
    }

    @FXML
    public void modifyItemButtonPressed(ActionEvent event) {
    }

    @FXML
    public void deleteItemButtonPressed(ActionEvent event) {
        // get the item to remove
        Item itemToRemove = this.itemTable.getSelectionModel().selectedItemProperty().get();

        // remove the item
        itemList.remove(itemToRemove);
    }
}
