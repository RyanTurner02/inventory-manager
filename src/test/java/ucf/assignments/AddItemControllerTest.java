/*
 * UCF COP3330 Summer 2021 Assignment 4 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddItemControllerTest {

    @Test
    public void item_can_have_a_description() {
        // create a task object
        Task task = new Task();

        // create an expected string for the expected description
        String expected = "Description text.";

        // initialize the description of the task object to the value of the expected description
        task.setDescription("Description text.");

        // store the task's description into a string
        String actual = task.getDescription();

        // assert that the description matches the expected string
        assertEquals(expected, actual);
    }

    @Test
    public void item_description_length_is_valid_1() {
        // create an add item controller object
        AddItemController addItemController = new AddItemController();

        // create a description string that is 1 character long
        String description = "a";

        // create a flag variable that checks if the description length is valid
        boolean flag = addItemController.hasValidDescriptionLength(description);

        // assert true that the description is valid
        assertTrue(flag);
    }

    @Test
    public void item_description_length_is_valid_2() {
        // create an add item controller object
        AddItemController addItemController = new AddItemController();

        // create a description string that is 256 characters long
        String description = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        // create a flag variable that checks if the description length is valid
        boolean flag = addItemController.hasValidDescriptionLength(description);

        // assert true that the description is valid
        assertTrue(flag);
    }

    @Test
    public void item_description_length_is_not_valid_1() {
        // create an add item controller object
        AddItemController addItemController = new AddItemController();

        // create a description string that is 256 characters long
        String description = "";

        // create a flag variable that checks if the description length is valid
        boolean flag = addItemController.hasValidDescriptionLength(description);

        // assert true that the description is valid
        assertFalse(flag);
    }

    @Test
    public void item_description_length_is_not_valid_2() {
        // create an add item controller object
        AddItemController addItemController = new AddItemController();

        // create a description string that is 257 characters long
        String description = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        // create a flag variable that checks if the description length is valid
        boolean flag = addItemController.hasValidDescriptionLength(description);

        // assert true that the description is valid
        assertFalse(flag);
    }

    @Test
    public void item_can_have_a_due_date() {
        // create a task object
        Task task = new Task();

        // create an expected string for the expected due date
        String expected = "2021-12-31";

        // initialize the due of the task object to the value of the expected due
        task.setDueDate("2021-12-31");

        // store the task's due date into a string
        String actual = task.getDueDate();

        // assert that the due date matches the expected due date
        assertEquals(expected, actual);
    }
}