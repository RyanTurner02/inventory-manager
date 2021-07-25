/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AddItemControllerTest {
    @Test
    public void one_hundred_items_can_be_stored() {
        // create an item list and an item object
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(0));

        // add the item object to the item list 100 times
        for (int i = 0; i < 100; i++) {
            itemList.add(item);
        }

        int expected = 100;
        int actual = itemList.size();

        // assert that the size of the item list is 100
        assertEquals(expected, actual);
    }

    @Test
    public void item_value_is_in_usd_monetary_value_1() {
        // create an item list and an item object and store it into the item list
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(20));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // get the monetary value and check if it is in usd format
        String monetaryValue = itemList.get(0).getMonetaryValue().toString();
        boolean flag = addItemController.hasUSDFormat(monetaryValue);

        // assert true that the item value is in usd format
        assertTrue(flag);
    }

    @Test
    public void item_value_is_in_usd_monetary_value_2() {
        // create an item list and an item object and store it into the item list
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(250.75));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // get the monetary value and check if it is in usd format
        String monetaryValue = itemList.get(0).getMonetaryValue().toString();
        boolean flag = addItemController.hasUSDFormat(monetaryValue);

        // assert true that the item value is in usd format
        assertTrue(flag);
    }

    @Test
    public void item_value_is_not_in_usd_monetary_value_1() {
        // create an item list and an item object and store it into the item list
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(100.755));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // get the monetary value and check if it is in usd format
        String monetaryValue = itemList.get(0).getMonetaryValue().toString();
        boolean flag = addItemController.hasUSDFormat(monetaryValue);

        // assert false that the item value is not in usd format
        assertFalse(flag);
    }

    @Test
    public void item_value_is_not_in_usd_monetary_value_2() {
        // create an item list and an item object and store it into the item list
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // get the monetary value and check if it is in usd format
        String monetaryValue = itemList.get(0).getMonetaryValue().toString();
        boolean flag = addItemController.hasUSDFormat(monetaryValue);

        // assert false that the item value is not in usd format
        assertFalse(flag);
    }

    @Test
    public void item_serial_number_is_alphanumeric_1() {
        // create an item list and an item object and store it into the item list
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "AaBb123GHk", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // get the serial number and check if it is alphanumeric
        String serialNumber = itemList.get(0).getSerialNumber();
        boolean flag = addItemController.hasAlphaNumericCharacters(serialNumber);

        // assert true that the serial number is alphanumeric
        assertTrue(flag);
    }

    @Test
    public void item_serial_number_is_alphanumeric_2() {
        // create an item list and an item object and store it into the item list
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "Znolaw8932", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // get the serial number and check if it is alphanumeric
        String serialNumber = itemList.get(0).getSerialNumber();
        boolean flag = addItemController.hasAlphaNumericCharacters(serialNumber);

        // assert true that the serial number is alphanumeric
        assertTrue(flag);
    }

    @Test
    public void item_serial_number_is_not_alphanumeric_1() {
        // create an item list and an item object and store it into the item list
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "!@#$%^&*()", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // get the serial number and check if it is alphanumeric
        String serialNumber = itemList.get(0).getSerialNumber();
        boolean flag = addItemController.hasAlphaNumericCharacters(serialNumber);

        // assert false that the serial number is not alphanumeric
        assertFalse(flag);
    }

    @Test
    public void item_serial_number_is_not_alphanumeric_2() {
        // create an item list and an item object and store it into the item list
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "abc!&*.<;/", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // get the serial number and check if it is alphanumeric
        String serialNumber = itemList.get(0).getSerialNumber();
        boolean flag = addItemController.hasAlphaNumericCharacters(serialNumber);

        // assert false that the serial number is not alphanumeric
        assertFalse(flag);
    }

    @Test
    public void item_serial_number_is_ten_characters_long_1() {
        // create an item list and add an item to the list
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "1234567890", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // get the serial number and check if it is 10 characters long
        String serialNumber = itemList.get(0).getSerialNumber();
        boolean flag = addItemController.hasInvalidSerialNumberLength(serialNumber);

        // assert false that the serial number is not invalid
        assertFalse(flag);
    }

    @Test
    public void item_serial_number_is_not_ten_characters_long_1() {
        // create an item list and add an item to the list
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // get the serial number and check if it is 10 characters long
        String serialNumber = itemList.get(0).getSerialNumber();
        boolean flag = addItemController.hasInvalidSerialNumberLength(serialNumber);

        // assert true that the serial number is invalid
        assertTrue(flag);
    }

    @Test
    public void item_duplicate_serial_number_found_1() {
        // create an item list, store two items with the same serial number
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item1 = new Item("Name", "ABCDEFGHIJ", new BigDecimal(0));
        Item item2 = new Item("Another Name", "abcdefghij", new BigDecimal(0));

        itemList.add(item1);
        itemList.add(item2);

        AddItemController addItemController = new AddItemController(itemList, null);

        // create a flag variable and check if there is a duplicate serial number
        String serialNumber = item1.getSerialNumber();
        boolean flag = addItemController.hasDuplicateSerialNumber(serialNumber);

        // assert true that there is a duplicate serial number
        assertTrue(flag);
    }

    @Test
    public void item_duplicate_serial_number_found_2() {
        // create an item list, store two items with the same serial number
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item1 = new Item("Name", "ABC123DEFZ", new BigDecimal(0));
        Item item2 = new Item("Another Name", "ABC123DEFZ", new BigDecimal(0));

        itemList.add(item1);
        itemList.add(item2);

        AddItemController addItemController = new AddItemController(itemList, null);

        // create a flag variable and check if there is a duplicate serial number
        String serialNumber = item1.getSerialNumber();
        boolean flag = addItemController.hasDuplicateSerialNumber(serialNumber);

        // assert true that there is a duplicate serial number
        assertTrue(flag);
    }

    @Test
    public void item_name_length_is_between_2_to_256_characters_1() {
        // create an item where the name is 2 characters
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("AA", "XXXXXXXXXX", new BigDecimal(10));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // create a flag variable and check if the name length is invalid
        String name = itemList.get(0).getName();
        boolean flag = addItemController.hasInvalidNameLength(name);

        // assert false that the name length is not invalid
        assertFalse(flag);
    }

    @Test
    public void item_name_length_is_between_2_to_256_characters_2() {
        // create an item where the name is 256 characters
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "XXXXXXXXXX", new BigDecimal(10));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // create a flag variable and check if the name length is invalid
        String name = itemList.get(0).getName();
        boolean flag = addItemController.hasInvalidNameLength(name);

        // assert false the name length is not invalid
        assertFalse(flag);
    }

    @Test
    public void item_name_length_is_not_between_2_to_256_characters_1() {
        // create an item where the name is 1 character
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("A", "XXXXXXXXXX", new BigDecimal(10));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // create a flag variable and check if the name length is invalid
        String name = itemList.get(0).getName();
        boolean flag = addItemController.hasInvalidNameLength(name);

        // assert true that the name length is invalid
        assertTrue(flag);
    }

    @Test
    public void item_name_length_is_not_between_2_to_256_characters_2() {
        // create an item where the name is 257 characters
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "XXXXXXXXXX", new BigDecimal(10));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        // create a flag variable and check if the name length is invalid
        String name = itemList.get(0).getName();
        boolean flag = addItemController.hasInvalidNameLength(name);

        // assert true that the name length is invalid
        assertTrue(flag);
    }

    @Test
    public void item_can_be_added() {
        // create an item list and add an item
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(0));
        itemList.add(item);

        int expected = 1;
        int actual = itemList.size();

        // assert that the item size is equal to 1
        assertEquals(expected, actual);
    }
}
