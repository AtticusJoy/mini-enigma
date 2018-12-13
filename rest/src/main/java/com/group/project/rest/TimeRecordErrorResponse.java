/*******************************************************************************************
 * File: TimeRecordErrorResponse.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: This class is used to return a status code and error message to the requesting
 * client
 *
 ******************************************************************************************/

package com.group.project.rest;

public class TimeRecordErrorResponse {

    private int status;
    private String message;

    public TimeRecordErrorResponse() {
    }

    TimeRecordErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    TimeRecordErrorResponse(int status, StackTraceElement[] stackTraceElements) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
