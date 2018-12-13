/*******************************************************************************************
 * File: TimeRecord.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: Data model for the time_action_record database table. This table contains
 * time records with user clock in/out information
 *
 ******************************************************************************************/

package com.group.project.entity;

import com.group.project.rest.InvalidTimeException;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "time_action_record")
@EntityListeners(AuditingEntityListener.class)
public class TimeRecord {

    // Allows the database to generate the primary key employee_record_id
    @Id
    @GeneratedValue
    @Column(name = "time_action_record_id")
    private int id;

    @Column(name = "employee_record_id")
    private int employeeId;

    @Column(name = "clock_in_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date clockIn;

    @Column(name = "clock_out_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date clockOut;

    @Column(name = "hours_worked")
    private Double hoursWorked;

    private final static int MILLISECONDS_PER_HOUR = 3600000;

    public TimeRecord() { }

    public TimeRecord(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getClockIn() {
        return clockIn;
    }

    // Sets clock in date-time and checks to make sure it's not in the future
    public void setClockIn(Date clockIn) {
        if(clockIn.after(new Date())){
            throw new InvalidTimeException("Error, clock in time cannot be in the future!");
        } else {
            this.clockIn = clockIn;
        }
    }

    public Date getClockOut() {
        return clockOut;
    }

    // Sets clock out date-time and checks to make sure it doesn't occur before clock in time
    // Also, calls calculateHoursWorked to calculate and save hours worked
    public void setClockOut(Date clockOut) {
        if(clockOut.before(clockIn)){
            throw new InvalidTimeException("Error, clock out time cannot occur before clock in time!");
        } else {
            this.clockOut = clockOut;
            hoursWorked = calculateHoursWorked();
        }
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    // Calculates and returns the hours worked
    private double calculateHoursWorked() {
        long duration = clockOut.getTime() - clockIn.getTime();

        return hoursWorked = (double)duration / MILLISECONDS_PER_HOUR;
    }
}