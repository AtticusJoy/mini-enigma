package com.group.project.service;

import com.group.project.entity.EmployeeRecord;
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

        int employeeId = employeeRecordRepository.findByUsername(username).getId();

        return timeRecordRepository.findByEmployeeId(employeeId);
    }

    @Override
    public void saveClockIn(String username) {

        // Test after existing employee version works
        // Creates new employee if not found and returns userId
        // int employeeId = employeeRecordRepository.findByUserName(username).orElse(createNewUser(username));

        System.out.println(username);
        int employeeId = employeeRecordRepository.findByUsername(username).getId();
        timeRecordRepository.save(new TimeRecord(employeeId));
    }

    @Override
    public void saveClockOut(String username) {

        // TO-DO: Add in ClockOut logic, both validation and updating existing record
        // or do we need to get existing, user setter, then write back? probably not
    }

    @Override
    public int saveNewEmployee(String username) {

        EmployeeRecord newEmployee = employeeRecordRepository.save(new EmployeeRecord(username));

        return newEmployee.getId();
    }
}
