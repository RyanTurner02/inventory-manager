/*
 * UCF COP3330 Summer 2021 Assignment 4 Solution
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

public class ModifyItemController implements Initializable {
    private static Task modifiedTask;
    private static Task oldTask;
    private boolean hasValidDescription;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private ComboBox<String> finishedComboBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // make the text wrap in the description text area
        this.descriptionTextArea.setWrapText(true);

        // keep the prompt text until the user starts typing
        this.descriptionTextArea.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");

        // Disable the text field of the due date picker
        this.dueDatePicker.getEditor().setDisable(true);
        this.dueDatePicker.getEditor().setOpacity(1);

        // initialize the finished combo box to display the options "Yes" and "No"
        this.finishedComboBox.setItems(FXCollections.observableArrayList("Yes", "No"));

        // initialize the flag to false
        this.hasValidDescription = false;

        // initialize the values of the menu options from current task object
        this.descriptionTextArea.setText(modifiedTask.getDescription());
        this.dueDatePicker.getEditor().setText(modifiedTask.getDueDate());

        // check if the modified task finished flag is true
        if (modifiedTask.getIsFinished()) {
            // set the combo box value to "Yes"
            this.finishedComboBox.setValue("Yes");
        } else {
            // else set the combo box value to "No"
            this.finishedComboBox.setValue("No");
        }
    }

    @FXML
    public void setTask(Task taskToModify) {
        // initialize the task in the controller to modify
        modifiedTask = taskToModify;

        // store the old task
        oldTask = taskToModify;
    }

    public Task getModifiedTask() {
        // return the modified task
        return modifiedTask;
    }

    public void initializeTaskDescription(String description) {
        // check if the length of the description is >= 1 and <= 256
        if (hasValidDescriptionLength(description)) {
            // set the value of the task description to the value from the description text area
            modifiedTask.setDescription(description);

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
        return this.hasValidDescription;
    }

    @FXML
    public void dueDatePickerPressed(ActionEvent event) {
        // get the value of the due date picker
        LocalDate localDate = dueDatePicker.getValue();

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
        modifiedTask.setDueDate(formattedDate);
    }

    @FXML
    public void finishedComboBoxPressed(ActionEvent event) {
        // check if the user entered the "Yes" option
        if (this.finishedComboBox.getValue().equals("Yes")) {
            // set the flag value of the task object to true
            modifiedTask.setIsFinished(true);

            // initialize the isFinished string
            modifiedTask.setIsFinishedString(modifiedTask.getIsFinished());
        } else {
            // else set the flag value of the task object to false
            modifiedTask.setIsFinished(false);

            // initialize the isFinished string
            modifiedTask.setIsFinishedString(modifiedTask.getIsFinished());
        }
    }

    @FXML
    public void modifyButtonPressed(ActionEvent event) {
        // initialize the task description
        initializeTaskDescription(this.descriptionTextArea.getText());

        // check if all the requirements are met for creating a task
        if (meetsRequirements()) {
            try {
                // load the Task.fxml file
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Task.fxml"));
                Parent root = loader.load();

                // create task controller object, modify the task from the list, and send the old task
                TaskController taskController = loader.getController();
                taskController.modifyTaskFromList(oldTask, modifiedTask);

                // switch scene
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                // print the stack trance when there is an error
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
