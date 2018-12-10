// created by Justin Weston

package com.group.project.service;

import com.group.project.dto.TimeRecordRow;
import com.group.project.entity.EmployeeRecord;
import com.group.project.entity.TimeRecord;
import com.group.project.repository.EmployeeRecordRepository;
import com.group.project.repository.TimeRecordRepository;
import com.group.project.rest.TimeResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TimeRecordServiceImpl implements TimeRecordService {

    private final double maxHoursWorkedPerRecord;
    private final String localTimeZone;

    private TimeRecordRepository timeRecordRepository;
    private EmployeeRecordRepository employeeRecordRepository;

    @Autowired
    public TimeRecordServiceImpl(@Value("${maxHoursWorkedPerRecord}") double maxHoursWorkedPerRecord,
                                 @Value("${localTimeZone}") String localTimeZone,
                                 TimeRecordRepository timeRecordRepository,
                                 EmployeeRecordRepository employeeRecordRepository) {
        this.maxHoursWorkedPerRecord = maxHoursWorkedPerRecord;
        this.localTimeZone = localTimeZone;
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
            timeRecord.setClockOut(new Date());

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

    // Doesn't allow for recordId to change as this shouldn't be modified for database integrity
    // Ignores direct modification of hours worked and calculates itself
    @Override
    public void updateTimeRecord(TimeRecordRow timeRecordRow) throws ParseException {

        // Retrieves current record or throws an exception if Id not found
        TimeRecord timeRecord = timeRecordRepository.findById(timeRecordRow.getId())
                .orElseThrow(() -> new TimeResourceNotFound("Record with id: " + timeRecordRow.getId() + " does not exist!"));

        // Sets employeeId if value has changed
        if (timeRecordRow.getUsername() != null) {
            int employeeId = employeeRecordRepository.findByUsername(timeRecordRow.getUsername()).getId();
            timeRecord.setEmployeeId(employeeId);
        }

        updateClockIn(timeRecord, timeRecordRow);
        updateClockOut(timeRecord, timeRecordRow);

        timeRecordRepository.save(timeRecord);
    }

    @Override
    public EmployeeRecord saveNewEmployee(String username) {

        return employeeRecordRepository.save(new EmployeeRecord(username));
    }

    // Maps each database entity TimeRecord to the corresponding data transfer object (DTO) fields
    private List<TimeRecordRow> mapToTimeRecordRow(List<TimeRecord> timeRecords) {

        ArrayList<TimeRecordRow> timeRecordRows = new ArrayList<>();
        DateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm");
        // Sets the application configured timezone before returning the time
        date.setTimeZone(TimeZone.getTimeZone(localTimeZone));
        time.setTimeZone(TimeZone.getTimeZone(localTimeZone));

        for (TimeRecord record : timeRecords) {
            TimeRecordRow timeRecordRow = new TimeRecordRow();
            timeRecordRow.setId(record.getId());
            timeRecordRow.setUsername(getUsername(record.getEmployeeId()));

            if (record.getClockIn() != null) {
                timeRecordRow.setDate(date.format(record.getClockIn()));
                timeRecordRow.setTimeIn(time.format(record.getClockIn()));
            }
            if (record.getClockOut() != null) {
                timeRecordRow.setTimeOut(time.format(record.getClockOut()));
            }
            if (record.getHoursWorked() != null) {
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

        return e.getUsername();
    }

    // Parses clock in time and date and updates as needed
    private void updateClockIn(TimeRecord timeRecord, TimeRecordRow timeRecordRow) throws ParseException {

        try {
            // Date and time formatters to cover use cases where user provides
            // date and time for update, just date, or just time
            DateFormat dateTimeFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
            dateTimeFormatter.setTimeZone(TimeZone.getTimeZone(localTimeZone));

            DateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
            dateFormatter.setTimeZone(TimeZone.getTimeZone(localTimeZone));

            DateFormat timeFormatter = new SimpleDateFormat("HH:mm");
            timeFormatter.setTimeZone(TimeZone.getTimeZone(localTimeZone));

            String clockInDate = timeRecordRow.getDate();
            String clockInTime = timeRecordRow.getTimeIn();
            Date clockInDateTime;

            // If both clock in time and date are given update
            // Else if just clock in time is given update time and keep existing date
            // Else if just clock in date is given update date and keep existing time
            if (clockInTime != null && clockInDate != null) {
                clockInDateTime = dateTimeFormatter.parse(clockInDate + " " + clockInTime);
                timeRecord.setClockIn(clockInDateTime);
            } else if (clockInTime != null) {
                clockInDate = dateFormatter.format(timeRecord.getClockIn());
                clockInDateTime = dateTimeFormatter.parse(clockInDate + " " + clockInTime);
                timeRecord.setClockIn(clockInDateTime);
            } else if (clockInDate != null) {
                clockInTime = timeFormatter.format(timeRecord.getClockIn());
                clockInDateTime = dateTimeFormatter.parse(clockInDate + " " + clockInTime);
                timeRecord.setClockIn(clockInDateTime);
            }
        } catch (ParseException exc) {
            throw new ParseException("Invalid time/date format. Date needs to be dd-MMM-yyyy and times should be HH:mm", 0);
        }
    }

    // Parses clock out time and updates as needed
    private void updateClockOut(TimeRecord timeRecord, TimeRecordRow timeRecordRow) throws ParseException {

        try{
            String clockOutTime = timeRecordRow.getTimeOut();
            // Updates clock out time if new value given, else refreshes clock out to recalculate hours worked
            // in case clock in changed
            if (clockOutTime != null) {
                DateFormat dateTimeFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                dateTimeFormatter.setTimeZone(TimeZone.getTimeZone(localTimeZone));
                DateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
                dateFormatter.setTimeZone(TimeZone.getTimeZone(localTimeZone));

                String clockInDate = dateFormatter.format(timeRecord.getClockIn());
                Date clockOutDateTime = dateTimeFormatter.parse(clockInDate + " " + clockOutTime);

                // increment the date if the clock out time is on the next day
                if (clockOutDateTime.before(timeRecord.getClockIn())) {
                    Calendar c = Calendar.getInstance();
                    c.setTime(clockOutDateTime);
                    c.add(Calendar.DATE, 1);
                    clockOutDateTime = c.getTime();
                }
                timeRecord.setClockOut(clockOutDateTime);
            } else {
                timeRecord.setClockOut(timeRecord.getClockOut());
            }
        } catch (ParseException exc) {
            throw new ParseException("Invalid time/date format. Date needs to be dd-MMM-yyyy and times should be HH:mm", 0);
        }
    }
}

