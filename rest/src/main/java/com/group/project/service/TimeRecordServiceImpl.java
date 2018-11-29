package com.group.project.service;

import com.group.project.dto.User;
import com.group.project.entity.TimeRecord;
import com.group.project.repository.TimeRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeRecordServiceImpl implements TimeRecordService {

    private TimeRecordRepository timeRecordRepository;

    public TimeRecordServiceImpl(TimeRecordRepository timeRecordRepository) {
        this.timeRecordRepository = timeRecordRepository;
    }

    @Override
    public List<TimeRecord> getTimeRecordsManager() {

        return timeRecordRepository.findAll();
    }

    @Override
    public List<TimeRecord> getTimeRecordsEmployee(User user) {

        // To-do: Create logic to retrieve employeeId from user
        // Most likely create an Employee Entity/Model and use JPA
        int employeeId = 1;

        return timeRecordRepository.findByEmployeeId(employeeId);
    }

    @Override
    public void saveClockIn(String username) {

        timeRecordRepository.save(new TimeRecord());
    }

    @Override
    public void saveClockOut(String username) {

        // TO-DO: Add in ClockOut logic, both validation and updating existing record
        // or do we need to get existing, user setter, then write back? probably not
    }
}
