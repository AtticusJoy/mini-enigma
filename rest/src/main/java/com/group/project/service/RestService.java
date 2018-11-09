package com.group.project.service;

import com.group.project.dto.UsernameAction;
import com.group.project.persistence.ConnectionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RestService {
    private static final Logger logger = LoggerFactory.getLogger(RestService.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public RestService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping(path = "/clockInOut", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody UsernameAction usernameAction) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd/HH/mm").format(new Date());
        try {
            jdbcTemplate.update("INSERT INTO recordsdb.Employees_Record (Username, Timestamp, Action) VALUES (?, ?, ?)", usernameAction.getUsername(), timeStamp, usernameAction.getAction());
            return ResponseEntity.status(HttpStatus.OK).body(usernameAction.getAction() + " Successful");
        } catch (Exception e) {
            String errorMessage = "An error occurred when inserting into the database: ";
            logger.error(errorMessage + e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage + e);
        }
    }
}
