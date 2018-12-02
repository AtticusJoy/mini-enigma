package com.group.project.service;

import com.group.project.dto.TimeRecordRow;
import com.group.project.entity.EmployeeRecord;

import java.util.List;

public interface TimeRecordService {

    List<TimeRecordRow> getTimeRecordsManager();
    List<TimeRecordRow> getTimeRecordsEmployee(String username);
    void saveClockIn(String username);
    void saveClockOut(String username);
    EmployeeRecord saveNewEmployee(String username);
}
