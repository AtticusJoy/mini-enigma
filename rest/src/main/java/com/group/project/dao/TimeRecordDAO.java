package com.group.project.dao;


import com.group.project.dto.User;
import com.group.project.entity.TimeRecord;

import java.util.List;

public interface TimeRecordDAO {

    List<TimeRecord> getTimeRecords(User user);
    String saveClockIn(String username);
    String saveClockOut(String username);
}
