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
        // initialize the text fields to the values from the item to be modified.
        this.nameTextField.setText(this.oldItem.getName());
        this.serialNumberTextField.setText(this.oldItem.getSerialNumber());
        this.valueTextField.setText(this.oldItem.getMonetaryValue().toString());
    }

    @FXML
    public void modifyItemButtonPressed(ActionEvent event) {
        String name = this.nameTextField.getText();
        String serialNumber = this.serialNumberTextField.getText();
        String valueString = this.valueTextField.getText();

        if(valueString.isEmpty()) {
            return;
        }

        BigDecimal value = new BigDecimal(this.valueTextField.getText()).setScale(2, RoundingMode.DOWN);

        // check if the user did not make any changes
        if (twoItemsMatch(this.oldItem, new Item(name, serialNumber, value))) {
            closeWindow();
            return;
        }

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

        // replace the old item
        Item item = new Item(name, serialNumber, value);
        int index = getOldItemIndex();
        itemList.set(index, item);

        closeWindow();
    }

    public boolean twoItemsMatch(Item item1, Item item2) {
        // check if both items' fields match
        return item1.getName().equalsIgnoreCase(item2.getName()) &&
                item1.getSerialNumber().equalsIgnoreCase(item2.getSerialNumber()) &&
                item1.getMonetaryValue().equals(item2.getMonetaryValue());
    }

    public int getOldItemIndex() {
        int index = 0;

        for (Item currentItem : itemList) {
            if (twoItemsMatch(currentItem, this.oldItem)) {
                break;
            }
            index++;
        }
        return index;
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
            // check if the current item is being processed
            if (twoItemsMatch(item, this.oldItem)) {
                continue;
            }

            // check if there is a duplicate serial number
            if (serialNumber.equalsIgnoreCase(item.getSerialNumber())) {
                return true;
            }
        }
        return false;
    }

    private void displayErrorWindow() {
        Stage errorStage = new Stage();
        errorStage.setTitle("Error");
        errorStage.setResizable(false);
        errorStage.setScene(this.sceneManager.getScene("Error"));
        errorStage.show();
    }

    private void closeWindow() {
        Stage stage = (Stage) this.modifyItemButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void cancelButtonPressed(ActionEvent event) {
        Stage stage = (Stage) this.cancelButton.getScene().getWindow();
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
            if (hasUSDFormat(c.getControlNewText()) && !c.getControlNewText().equals(".")) {
                return c;
            } else {
                return null;
            }
        }));
    }

    public boolean hasUSDFormat(String monetaryValue) {
        return monetaryValue.matches("^|^\\d{0,10}$|^\\d{0,10}+\\.\\d{0,2}$");
    }
}
