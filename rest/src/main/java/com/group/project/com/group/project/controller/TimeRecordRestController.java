package com.group.project.com.group.project.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeRecordRestController {

    // @Autowired
    // private TimeRecordService timeRecordService;

    @PostMapping("/getData")
    // Update to include User as parameter once POJO built
    // Update to return ResponseEntity<List<TimeRecordUI>> once POJO built
    public String listTimeRecords(String username) {

        return "returning from TimeRecordRestController: listTimeRecords";
    }

    @PostMapping ("/clockIn")
    // Update to include User as parameter once POJO built
    // Update to return ResponseEntity with status
    public String addClockIn(String username) {

        return "returning from TimeRecordRestController: addClockIn";
    }

    @PostMapping ("clockOut")
    // Update to include User as parameter once POJO built
    // Update to return ResponseEntity with status
    public String addClockOut(String username) {

        return "returning from TimeRecordRestController: addClockOut";
    }
}
