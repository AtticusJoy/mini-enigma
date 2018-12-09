package com.group.project.dto;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class TimeRecordRowTest {

    private TimeRecordRow timeRecordRow;

    @Before
    public void setUp() throws Exception {
        timeRecordRow = new TimeRecordRow();
    }

    @Test
    public void getId() {
        timeRecordRow.setId(1);
        assertThat(timeRecordRow.getId()).isEqualTo(1);
    }

    @Test
    public void getUsername() {
        timeRecordRow.setUsername("jane");
        assertThat(timeRecordRow.getUsername()).isEqualTo("jane");
    }

    @Test
    public void getDate() {
        timeRecordRow.setDate("12/08");
        assertThat(timeRecordRow.getDate()).isEqualTo("12/08");
    }

    @Test
    public void getTimeIn() {
        timeRecordRow.setTimeIn("10AM");
        assertThat(timeRecordRow.getTimeIn()).isEqualTo("10AM");
    }

    @Test
    public void getTimeOut() {
        timeRecordRow.setTimeOut("5PM");
        assertThat(timeRecordRow.getTimeOut()).isEqualTo("5PM");
    }

    @Test
    public void getHoursWorked() {
        timeRecordRow.setHoursWorked("8");
        assertThat(timeRecordRow.getHoursWorked()).isEqualTo("8");
    }

    @Test
    public void compareTo() {
        TimeRecordRow timeRecordRow1 = new TimeRecordRow();
        TimeRecordRow timeRecordRow2 = new TimeRecordRow();
        timeRecordRow1.setUsername("jane");
        timeRecordRow1.setDate("12/08");
        timeRecordRow1.setTimeIn("10AM");
        timeRecordRow2.setUsername("jane");
        timeRecordRow2.setDate("12/07");
        timeRecordRow2.setTimeIn("9AM");
        assertThat(timeRecordRow1.compareTo(timeRecordRow2)).isEqualTo(1);
    }
}