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
        String name = "Name";
        String newName = "Another Name";
        String serialNumber = "HelloWorld";
        String monetaryValue = "0";

        ObservableList<Item> actualItemList = FXCollections.observableArrayList();
        actualItemList.add(new Item(name, serialNumber, new BigDecimal(monetaryValue)));

        ObservableList<Item> expectedItemList = FXCollections.observableArrayList();
        expectedItemList.add(new Item(newName, serialNumber, new BigDecimal(monetaryValue)));

        Item item = new Item(newName, serialNumber, new BigDecimal(monetaryValue));
        actualItemList.set(0, item);

        boolean flag = true;

        int size = actualItemList.size();
        for(int i = 0; i < size; i++) {
            if(!actualItemList.get(i).getName().equalsIgnoreCase(expectedItemList.get(i).getName())) {
                flag = false;
                break;
            }

            if(!actualItemList.get(i).getSerialNumber().equalsIgnoreCase(expectedItemList.get(i).getSerialNumber())) {
                flag = false;
                break;
            }

            if(!actualItemList.get(i).getMonetaryValue().equals(expectedItemList.get(i).getMonetaryValue())) {
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void item_serial_number_can_be_edited() {
        String name = "Name";
        String serialNumber = "HelloWorld";
        String newSerialNumber = "Another Name";
        String monetaryValue = "0";

        ObservableList<Item> actualItemList = FXCollections.observableArrayList();
        actualItemList.add(new Item(name, serialNumber, new BigDecimal(monetaryValue)));

        ObservableList<Item> expectedItemList = FXCollections.observableArrayList();
        expectedItemList.add(new Item(name, newSerialNumber, new BigDecimal(monetaryValue)));

        Item item = new Item(name, newSerialNumber, new BigDecimal(monetaryValue));
        actualItemList.set(0, item);

        boolean flag = true;

        int size = actualItemList.size();
        for(int i = 0; i < size; i++) {
            if(!actualItemList.get(i).getName().equalsIgnoreCase(expectedItemList.get(i).getName())) {
                flag = false;
                break;
            }

            if(!actualItemList.get(i).getSerialNumber().equalsIgnoreCase(expectedItemList.get(i).getSerialNumber())) {
                flag = false;
                break;
            }

            if(!actualItemList.get(i).getMonetaryValue().equals(expectedItemList.get(i).getMonetaryValue())) {
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void item_duplicate_serial_number_found() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "helloworld", new BigDecimal(10)));
        itemList.add(new Item("Item Name", "absfJ2ivkn", new BigDecimal(10)));
        itemList.add(new Item("Another Name", "HelloWorld", new BigDecimal(10)));

        ModifyItemController modifyItemController = new ModifyItemController(itemList, itemList.get(0), null);
        boolean flag = modifyItemController.hasDuplicateSerialNumber(itemList.get(0).getSerialNumber());

        assertTrue(flag);
    }

    @Test
    public void item_value_can_be_edited() {
        String name = "Name";
        String serialNumber = "HelloWorld";
        String monetaryValue = "0";
        String newMonetaryValue = "500.50";

        ObservableList<Item> actualItemList = FXCollections.observableArrayList();
        actualItemList.add(new Item(name, serialNumber, new BigDecimal(monetaryValue)));

        ObservableList<Item> expectedItemList = FXCollections.observableArrayList();
        expectedItemList.add(new Item(name, serialNumber, new BigDecimal(newMonetaryValue)));

        Item item = new Item(name, serialNumber, new BigDecimal(newMonetaryValue));
        actualItemList.set(0, item);

        boolean flag = true;

        int size = actualItemList.size();
        for(int i = 0; i < size; i++) {
            if(!actualItemList.get(i).getName().equalsIgnoreCase(expectedItemList.get(i).getName())) {
                flag = false;
                break;
            }

            if(!actualItemList.get(i).getSerialNumber().equalsIgnoreCase(expectedItemList.get(i).getSerialNumber())) {
                flag = false;
                break;
            }

            if(!actualItemList.get(i).getMonetaryValue().equals(expectedItemList.get(i).getMonetaryValue())) {
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }
}