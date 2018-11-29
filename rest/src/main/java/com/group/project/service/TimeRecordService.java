package com.group.project.service;

import com.group.project.dto.User;
import com.group.project.entity.TimeRecord;

import java.util.List;

public interface TimeRecordService {

    List<TimeRecord> getTimeRecordsManager();
    List<TimeRecord> getTimeRecordsEmployee(User user);
    void saveClockIn(String username);
    void saveClockOut(String username);
}
