/*******************************************************************************************
 * File: EmployeeRecordRepository.java
 * Date: 12Dec2018
 * Author: Justin Weston
 * Purpose: Allows querying of the database table that EmployeeRecord models. This is done by
 * extending JpaRepository which contains commonly used query methods that don't need to be
 * explicitly implemented. Additional queries can be written such as findByUsername to extend
 * as needed
 *
 ******************************************************************************************/


package com.group.project.repository;

import com.group.project.entity.EmployeeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRecordRepository extends JpaRepository<EmployeeRecord, Integer> {

    // Creates query and returns EmployeeRecord "row" by selecting on username
    EmployeeRecord findByUsername(String username);
}
