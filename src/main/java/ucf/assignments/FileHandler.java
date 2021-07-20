/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import com.google.gson.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class FileHandler {

    public ObservableList<Task> importFromJSON(TableView<Task> tableView) {
        // create a list of tasks that will be returned
        ObservableList<Task> taskList = FXCollections.observableArrayList();

        // create a window stage
        Window stage = tableView.getScene().getWindow();

        // create a file chooser object
        FileChooser fileChooser = new FileChooser();

        // set the title to "Save Task"
        fileChooser.setTitle("Load Task");

        // only allow for .json file to be opened
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));

        // show the load dialog and save it to the file object
        File file = fileChooser.showOpenDialog(stage);

        // check if the user did not open a file
        if (file == null) {
            // exit the method and return the task list
            return taskList;
        }

        // get the tasks from the json file and store it to the task list
        taskList = getTasksFromJSONFile(file);

        // return the task list
        return taskList;
    }

    public ObservableList<Task> getTasksFromJSONFile(File file) {
        // create a task list that will store the tasks from a json file
        ObservableList<Task> taskList = FXCollections.observableArrayList();

        try {
            // load the json file
            JsonElement fileElement = JsonParser.parseReader(new FileReader(file));

            // get the file elements as a json array
            JsonArray jsonArray = fileElement.getAsJsonArray();

            // iterate through the objects in the json file
            for (JsonElement jsonElement : jsonArray) {
                // get the description from the json array
                String description = jsonElement.getAsJsonObject().get("description").getAsString();

                // get the due date from the json array
                String dueDate = jsonElement.getAsJsonObject().get("dueDate").getAsString();

                // get the finished flag from the json array
                boolean isFinished = jsonElement.getAsJsonObject().get("isFinished").getAsBoolean();

                // create a new task object and add it to the task list
                taskList.add(new Task(description, dueDate, isFinished));
            }
        } catch (FileNotFoundException e) {
            // print the stack trace when we have an exception
            e.printStackTrace();
        }
        // return the task list
        return taskList;
    }

    public void exportToJSON(TableView<Task> tableView, ObservableList<Task> taskList) {
        // create a window stage
        Window stage = tableView.getScene().getWindow();

        // create a file chooser object
        FileChooser fileChooser = new FileChooser();

        // set the title to "Save Task"
        fileChooser.setTitle("Save Task");

        // set the initial file name to "tasks"
        fileChooser.setInitialFileName("tasks");

        // only allow for .json file to be saved
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON Files", "*.json"));

        try {
            // show the save dialog and save it to the file object
            File file = fileChooser.showSaveDialog(stage);

            // check if the user did not save the file
            if (file == null) {
                // exit the method
                return;
            }

            // create a file writer with the file location
            FileWriter fileWriter = new FileWriter(file);

            // create a gson object and make it format for a file
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

            // get the tasks and convert it into a json string
            String jsonString = gson.toJson(taskList);

            // write the json string into a file
            fileWriter.write(jsonString);

            // close the file writer
            fileWriter.close();
        } catch (Exception e) {
            // print the stack trace when we have an exception
            e.printStackTrace();
        }
    }

    public String getJSONString(ObservableList<Task> taskList) {
        // create a gson object and make it format for a file
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        // get the tasks and convert it into a json string
        String jsonString = gson.toJson(taskList);

        // return the json string
        return jsonString;
    }
}
