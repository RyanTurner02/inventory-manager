/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ModifyItemControllerTest {

    @Test
    public void description_can_be_edited() {
        // create a modify item controller object
        ModifyItemController modifyItemController = new ModifyItemController();

        // create an initial description
        String description = "Hello World!";

        // initialize a task and add the description of it
        modifyItemController.setTask(new Task(description, "2021-12-31", false));

        // create an expected description
        String expected = "This description is modified!";

        // modify the description to match the expected string
        modifyItemController.initializeTaskDescription("This description is modified!");

        // store the modified description in a string
        String actual = modifyItemController.getModifiedTask().getDescription();

        // assert that the expected description is the same as the modified description
        assertEquals(expected, actual);
    }

    @Test
    public void due_date_can_be_edited() {
        // create a task object and initialize it with a due date that will be modified
        Task task = new Task("", "2020-01-01", false);

        // get the year
        int year = 2021;

        // get the month
        int month = 12;

        // get the day
        int day = 31;

        // get the value of the due date picker
        LocalDate localDate = LocalDate.of(year, month, day);

        // create a date handler object
        DateHandler dateHandler = new DateHandler(localDate);

        // get the due date
        String dueDate = dateHandler.getFormattedDate(year, month, day);

        // initialize the due date
        task.setDueDate(dueDate);

        // create an expected string for the due date
        String expected = "2021-12-31";

        // get the due date
        String actual = task.getDueDate();

        // assert that the expected due date matches the actual due date
        assertEquals(expected, actual);
    }

    @Test
    public void item_can_be_complete_or_incomplete_1() {
        // create a modify item controller object
        ModifyItemController modifyItemController = new ModifyItemController();

        // initialize the task with it's finished variable being false
        modifyItemController.setTask(new Task("", "", false));

        // change the task's complete status to true
        modifyItemController.getModifiedTask().setIsFinished(true);

        // get the boolean value
        boolean actual = modifyItemController.getModifiedTask().getIsFinished();

        // assertTrue that the item is marked as complete
        assertTrue(actual);
    }

    @Test
    public void item_can_be_complete_or_incomplete_2() {
        // create a modify item controller object
        ModifyItemController modifyItemController = new ModifyItemController();

        // initialize the task with it's finished variable being true
        modifyItemController.setTask(new Task("", "", true));

        // change the task's complete status to true
        modifyItemController.getModifiedTask().setIsFinished(false);

        // get the boolean value
        boolean actual = modifyItemController.getModifiedTask().getIsFinished();

        // assertFalse that the item is marked as incomplete
        assertFalse(actual);
    }
}