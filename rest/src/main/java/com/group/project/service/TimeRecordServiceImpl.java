package com.group.project.service;

import com.group.project.entity.TimeRecord;
import com.group.project.repository.EmployeeRecordRepository;
import com.group.project.repository.TimeRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeRecordServiceImpl implements TimeRecordService {

    private TimeRecordRepository timeRecordRepository;
    private EmployeeRecordRepository employeeRecordRepository;

    public TimeRecordServiceImpl(TimeRecordRepository timeRecordRepository,
                                 EmployeeRecordRepository employeeRecordRepository) {
        this.timeRecordRepository = timeRecordRepository;
        this.employeeRecordRepository = employeeRecordRepository;
    }

    @Override
    public List<TimeRecord> getTimeRecordsManager() {

        return timeRecordRepository.findAll();
    }

    @Override
    public List<TimeRecord> getTimeRecordsEmployee(String username) {

        int employeeId = employeeRecordRepository.findByUserName(username);

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
