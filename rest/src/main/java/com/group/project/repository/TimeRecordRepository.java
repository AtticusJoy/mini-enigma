package com.group.project.repository;

import com.group.project.entity.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRecordRepository extends JpaRepository<TimeRecord, Integer> {
}
