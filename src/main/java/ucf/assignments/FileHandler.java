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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class FileHandler {

    public ObservableList<Item> importItemsFromFile(TableView<Item> tableView) {
        // create a list of items that will be returned
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        // create a window stage
        Window stage = tableView.getScene().getWindow();

        // create a file chooser object
        FileChooser fileChooser = new FileChooser();

        // set the title to "Load Items"
        fileChooser.setTitle("Load Items");

        // allow for .json, .txt, and .html files to be opened
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON File", "*.json"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"),
                new FileChooser.ExtensionFilter("HTML File", "*.html"));

        try {
            // show the load dialog and save it to the file object
            File file = fileChooser.showOpenDialog(stage);

            // check if the user did not open a file
            if (file == null) {
                // exit the method and return the item list
                return itemList;
            }

            // get the file path
            String filePath = file.getCanonicalPath();

            // if the file extension is .json
            if (filePath.endsWith(".json")) {
                // get the items from the json file and store it to the item list
                itemList = getItemsFromJSONFile(file);
            }

            // else if the file extension is .txt
            else if (filePath.endsWith(".txt")) {
                // get the items from the tsv file and store it to the item list
                itemList = getItemsFromTSVTextFile(file);
            }

            // else if the file extension is .html
            else if (filePath.endsWith(".html")) {
                // get the items from the html file and store it to the item list
                itemList = getItemsFromHTMLFile(file);
            }
        } catch (Exception e) {
            // print the stack trace when we have an exception
            e.printStackTrace();
        }

        // return the item list
        return itemList;
    }

    public ObservableList<Item> getItemsFromJSONFile(File file) {
        // create a item list that will store the items
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

                // get the item's monetary value from the json array
                BigDecimal value = jsonElement.getAsJsonObject().get("monetaryValue").getAsBigDecimal();

                // create a new item object and add it to the item list
                itemList.add(new Item(description, dueDate, value));
            }
        } catch (FileNotFoundException e) {
            // print the stack trace
            e.printStackTrace();
        }
        // return the item list
        return itemList;
    }

    public ObservableList<Item> getItemsFromTSVTextFile(File file) {
        // create an item list that will store the items
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        try {
            // create a scanner object
            Scanner reader = new Scanner(file);

            // check if the first line is the header
            if (reader.nextLine().contains("Name\tSerial Number\tValue".toLowerCase())) {
                // skip to the next line
                reader.nextLine();
            }

            // iterate through the file
            while (reader.hasNextLine()) {
                // store the current item values in an array
                String[] currentItemValues = reader.nextLine().split("\t");

                // add the current item values to the item list
                itemList.add(new Item(currentItemValues[0], currentItemValues[1], new BigDecimal(currentItemValues[2].replace("$", "")).setScale(2, RoundingMode.DOWN)));
            }
        } catch (FileNotFoundException e) {
            // print the stack trace
            e.printStackTrace();
        }

        // return the item list
        return itemList;
    }

    public ObservableList<Item> getItemsFromHTMLFile(File file) {
        // create an item list that will store the items
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        try {
            // create a scanner object
            Scanner reader = new Scanner(file);

            // create a string that stores the file text
            String fileString = "";

            // iterate through the file
            while (reader.hasNextLine()) {
                fileString += String.format("%s\n", reader.nextLine());
            }

            // parse the html into a document
            Document document = Jsoup.parse(fileString);

            // store the table
            Elements tables = document.select("table");

            // store the table rows
            Elements tableRows = tables.get(0).select("tr");

            // iterate through the table rows
            for (Element tableRow : tableRows) {
                if (tableRow.select("td").size() == 3) {
                    // store the name
                    String name = tableRow.select("td").get(0).text();

                    // store the serial number
                    String serialNumber = tableRow.select("td").get(1).text();

                    // store the monetary value as a string
                    String monetaryValueString = tableRow.select("td").get(2).text();

                    // remove the '$' from the string
                    monetaryValueString = monetaryValueString.replace("$", "");

                    // store the monetary value as a big decimal with 2 decimals
                    BigDecimal monetaryValue = new BigDecimal(monetaryValueString).setScale(2, RoundingMode.DOWN);

                    // add the name, serial number, and monetary value to the item list
                    itemList.add(new Item(name, serialNumber, monetaryValue));
                }
            }
        } catch (FileNotFoundException e) {
            // print the stack trace
            e.printStackTrace();
        }
        // return the item list
        return itemList;
    }

    public void exportItemsToFile(TableView<Item> tableView, ObservableList<Item> itemList) {
        // create a window stage
        Window stage = tableView.getScene().getWindow();

        // create a file chooser object
        FileChooser fileChooser = new FileChooser();

        // set the title to "Save Items"
        fileChooser.setTitle("Save Items");

        // set the initial file name to "Items"
        fileChooser.setInitialFileName("Items");

        // allow for .json, .txt, and .html files to be saved
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON File", "*.json"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"),
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
                // get the items and convert it into a json string
                String jsonString = getJSONString(itemList);

                // write the json string into a file
                fileWriter.write(jsonString);
            }

            // else if the file extension is .txt
            else if (filePath.endsWith(".txt")) {
                // get the items and convert it into a tsv string
                String tsvString = getTSVString(itemList);

                // write the tsv string into a file
                fileWriter.write(tsvString);
            }

            // else if the file extension is .html
            else if (filePath.endsWith(".html")) {
                // get the items and convert it into an html string
                String htmlString = getHTMLString(itemList);

                // write the html string into a file
                fileWriter.write(htmlString);
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

    public String getTSVString(ObservableList<Item> itemList) {
        String tsvString = "Name\tSerial Number\tValue\n";

        for (Item item : itemList) {
            tsvString += (item.getName() + "\t" + item.getSerialNumber() + "\t$" + item.getMonetaryValue() + "\n");
        }

        return tsvString;
    }

    public String getHTMLString(ObservableList<Item> itemList) {
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>Inventory Manager</title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                getHTMLTable(itemList) + "\n" +
                "\t</body>\n" +
                "</html>\n";
    }

    public String getHTMLTable(ObservableList<Item> itemList) {
        String htmlTable = "\t\t<table align=\"center\" border=\"1\">\n" +
                "\t\t<tr>\n" +
                "\t\t\t<th>Name</th>\n" +
                "\t\t\t<th>Serial Number</th>\n" +
                "\t\t\t<th>Value</th>\n" +
                "\t\t</tr>\n";

        for (Item item : itemList) {
            htmlTable += "\t\t<tr>\n";
            htmlTable += "\t\t\t<td>" + item.getName() + "</td>\n";
            htmlTable += "\t\t\t<td>" + item.getSerialNumber() + "</td>\n";
            htmlTable += "\t\t\t<td> $" + item.getMonetaryValue() + "</td>\n";
            htmlTable += "\t\t</tr>\n";
        }

        htmlTable += "\t\t</table>";

        return htmlTable;
    }
}
