/*******************************************************************************************
 * File: TimeRecordRestController.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: REST controller
 *
 ******************************************************************************************/

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

    // Accepts a JSON User object and converts it to the User DTO
    // Calls the corresponding service method to retrieve the list
    // of time records depending on the User role
    // Returns the list of time records
    // Both input and output are converted from/to JSON by Spring using Jackson
    @PostMapping(path = "/getData", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TimeRecordRow> listTimeRecords(@RequestBody User user) {

        List<TimeRecordRow> timeRecords;

        if (user.getRole().equalsIgnoreCase("Manager")) {
            timeRecords = timeRecordService.getTimeRecordsManager(user.getUsername());
        } else if (user.getRole().equalsIgnoreCase("Employee")) {
            timeRecords = timeRecordService.getTimeRecordsEmployee(user.getUsername());
        } else {
            throw new TimeResourceNotFoundException(user.getRole() + " is not a valid Role.");
        }

        Collections.sort(timeRecords);
        return timeRecords;
    }

    // Accepts a text username and calls the corresponding service method to handle the clock in entry
    // Returns a success message once completed (Exceptions and failure status is handled in
    // TimeRecordExceptionHandler class)
    @PostMapping (path = "/clockIn", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addClockIn(@RequestBody String username) {

        timeRecordService.saveClockIn(username);
        String response = "Successfully clocked " + username + " in!";

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Accepts a text username and calls the corresponding service method to handle the clock in entry
    // Returns a success message once completed (Exceptions and failure status is handled in
    // TimeRecordExceptionHandler class)
    @PostMapping (path = "/clockOut", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addClockOut(@RequestBody String username) {

        timeRecordService.saveClockOut(username);
        String response = "Successfully clocked " + username + " out!";

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    // Accepts a JSON TimeRecordRow object with the time information to modify
    // and calls the corresponding service method to handle the modification
    // Returns a success message once completed (Exceptions and failure status is handled in
    // TimeRecordExceptionHandler class)
    @PutMapping (path = "/modifyTime", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modifyTimeRecord(@RequestBody TimeRecordRow timeRecordRow) throws ParseException {
        timeRecordService.updateTimeRecord(timeRecordRow);

        return new ResponseEntity<>("Successfully updated record with id: " + timeRecordRow.getId(), HttpStatus.OK);
    }

    // Used for unit testing
    void setTimeRecordService(TimeRecordService timeRecordService){
        this.timeRecordService = timeRecordService;
    }
}
