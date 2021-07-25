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
        this("", "", BigDecimal.ZERO);
    }

    public Item(String name, String serialNumber, BigDecimal monetaryValue) {
        this.name = name;
        this.serialNumber = serialNumber;
        this.monetaryValue = monetaryValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public BigDecimal getMonetaryValue() {
        return monetaryValue;
    }

    public void setMonetaryValue(BigDecimal monetaryValue) {
        this.monetaryValue = monetaryValue;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", monetaryValue=" + monetaryValue +
                '}';
    }
}
