package com.group.project.entity;

import lombok.Data;

import javax.persistence.*;

@Data
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
}
