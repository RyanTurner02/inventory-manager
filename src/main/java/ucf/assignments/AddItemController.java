/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AddItemController {
    private ObservableList<Item> itemList;
    private SceneManager sceneManager;

    @FXML
    private Button addItemButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField serialNumberTextField;

    @FXML
    private TextField valueTextField;

    public AddItemController(ObservableList<Item> itemList, SceneManager sceneManager) {
        this.itemList = itemList;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void addItemButtonPressed(ActionEvent event) {
        String name = this.nameTextField.getText();
        String serialNumber = this.serialNumberTextField.getText();
        String valueString = this.valueTextField.getText();

        if (valueString.isEmpty()) {
            return;
        }

        BigDecimal value = new BigDecimal(this.valueTextField.getText()).setScale(2, RoundingMode.DOWN);

        // check if the user inputted anything that does not meet the requirements
        if (hasInvalidNameLength(name)) {
            return;
        }

        if (hasInvalidSerialNumberLength(serialNumber)) {
            return;
        }

        if (hasDuplicateSerialNumber(serialNumber)) {
            displayErrorWindow();
            return;
        }

        // add the item to the list
        Item item = new Item(name, serialNumber, value);
        itemList.add(item);

        clearTextFields();
        closeWindow();
    }

    public boolean hasInvalidNameLength(String name) {
        return name.length() < 2 || name.length() > 256;
    }

    public boolean hasInvalidSerialNumberLength(String serialNumber) {
        return serialNumber.length() != 10;
    }

    public boolean hasDuplicateSerialNumber(String serialNumber) {
        // iterate through the item list
        for (Item item : this.itemList) {
            // check if there is a duplicate serial number
            if (serialNumber.equalsIgnoreCase(item.getSerialNumber())) {
                return true;
            }
        }
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
        // get the stage and close it
        Stage stage = (Stage) this.addItemButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancelButtonPressed(ActionEvent event) {
        // clear the text fields and get the stage and close it
        clearTextFields();
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
        stage.close();
    }

    private void clearTextFields() {
        // clear all text fields
        this.nameTextField.clear();
        this.serialNumberTextField.clear();
        this.valueTextField.clear();
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
        return serialNumber.length() <= 10;
    }

    public boolean hasAlphaNumericCharacters(String serialNumber) {
        return serialNumber.matches("^|^[a-zA-Z0-9]+$");
    }

    @FXML
    public void valueTextFieldPressed(KeyEvent keyEvent) {
        this.valueTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (hasUSDFormat(c.getControlNewText())) {
                return c;
            } else {
                return null;
            }
        }));
    }

    public boolean hasUSDFormat(String monetaryValue) {
        return monetaryValue.matches("^|^\\d+\\.?\\d{0,2}$");
    }
}
