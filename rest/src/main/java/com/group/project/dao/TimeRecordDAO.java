package com.group.project.dao;

import com.group.project.entity.TimeRecord;

import java.util.List;

public interface TimeRecordDAO {

    int getUserId(String username);
    List<TimeRecord> getEmployeeTimeRecords(int userId);
    List<TimeRecord> getManagerTimeRecords(int userId);
    String saveClockIn(String username);
    boolean isValidClockIn(String username);
    String saveClockOut(String username);
    boolean isValidClockOut(String username);
}
