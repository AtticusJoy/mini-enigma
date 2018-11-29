package com.group.project.entity;

import javax.persistence.*;

@Entity
@Table (name = "employees_record")
public class EmployeeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_record_id")
    private Integer id;

    @Column(name = "employee_username")
    private String username;

    public EmployeeRecord() {
    }

    public EmployeeRecord(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
