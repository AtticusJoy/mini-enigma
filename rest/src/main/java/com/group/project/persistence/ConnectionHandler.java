package com.group.project.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandler {

    public static Connection startConnection(){
        Connection connection = null;
        String url       = "jdbc:mysql://localhost:3306/mysqljdbc";
        String user      = "root";
        String password  = "root_password";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
