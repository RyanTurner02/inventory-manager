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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class AddItemController {
    private ObservableList<Item> itemList;
    private SceneManager sceneManager;

    @FXML private Button addItemButton;
    @FXML private Button cancelButton;
    @FXML private TextField nameTextField;
    @FXML private TextField serialNumberTextField;
    @FXML private TextField valueTextField;

    public AddItemController(ObservableList<Item> itemList, SceneManager sceneManager) {
        this.itemList = itemList;
        this.sceneManager = sceneManager;
    }

    public void addItemButtonPressed(ActionEvent event) {
        // get the inputted name
        String name = this.nameTextField.getText();

        // get the inputted serial number
        String serialNumber = this.serialNumberTextField.getText();

        // get the inputted value
        BigDecimal value = new BigDecimal(this.valueTextField.getText());

        // iterate through the item list
        for (Item item : itemList) {
            // check if the inputted serial number is equal to the item's serial number from the item list
            if (serialNumber.equalsIgnoreCase(item.getSerialNumber())) {
                // display an error window

                // exit the function
                return;
            }
        }

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
        this.nameTextField.setText("");
        this.serialNumberTextField.setText("");
        this.valueTextField.setText("");
    }

    public void nameTextFieldTyped(KeyEvent keyEvent) {
        // check if the name text field is greater than 256 characters
        if (this.nameTextField.getLength() > 256) {
            // prevent the user from entering any more characters
            this.nameTextField.setText(this.nameTextField.getText(0, 256));

            // set the caret to the 256th position
            this.serialNumberTextField.positionCaret(256);
        }
    }

    public void serialNumberTextFieldTyped(KeyEvent keyEvent) {
        // check if the serial number text field is greater than 10 characters
        if (this.serialNumberTextField.getLength() > 10) {
            // prevent the user from entering any more characters
            this.serialNumberTextField.setText(this.serialNumberTextField.getText(0, 10));

            // set the caret to the 10th position
            this.serialNumberTextField.positionCaret(10);
        }
    }

    public void valueTextFieldTyped(KeyEvent keyEvent) {

    }
}
