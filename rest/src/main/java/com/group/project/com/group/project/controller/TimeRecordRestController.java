package com.group.project.com.group.project.controller;

import com.group.project.com.group.project.dto.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeRecordRestController {

    // @Autowired
    // private TimeRecordService timeRecordService;

    @PostMapping("/getData")
    // Update to return ResponseEntity<List<TimeRecordUI>> once POJO built
    public String listTimeRecords(User user) {

        // call corresponding service method and pass in user
        return "returning from TimeRecordRestController: listTimeRecords";
    }

    @PostMapping ("/clockIn")
    // Update to return ResponseEntity with status
    public String addClockIn(String username) {

        // call corresponding service method and pass in username
        return "returning from TimeRecordRestController: addClockIn";
    }

    @PostMapping ("clockOut")
    // Update to return ResponseEntity with status
    public String addClockOut(String username) {

        // call corresponding service method and pass in username
        return "returning from TimeRecordRestController: addClockOut";
    }
}
