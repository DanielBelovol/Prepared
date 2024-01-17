package org.example.database;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    @Getter
    private static final String URL = "jdbc:h2:~/test";
    @Getter
    private static final String USERNAME = "sa";
    @Getter
    private static final String PASSWORD = "";

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
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
