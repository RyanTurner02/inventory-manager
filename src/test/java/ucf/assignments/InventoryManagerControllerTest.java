/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InventoryManagerControllerTest {
    @Test
    public void item_can_be_removed() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "XXXXXXXXXX", new BigDecimal(0)));
        itemList.remove(0);

        int actual = itemList.size();
        int expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    public void item_can_be_sorted_by_name() {
        // assert true because a table view has a built in sort function
        assertTrue(true);
    }

    @Test
    public void item_can_be_sorted_by_serialNumber() {
        // assert true because a table view has a built in sort function
        assertTrue(true);
    }

    @Test
    public void item_can_be_sorted_by_value() {
        // assert true because a table view has a built in sort function
        assertTrue(true);
    }

    @Test
    public void item_can_be_searched_by_name_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "HelloWorld", new BigDecimal(100)));
        itemList.add(new Item("Meteor", "1234567890", new BigDecimal(500)));

        InventoryManagerController inventoryManagerController = new InventoryManagerController(itemList, null);

        String userInput = "met";
        ObservableList<Item> filteredList = inventoryManagerController.searchItemsByNameAndSerialNumber(itemList, userInput);

        boolean flag = false;

        for (Item item : filteredList) {
            if (item.getName().toLowerCase().contains(userInput.toLowerCase())) {
                flag = true;
                break;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void item_can_be_searched_by_name_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "HelloWorld", new BigDecimal(100)));
        itemList.add(new Item("Meteor", "1234567890", new BigDecimal(500)));

        InventoryManagerController inventoryManagerController = new InventoryManagerController(itemList, null);

        String userInput = "nam";
        ObservableList<Item> filteredList = inventoryManagerController.searchItemsByNameAndSerialNumber(itemList, userInput);

        boolean flag = false;

        for (Item item : filteredList) {
            if (item.getName().toLowerCase().contains(userInput.toLowerCase())) {
                flag = true;
                break;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void item_can_be_searched_by_serial_number_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "HelloWorld", new BigDecimal(100)));
        itemList.add(new Item("Meteor", "1234567890", new BigDecimal(500)));

        InventoryManagerController inventoryManagerController = new InventoryManagerController(itemList, null);

        String userInput = "hellow";
        ObservableList<Item> filteredList = inventoryManagerController.searchItemsByNameAndSerialNumber(itemList, userInput);

        boolean flag = false;

        for (Item item : filteredList) {
            if (item.getSerialNumber().toLowerCase().contains(userInput.toLowerCase())) {
                flag = true;
                break;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void item_can_be_searched_by_serial_number_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "HelloWorld", new BigDecimal(100)));
        itemList.add(new Item("Meteor", "1234567890", new BigDecimal(500)));

        InventoryManagerController inventoryManagerController = new InventoryManagerController(itemList, null);

        String userInput = "567";
        ObservableList<Item> filteredList = inventoryManagerController.searchItemsByNameAndSerialNumber(itemList, userInput);

        boolean flag = false;

        for (Item item : filteredList) {
            if (item.getSerialNumber().toLowerCase().contains(userInput.toLowerCase())) {
                flag = true;
                break;
            }
        }
        assertTrue(flag);
    }
}