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

    public void addItemButtonPressed(ActionEvent event) {
        // get the inputted name
        String name = this.nameTextField.getText();

        // get the length of the name
        int nameLength = name.length();

        // check if the name length is less than 2
        if (nameLength < 2) {
            // exit the function
            return;
        }

        // get the inputted serial number
        String serialNumber = this.serialNumberTextField.getText();

        // check if the length of the serial number is not equal to 10
        if (serialNumber.length() != 10) {
            // exit the function
            return;
        }

        // iterate through the item list
        for (Item item : itemList) {
            // check if the inputted serial number is equal to the item's serial number from the item list
            if (serialNumber.equalsIgnoreCase(item.getSerialNumber())) {
                // display an error window
                Stage errorStage = new Stage();
                errorStage.setTitle("Error");
                errorStage.setResizable(false);
                errorStage.setScene(this.sceneManager.getScene("Error"));
                errorStage.show();

                // exit the function
                return;
            }
        }

        // get the inputted value
        BigDecimal value = new BigDecimal(this.valueTextField.getText()).setScale(2, RoundingMode.DOWN);

        // create an item object
        Item item = new Item(name, serialNumber, value);

        // pass the item to the inventory manager controller
        itemList.add(item);

        // clear the text fields
        clearTextFields();

        // get the stage
        Stage stage = (Stage) this.addItemButton.getScene().getWindow();

        // close the stage
        stage.close();
    }

    public void cancelButtonPressed(ActionEvent event) {
        // clear the text fields
        clearTextFields();

        // get the stage
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();

        // close the stage
        stage.close();
    }

    private void clearTextFields() {
        this.nameTextField.clear();
        this.serialNumberTextField.clear();
        this.valueTextField.clear();
    }

    public void nameTextFieldPressed(KeyEvent keyEvent) {
        this.nameTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().length() <= 256) {
                return c;
            } else {
                return null;
            }
        }));
    }

    public void serialNumberTextFieldPressed(KeyEvent keyEvent) {
        this.serialNumberTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().length() <= 10 && c.getControlNewText().matches("^|^[a-zA-Z0-9]+$")) {
                return c;
            } else {
                return null;
            }
        }));
    }

    public void valueTextFieldPressed(KeyEvent keyEvent) {
        this.valueTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().matches("^|^\\d+\\.?\\d{0,2}$")) {
                return c;
            } else {
                return null;
            }
        }));
    }
}
