package com.group.project.repository;

import com.group.project.entity.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeRecordRepository extends JpaRepository<TimeRecord, Integer> {

    List<TimeRecord> findByEmployeeId(int employeeId);
    TimeRecord findTopByEmployeeIdOrderByIdDesc(int employeeId);
}
