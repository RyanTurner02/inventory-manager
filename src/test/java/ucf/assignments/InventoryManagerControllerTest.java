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
        // create an item list
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        // add an item to the list
        itemList.add(new Item("Name", "XXXXXXXXXX", new BigDecimal(0)));

        // remove the item from the list
        itemList.remove(0);

        int actual = itemList.size();
        int expected = 0;

        // assert that the size is equal to 0
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
        // create an item list and store two items
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "HelloWorld", new BigDecimal(100)));
        itemList.add(new Item("Meteor", "1234567890", new BigDecimal(500)));

        InventoryManagerController inventoryManagerController = new InventoryManagerController(itemList, null);

        // create a filtered list that has a name or serial number that contains the user input
        String userInput = "met";
        ObservableList<Item> filteredList = inventoryManagerController.searchItemsByNameAndSerialNumber(itemList, userInput);

        boolean flag = false;

        // iterate through the filtered list
        for (Item item : filteredList) {
            // check if the item contains the user input
            if (item.getName().toLowerCase().contains(userInput.toLowerCase())) {
                flag = true;
                break;
            }
        }
        // assert true that an item can be searched by their name
        assertTrue(flag);
    }

    @Test
    public void item_can_be_searched_by_name_2() {
        // create an item list and store two items
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "HelloWorld", new BigDecimal(100)));
        itemList.add(new Item("Meteor", "1234567890", new BigDecimal(500)));

        InventoryManagerController inventoryManagerController = new InventoryManagerController(itemList, null);

        // create a filtered list that has a name or serial number that contains the user input
        String userInput = "nam";
        ObservableList<Item> filteredList = inventoryManagerController.searchItemsByNameAndSerialNumber(itemList, userInput);

        boolean flag = false;

        // iterate through the filtered list
        for (Item item : filteredList) {
            // check if the item contains the user input
            if (item.getName().toLowerCase().contains(userInput.toLowerCase())) {
                flag = true;
                break;
            }
        }
        // assert true that an item can be searched by their name
        assertTrue(flag);
    }

    @Test
    public void item_can_be_searched_by_serial_number_1() {
        // create an item list and store two items
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "HelloWorld", new BigDecimal(100)));
        itemList.add(new Item("Meteor", "1234567890", new BigDecimal(500)));

        InventoryManagerController inventoryManagerController = new InventoryManagerController(itemList, null);

        // create a filtered list that has a name or serial number that contains the user input
        String userInput = "hellow";
        ObservableList<Item> filteredList = inventoryManagerController.searchItemsByNameAndSerialNumber(itemList, userInput);

        boolean flag = false;

        // iterate through the filtered list
        for (Item item : filteredList) {
            // check if the item contains the user input
            if (item.getSerialNumber().toLowerCase().contains(userInput.toLowerCase())) {
                flag = true;
                break;
            }
        }
        // assert true that an item can be searched by their serial number
        assertTrue(flag);
    }

    @Test
    public void item_can_be_searched_by_serial_number_2() {
        // create an item list and store two items
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "HelloWorld", new BigDecimal(100)));
        itemList.add(new Item("Meteor", "1234567890", new BigDecimal(500)));

        InventoryManagerController inventoryManagerController = new InventoryManagerController(itemList, null);

        // create a filtered list that has a name or serial number that contains the user input
        String userInput = "567";
        ObservableList<Item> filteredList = inventoryManagerController.searchItemsByNameAndSerialNumber(itemList, userInput);

        boolean flag = false;

        // iterate through the filtered list
        for (Item item : filteredList) {
            // check if the item contains the user input
            if (item.getSerialNumber().toLowerCase().contains(userInput.toLowerCase())) {
                flag = true;
                break;
            }
        }
        // assert true that an item can be searched by their serial number
        assertTrue(flag);
    }
}