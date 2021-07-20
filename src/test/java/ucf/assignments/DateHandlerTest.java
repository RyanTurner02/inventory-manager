/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateHandlerTest {
    @Test
    public void due_date_is_in_valid_format_1() {
        // create year variable
        int year = 2021;

        // create month variable
        int month = 10;

        // create day variable
        int day = 10;

        // create and initialize local date
        LocalDate localDate = LocalDate.of(year, month, day);

        // create a date handler object
        DateHandler dateHandler = new DateHandler(localDate);

        // get and store the formatted date into a string
        String actual = dateHandler.getFormattedDate(year, month, day);

        // create an expected string
        String expected = "2021-10-10";

        // assert that the expected string matches the formatted string
        assertEquals(expected, actual);
    }

    @Test
    public void due_date_is_in_valid_format_2() {
        // create year variable
        int year = 2021;

        // create month variable
        int month = 1;

        // create day variable
        int day = 1;

        // create and initialize local date
        LocalDate localDate = LocalDate.of(year, month, day);

        // create a date handler object
        DateHandler dateHandler = new DateHandler(localDate);

        // get and store the formatted date into a string
        String actual = dateHandler.getFormattedDate(year, month, day);

        // create an expected string
        String expected = "2021-01-01";

        // assert that the expected string matches the formatted string
        assertEquals(expected, actual);
    }

    @Test
    public void due_date_is_in_valid_format_3() {
        // create year variable
        int year = 2021;

        // create month variable
        int month = 1;

        // create day variable
        int day = 10;

        // create and initialize local date
        LocalDate localDate = LocalDate.of(year, month, day);

        // create a date handler object
        DateHandler dateHandler = new DateHandler(localDate);

        // get and store the formatted date into a string
        String actual = dateHandler.getFormattedDate(year, month, day);

        // create an expected string
        String expected = "2021-01-10";

        // assert that the expected string matches the formatted string
        assertEquals(expected, actual);
    }

    @Test
    public void due_date_is_in_valid_format_4() {
        // create year variable
        int year = 2021;

        // create month variable
        int month = 10;

        // create day variable
        int day = 1;

        // create and initialize local date
        LocalDate localDate = LocalDate.of(year, month, day);

        // create a date handler object
        DateHandler dateHandler = new DateHandler(localDate);

        // get and store the formatted date into a string
        String actual = dateHandler.getFormattedDate(year, month, day);

        // create an expected string
        String expected = "2021-10-01";

        // assert that the expected string matches the formatted string
        assertEquals(expected, actual);
    }
}