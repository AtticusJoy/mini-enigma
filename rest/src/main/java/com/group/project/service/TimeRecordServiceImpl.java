// created by Justin Weston

package com.group.project.service;

import com.group.project.dto.TimeRecordRow;
import com.group.project.entity.EmployeeRecord;
import com.group.project.entity.TimeRecord;
import com.group.project.repository.EmployeeRecordRepository;
import com.group.project.repository.TimeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public List<TimeRecordRow> getTimeRecordsManager() {

        List<TimeRecord> timeRecords = timeRecordRepository.findAll();

        System.out.println("Max Time Worked: " + maxHoursWorkedPerRecord);
        return convertToTimeRecordRow(timeRecords);
    }

    @Override
    public List<TimeRecordRow> getTimeRecordsEmployee(String username) {

        if (employeeRecordRepository.findByUsername(username) != null) {
            int employeeId = employeeRecordRepository.findByUsername(username).getId();

            List<TimeRecord> timeRecords = timeRecordRepository.findByEmployeeId(employeeId);

            return convertToTimeRecordRow(timeRecords);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void saveClockIn(String username) {

        EmployeeRecord employee = employeeRecordRepository.findByUsername(username); //.orElse(saveNewEmployee(username));
        if (employee == null) {
            employee = saveNewEmployee(username);
        }
        timeRecordRepository.save(new TimeRecord(employee.getId()));
    }

    @Override
    public void saveClockOut(String username) {

        int employeeId = employeeRecordRepository.findByUsername(username).getId();

        // gets most recent record for the employeeId
        TimeRecord timeRecord = timeRecordRepository.findTopByEmployeeIdOrderByIdDesc(employeeId);

        // check if timeRecord has a clock out time already
        if (timeRecord.getClockOut() != null) {
            System.out.println("Error, clock out already exists on most recent time record!");
        } else {

            // Will set current time as clock out time and calculate hoursWorked
            timeRecord.clockUserOut();

            // check if hoursWorked is > ${maxTimeWorkedPerEntry} (defined in application.properties)
            if (timeRecord.getHoursWorked() > maxHoursWorkedPerRecord) {
                System.out.println("Error, " + timeRecord.getHoursWorked() + " hours worked exceeds maximum allowed " +
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

    private List<TimeRecordRow> convertToTimeRecordRow(List<TimeRecord> timeRecords) {

        ArrayList<TimeRecordRow> timeRecordRows = new ArrayList<>();
        DateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm");

        for(TimeRecord record : timeRecords) {
            TimeRecordRow timeRecordRow = new TimeRecordRow();
            timeRecordRow.setId(record.getId());
            timeRecordRow.setUsername(getUsername(record.getEmployeeId()));

            if(record.getClockIn() != null) {
                timeRecordRow.setDate(date.format(record.getClockIn()));
            }
            if(record.getClockIn() != null) {
                timeRecordRow.setTimeIn(time.format(record.getClockIn()));
            }
            if(record.getClockOut() != null) {
                timeRecordRow.setTimeOut(time.format(record.getClockOut()));
            }
            if(record.getHoursWorked() != null) {
                timeRecordRow.setHoursWorked(String.valueOf(record.getHoursWorked()));
            }


            System.out.println("{\nid: " + timeRecordRow.getId()
                                + "\nuserName: " + timeRecordRow.getUsername()
                                + "\ndate: " + timeRecordRow.getDate()
                                + "\ntimeIn: " + timeRecordRow.getTimeIn()
                                + "\ntimeOut: " + timeRecordRow.getTimeOut()
                                + "\nhoursWorked: " + timeRecordRow.getHoursWorked());

            timeRecordRows.add(timeRecordRow);
        }

        return timeRecordRows;
    }

    private String getUsername(int employeeId) {
        EmployeeRecord e = employeeRecordRepository.findOne(employeeId);
        System.out.println(e.getUsername());

        return  e.getUsername();
    }
}
