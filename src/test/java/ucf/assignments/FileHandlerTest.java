/*
 * UCF COP3330 Summer 2021 Assignment 4 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    @Test
    public void tasks_can_be_imported() {
        // create a list that will be the actual values of the task list
        ObservableList<Task> actualTaskList = FXCollections.observableArrayList();

        // add the first task to the actual task list
        actualTaskList.add(new Task("Halloween", "2021-10-31", false));

        // add the second task to the actual task list
        actualTaskList.add(new Task("New Years Eve", "2021-12-31", false));

        // add the third task to the actual task list
        actualTaskList.add(new Task("first finished task", "2021-07-09", true));

        // create a file handler object
        FileHandler fileHandler = new FileHandler();

        // create a file object with a testing json file
        File file = new File("src/test/resources/ucf/assignments/test.json");

        // create a task list and get the tasks from the json file
        ObservableList<Task> expectedTaskList = fileHandler.getTasksFromJSONFile(file);

        // store the length of the task list
        int length = expectedTaskList.size();

        // create a flag variable that will check if the
        boolean flag = true;

        // create a for loop that will iterate through the imported tasks
        for (int i = 0; i < length; i++) {
            // check if the expected description does not match the imported description
            if (!expectedTaskList.get(i).getDescription().equals(actualTaskList.get(i).getDescription())) {
                // set the flag to false
                flag = false;

                // exit the loop
                break;
            }

            // check if the expected due date does not match the imported due date
            if (!expectedTaskList.get(i).getDueDate().equals(actualTaskList.get(i).getDueDate())) {
                // set the flag to false
                flag = false;

                // exit the loop
                break;
            }

            // check if the expected finished flag does not match the imported finished flag
            if (expectedTaskList.get(i).getIsFinished() != actualTaskList.get(i).getIsFinished()) {
                // set the flag to false
                flag = false;

                // exit the loop
                break;
            }
        }

        // assertTrue that the tasks match the expected tasks
        assertTrue(flag);
    }

    @Test
    public void tasks_can_be_exported() {
        // create a file handler object
        FileHandler fileHandler = new FileHandler();

        // create a task list and initialize it
        ObservableList<Task> taskList = FXCollections.observableArrayList();

        // add a task to the task list
        taskList.add(new Task("Task task task", "2021-12-01", true));

        // add a task to the task list
        taskList.add(new Task("Product", "2021-06-05", true));

        // add a task to the task list
        taskList.add(new Task("Thing", "2021-12-31", false));

        // create an expected string that has the tasks correctly formatted into a JSON file
        String expected = "[\n" +
                "  {\n" +
                "    \"description\": \"Task task task\",\n" +
                "    \"dueDate\": \"2021-12-01\",\n" +
                "    \"isFinished\": true\n" +
                "  },\n" +
                "  {\n" +
                "    \"description\": \"Product\",\n" +
                "    \"dueDate\": \"2021-06-05\",\n" +
                "    \"isFinished\": true\n" +
                "  },\n" +
                "  {\n" +
                "    \"description\": \"Thing\",\n" +
                "    \"dueDate\": \"2021-12-31\",\n" +
                "    \"isFinished\": false\n" +
                "  }\n" +
                "]";

        // get the json string
        String actual = fileHandler.getJSONString(taskList);

        // assert that the task list can be correctly formatted into a JSON file
        assertEquals(expected, actual);
    }
}