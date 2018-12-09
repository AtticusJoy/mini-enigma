package com.group.project.entity;

import com.group.project.rest.InvalidTimeException;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeRecordTest {

    private TimeRecord timeRecord;

    @Before
    public void setUp() throws Exception {
        timeRecord = new TimeRecord();
    }

    @Test
    public void getId() {
        timeRecord.setId(1);
        assertThat(timeRecord.getId()).isEqualTo(1);
    }

    @Test
    public void getEmployeeId() {
        timeRecord.setEmployeeId(2);
        assertThat(timeRecord.getEmployeeId()).isEqualTo(2);
    }

    @Test
    public void getClockIn() {
        Date date = new Date();
        timeRecord.setClockIn(date);
        assertThat(timeRecord.getClockIn()).isEqualTo(date);
    }

    @Test(expected = InvalidTimeException.class)
    public void getClockInThrowsException(){
        Date date = new Date();
        long futureDate = date.getTime() + 1000000;
        timeRecord.setClockIn(new Date(futureDate));
    }

    @Test
    public void clockUserOut() {

    }
}