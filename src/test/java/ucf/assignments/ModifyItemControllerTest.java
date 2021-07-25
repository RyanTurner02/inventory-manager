/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ModifyItemControllerTest {
    @Test
    public void item_name_can_be_edited() {
        // create item fields
        String name = "Name";
        String newName = "Another Name";
        String serialNumber = "HelloWorld";
        String monetaryValue = "0";

        ObservableList<Item> actualItemList = FXCollections.observableArrayList();
        actualItemList.add(new Item(name, serialNumber, new BigDecimal(monetaryValue)));

        ObservableList<Item> expectedItemList = FXCollections.observableArrayList();
        expectedItemList.add(new Item(newName, serialNumber, new BigDecimal(monetaryValue)));

        // change the item's name
        Item item = new Item(newName, serialNumber, new BigDecimal(monetaryValue));
        actualItemList.set(0, item);

        // check that the actual item's values matches the expected item's values
        boolean flag = true;

        int size = actualItemList.size();
        for (int i = 0; i < size; i++) {
            if (!actualItemList.get(i).getName().equalsIgnoreCase(expectedItemList.get(i).getName())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getSerialNumber().equalsIgnoreCase(expectedItemList.get(i).getSerialNumber())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getMonetaryValue().equals(expectedItemList.get(i).getMonetaryValue())) {
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void item_serial_number_can_be_edited() {
        // create item fields
        String name = "Name";
        String serialNumber = "HelloWorld";
        String newSerialNumber = "Another Name";
        String monetaryValue = "0";

        ObservableList<Item> actualItemList = FXCollections.observableArrayList();
        actualItemList.add(new Item(name, serialNumber, new BigDecimal(monetaryValue)));

        ObservableList<Item> expectedItemList = FXCollections.observableArrayList();
        expectedItemList.add(new Item(name, newSerialNumber, new BigDecimal(monetaryValue)));

        // change the item's serial number
        Item item = new Item(name, newSerialNumber, new BigDecimal(monetaryValue));
        actualItemList.set(0, item);

        // check that the actual item's values matches the expected item's values
        boolean flag = true;

        int size = actualItemList.size();
        for (int i = 0; i < size; i++) {
            if (!actualItemList.get(i).getName().equalsIgnoreCase(expectedItemList.get(i).getName())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getSerialNumber().equalsIgnoreCase(expectedItemList.get(i).getSerialNumber())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getMonetaryValue().equals(expectedItemList.get(i).getMonetaryValue())) {
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void item_duplicate_serial_number_found() {
        // create an item list and store 3 items where two have matching serial numbers
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "helloworld", new BigDecimal(10)));
        itemList.add(new Item("Item Name", "absfJ2ivkn", new BigDecimal(10)));
        itemList.add(new Item("Another Name", "HelloWorld", new BigDecimal(10)));

        // create a flag variable and check if there is a duplicate serial number
        ModifyItemController modifyItemController = new ModifyItemController(itemList, itemList.get(0), null);
        boolean flag = modifyItemController.hasDuplicateSerialNumber(itemList.get(0).getSerialNumber());

        // assert true that a duplicate serial number has been found
        assertTrue(flag);
    }

    @Test
    public void item_value_can_be_edited() {
        // create item fields
        String name = "Name";
        String serialNumber = "HelloWorld";
        String monetaryValue = "0";
        String newMonetaryValue = "500.50";

        ObservableList<Item> actualItemList = FXCollections.observableArrayList();
        actualItemList.add(new Item(name, serialNumber, new BigDecimal(monetaryValue)));

        ObservableList<Item> expectedItemList = FXCollections.observableArrayList();
        expectedItemList.add(new Item(name, serialNumber, new BigDecimal(newMonetaryValue)));

        // change the item's monetary value
        Item item = new Item(name, serialNumber, new BigDecimal(newMonetaryValue));
        actualItemList.set(0, item);

        // check that the actual item's values matches the expected item's values
        boolean flag = true;

        int size = actualItemList.size();
        for (int i = 0; i < size; i++) {
            if (!actualItemList.get(i).getName().equalsIgnoreCase(expectedItemList.get(i).getName())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getSerialNumber().equalsIgnoreCase(expectedItemList.get(i).getSerialNumber())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getMonetaryValue().equals(expectedItemList.get(i).getMonetaryValue())) {
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }
}