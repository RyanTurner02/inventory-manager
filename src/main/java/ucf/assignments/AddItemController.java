/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddItemController implements Initializable {
    private Task task;
    private boolean hasValidDescription;
    private boolean hasDueDateSelected;
    private boolean hasFinishedSelected;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private ComboBox<String> finishedComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // initialize the task object
        this.task = new Task();

        // make the text wrap in the description text area
        this.descriptionTextArea.setWrapText(true);

        // keep the prompt text until the user starts typing
        this.descriptionTextArea.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");

        // Disable the text field of the due date picker
        this.dueDatePicker.getEditor().setDisable(true);
        this.dueDatePicker.getEditor().setOpacity(1);

        // initialize the finished combo box to display the options "Yes" and "No"
        this.finishedComboBox.setItems(FXCollections.observableArrayList("Yes", "No"));

        // initialize the flags to false
        this.hasValidDescription = false;
        this.hasDueDateSelected = false;
        this.hasFinishedSelected = true;
    }

    public void initializeTaskDescription() {
        // set the value of the task description to the value from the description text area
        this.task.setDescription(this.descriptionTextArea.getText());

        // check if the length of the description is >= 1 and <= 256
        if (hasValidDescriptionLength(this.task.getDescription())) {
            // set the description flag to true
            this.hasValidDescription = true;
        } else {
            // else set the description flag to false
            this.hasValidDescription = false;
        }
    }

    public boolean hasValidDescriptionLength(String description) {
        // store the description's length in a variable
        int length = description.length();

        // return true or false depending on if the length of the description is between 1 - 256
        return length >= 1 && length <= 256;
    }

    public boolean meetsRequirements() {
        // return true or false depending on if the flags are all true
        return this.hasValidDescription && this.hasDueDateSelected && this.hasFinishedSelected;
    }

    @FXML
    public void dueDatePickerPressed(ActionEvent event) {
        // get the value of the due date picker
        LocalDate localDate = this.dueDatePicker.getValue();

        // create a date handler object
        DateHandler dateHandler = new DateHandler(localDate);

        // get the year
        int year = dateHandler.getLocalDate().getYear();

        // get the month
        int month = dateHandler.getLocalDate().getMonthValue();

        // get the day
        int day = dateHandler.getLocalDate().getDayOfMonth();

        // get the formatted date (yyyy-mm-dd)
        String formattedDate = dateHandler.getFormattedDate(year, month, day);

        // change the text of the editor to the formatted date
        this.dueDatePicker.getEditor().setText(formattedDate);

        // set the due date value of the task object to the formatted date
        this.task.setDueDate(formattedDate);

        // set the due date flag to true
        this.hasDueDateSelected = true;
    }

    @FXML
    public void finishedComboBoxPressed(ActionEvent event) {
        // check if the user entered the "Yes" option
        if (this.finishedComboBox.getValue().equals("Yes")) {
            // set the flag value of the task object to true
            this.task.setIsFinished(true);

            // initialize the isFinished string
            this.task.setIsFinishedString(this.task.getIsFinished());
        } else {
            // else set the flag value of the task object to false
            this.task.setIsFinished(false);

            // initialize the isFinished string
            this.task.setIsFinishedString(this.task.getIsFinished());
        }
    }

    @FXML
    public void createButtonPressed(ActionEvent event) {
        // initialize the task description
        initializeTaskDescription();

        // check if all the requirements are met for creating a task
        if (meetsRequirements()) {
            try {
                // load the Task.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Task.fxml"));
                Parent root = loader.load();

                // create task controller object and add the task to the list
                TaskController taskController = loader.getController();
                taskController.addTaskToList(this.task);

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
    public void cancelButtonPressed(ActionEvent event) {
        // create an object that handles scenes
        SceneHandler sceneHandler = new SceneHandler();

        // switch to the task scene
        sceneHandler.switchToScene(event, "Task.fxml");
    }
}
