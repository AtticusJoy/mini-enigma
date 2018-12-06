// created by Justin Weston

package com.group.project.service;

import com.group.project.dto.TimeRecordRow;
import com.group.project.entity.EmployeeRecord;
import com.group.project.entity.TimeRecord;
import com.group.project.repository.EmployeeRecordRepository;
import com.group.project.repository.TimeRecordRepository;
import com.group.project.rest.InvalidTimeException;
import com.group.project.rest.TimeResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeRecordServiceImpl implements TimeRecordService {

    private final double maxHoursWorkedPerRecord;

    private TimeRecordRepository timeRecordRepository;
    private EmployeeRecordRepository employeeRecordRepository;

    @Autowired
    public TimeRecordServiceImpl(@Value("${maxHoursWorkedPerRecord}") double maxHoursWorkedPerRecord, TimeRecordRepository timeRecordRepository,
                                 EmployeeRecordRepository employeeRecordRepository) {
        this.maxHoursWorkedPerRecord = maxHoursWorkedPerRecord;
        this.timeRecordRepository = timeRecordRepository;
        this.employeeRecordRepository = employeeRecordRepository;
    }

    @Override
    public List<TimeRecordRow> getTimeRecordsManager(String username) {

        if (employeeRecordRepository.findByUsername(username) == null) {
            saveNewEmployee(username);
        }

        List<TimeRecord> timeRecords = timeRecordRepository.findAll();

        return mapToTimeRecordRow(timeRecords);
    }

    @Override
    public List<TimeRecordRow> getTimeRecordsEmployee(String username) {

        if (employeeRecordRepository.findByUsername(username) != null) {
            int employeeId = employeeRecordRepository.findByUsername(username).getId();

            List<TimeRecord> timeRecords = timeRecordRepository.findByEmployeeId(employeeId);

            return mapToTimeRecordRow(timeRecords);
        } else {
            saveNewEmployee(username);
            return new ArrayList<>();
        }
    }

    @Override
    public void saveClockIn(String username) {

        EmployeeRecord employee = employeeRecordRepository.findByUsername(username); //.orElse(saveNewEmployee(username));

        timeRecordRepository.save(new TimeRecord(employee.getId()));
    }

    @Override
    public void saveClockOut(String username) {

        int employeeId = employeeRecordRepository.findByUsername(username).getId();

        // gets most recent record for the employeeId
        TimeRecord timeRecord = timeRecordRepository.findTopByEmployeeIdOrderByIdDesc(employeeId);

        // check if timeRecord has a clock out time already
        if (timeRecord == null) {
            throw new TimeResourceNotFound("Error, no clock in entries exists!");
        } else if (timeRecord.getClockOut() != null) {
            throw new TimeResourceNotFound("Error, user has already clocked out on most recent time record!");
        } else {

            // Will set current time as clock out time and calculate hoursWorked
            timeRecord.clockUserOut();

            // check if hoursWorked is > ${maxTimeWorkedPerEntry} (defined in application.properties)
            if (timeRecord.getHoursWorked() > maxHoursWorkedPerRecord) {
                DecimalFormat df = new DecimalFormat("0.00");
                String hoursWorked = df.format(timeRecord.getHoursWorked());
                throw new TimeResourceNotFound("Error, " + hoursWorked + " hours worked exceeds maximum allowed " +
                        "of " + maxHoursWorkedPerRecord);
            } else {
                timeRecordRepository.save(timeRecord);
            }
        }
    }

    @Override
    public EmployeeRecord saveNewEmployee(String username) {

        return employeeRecordRepository.save(new EmployeeRecord(username));
    }

    private List<TimeRecordRow> mapToTimeRecordRow(List<TimeRecord> timeRecords) {

        ArrayList<TimeRecordRow> timeRecordRows = new ArrayList<>();
        DateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm");

        for(TimeRecord record : timeRecords) {
            TimeRecordRow timeRecordRow = new TimeRecordRow();
            timeRecordRow.setId(record.getId());
            timeRecordRow.setUsername(getUsername(record.getEmployeeId()));

            if(record.getClockIn() != null) {
                timeRecordRow.setDate(date.format(record.getClockIn()));
                timeRecordRow.setTimeIn(time.format(record.getClockIn()));
            }
            if(record.getClockOut() != null) {
                timeRecordRow.setTimeOut(time.format(record.getClockOut()));
            }
            if(record.getHoursWorked() != null) {
                DecimalFormat df = new DecimalFormat("0.00");
                String hoursWorked = df.format(record.getHoursWorked());
                timeRecordRow.setHoursWorked(hoursWorked);
            }

            timeRecordRows.add(timeRecordRow);
        }

        return timeRecordRows;
    }

    private String getUsername(int employeeId) {
        EmployeeRecord e = employeeRecordRepository.getOne(employeeId);

        return  e.getUsername();
    }
}
