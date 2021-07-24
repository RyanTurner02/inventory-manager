/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class Item {

    @Expose
    private String name;

    @Expose
    private String serialNumber;

    @Expose
    private BigDecimal monetaryValue;

    public Item() {
        // initialize the instance variables strings to blank and 0 for the big decimal value
        this("", "", BigDecimal.ZERO);
    }

    public Item(String name, String serialNumber, BigDecimal monetaryValue) {
        // initialize the description instance variable to the description variable
        this.name = name;

        // initialize the dueDate instance variable to the dueDate variable
        this.serialNumber = serialNumber;

        // initialize the value instance variable to the value variable
        this.monetaryValue = monetaryValue;
    }

    public String getName() {
        // return the description
        return name;
    }

    public void setName(String name) {
        // set the description instance variable to the description variable
        this.name = name;
    }

    public String getSerialNumber() {
        // return the dueDate
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        // set the dueDate instance variable to the dueDate variable
        this.serialNumber = serialNumber;
    }

    public BigDecimal getMonetaryValue() {
        // return the item's value
        return monetaryValue;
    }

    public void setMonetaryValue(BigDecimal monetaryValue) {
        // set the value instance variable to the value variable
        this.monetaryValue = monetaryValue;
    }
}
