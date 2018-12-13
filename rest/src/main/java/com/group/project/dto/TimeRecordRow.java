/*******************************************************************************************
 * File: TimeRecordRow.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: Data transfer object composed of time record data
 *
 ******************************************************************************************/

package com.group.project.dto;

import java.util.Comparator;

public class TimeRecordRow implements Comparable<TimeRecordRow> {

    private int id;
    private String username;

    // date will be the time in date in the case where a time record spans multiple days
    private String date;

    private String timeIn;
    private String timeOut;
    private String hoursWorked;

    public TimeRecordRow () { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getHoursWorked() { return hoursWorked; }

    public void setHoursWorked(String hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    // Compares based on username, then date of clock in, then time in
    @Override
    public int compareTo(TimeRecordRow o) {
        return Comparator.comparing(TimeRecordRow::getUsername)
                .thenComparing(TimeRecordRow::getDate)
                .thenComparing(TimeRecordRow::getTimeIn)
                .compare(this, o);
    }
}
