package com.group.project.service;

import com.group.project.dao.TimeRecordDAO;
import com.group.project.dto.User;
import com.group.project.entity.TimeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeRecordServiceImpl implements TimeRecordService {

    private TimeRecordDAO timeRecordDAO;

    @Autowired
    public TimeRecordServiceImpl(TimeRecordDAO timeRecordDAO) {
        this.timeRecordDAO = timeRecordDAO;
    }

    @Override
    public List<TimeRecord> getTimeRecords(User user) {

        return timeRecordDAO.getTimeRecords(user);
    }

    @Override
    public String saveClockIn(String username) {

        return timeRecordDAO.saveClockIn(username);
    }

    @Override
    public String saveClockOut(String username) {

        return timeRecordDAO.saveClockOut(username);
    }
}
