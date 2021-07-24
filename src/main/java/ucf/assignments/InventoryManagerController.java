/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class InventoryManagerController implements Initializable {
    private ObservableList<Item> itemList;
    private SceneManager sceneManager;

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
    private TableColumn<Item, String> valueColumn;

    @FXML
    private Button addItemButton;

    @FXML
    private Button modifyItemButton;

    @FXML
    private Button deleteItemButton;

    @FXML
    private TextField searchTextField;

    public InventoryManagerController(ObservableList<Item> itemList, SceneManager sceneManager) {
        this.itemList = itemList;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialize the name column
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        // initialize the serial number column
        this.serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        // initialize the value column
        this.valueColumn.setCellValueFactory(c -> new SimpleStringProperty("$" + c.getValue().getMonetaryValue()));

        // allow the table columns to be automatically resized
        this.nameColumn.prefWidthProperty().bind(itemTable.widthProperty().multiply(0.4375));
        this.serialNumberColumn.prefWidthProperty().bind(itemTable.widthProperty().multiply(0.4375));
        this.valueColumn.prefWidthProperty().bind(itemTable.widthProperty().multiply(0.1225));

        // display the items
        this.itemTable.setItems(itemList);
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
        Stage stage = new Stage();
        stage.setTitle("Item Creator");
        stage.setResizable(false);
        stage.setScene(this.sceneManager.getScene("AddItem"));
        stage.show();
    }

    @FXML
    public void modifyItemButtonPressed(ActionEvent event) {
        // check if the user selected an object
        if (this.itemTable.getSelectionModel().selectedItemProperty().get() != null) {
            Stage stage = new Stage();
            stage.setTitle("Item Modifier");
            stage.setResizable(false);
            stage.setScene(this.sceneManager.getScene("ModifyItem"));
            stage.show();
        }
    }

    @FXML
    public void deleteItemButtonPressed(ActionEvent event) {
        // get the item to remove
        Item itemToRemove = this.itemTable.getSelectionModel().selectedItemProperty().get();

        // remove the item
        itemList.remove(itemToRemove);

        // display the items
        this.itemTable.setItems(itemList);
    }

    @FXML
    public void searchTextFieldTyped(KeyEvent event) {
        // create an observable list
        ObservableList<Item> searchedItemList = FXCollections.observableArrayList();

        // store the user input from the text field
        String userInput = this.searchTextField.textProperty().get();

        // store the item list size
        int size = itemList.size();

        // iterate through the item list
        for (int i = 0; i < size; i++) {
            // check if the user input contains the item's name or serial number
            if (itemList.get(i).getName().toLowerCase().contains(userInput.toLowerCase()) ||
                    itemList.get(i).getSerialNumber().toLowerCase().contains(userInput.toLowerCase())) {
                // add it to the filtered list
                searchedItemList.add(itemList.get(i));
            }
        }

        // display the filtered items
        this.itemTable.setItems(searchedItemList);
    }
}
