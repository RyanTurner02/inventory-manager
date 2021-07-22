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
import java.math.BigDecimal;

public class FileHandler {

    public ObservableList<Item> importItemsFromFile(TableView<Item> tableView) {
        // create a list of tasks that will be returned
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        // create a window stage
        Window stage = tableView.getScene().getWindow();

        // create a file chooser object
        FileChooser fileChooser = new FileChooser();

        // set the title to "Save Task"
        fileChooser.setTitle("Load Task");

        // allow for .json, .tsv, and .html files to be opened
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON File", "*.json"),
                new FileChooser.ExtensionFilter("TSV File", "*.tsv"),
                new FileChooser.ExtensionFilter("HTML File", "*.html"));

        try {
            // show the load dialog and save it to the file object
            File file = fileChooser.showOpenDialog(stage);

            // check if the user did not open a file
            if (file == null) {
                // exit the method and return the task list
                return itemList;
            }

            // get the file path
            String filePath = file.getCanonicalPath();

            // if the file extension is .json
            if (filePath.endsWith(".json")) {
                // get the tasks from the json file and store it to the task list
                itemList = getTasksFromJSONFile(file);
            }

            // else if the file extension is .tsv
            else if (filePath.endsWith(".tsv")) {
                System.out.println("TSV");
            }

            // else if the file extension is .html
            else if (filePath.endsWith(".html")) {
                System.out.println("HTML");
            }
        } catch (Exception e) {
            // print the stack trace when we have an exception
            e.printStackTrace();
        }

        // return the task list
        return itemList;
    }

    public ObservableList<Item> getTasksFromJSONFile(File file) {
        // create a task list that will store the tasks from a json file
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        try {
            // load the json file
            JsonElement fileElement = JsonParser.parseReader(new FileReader(file));

            // get the file elements as a json array
            JsonArray jsonArray = fileElement.getAsJsonArray();

            // iterate through the objects in the json file
            for (JsonElement jsonElement : jsonArray) {
                // get the item's name from the json array
                String description = jsonElement.getAsJsonObject().get("name").getAsString();

                // get the item's serial number from the json array
                String dueDate = jsonElement.getAsJsonObject().get("serialNumber").getAsString();

                // get the item's value from the json array
                BigDecimal value = jsonElement.getAsJsonObject().get("value").getAsBigDecimal();

                // create a new task object and add it to the task list
                itemList.add(new Item(description, dueDate, value));
            }
        } catch (FileNotFoundException e) {
            // print the stack trace when we have an exception
            e.printStackTrace();
        }
        // return the task list
        return itemList;
    }

    public void exportItemListToFile(TableView<Item> tableView, ObservableList<Item> itemList) {
        // create a window stage
        Window stage = tableView.getScene().getWindow();

        // create a file chooser object
        FileChooser fileChooser = new FileChooser();

        // set the title to "Save Items"
        fileChooser.setTitle("Save Items");

        // set the initial file name to "Items"
        fileChooser.setInitialFileName("Items");

        // allow for .json, .tsv, and .html files to be saved
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON File", "*.json"),
                new FileChooser.ExtensionFilter("TSV File", "*.tsv"),
                new FileChooser.ExtensionFilter("HTML File", "*.html"));

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

            // get the file path
            String filePath = file.getCanonicalPath();

            // if the file extension is .json
            if (filePath.endsWith(".json")) {
                // get the tasks and convert it into a json string
                String jsonString = getJSONString(itemList);

                // write the json string into a file
                fileWriter.write(jsonString);
            }

            // else if the file extension is .tsv
            else if (filePath.endsWith(".tsv")) {
                System.out.println("TSV");
            }

            // else if the file extension is .html
            else if (filePath.endsWith(".html")) {
                System.out.println("HTML");
            }

            // close the file writer
            fileWriter.close();
        } catch (Exception e) {
            // print the stack trace when we have an exception
            e.printStackTrace();
        }
    }

    public String getJSONString(ObservableList<Item> itemList) {
        // create a gson object and make it format for a file
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

        // return the items as a json string
        return gson.toJson(itemList);
    }

    public String getHTML() {
        String ret = "";

        return ret;
    }

    public String getHTMLTable() {
        String table = "";

        return table;
    }
}
