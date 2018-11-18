package com.group.project.service;

import com.group.project.dto.User;
import com.group.project.entity.TimeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeRecordServiceImpl implements TimeRecordService {

    //@Autowired
    //private TimeRecordDAO timeRecordDAO;

    @Override
    public List<TimeRecord> getTimeRecords(User user) {

        // call corresponding DAO method

        return null;
    }

    @Override
    public String saveClockIn(String username) {

        // call corresponding DAO method

        return null;
    }

    @Override
    public String saveClockOut(String username) {

        // call corresponding DAO method

        return null;
    }
}
