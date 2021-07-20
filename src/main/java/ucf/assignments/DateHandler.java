/*
 * UCF COP3330 Summer 2021 Assignment 4 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import java.time.LocalDate;

public class DateHandler {
    private LocalDate localDate;

    public DateHandler() {

    }

    public DateHandler(LocalDate localDate) {
        // initialize the local data instance variable to the local date argument
        this.localDate = localDate;
    }

    public LocalDate getLocalDate() {
        // return the local date
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        // set the local data instance variable to the local date argument
        this.localDate = localDate;
    }

    public String getFormattedDate(int year, int month, int day) {
        // return a formatted string with the year, month, day and hyphens
        return String.format("%s-%s-%s", year, getFormattedValue(month), getFormattedValue(day));
    }

    public String getFormattedValue(int value) {
        // check if the value is less than 10
        if (value < 10) {
            // return a '0' and the month
            return String.format("0%d", value);
        }
        // return the month
        return String.format("%d", value);
    }
}
