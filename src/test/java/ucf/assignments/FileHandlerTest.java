/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class FileHandlerTest {
    @Test
    public void items_can_be_saved_into_json_file_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "XXXXXXXXXX", new BigDecimal(10.50)));

        FileHandler fileHandler = new FileHandler();

        String actual = fileHandler.getJSONString(itemList);
        String expected = "[\n" +
                "  {\n" +
                "    \"name\": \"Name\",\n" +
                "    \"serialNumber\": \"XXXXXXXXXX\",\n" +
                "    \"monetaryValue\": 10.5\n" +
                "  }\n" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    public void items_can_be_saved_into_json_file_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "abjk289Akf", new BigDecimal(10.50)));
        itemList.add(new Item("Another Name", "HelloWorld", new BigDecimal(1050.25)));

        FileHandler fileHandler = new FileHandler();

        String actual = fileHandler.getJSONString(itemList);
        String expected = "[\n" +
                "  {\n" +
                "    \"name\": \"Name\",\n" +
                "    \"serialNumber\": \"abjk289Akf\",\n" +
                "    \"monetaryValue\": 10.5\n" +
                "  },\n" +
                "  {\n" +
                "    \"name\": \"Another Name\",\n" +
                "    \"serialNumber\": \"HelloWorld\",\n" +
                "    \"monetaryValue\": 1050.25\n" +
                "  }\n" +
                "]";

        assertEquals(expected, actual);
    }

    @Test
    public void items_can_be_saved_into_tsv_text_file_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "XXXXXXXXXX", new BigDecimal(10.50)));

        FileHandler fileHandler = new FileHandler();

        String actual = fileHandler.getTSVString(itemList);
        String expected = "Name\tSerial Number\tValue\n" +
                "Name\tXXXXXXXXXX\t$10.5\n";

        assertEquals(expected, actual);
    }

    @Test
    public void items_can_be_saved_into_tsv_text_file_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "abjk289Akf", new BigDecimal(10.50)));
        itemList.add(new Item("Another Name", "HelloWorld", new BigDecimal(1050.25)));

        FileHandler fileHandler = new FileHandler();

        String actual = fileHandler.getTSVString(itemList);
        String expected = "Name\tSerial Number\tValue\n" +
                "Name\tabjk289Akf\t$10.5\n" +
                "Another Name\tHelloWorld\t$1050.25\n";

        assertEquals(expected, actual);
    }

    @Test
    public void items_can_be_saved_into_html_file_1() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "XXXXXXXXXX", new BigDecimal(10.50)));

        FileHandler fileHandler = new FileHandler();

        String actual = fileHandler.getHTMLString(itemList);
        String expected = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>Inventory Manager</title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<table align=\"center\" border=\"1\">\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<th>Name</th>\n" +
                "\t\t\t\t<th>Serial Number</th>\n" +
                "\t\t\t\t<th>Value</th>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>Name</td>\n" +
                "\t\t\t\t<td>XXXXXXXXXX</td>\n" +
                "\t\t\t\t<td> $10.5</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</table>\n" +
                "\t</body>\n" +
                "</html>\n";

        assertEquals(expected, actual);
    }

    @Test
    public void items_can_be_saved_into_html_file_2() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item("Name", "abjk289Akf", new BigDecimal(10.50)));
        itemList.add(new Item("Another Name", "HelloWorld", new BigDecimal(1050.25)));

        FileHandler fileHandler = new FileHandler();

        String actual = fileHandler.getHTMLString(itemList);
        String expected = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>Inventory Manager</title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<table align=\"center\" border=\"1\">\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<th>Name</th>\n" +
                "\t\t\t\t<th>Serial Number</th>\n" +
                "\t\t\t\t<th>Value</th>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>Name</td>\n" +
                "\t\t\t\t<td>abjk289Akf</td>\n" +
                "\t\t\t\t<td> $10.5</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td>Another Name</td>\n" +
                "\t\t\t\t<td>HelloWorld</td>\n" +
                "\t\t\t\t<td> $1050.25</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</table>\n" +
                "\t</body>\n" +
                "</html>\n";

        assertEquals(expected, actual);
    }

    @Test
    public void items_can_be_loaded_from_json_file() {
        ObservableList<Item> expectedItemList = FXCollections.observableArrayList();
        expectedItemList.add(new Item("Hello", "wsAJ389ouN", new BigDecimal(1894.39).setScale(2, RoundingMode.DOWN)));

        String filePath = "src/test/resources/ucf/assignments/Items.json";
        File file = new File(filePath);

        FileHandler fileHandler = new FileHandler();
        ObservableList<Item> actualItemList = fileHandler.getItemsFromHTMLFile(file);

        boolean flag = true;

        int size = actualItemList.size();
        for (int i = 0; i < size; i++) {
            if (!actualItemList.get(i).getName().equals(expectedItemList.get(i).getName())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getSerialNumber().equals(expectedItemList.get(i).getSerialNumber())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getMonetaryValue().equals(expectedItemList.get(i).getMonetaryValue())) {
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void items_can_loaded_from_tsv_text_file() {
        ObservableList<Item> expectedItemList = FXCollections.observableArrayList();
        expectedItemList.add(new Item("Hello", "wsAJ389ouN", new BigDecimal(1894.39).setScale(2, RoundingMode.DOWN)));

        String filePath = "src/test/resources/ucf/assignments/Items.txt";
        File file = new File(filePath);

        FileHandler fileHandler = new FileHandler();
        ObservableList<Item> actualItemList = fileHandler.getItemsFromHTMLFile(file);

        boolean flag = true;

        int size = actualItemList.size();
        for (int i = 0; i < size; i++) {
            if (!actualItemList.get(i).getName().equals(expectedItemList.get(i).getName())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getSerialNumber().equals(expectedItemList.get(i).getSerialNumber())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getMonetaryValue().equals(expectedItemList.get(i).getMonetaryValue())) {
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void items_can_be_loaded_from_html_file() {
        ObservableList<Item> expectedItemList = FXCollections.observableArrayList();
        expectedItemList.add(new Item("Hello", "wsAJ389ouN", new BigDecimal(1894.39).setScale(2, RoundingMode.DOWN)));

        String filePath = "src/test/resources/ucf/assignments/Items.html";
        File file = new File(filePath);

        FileHandler fileHandler = new FileHandler();
        ObservableList<Item> actualItemList = fileHandler.getItemsFromHTMLFile(file);

        boolean flag = true;

        int size = actualItemList.size();
        for (int i = 0; i < size; i++) {
            if (!actualItemList.get(i).getName().equals(expectedItemList.get(i).getName())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getSerialNumber().equals(expectedItemList.get(i).getSerialNumber())) {
                flag = false;
                break;
            }

            if (!actualItemList.get(i).getMonetaryValue().equals(expectedItemList.get(i).getMonetaryValue())) {
                flag = false;
                break;
            }
        }
        assertTrue(flag);
    }
}