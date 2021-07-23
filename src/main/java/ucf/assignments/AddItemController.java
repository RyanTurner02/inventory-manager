/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.math.BigDecimal;

public class AddItemController {
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

    public void addItemButtonPressed(ActionEvent event) {
        // get the inputted name
        String name = this.nameTextField.getText();

        // get the inputted serial number
        String serialNumber = this.serialNumberTextField.getText();

        // get the inputted value
        BigDecimal value = BigDecimal.valueOf(Long.parseLong(this.valueTextField.getText()));

        // create an item object
        Item item = new Item(name, serialNumber, value);
    }

    public void cancelButtonPressed(ActionEvent event) {
        // get the stage
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();

        // close the stage
        stage.close();
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
