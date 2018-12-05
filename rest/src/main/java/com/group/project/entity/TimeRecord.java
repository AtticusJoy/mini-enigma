// created by Justin Weston

package com.group.project.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.util.Date;

@Entity
@Table(name = "time_action_record")
@EntityListeners(AuditingEntityListener.class)
public class TimeRecord {

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

    public TimeRecord() {
    }

    public TimeRecord(int employeeId) {
        this.employeeId = employeeId;
    }

    public void clockUserOut() {
        setClockOut(new Date());

        long duration = clockOut.getTime() - clockIn.getTime();
        hoursWorked = (double)duration / MILLISECONDS_PER_HOUR;

        DecimalFormat df = new DecimalFormat("0.00");
        String s = df.format(hoursWorked);

        System.out.println("Milliseconds duration: " + duration);
        System.out.println("Clock-in: " + clockIn);
        System.out.println("Clock-out: " + clockOut);
        System.out.println("Hours worked: " + s);
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

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
}