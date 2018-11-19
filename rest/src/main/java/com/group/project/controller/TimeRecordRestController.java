package com.group.project.controller;

import com.group.project.dto.TimeRecordRow;
import com.group.project.dto.User;
import com.group.project.entity.TimeRecord;
import com.group.project.service.TimeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/getData")
    // Add Consumes etc. that Peter expects
    public ResponseEntity<List<TimeRecordRow>> listTimeRecords(User user) {

        List<TimeRecord> timeRecords = timeRecordService.getTimeRecords(user);

        // return ResponseEntity per Resource example
        return null;
    }

    @PostMapping ("/clockIn")
    public ResponseEntity addClockIn(String username) {

        String status = timeRecordService.saveClockIn(username);

        return null;
    }

    @PostMapping ("clockOut")
    public String addClockOut(String username) {

        String status = timeRecordService.saveClockOut(username);

        return null;
    }
}
