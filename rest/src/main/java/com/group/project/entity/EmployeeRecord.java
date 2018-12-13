/*******************************************************************************************
 * File: EmployeeRecord.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: Data model for the employees_record database table. This table contains
 * employee information
 *
 ******************************************************************************************/

package com.group.project.entity;

import javax.persistence.*;

@Entity
@Table (name = "employees_record")
public class EmployeeRecord {

    // Allows the database to generate the primary key employee_record_id
    @Id
    @GeneratedValue
    @Column(name = "employee_record_id")
    private int id;

    @Column(name = "employee_username")
    private String username;

    public EmployeeRecord() { }

    public EmployeeRecord(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
