package com.group.project.service;

import com.group.project.dto.UsernameAction;
import com.group.project.persistence.ConnectionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RestService {
    private static final Logger logger = LoggerFactory.getLogger(RestService.class);

    @PostMapping(path = "/clockIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody UsernameAction usernameAction) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd/HH/mm").format(new Date());
        String query = MessageFormat.format("INSERT INTO employeerecords (username, timestamp, action) VALUES ({0}, {1}, {2})",
                "'" + usernameAction.getUsername() + "'", "'" + timeStamp  + "'", "'" + usernameAction.getAction() + "'");
        try {
            Connection connection = ConnectionHandler.startConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
            ConnectionHandler.closeConnection(connection);
            return ResponseEntity.status(HttpStatus.OK).body(usernameAction.getAction() + " Successful");
        } catch (Exception e) {
            String errorMessage = "An error occurred when inserting into the database: ";
            logger.error(errorMessage + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage + e);
        }
    }
}
