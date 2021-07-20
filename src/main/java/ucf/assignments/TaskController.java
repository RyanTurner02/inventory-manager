/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskController implements Initializable {
    private static ObservableList<Task> taskList;
    private boolean viewingComplete;
    private boolean viewingIncomplete;

    @FXML
    private TableView<Task> tableView;

    @FXML
    private TableColumn<Task, String> descriptionColumn;

    @FXML
    private TableColumn<Task, String> dueDateColumn;

    @FXML
    private TableColumn<Task, String> finishedColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set the description column
        this.descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // set the due date column
        this.dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        // set the finished column to either "Yes" or "No"
        this.finishedColumn.setCellValueFactory(c -> new SimpleStringProperty(String.valueOf(c.getValue().getIsFinishedString())));

        // initialize the viewing complete and incomplete flags to true
        this.viewingComplete = true;
        this.viewingIncomplete = true;

        // initialize the list of tasks if it is null
        if (taskList == null) {
            initializeTaskList();
        }

        // display the tasks
        this.tableView.setItems(taskList);
    }

    public void initializeTaskList() {
        // initialize the task list
        taskList = FXCollections.observableArrayList();
    }

    @FXML
    public void addTaskToList(Task task) {
        // add the new task to the task list
        taskList.add(task);
    }

    public void removeTaskFromList(Task task) {
        // remove the task from the list
        taskList.remove(task);
    }

    public ObservableList<Task> getTaskList() {
        // return the task list
        return taskList;
    }

    public void modifyTaskFromList(Task oldTask, Task modifiedTask) {
        // iterate through the tasks
        int i = 0;
        for (Task task : taskList) {
            // check if the current task matches the task that is going to be removed
            if (task.getDescription().equals(oldTask.getDescription()) &&
                    task.getDueDate().equals(oldTask.getDueDate()) &&
                    task.getIsFinished() == oldTask.getIsFinished()) {

                // set the current task to be the modified task
                taskList.set(i, modifiedTask);

                // exit the loop
                break;
            }
            // increment the index counter
            i++;
        }
    }

    @FXML
    public void importPressed(ActionEvent event) {
        // create an object that handles files
        FileHandler fileHandler = new FileHandler();

        // add all the contents from the json file to the task list
        taskList.addAll(fileHandler.importFromJSON(this.tableView));

        // update the table depending on what the user is currently viewing
        updateTable(event);
    }

    @FXML
    public void exportPressed(ActionEvent event) {
        // create an object that handles files
        FileHandler fileHandler = new FileHandler();

        // export all the contents from the list to a json file
        fileHandler.exportToJSON(this.tableView, taskList);
    }

    @FXML
    public void displayAllPressed(ActionEvent event) {
        // set the complete flag to true
        this.viewingComplete = true;

        // set the incomplete flag to true
        this.viewingIncomplete = true;

        // display all tasks
        this.tableView.setItems(getAllTasks());
    }

    public ObservableList<Task> getAllTasks() {
        // return the task list
        return taskList;
    }

    @FXML
    public void displayIncompletePressed(ActionEvent event) {
        // set the complete flag to false
        this.viewingComplete = false;

        // set the incomplete flag to true
        this.viewingIncomplete = true;

        // create a list that stores incomplete tasks and get them
        ObservableList<Task> incompleteTasks = getIncompleteTasks();

        // display the incomplete tasks
        this.tableView.setItems(incompleteTasks);
    }

    public ObservableList<Task> getIncompleteTasks() {
        // create a list that stores incomplete tasks
        ObservableList<Task> incompleteTasks = FXCollections.observableArrayList();

        // iterate through the tasks
        for (Task task : taskList) {
            // check if the task is not finished
            if (!task.getIsFinished()) {
                // add it the incomplete task list
                incompleteTasks.add(task);
            }
        }
        // return the list of incomplete tasks
        return incompleteTasks;
    }

    @FXML
    public void displayCompletedPressed(ActionEvent event) {
        // set the complete flag to true
        this.viewingComplete = true;

        // set the incomplete flag to false
        this.viewingIncomplete = false;

        // create a list that stores complete tasks and get them
        ObservableList<Task> completedTasks = getCompletedTasks();

        // display the completed tasks
        this.tableView.setItems(completedTasks);
    }

    public ObservableList<Task> getCompletedTasks() {
        // create a list that stores complete tasks
        ObservableList<Task> completedTasks = FXCollections.observableArrayList();

        // iterate through the tasks
        for (Task task : taskList) {
            // check if the task is finished
            if (task.getIsFinished()) {
                // add it to the completed tasks list
                completedTasks.add(task);
            }
        }
        // return the list of completed tasks
        return completedTasks;
    }

    public void updateTable(ActionEvent event) {
        // check if the user is viewing both the complete and incomplete items
        if (this.viewingComplete && this.viewingIncomplete) {
            // display all items
            displayAllPressed(event);
        }

        // else check if the user is viewing the complete items
        else if (this.viewingComplete) {
            // display the complete items
            displayCompletedPressed(event);
        }

        // else the user is viewing the incomplete items
        else {
            // display the incomplete items
            displayIncompletePressed(event);
        }
    }

    @FXML
    public void resetMenuItemPressed(ActionEvent event) {
        // clear the table
        this.tableView.setItems(null);

        // display all the items
        displayAllPressed(event);
    }

    @FXML
    public void addItemButtonPressed(ActionEvent event) {
        // switch to the add item scene
        switchToAddItemScene(event);
    }

    @FXML
    public void modifyItemButtonPressed(ActionEvent event) {
        // get the task to modify
        Task taskToModify = this.tableView.getSelectionModel().selectedItemProperty().get();

        // check if the user selected a task
        if (taskToModify != null) {
            try {
                // create modify item controller object and send the task to modify
                ModifyItemController modifyItemController = new ModifyItemController();
                modifyItemController.setTask(taskToModify);

                // load the fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyItem.fxml"));
                Parent root = loader.load();

                // switch scene
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                // print the stack trace
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void deleteItemButtonPressed(ActionEvent event) {
        // get the task to remove
        Task taskToRemove = this.tableView.getSelectionModel().selectedItemProperty().get();

        // remove the task
        taskList.remove(taskToRemove);

        // update the table depending on what the user is currently viewing
        updateTable(event);
    }

    @FXML
    public void deleteAllItemsButtonPressed(ActionEvent event) {
        // remove all of the elements from the task list
        deleteAllTasks();

        // display all contents of the table
        displayAllPressed(event);
    }

    public void deleteAllTasks() {
        // remove all of the tasks from the task list
        taskList.clear();
    }

    public void switchToAddItemScene(ActionEvent event) {
        // create an object that handles scenes
        SceneHandler sceneHandler = new SceneHandler();

        // switch to the add item scene
        sceneHandler.switchToScene(event, "AddItem.fxml");
    }
}
