package org.example.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseUtility {
    public static PreparedStatement initializeStatementForClient() {
        try {
            Connection conn = Database.getInstance().getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO client (ID, Name) VALUES (?,?)");
            return statement;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static PreparedStatement initializeStatementForWorker() {
        try {
            Connection conn = Database.getInstance().getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO worker (ID, Name, Birthday, Level, Salary) VALUES (?,?,?,?,?)");
            return statement;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static PreparedStatement initializeStatementForProject() {
        try {
            Connection conn = Database.getInstance().getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO project (ID, CLIENT_ID, START_DATE, FINISH_DATE) VALUES (?,?,?,?)");
            return statement;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static PreparedStatement initializeStatementForProjectWorker() {
        try {
            Connection conn = Database.getInstance().getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES (?,?)");
            return statement;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
