package com.group.project.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "time_action_record")
public class TimeRecord {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue
    @Column(name = "time_action_record_id")
    private int id;

    @Column(name = "employee_record_id")
    private int employeeId;

    @Column(name = "clock_in_timestamp")
    private Date clockIn;

    @Column(name = "clock_out_timestamp")
    private Date clockOut;

    public TimeRecord() {
    }

    public TimeRecord(int employeeId) {
        this.employeeId = employeeId;
    }

    // set clockOut to current time and calculate hoursWorked
    // clockOut - clockIn
    public void clockUserOut() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getClockIn() {
        return clockIn;
    }

    public void setClockIn(Date clockIn) {
        this.clockIn = clockIn;
    }

    public Date getClockOut() {
        return clockOut;
    }

    public void setClockOut(Date clockOut) {
        this.clockOut = clockOut;
    }
}
