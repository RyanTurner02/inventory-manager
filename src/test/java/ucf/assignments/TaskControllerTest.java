/*
 * UCF COP3330 Summer 2021 Assignment 4 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskControllerTest {

    @Test
    public void list_can_have_100_items() {
        // create a task controller object
        TaskController taskController = new TaskController();

        // initialize the task list
        taskController.initializeTaskList();

        // create a loop that iterates 100 times
        for (int i = 0; i < 100; i++) {
            // initialize a new task to the current index
            taskController.addTaskToList(new Task());
        }

        // store the size of the task list
        int actual = taskController.getTaskList().size();

        // store the value of the expected size
        int expected = 100;

        // assert that the size of the list is equal to 100
        assertEquals(expected, actual);
    }

    @Test
    public void item_can_be_added_to_list() {
        // create a task controller object
        TaskController taskController = new TaskController();

        // initialize the task list
        taskController.initializeTaskList();

        // initialize a new task to the task list
        taskController.addTaskToList(new Task());

        // store the size of the task list
        int actual = taskController.getTaskList().size();

        // store the value of the expected size
        int expected = 1;

        // assert that the size of the list is equal to 1
        assertEquals(expected, actual);
    }

    @Test
    public void item_can_be_removed_from_list() {
        // create a task controller object
        TaskController taskController = new TaskController();

        // initialize the task list
        taskController.initializeTaskList();

        // add a new task to the task list
        taskController.addTaskToList(new Task());

        // remove the task from the task list
        taskController.removeTaskFromList(taskController.getTaskList().get(0));

        // store the size of the task list
        int actual = taskController.getTaskList().size();

        // store the value of the expected size
        int expected = 0;

        // assert that the size of the list is equal to 0
        assertEquals(expected, actual);
    }

    @Test
    public void item_can_have_due_date() {
        // create a task controller object
        TaskController taskController = new TaskController();

        // initialize the task list
        taskController.initializeTaskList();

        // add a new task to the task list with a given due date
        taskController.addTaskToList(new Task("", "2021-01-01", false));

        // store the actual due date in a string
        String actual = taskController.getTaskList().get(0).getDueDate();

        // create an expected string of the due date
        String expected = "2021-01-01";

        // assert that the task's due date matches the expected due date
        assertEquals(expected, actual);
    }

    @Test
    public void all_items_can_be_removed_from_list() {
        // create a tasks controller object
        TaskController taskController = new TaskController();

        // initialize the task list
        taskController.initializeTaskList();

        // create a for loop that will iterate 10 times
        for (int i = 0; i < 10; i++) {
            // add a task to the list
            taskController.addTaskToList(new Task());
        }

        // delete all tasks from the list
        taskController.deleteAllTasks();

        // store the size of the list
        int actual = taskController.getTaskList().size();

        // create an expected variable being 0
        int expected = 0;

        // assert that the size of the task list is 0
        assertEquals(expected, actual);
    }

    @Test
    public void incomplete_items_can_be_displayed() {
        // create a tasks controller object
        TaskController taskController = new TaskController();

        // initialize the task list
        taskController.initializeTaskList();

        // create a for loop that will iterate 100 times
        for (int i = 0; i < 100; i++) {
            // check if i is a multiple of 5
            if (i % 5 == 0) {
                // add a task to the list which is marked as incomplete
                taskController.addTaskToList(new Task("", "", false));
            } else {
                // else add a task to the list which is marked as complete
                taskController.addTaskToList(new Task("", "", true));
            }
        }

        // create an observable list object which contains the incomplete tasks
        ObservableList<Task> taskList = taskController.getIncompleteTasks();

        // store the expected size of the list
        int expected = 20;

        // store the size of the list
        int actual = taskList.size();

        // assert that the size of the task list is 20
        assertEquals(expected, actual);
    }

    @Test
    public void all_items_can_be_displayed() {
        // create a tasks controller object
        TaskController taskController = new TaskController();

        // initialize the task list
        taskController.initializeTaskList();

        // create a for loop that will iterate 100 times
        for (int i = 0; i < 100; i++) {
            // add the task to the task list
            taskController.addTaskToList(new Task());
        }

        // create an observable list object which contains all tasks
        ObservableList<Task> taskList = taskController.getAllTasks();

        // store the expected size of the list
        int expected = 100;

        // store the size of the list
        int actual = taskList.size();

        // assert that the size of the task list is 100
        assertEquals(expected, actual);
    }

    @Test
    public void completed_items_can_be_displayed() {
        // create a tasks controller object
        TaskController taskController = new TaskController();

        // initialize the task list
        taskController.initializeTaskList();

        // create a for loop that will iterate 100 times
        for (int i = 0; i < 100; i++) {
            // check if i is a multiple of 5
            if (i % 5 == 0) {
                // add a task to the list which is marked as complete
                taskController.addTaskToList(new Task("", "", true));
            } else {
                // else add a task to the list which is marked as incomplete
                taskController.addTaskToList(new Task("", "", false));
            }
        }

        // create an observable list object which contains the complete tasks
        ObservableList<Task> taskList = taskController.getCompletedTasks();

        // store the expected size of the list
        int expected = 20;

        // store the size of the list
        int actual = taskList.size();

        // assert that the size of the task list is 20
        assertEquals(expected, actual);
    }
}
