package com.group.project.rest;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeRecordErrorResponseTest {

    private TimeRecordErrorResponse timeRecordErrorResponse;

    @Before
    public void setUp() throws Exception {
        timeRecordErrorResponse = new TimeRecordErrorResponse();
    }

    @Test
    public void getStatus() {
        timeRecordErrorResponse.setStatus(1);
        assertThat(timeRecordErrorResponse.getStatus()).isEqualTo(1);
    }

    @Test
    public void getMessage() {
        timeRecordErrorResponse.setMessage("message");
        assertThat(timeRecordErrorResponse.getMessage()).isEqualTo("message");
    }

    @Test
    public void testConstructor(){
        TimeRecordErrorResponse timeRecordErrorResponse = new TimeRecordErrorResponse(1, "message");
        assertThat(timeRecordErrorResponse.getStatus()).isEqualTo(1);
        assertThat(timeRecordErrorResponse.getMessage()).isEqualTo("message");
    }
}