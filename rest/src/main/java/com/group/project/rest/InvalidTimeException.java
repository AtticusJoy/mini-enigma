/*******************************************************************************************
 * File: InvalidTimeException.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: Used to represent invalid time run time errors. Simply extends RunTimeException,
 * and uses the superclass constructors to save the reason for the error
 *
 ******************************************************************************************/

package com.group.project.rest;

public class InvalidTimeException extends RuntimeException {

    public InvalidTimeException() {
    }

    public InvalidTimeException(String message) {
        super(message);
    }

    public InvalidTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTimeException(Throwable cause) {
        super(cause);
    }

    public InvalidTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
