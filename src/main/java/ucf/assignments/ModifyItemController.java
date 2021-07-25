/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyItemController implements Initializable {
    private ObservableList<Item> itemList;
    private Item oldItem;
    private SceneManager sceneManager;

    @FXML
    private Button modifyItemButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField serialNumberTextField;

    @FXML
    private TextField valueTextField;

    public ModifyItemController(ObservableList<Item> itemList, Item oldItem, SceneManager sceneManager) {
        this.itemList = itemList;
        this.oldItem = oldItem;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.nameTextField.setText(this.oldItem.getName());
        this.serialNumberTextField.setText(this.oldItem.getSerialNumber());
        this.valueTextField.setText(this.oldItem.getMonetaryValue().toString());
    }

    @FXML
    public void modifyItemButtonPressed(ActionEvent event) {
        // get the inputted name
        String name = this.nameTextField.getText();

        // get the inputted serial number
        String serialNumber = this.serialNumberTextField.getText();

        // get the inputted monetary value
        BigDecimal value = new BigDecimal(this.valueTextField.getText()).setScale(2, RoundingMode.DOWN);

        // check if the user did not make any changes
        if (twoItemsMatch(this.oldItem, new Item(name, serialNumber, value))) {
            // close the window
            closeWindow();

            // exit the function
            return;
        }

        // check if the name length is invalid
        if (hasInvalidNameLength(name)) {
            // exit the function
            return;
        }

        // check if the length of the serial number is invalid
        if (hasInvalidSerialNumberLength(serialNumber)) {
            // exit the function
            return;
        }

        // check if the user entered a duplicate serial number
        if (hasDuplicateSerialNumber(serialNumber)) {
            // display an error window
            displayErrorWindow();

            // exit the function
            return;
        }

        // create an item object
        Item item = new Item(name, serialNumber, value);

        // get the old item index
        int index = getOldItemIndex();

        // set the old item to the new item
        itemList.set(index, item);

        // close the window
        closeWindow();
    }

    public boolean twoItemsMatch(Item item1, Item item2) {
        // check if both items' fields match
        return item1.getName().equalsIgnoreCase(item2.getName()) &&
                item1.getSerialNumber().equalsIgnoreCase(item2.getSerialNumber()) &&
                item1.getMonetaryValue().equals(item2.getMonetaryValue());
    }

    public int getOldItemIndex() {
        // create an index counter variable
        int index = 0;

        // iterate through the item list
        for (Item currentItem : itemList) {
            // check if the current item's fields equals the old item's fields
            if (twoItemsMatch(currentItem, this.oldItem)) {
                break;
            }
            // increment the index counter
            index++;
        }
        // return the index
        return index;
    }

    public boolean hasInvalidNameLength(String name) {
        // return true if the name length is less than 2 or greater than 256 characters
        return name.length() < 2 || name.length() > 256;
    }

    public boolean hasInvalidSerialNumberLength(String serialNumber) {
        // return true if the serial number length is not equal to 10
        return serialNumber.length() != 10;
    }

    public boolean hasDuplicateSerialNumber(String serialNumber) {
        // iterate through the item list
        for (Item item : this.itemList) {
            // check if the current item is being processed
            if (twoItemsMatch(item, this.oldItem)) {
                // skip the rest of the current iteration
                continue;
            }

            // check if there is a duplicate serial number
            if (serialNumber.equalsIgnoreCase(item.getSerialNumber())) {
                // return true if a duplicate serial number has been found
                return true;
            }
        }
        // return false if there is no duplicate serial number
        return false;
    }

    private void displayErrorWindow() {
        // display an error window
        Stage errorStage = new Stage();
        errorStage.setTitle("Error");
        errorStage.setResizable(false);
        errorStage.setScene(this.sceneManager.getScene("Error"));
        errorStage.show();
    }

    private void closeWindow() {
        // get the stage
        Stage stage = (Stage) this.modifyItemButton.getScene().getWindow();

        // close the stage
        stage.close();
    }

    @FXML
    public void cancelButtonPressed(ActionEvent event) {
        // get the stage
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();

        // close the stage
        stage.close();
    }

    @FXML
    public void nameTextFieldPressed(KeyEvent keyEvent) {
        this.nameTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (lessThanMaxNameLength(c.getControlNewText())) {
                return c;
            } else {
                return null;
            }
        }));
    }

    private boolean lessThanMaxNameLength(String name) {
        // return true if the name length is less than or equal to 256 characters
        return name.length() <= 256;
    }

    @FXML
    public void serialNumberTextFieldPressed(KeyEvent keyEvent) {
        this.serialNumberTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (lessThanMaxSerialNumberLength(c.getControlNewText()) && hasAlphaNumericCharacters(c.getControlNewText())) {
                return c;
            } else {
                return null;
            }
        }));
    }

    private boolean lessThanMaxSerialNumberLength(String serialNumber) {
        // return true if the serial number length is less than or equal to 10 characters
        return serialNumber.length() <= 10;
    }

    public boolean hasAlphaNumericCharacters(String serialNumber) {
        // return true if there are only alphanumeric characters
        return serialNumber.matches("^|^[a-zA-Z0-9]+$");
    }

    @FXML
    public void valueTextFieldPressed(KeyEvent keyEvent) {
        this.valueTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (hasNumbersAndTwoDecimals(c.getControlNewText())) {
                return c;
            } else {
                return null;
            }
        }));
    }

    public boolean hasNumbersAndTwoDecimals(String monetaryValue) {
        // return true if there are only numbers or numbers with a maximum of 2 decimals
        return monetaryValue.matches("^|^\\d+\\.?\\d{0,2}$");
    }
}
