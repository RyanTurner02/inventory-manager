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
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        Window stage = tableView.getScene().getWindow();

        // create a file chooser object and initialize the title window and extensions
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Items");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON File", "*.json"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"),
                new FileChooser.ExtensionFilter("HTML File", "*.html"));

        try {
            File file = fileChooser.showOpenDialog(stage);

            // exit the method if the user did not open a file
            if (file == null) {
                return itemList;
            }

            String filePath = file.getCanonicalPath();

            // get the items from a json file and store it to the item list
            if (filePath.endsWith(".json")) {
                itemList = getItemsFromJSONFile(file);
            }

            // get the items from the tsv txt file and store it to the item list
            else if (filePath.endsWith(".txt")) {
                itemList = getItemsFromTSVTextFile(file);
            }

            // get the items from the html file and store it to the item list
            else if (filePath.endsWith(".html")) {
                itemList = getItemsFromHTMLFile(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public ObservableList<Item> getItemsFromJSONFile(File file) {
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        try {
            // load the json file
            JsonElement fileElement = JsonParser.parseReader(new FileReader(file));

            // get the file elements as a json array
            JsonArray jsonArray = fileElement.getAsJsonArray();

            // iterate through the objects in the json file
            for (JsonElement jsonElement : jsonArray) {
                // get the item fields from the json array
                String description = jsonElement.getAsJsonObject().get("name").getAsString();
                String dueDate = jsonElement.getAsJsonObject().get("serialNumber").getAsString();
                BigDecimal value = jsonElement.getAsJsonObject().get("monetaryValue").getAsBigDecimal();

                // create a new item object and add it to the item list
                itemList.add(new Item(description, dueDate, value));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public ObservableList<Item> getItemsFromTSVTextFile(File file) {
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        try {
            Scanner reader = new Scanner(file);

            // skip the first line of the file if it is the header
            if (reader.nextLine().contains("Name\tSerial Number\tValue".toLowerCase())) {
                reader.nextLine();
            }

            // iterate through the file
            while (reader.hasNextLine()) {
                // store the current item values in an array and add the current item values to the item list
                String[] currentItemValues = reader.nextLine().split("\t");
                itemList.add(new Item(currentItemValues[0], currentItemValues[1], new BigDecimal(currentItemValues[2].replace("$", "")).setScale(2, RoundingMode.DOWN)));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public ObservableList<Item> getItemsFromHTMLFile(File file) {
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        try {
            Scanner reader = new Scanner(file);

            // create a string that stores the file text
            String fileString = "";

            // iterate through the file and add it to the string
            while (reader.hasNextLine()) {
                fileString += String.format("%s\n", reader.nextLine());
            }

            // parse the html into a document
            Document document = Jsoup.parse(fileString);

            // store the tables
            Elements tables = document.select("table");

            // iterate through the tables
            for (Element table : tables) {
                for (Element tableRow : table.select("tr")) {
                    // check if the size of the table data is equal to 3
                    if (tableRow.select("td").size() == 3) {
                        // get the fields from the html table
                        String name = tableRow.select("td").get(0).text();
                        String serialNumber = tableRow.select("td").get(1).text();
                        String monetaryValueString = tableRow.select("td").get(2).text();

                        // remove the '$' from the monetary value
                        monetaryValueString = monetaryValueString.replace("$", "");

                        // store the monetary value as a big decimal with 2 decimals
                        BigDecimal monetaryValue = new BigDecimal(monetaryValueString).setScale(2, RoundingMode.DOWN);

                        // add the name, serial number, and monetary value to the item list
                        itemList.add(new Item(name, serialNumber, monetaryValue));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return itemList;
    }

    public void exportItemsToFile(TableView<Item> tableView, ObservableList<Item> itemList) {
        Window stage = tableView.getScene().getWindow();

        // create a file chooser object and initialize the title window, file name, and extensions
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Items");
        fileChooser.setInitialFileName("Items");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JSON File", "*.json"),
                new FileChooser.ExtensionFilter("Text File", "*.txt"),
                new FileChooser.ExtensionFilter("HTML File", "*.html"));

        try {
            File file = fileChooser.showSaveDialog(stage);

            // exit the method if the user did not save a file
            if (file == null) {
                return;
            }

            FileWriter fileWriter = new FileWriter(file);
            String filePath = file.getCanonicalPath();

            // get the items and write it into a json file
            if (filePath.endsWith(".json")) {
                String jsonString = getJSONString(itemList);
                fileWriter.write(jsonString);
            }

            // get the items and write it into a tsv txt file
            else if (filePath.endsWith(".txt")) {
                String tsvString = getTSVString(itemList);
                fileWriter.write(tsvString);
            }

            // get the items and write it into an html file
            else if (filePath.endsWith(".html")) {
                String htmlString = getHTMLString(itemList);
                fileWriter.write(htmlString);
            }
            fileWriter.close();
        } catch (Exception e) {
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
        // create a header
        String tsvString = "Name\tSerial Number\tValue\n";

        // add the items
        for (Item item : itemList) {
            tsvString += (item.getName() + "\t" + item.getSerialNumber() + "\t$" + item.getMonetaryValue() + "\n");
        }
        return tsvString;
    }

    public String getHTMLString(ObservableList<Item> itemList) {
        // return the html skeleton with a table
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
        // create the html table with the first row being the header
        String htmlTable = "\t\t<table align=\"center\" border=\"1\">\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<th>Name</th>\n" +
                "\t\t\t\t<th>Serial Number</th>\n" +
                "\t\t\t\t<th>Value</th>\n" +
                "\t\t\t</tr>\n";

        // get the items in a table row
        for (Item item : itemList) {
            htmlTable += "\t\t\t<tr>\n";
            htmlTable += "\t\t\t\t<td>" + item.getName() + "</td>\n";
            htmlTable += "\t\t\t\t<td>" + item.getSerialNumber() + "</td>\n";
            htmlTable += "\t\t\t\t<td> $" + item.getMonetaryValue() + "</td>\n";
            htmlTable += "\t\t\t</tr>\n";
        }

        // close the table
        htmlTable += "\t\t</table>";

        return htmlTable;
    }
}
