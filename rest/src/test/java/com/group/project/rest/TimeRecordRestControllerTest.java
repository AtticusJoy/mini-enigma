package com.group.project.rest;

import com.group.project.dto.TimeRecordRow;
import com.group.project.dto.User;
import com.group.project.service.TimeRecordService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TimeRecordRestControllerTest {

    private TimeRecordRestController timeRecordRestController;
    private TimeRecordService timeRecordService;

    @Before
    public void setUp() throws Exception {
        timeRecordRestController = new TimeRecordRestController();
        timeRecordService = mock(TimeRecordService.class);

        timeRecordRestController.setTimeRecordService(timeRecordService);
    }

    @Test
    public void listTimeRecordsManager() {
        User user = new User();
        user.setRole("Manager");
        user.setUsername("Jane");
        TimeRecordRow timeRecordRow = new TimeRecordRow();
        timeRecordRow.setId(1);
        timeRecordRow.setUsername("Jane");
        List<TimeRecordRow> timeRecordRowList = new ArrayList<>();
        timeRecordRowList.add(timeRecordRow);
        when(timeRecordService.getTimeRecordsManager(anyString())).thenReturn(timeRecordRowList);
        List<TimeRecordRow> result = timeRecordRestController.listTimeRecords(user);
        assertThat(result.get(0).getId()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo("Jane");
    }

    @Test
    public void listTimeRecordsEmployee() {
        User user = new User();
        user.setRole("Employee");
        user.setUsername("Jack");
        TimeRecordRow timeRecordRow = new TimeRecordRow();
        timeRecordRow.setId(1);
        timeRecordRow.setUsername("Jack");
        List<TimeRecordRow> timeRecordRowList = new ArrayList<>();
        timeRecordRowList.add(timeRecordRow);
        when(timeRecordService.getTimeRecordsEmployee(anyString())).thenReturn(timeRecordRowList);
        List<TimeRecordRow> result = timeRecordRestController.listTimeRecords(user);
        assertThat(result.get(0).getId()).isEqualTo(1);
        assertThat(result.get(0).getUsername()).isEqualTo("Jack");
    }

    @Test(expected = TimeResourceNotFoundException.class)
    public void listTimeRecordsThrowsException() {
        User user = new User();
        user.setRole("Other");
        user.setUsername("Jack");
        timeRecordRestController.listTimeRecords(user);
    }

    @Test
    public void addClockIn() {
        ResponseEntity result = timeRecordRestController.addClockIn("Jane");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void addClockOut() {
        ResponseEntity result = timeRecordRestController.addClockOut("Jane");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}