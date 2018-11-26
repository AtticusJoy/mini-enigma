package com.group.project.controller;

import com.group.project.dto.TimeRecordRow;
import com.group.project.dto.User;
import com.group.project.entity.TimeRecord;
import com.group.project.service.TimeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TimeRecordRestController {

    private static final Logger logger = LoggerFactory.getLogger(TimeRecordRestController.class);

    private TimeRecordService timeRecordService;

    public TimeRecordRestController(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

    @PostMapping(path = "/getData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // Add Consumes etc. that Peter expects
    public ResponseEntity<List> listTimeRecords(@RequestBody User user) {
//        List<TimeRecord> timeRecords = timeRecordService.getTimeRecords(user);
        // convert List<TimeRecord> into List<TimeRecordRow> then return

        List<TimeRecordRow> results = new ArrayList<>();

        TimeRecordRow timeRecordRow1 = new TimeRecordRow();
        timeRecordRow1.setId(1);
        timeRecordRow1.setUsername("Peter");
        timeRecordRow1.setDate("11/25/2018");
        timeRecordRow1.setTimeIn("9AM");
        timeRecordRow1.setTimeOut("5PM");
        timeRecordRow1.setHoursWorked(8);


        TimeRecordRow timeRecordRow2 = new TimeRecordRow();
        timeRecordRow2.setId(2);
        timeRecordRow2.setUsername("Cody");
        timeRecordRow2.setDate("11/25/2018");
        timeRecordRow2.setTimeIn("9AM");
        timeRecordRow2.setTimeOut("5PM");
        timeRecordRow2.setHoursWorked(8);

        results.add(timeRecordRow1);
        results.add(timeRecordRow2);

        logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + user.getUsername() + " " + user.getRole());

        return ResponseEntity.ok(results);
    }

    @PostMapping (path = "/clockIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addClockIn(@RequestBody String username) {

        String status = timeRecordService.saveClockIn(username);

        // To-do: Either check status using conditional, or always return success if no exception. If exception return that
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PostMapping (path = "/clockOut", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addClockOut(@RequestBody String username) {

        String status = timeRecordService.saveClockOut(username);

        // To-do: Either check status using conditional, or always return success if no exception. If exception return that
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
}
