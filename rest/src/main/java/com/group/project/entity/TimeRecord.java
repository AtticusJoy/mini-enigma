// created by Justin Weston

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
        if(clockIn.after(new Date())){
            throw new InvalidTimeException("Error, clock in time cannot be in the future!");
        } else {
            this.clockIn = clockIn;
        }
    }

    public Date getClockOut() {
        return clockOut;
    }

    private void setClockOut(Date clockOut) {
        if(clockOut.before(clockIn)){
            throw new InvalidTimeException("Error, clock out time cannot occur before clock in time!");
        } else {
            this.clockOut = clockOut;
        }
    }

    public Double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void clockUserOut() {
        setClockOut(new Date());
        hoursWorked = calculateHoursWorked();
    }

    private double calculateHoursWorked() {
        long duration = clockOut.getTime() - clockIn.getTime();

        return hoursWorked = (double)duration / MILLISECONDS_PER_HOUR;
    }
}