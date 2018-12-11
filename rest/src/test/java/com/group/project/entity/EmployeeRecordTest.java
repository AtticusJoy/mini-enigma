package com.group.project.entity;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeRecordTest {

    private EmployeeRecord employeeRecord;

    @Before
    public void setUp() throws Exception {
        employeeRecord = new EmployeeRecord();
    }

    @Test
    public void getId() {
        employeeRecord.setId(1);
        assertThat(employeeRecord.getId()).isEqualTo(1);
    }

    @Test
    public void getUsername() {
        employeeRecord.setUsername("jane");
        assertThat(employeeRecord.getUsername()).isEqualTo("jane");
    }
}