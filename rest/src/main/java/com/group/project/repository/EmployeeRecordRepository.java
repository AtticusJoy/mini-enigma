package com.group.project.repository;

import com.group.project.entity.EmployeeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRecordRepository extends JpaRepository<EmployeeRecord, Integer> {

    EmployeeRecord findByUsername(String username);
}
