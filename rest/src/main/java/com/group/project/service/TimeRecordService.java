/*******************************************************************************************
 * File: TimeRecordService.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: Service interface for TimeRecord handling. Defines basic CRUD methods
 *
 ******************************************************************************************/

package com.group.project.service;

import com.group.project.dto.TimeRecordRow;
import com.group.project.entity.EmployeeRecord;

import java.text.ParseException;
import java.util.List;

public interface TimeRecordService {

    List<TimeRecordRow> getTimeRecordsManager(String username);
    List<TimeRecordRow> getTimeRecordsEmployee(String username);
    void saveClockIn(String username);
    void saveClockOut(String username);
    void updateTimeRecord(TimeRecordRow timeRecordRow) throws ParseException;
    EmployeeRecord saveNewEmployee(String username);
}
