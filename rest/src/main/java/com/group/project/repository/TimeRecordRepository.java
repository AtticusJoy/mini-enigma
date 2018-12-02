// created by Justin Weston

package com.group.project.repository;

import com.group.project.entity.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Integer> {

    List<TimeRecord> findByEmployeeId(int employeeId);
    TimeRecord findTopByEmployeeIdOrderByIdDesc(int employeeId);
}
