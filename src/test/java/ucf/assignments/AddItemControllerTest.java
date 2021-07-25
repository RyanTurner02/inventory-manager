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
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(0));

        for(int i = 0; i < 100; i++) {
            itemList.add(item);
        }

        int expected = 100;
        int actual = itemList.size();

        assertEquals(expected, actual);
    }

    @Test
    public void item_value_is_in_usd_monetary_value_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(20));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String monetaryValue = itemList.get(0).getMonetaryValue().toString();
        boolean flag = addItemController.hasNumbersAndTwoDecimals(monetaryValue);

        assertTrue(flag);
    }

    @Test
    public void item_value_is_in_usd_monetary_value_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(250.75));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String monetaryValue = itemList.get(0).getMonetaryValue().toString();
        boolean flag = addItemController.hasNumbersAndTwoDecimals(monetaryValue);

        assertTrue(flag);
    }

    @Test
    public void item_value_is_not_in_usd_monetary_value_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(100.755));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String monetaryValue = itemList.get(0).getMonetaryValue().toString();
        boolean flag = addItemController.hasNumbersAndTwoDecimals(monetaryValue);

        assertFalse(flag);
    }

    @Test
    public void item_value_is_not_in_usd_monetary_value_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String monetaryValue = itemList.get(0).getMonetaryValue().toString();
        boolean flag = addItemController.hasNumbersAndTwoDecimals(monetaryValue);

        assertFalse(flag);
    }

    @Test
    public void item_serial_number_is_alphanumeric_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "AaBb123GHk", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String monetaryValue = itemList.get(0).getSerialNumber().toString();
        boolean flag = addItemController.hasAlphaNumericCharacters(monetaryValue);

        assertTrue(flag);
    }

    @Test
    public void item_serial_number_is_alphanumeric_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "Znolaw8932", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String monetaryValue = itemList.get(0).getSerialNumber().toString();
        boolean flag = addItemController.hasAlphaNumericCharacters(monetaryValue);

        assertTrue(flag);
    }

    @Test
    public void item_serial_number_is_not_alphanumeric_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "!@#$%^&*()", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String monetaryValue = itemList.get(0).getSerialNumber().toString();
        boolean flag = addItemController.hasAlphaNumericCharacters(monetaryValue);

        assertFalse(flag);
    }

    @Test
    public void item_serial_number_is_not_alphanumeric_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "abc!&*.<;/", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String monetaryValue = itemList.get(0).getSerialNumber().toString();
        boolean flag = addItemController.hasAlphaNumericCharacters(monetaryValue);

        assertFalse(flag);
    }

    @Test
    public void item_serial_number_is_ten_characters_long_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "1234567890", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String monetaryValue = itemList.get(0).getSerialNumber().toString();
        boolean flag = addItemController.hasInvalidSerialNumberLength(monetaryValue);

        assertFalse(flag);
    }

    @Test
    public void item_serial_number_is_not_ten_characters_long_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "", new BigDecimal(0.001));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String monetaryValue = itemList.get(0).getSerialNumber().toString();
        boolean flag = addItemController.hasInvalidSerialNumberLength(monetaryValue);

        assertTrue(flag);
    }

    @Test
    public void item_duplicate_serial_number_found_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item1 = new Item("Name", "ABCDEFGHIJ", new BigDecimal(0));
        Item item2 = new Item("Another Name", "abcdefghij", new BigDecimal(0));

        itemList.add(item1);
        itemList.add(item2);

        AddItemController addItemController = new AddItemController(itemList, null);

        String serialNumber = item1.getSerialNumber();
        boolean flag = addItemController.hasDuplicateSerialNumber(serialNumber);

        assertTrue(flag);
    }

    @Test
    public void item_duplicate_serial_number_found_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item1 = new Item("Name", "ABC123DEFZ", new BigDecimal(0));
        Item item2 = new Item("Another Name", "ABC123DEFZ", new BigDecimal(0));

        itemList.add(item1);
        itemList.add(item2);

        AddItemController addItemController = new AddItemController(itemList, null);

        String serialNumber = item1.getSerialNumber();
        boolean flag = addItemController.hasDuplicateSerialNumber(serialNumber);

        assertTrue(flag);
    }

    @Test
    public void item_name_length_is_between_2_to_256_characters_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("AA", "XXXXXXXXXX", new BigDecimal(10));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String name = itemList.get(0).getName();
        boolean flag = addItemController.hasInvalidNameLength(name);

        assertFalse(flag);
    }

    @Test
    public void item_name_length_is_between_2_to_256_characters_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "XXXXXXXXXX", new BigDecimal(10));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String name = itemList.get(0).getName();
        boolean flag = addItemController.hasInvalidNameLength(name);

        assertFalse(flag);
    }

    @Test
    public void item_name_length_is_not_between_2_to_256_characters_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("A", "XXXXXXXXXX", new BigDecimal(10));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String name = itemList.get(0).getName();
        boolean flag = addItemController.hasInvalidNameLength(name);

        assertTrue(flag);
    }

    @Test
    public void item_name_length_is_not_between_2_to_256_characters_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "XXXXXXXXXX", new BigDecimal(10));
        itemList.add(item);

        AddItemController addItemController = new AddItemController(itemList, null);

        String name = itemList.get(0).getName();
        boolean flag = addItemController.hasInvalidNameLength(name);

        assertTrue(flag);
    }

    @Test
    public void item_can_be_added() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        Item item = new Item("Name", "XXXXXXXXXX", new BigDecimal(0));
        itemList.add(item);

        int expected = 1;
        int actual = itemList.size();

        assertEquals(expected, actual);
    }
}
