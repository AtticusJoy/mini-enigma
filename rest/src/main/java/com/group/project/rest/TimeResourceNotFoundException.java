/*******************************************************************************************
 * File: TimeResourceNotFoundException.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: Used to represent if a time record property can't be found.
 * Simply extends RunTimeException, and uses the superclass constructors
 * to save the reason for the error
 *
 ******************************************************************************************/

package com.group.project.rest;

public class TimeResourceNotFoundException extends RuntimeException {

    public TimeResourceNotFoundException() {
    }

    public TimeResourceNotFoundException(String message) {
        super(message);
    }

    public TimeResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TimeResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public TimeResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
