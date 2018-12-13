/*******************************************************************************************
 * File: TimeRecordExceptionHandler.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: Adds an additional layer between any controllers and the client that can be used
 * to handle exceptions. Both specific exception methods are defined as well as a general
 * catch all that will handle unexpected errors and return a HttpStatus and error message
 * through the TimeRecordErrorResponse class
 *
 ******************************************************************************************/

package com.group.project.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TimeRecordExceptionHandler {

    // Handles invalid times; e.g. clock in time in future, clock out occurring before clock in, etc.
    @ExceptionHandler
    public ResponseEntity<TimeRecordErrorResponse> handleException(InvalidTimeException exc) {

        TimeRecordErrorResponse error = new TimeRecordErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // General time resource exceptions; e.g. invalid role, user already clocked out, etc.
    @ExceptionHandler
    public ResponseEntity<TimeRecordErrorResponse> handleException(TimeResourceNotFoundException exc) {

        TimeRecordErrorResponse error = new TimeRecordErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Catch all for unexpected exceptions, e.g. database is offline
    @ExceptionHandler
    public ResponseEntity<TimeRecordErrorResponse> handleGeneralExceptions(Exception exc) {

        TimeRecordErrorResponse error = new TimeRecordErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exc.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
