package com.group.project.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TimeRecordExceptionHandler {

    // Handles invalid times; e.g. clock in time in future, clock out occurring before clock in
    @ExceptionHandler
    public ResponseEntity<TimeRecordErrorResponse> handleException(InvalidTimeException exc) {

        TimeRecordErrorResponse error = new TimeRecordErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // General time resource exceptions; e.g. invalid role, user already clocked out, etc.
    @ExceptionHandler
    public ResponseEntity<TimeRecordErrorResponse> handleException(TimeResourceNotFound exc) {

        TimeRecordErrorResponse error = new TimeRecordErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Catch all for unexpected exceptions
    @ExceptionHandler
    public ResponseEntity<TimeRecordErrorResponse> handleGeneralExceptions(Exception exc) {

        TimeRecordErrorResponse error = new TimeRecordErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exc.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
