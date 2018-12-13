/*******************************************************************************************
 * File: TimeRecordRepository.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: Allows querying of the database table that TimeRecord models. This is done by
 * extending JpaRepository which contains commonly used query methods that don't need to be
 * explicitly implemented. Additional queries can be written such as findByEmployeeId to extend
 * as needed
 *
 ******************************************************************************************/

package com.group.project.repository;

import com.group.project.entity.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord, Integer> {

    // Creates query and returns all TimeRecord rows that contain the employeeId parameter
    List<TimeRecord> findByEmployeeId(int employeeId);

    // Finds the most recent TimeRecord for the given employeeId
    // This is accomplished by ordering by id with the highest id first, then returning the top row
    TimeRecord findTopByEmployeeIdOrderByIdDesc(int employeeId);
}
