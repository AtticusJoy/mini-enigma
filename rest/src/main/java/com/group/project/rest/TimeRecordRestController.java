// created by Justin Weston

package com.group.project.rest;

import com.group.project.dto.TimeRecordRow;
import com.group.project.dto.User;
import com.group.project.service.TimeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Collections;
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
    public List<TimeRecordRow> listTimeRecords(@RequestBody User user) {

        List<TimeRecordRow> timeRecords;

        if (user.getRole().equalsIgnoreCase("Manager")) {
            timeRecords = timeRecordService.getTimeRecordsManager(user.getUsername());
        } else if (user.getRole().equalsIgnoreCase("Employee")) {
            timeRecords = timeRecordService.getTimeRecordsEmployee(user.getUsername());
        } else {
            throw new TimeResourceNotFound(user.getRole() + " is not a valid Role.");
        }

        Collections.sort(timeRecords);
        return timeRecords;
    }

    @PostMapping (path = "/clockIn", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addClockIn(@RequestBody String username) {

        timeRecordService.saveClockIn(username);
        String response = "Successfully clocked " + username + " in!";

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping (path = "/clockOut", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addClockOut(@RequestBody String username) {

        timeRecordService.saveClockOut(username);
        String response = "Successfully clocked " + username + " out!";

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping (path = "/modifyTime", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modifyTimeRecord(@RequestBody TimeRecordRow timeRecordRow) throws ParseException {
        timeRecordService.updateTimeRecord(timeRecordRow);

        return new ResponseEntity<>("Successfully updated record with id: " + timeRecordRow.getId(), HttpStatus.OK);
    }
}
