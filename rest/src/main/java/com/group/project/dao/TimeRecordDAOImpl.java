package com.group.project.dao;

import com.group.project.dto.User;
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
    public List<TimeRecord> getTimeRecords(User user) {

        // get the current Hibernate session
        // Session currentSession = sessionFactory.getCurrentSession()

        // Create query for either Manager or Employee based on user.getRole()
        // Query<TimeRecord> query = currentSession.createQuery("SQL or HQL query here", TimeRecord.class);
        // Make sure query is written to get TimeActionRecord.time_action_record_id, EmployeesRecord.employee_username, clock_in, clock_out
        // If a manager, order by employee_username then clock_in, then clock_out (last one might be overkill)
        // If employee, order by clock_in then clock_out

        // execute query and get result list
        // List<TimeRecord> timeRecords = query.getResultList();

        return null;
    }

    @Override
    public String saveClockIn(String username) {

        // Get employee_record_id from the username
        // Use this to create a new TimeRecord object and save to database.
        // id and clock in time should be generated from the database
        // Ensure clock out time is initially null

        // return success String message;

        return null;
    }

    @Override
    public String saveClockOut(String username) {

        // Get employee_record_id from the username
        // Use this to look up the newest TimeActionRecord (greatest id# for that user most likely)
        // If clock out time != null, return String error message
        // Else use current time for clock out

        // Consider database constraint to make sure clockout time is after clock in time (mostly for edits in future)

        return null;
    }
}
