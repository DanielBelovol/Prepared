package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private String JDBC_URL = "jdbc:postgresql://localhost:5432/mydatabase";  // Замените "mydatabase" на имя вашей базы данных
    private String USERNAME = "admin";
    private String PASSWORD = "88888888";
    private static Database instance;

    private Database(){

    }
    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
