package com.group.project.repository;

import com.group.project.entity.EmployeeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRecordRepository extends JpaRepository<EmployeeRecord, Integer> {
}
