package com.group.project.rest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeRecordExceptionHandlerTest {

    private TimeRecordExceptionHandler timeRecordExceptionHandler;

    @Before
    public void setUp() throws Exception {
        timeRecordExceptionHandler = new TimeRecordExceptionHandler();
    }

    @Test
    public void handleException() {
        ResponseEntity result = timeRecordExceptionHandler.handleException(new InvalidTimeException());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void handleException1() {
        ResponseEntity result = timeRecordExceptionHandler.handleException(new TimeResourceNotFound());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void handleGeneralExceptions() {
        ResponseEntity result = timeRecordExceptionHandler.handleGeneralExceptions(new Exception());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}