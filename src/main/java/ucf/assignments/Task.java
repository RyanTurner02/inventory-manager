/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Ryan Turner
 */

package ucf.assignments;

import com.google.gson.annotations.Expose;

public class Task {

    @Expose
    private String description;

    @Expose
    private String dueDate;

    @Expose
    private boolean isFinished;

    private String isFinishedString;

    public Task() {
        // initialize the instance variables strings to blank and false for the flag
        this("", "", false);
    }

    public Task(String description, String dueDate, boolean isFinished) {
        // initialize the description instance variable to the description variable
        this.description = description;

        // initialize the dueDate instance variable to the dueDate variable
        this.dueDate = dueDate;

        // initialize the isFinished instance variable to the isFinished variable
        this.isFinished = isFinished;

        // initialize the isFinished string depending on the value of the isFinished boolean flag
        setIsFinishedString(isFinished);
    }

    public String getDescription() {
        // return the description
        return description;
    }

    public void setDescription(String description) {
        // set the description instance variable to the description variable
        this.description = description;
    }

    public String getDueDate() {
        // return the dueDate
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        // set the dueDate instance variable to the dueDate variable
        this.dueDate = dueDate;
    }

    public boolean getIsFinished() {
        // return the isFinished flag
        return isFinished;
    }

    public void setIsFinished(boolean finished) {
        // set the isFinished instance flag to the finished variable
        isFinished = finished;
    }

    public String getIsFinishedString() {
        // return the isFinishedString
        return this.isFinishedString;
    }

    public void setIsFinishedString(boolean isFinished) {
        // initialize the isFinishedString to "Yes" if the isFinished flag is true
        // else initialize it to "No"
        this.isFinishedString = isFinished ? "Yes" : "No";
    }

    @Override
    public String toString() {
        // return a string that displays task object's values
        return "Task{" +
                "description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", isFinished=" + isFinished +
                '}';
    }
}
