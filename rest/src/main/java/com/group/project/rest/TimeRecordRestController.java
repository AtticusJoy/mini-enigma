// created by Justin Weston

package com.group.project.rest;

import com.group.project.dto.TimeRecordRow;
import com.group.project.dto.User;
import com.group.project.service.TimeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class TimeRecordRestController {

    private TimeRecordService timeRecordService;

    protected TimeRecordRestController() {}

    @Autowired
    public TimeRecordRestController(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

    @PostMapping(path = "/getData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> listTimeRecords(@RequestBody User user) {

        List<TimeRecordRow> timeRecords;

        if (user.getRole().equalsIgnoreCase("Manager")) {
            timeRecords = timeRecordService.getTimeRecordsManager();
        } else {
            timeRecords = timeRecordService.getTimeRecordsEmployee(user.getUsername());
        }

        // convert List<TimeRecord> into List<TimeRecordRow> then return
        // consider doing this in service with convert helper method
        // and Controller is only a traffic cop?

        return ResponseEntity.ok(timeRecords);
    }

    @PostMapping (path = "/clockIn", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addClockIn(@RequestBody String username) {

        timeRecordService.saveClockIn(username);
        String status = "Successfully inserted new time record!";

        // To-do: Either check status using conditional, or always return success if no exception. If exception return that
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PostMapping (path = "/clockOut", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addClockOut(@RequestBody String username) {

        timeRecordService.saveClockOut(username);
        String status = "Successfully clocked user out!";

        // To-do: Either check status using conditional, or always return success if no exception. If exception return that
        return ResponseEntity.status(HttpStatus.OK).body(status);
    }
}
