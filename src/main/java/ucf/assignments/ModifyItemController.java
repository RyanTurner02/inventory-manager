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

public class ModifyItemController {
    private ObservableList<Item> itemList;
    private SceneManager sceneManager;

    private String oldName, oldSerialNumber;
    private BigDecimal oldMonetaryValue;

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

    public ModifyItemController(ObservableList<Item> itemList, SceneManager sceneManager) {
        this.itemList = itemList;
        this.sceneManager = sceneManager;
    }

    @FXML
    public void modifyItemButtonPressed(ActionEvent event) {
        // iterate through the item list
        for (Item item : itemList) {
            // get the item to modify from the list
        }
        // change the item's values
    }

    @FXML
    public void cancelButtonPressed(ActionEvent event) {
        // clear the text fields
        clearTextFields();

        // get the stage
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();

        // close the stage
        stage.close();
    }

    private void clearTextFields() {
        // clear the name text field
        this.nameTextField.clear();

        // clear the serial number text field
        this.serialNumberTextField.clear();

        // clear the monetary value text field
        this.valueTextField.clear();
    }

    @FXML
    public void nameTextFieldPressed(KeyEvent keyEvent) {
        this.nameTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().length() <= 256) {
                return c;
            } else {
                return null;
            }
        }));
    }

    @FXML
    public void serialNumberTextFieldPressed(KeyEvent keyEvent) {
        this.serialNumberTextField.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().length() <= 10 && c.getControlNewText().matches("^|^[a-zA-Z0-9]+$")) {
                return c;
            } else {
                return null;
            }
        }));
    }

    @FXML
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
