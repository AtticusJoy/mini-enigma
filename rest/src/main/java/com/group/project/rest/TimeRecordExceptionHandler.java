package com.group.project.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TimeRecordExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TimeRecordErrorResponse> handleException(InvalidTimeException exc) {

        TimeRecordErrorResponse error = new TimeRecordErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


}
