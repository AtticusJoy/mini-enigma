// created by Justin Weston

package com.group.project.repository;

import com.group.project.entity.EmployeeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRecordRepository extends JpaRepository<EmployeeRecord, Integer> {

    // Creates query and returns EmployeeRecord "row" by selecting on username
    EmployeeRecord findByUsername(String username);
}
