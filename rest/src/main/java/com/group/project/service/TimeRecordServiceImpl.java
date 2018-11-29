package com.group.project.service;

import com.group.project.entity.EmployeeRecord;
import com.group.project.entity.TimeRecord;
import com.group.project.repository.EmployeeRecordRepository;
import com.group.project.repository.TimeRecordRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
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

        EmployeeRecord employee = employeeRecordRepository.findByUsername(username); //.orElse(saveNewEmployee(username));
        if (employee == null) {
            employee = saveNewEmployee(username);
        }
        //System.out.println(username);
        timeRecordRepository.save(new TimeRecord(employee.getId()));
    }

    @Override
    public void saveClockOut(String username) {

        // TO-DO: Add in ClockOut logic, both validation and updating existing record
        // or do we need to get existing, user setter, then write back? probably not

        int employeeId = employeeRecordRepository.findByUsername(username).getId();

        // gets most recent record for the employeeId
        TimeRecord timeRecord = timeRecordRepository.findTopByEmployeeIdOrderByIdDesc(employeeId);
        System.out.println(timeRecord.getId());
    }

    @Override
    public EmployeeRecord saveNewEmployee(String username) {

        return employeeRecordRepository.save(new EmployeeRecord(username));
    }
}
