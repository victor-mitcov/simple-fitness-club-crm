package com.club.db;

import java.sql.*;

public class DatabaseManager {
    private Connection connection = null;

    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/fitness_club?" + "user=root&password=root");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
