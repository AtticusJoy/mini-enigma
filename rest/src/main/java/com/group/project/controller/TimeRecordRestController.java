package com.group.project.controller;

import com.group.project.dto.TimeRecordRow;
import com.group.project.dto.User;
import com.group.project.entity.TimeRecord;
import com.group.project.service.TimeRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeRecordRestController {

    private TimeRecordService timeRecordService;

    public TimeRecordRestController(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

    @PostMapping(path = "/getData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // Add Consumes etc. that Peter expects
    public ResponseEntity<List> listTimeRecords(User user) {

        List<TimeRecord> timeRecords = timeRecordService.getTimeRecords(user);

        // convert List<TimeRecord> into List<TimeRecordRow> then return

        return ResponseEntity.ok(timeRecords);
    }

    @PostMapping (path = "/clockIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addClockIn(String username) {

        String status = timeRecordService.saveClockIn(username);

        // To-do: Either check status using conditional, or always return success if no exception. If exception return that
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PostMapping (path = "clockOut", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addClockOut(String username) {

        String status = timeRecordService.saveClockOut(username);

        // To-do: Either check status using conditional, or always return success if no exception. If exception return that
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
}
