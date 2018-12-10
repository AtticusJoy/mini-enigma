// created by Justin Weston

package com.group.project.repository;

import com.group.project.entity.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Integer> {

    // Creates query and returns all TimeRecord rows that contain the employeeId
    List<TimeRecord> findByEmployeeId(int employeeId);

    // Finds the most recent TimeRecord for the given employeeId
    // This is accomplished by ordering by id with the highest id first, then returning the top row
    TimeRecord findTopByEmployeeIdOrderByIdDesc(int employeeId);
}
