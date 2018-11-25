package com.group.project.dao;

import com.group.project.entity.TimeRecord;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeRecordDAOImpl implements TimeRecordDAO {

    // Fix config issue to be able to Autowire/DI SessionFactory
    //@Autowired
    //private SessionFactory sessionFactory;

    @Override
    public List<TimeRecord> getEmployeeTimeRecords(int userId) {

        // get the current Hibernate session
        // Session currentSession = sessionFactory.getCurrentSession()

        // Query<TimeRecord> query = currentSession.createQuery("SQL or HQL query here", TimeRecord.class);
        // Order by clock_in time with newest first

        // executes query and gets result list
        // List<TimeRecord> timeRecords = query.getResultList();
        // return timeRecords;

        return null;
    }

    @Override
    public List<TimeRecord> getManagerTimeRecords(int userId) {

        // get the current Hibernate session
        // Session currentSession = sessionFactory.getCurrentSession()

        // Query<TimeRecord> query = currentSession.createQuery("SQL or HQL query here", TimeRecord.class);
        // If a manager, order by employee_username then by clock_in with newest entries first

        // executes query and gets result list
        // List<TimeRecord> timeRecords = query.getResultList();
        // return timeRecords;

        return null;
    }

    @Override
    public int getUserId(String username) {

        // return existing user ID or create new user and return new id

        return 0;
    }

    @Override
    public String saveClockIn(String username) {

        // Get employee_record_id from the username
        // isValidClockIn(username) should be done here or on service layer before call
        // if valid, create a new TimeRecord object and save to database
        // timerecord id  and clock in time should be generated from the database
        // Clock out time is initially null

        // return success/failure String message;

        return null;
    }

    @Override
    public String saveClockOut(String username) {

        // Get employee_record_id from the username
        // Use this to look up the newest TimeActionRecord (greatest id# for that user most likely)
        // isValidClockOut(username) should be done here or on service layer before call
        // if valid, use current time for clockout

        // Consider database constraint to make sure clockout time is after clock in time (mostly useful for edits in future)

        // Calculate hours worked and save to database field.
        // What format is best here as we'll ultimately need UI to display (e.g. 8.50 hrs is 8 and a half hours)

        return null;
    }

    @Override
    public boolean isValidClockIn(String username) {
        // was last clock in > X hours (use case missed punch out)
        // or does last clock in have a clock out time (use case multiple punch in/out per day)

        return false;
    }

    @Override
    public boolean isValidClockOut(String username) {
        // does newest TimeActionRecord have no clockout info already and does it occur within X hours

        return false;
    }
}
