/*******************************************************************************************
 * File: TimeRecordService.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: Service implementation for TimeRecord handling. Implements basic
 * CRUD methods, leveraging Spring annotations and JpaRepository functionality
 * to accomplish most of the heavy lifting
 *
 ******************************************************************************************/
package com.group.project.service;

import com.group.project.dto.TimeRecordRow;
import com.group.project.entity.EmployeeRecord;
import com.group.project.entity.TimeRecord;
import com.group.project.repository.EmployeeRecordRepository;
import com.group.project.repository.TimeRecordRepository;
import com.group.project.rest.TimeResourceNotFoundException;
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

    // Defined in application.properties and injected in the constructor
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

    // This method is used to return a list of records that a user
    // with the Manager role can view
    // Also, checks to see if the user exists in the database
    // and if they don't adds the user
    @Override
    public List<TimeRecordRow> getTimeRecordsManager(String username) {

        if (employeeRecordRepository.findByUsername(username) == null) {
            saveNewEmployee(username);
        }

        List<TimeRecord> timeRecords = timeRecordRepository.findAll();

        return mapToTimeRecordRow(timeRecords);
    }

    // This method is used to return a list of records that a user
    // with the Employee role can view
    // Also, checks to see if the user exists in the database
    // and if they don't adds the user
    @Override
    public List<TimeRecordRow> getTimeRecordsEmployee(String username) {

        // if the employee exists, return all records with their user id
        // else create the employee in the database and return an empty list
        if (employeeRecordRepository.findByUsername(username) != null) {
            int employeeId = employeeRecordRepository.findByUsername(username).getId();

            List<TimeRecord> timeRecords = timeRecordRepository.findByEmployeeId(employeeId);

            return mapToTimeRecordRow(timeRecords);
        } else {
            saveNewEmployee(username);
            return new ArrayList<>();
        }
    }

    // Creates a clock in record on the database for the given username
    // with the current date and time
    @Override
    public void saveClockIn(String username) {

        // Looks up the employee username on the employee record database table
        EmployeeRecord employee = employeeRecordRepository.findByUsername(username);

        // Uses the employee record to retrieve the employee id
        // and then creates a new TimeRecord for the given employee
        timeRecordRepository.save(new TimeRecord(employee.getId()));
    }

    // Retrieves the most recent time record and uses the current date
    // and time as the clock out for the given username
    @Override
    public void saveClockOut(String username) {

        // Retrieves the employeeId for the username
        int employeeId = employeeRecordRepository.findByUsername(username).getId();

        // gets most recent record for the employeeId
        TimeRecord timeRecord = timeRecordRepository.findTopByEmployeeIdOrderByIdDesc(employeeId);

        // if no time records exist throw exception
        // else if most recent time record already has clock out information throw exception
        // else save the current date time as the clock out
        if (timeRecord == null) {
            throw new TimeResourceNotFoundException("Error, no clock in entries exists!");
        } else if (timeRecord.getClockOut() != null) {
            throw new TimeResourceNotFoundException("Error, user has already clocked out on most recent time record!");
        } else {

            // Will set current time as clock out time and calculate hoursWorked
            timeRecord.setClockOut(new Date());

            // if hoursWorked is > ${maxTimeWorkedPerEntry} (defined in application.properties) throw exception
            // else save clock out information to database
            if (timeRecord.getHoursWorked() > maxHoursWorkedPerRecord) {
                DecimalFormat df = new DecimalFormat("0.00");
                String hoursWorked = df.format(timeRecord.getHoursWorked());
                throw new TimeResourceNotFoundException("Error, " + hoursWorked + " hours worked exceeds maximum allowed " +
                        "of " + maxHoursWorkedPerRecord);
            } else {
                timeRecordRepository.save(timeRecord);
            }
        }
    }

    // Updates existing time record information with that given in timeRecordRow parameter
    // If any fields are null, ignores them
    // Blocks changes to recordId as this shouldn't be modified for database integrity
    // and also for direct modification of hours worked as this is calculated if clock in
    // or clock out changes
    @Override
    public void updateTimeRecord(TimeRecordRow timeRecordRow) throws ParseException {

        // Retrieves current record or throws an exception if Id not found
        TimeRecord timeRecord = timeRecordRepository.findById(timeRecordRow.getId())
                .orElseThrow(() -> new TimeResourceNotFoundException("Record with id: "
                        + timeRecordRow.getId() + " does not exist!"));

        // Sets employeeId if value has changed
        if (timeRecordRow.getUsername() != null) {
            int employeeId = employeeRecordRepository.findByUsername(timeRecordRow.getUsername()).getId();
            timeRecord.setEmployeeId(employeeId);
        }

        updateClockIn(timeRecord, timeRecordRow);
        updateClockOut(timeRecord, timeRecordRow);

        timeRecordRepository.save(timeRecord);
    }

    // Creates a new employee
    @Override
    public EmployeeRecord saveNewEmployee(String username) {

        return employeeRecordRepository.save(new EmployeeRecord(username));
    }

    // Maps each database entity TimeRecord to the corresponding TimeRecordRow
    // data transfer object (DTO) fields
    // Clock in and out date-time is parsed into distinct time and date and converted from
    // UTC to localTimeZone variable defined in application.properties
    private List<TimeRecordRow> mapToTimeRecordRow(List<TimeRecord> timeRecords) {

        ArrayList<TimeRecordRow> timeRecordRows = new ArrayList<>();

        DateFormat date = new SimpleDateFormat("dd-MMM-yyyy");
        DateFormat time = new SimpleDateFormat("HH:mm");

        // Sets the application configured timezone before returning the time
        date.setTimeZone(TimeZone.getTimeZone(localTimeZone));
        time.setTimeZone(TimeZone.getTimeZone(localTimeZone));

        // For each time record, for fields that aren't null map to
        // corresponding timeRecordRow DTO fields
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

    // Returns username for the given employeeId
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
            throw new ParseException("Invalid time/date format. Date needs to be dd-MMM-yyyy and time should be HH:mm", 0);
        }
    }

    // Parses clock out time and updates as needed
    // Throws ParseException
    private void updateClockOut(TimeRecord timeRecord, TimeRecordRow timeRecordRow) throws ParseException {

        try{
            String clockOutTime = timeRecordRow.getTimeOut();

            // Updates clock out time if new value given, else uses current clock out
            // and saves it again to trigger recalculation of hours worked
            // in case clock in changed
            if (clockOutTime != null) {
                DateFormat dateTimeFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                dateTimeFormatter.setTimeZone(TimeZone.getTimeZone(localTimeZone));
                DateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
                dateFormatter.setTimeZone(TimeZone.getTimeZone(localTimeZone));

                // Uses the clock in date initially as the clock out date
                String clockInDate = dateFormatter.format(timeRecord.getClockIn());
                Date clockOutDateTime = dateTimeFormatter.parse(clockInDate + " " + clockOutTime);

                // Checks if the clock out date-time (defaulted as the clock in date)
                // occurs before the clock in date
                // This will only occur if the time record spanned to the following day
                // if so, increment to the next day
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

