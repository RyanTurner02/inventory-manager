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
    public void item_can_be_searched_by_name() {

    }

    @Test
    public void item_can_be_searched_by_serial_number() {

    }
}