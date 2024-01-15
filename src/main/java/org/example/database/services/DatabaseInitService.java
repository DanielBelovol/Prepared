package org.example.database.services;

import org.example.database.Database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            executeScript(statement, "init_db.sql");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // TEST
//        DatabaseQueryService service = new DatabaseQueryService();
//        System.out.println(service.findHighestSalaryWorker());
//        System.out.println(service.findMaxDurationProject());
//        System.out.println(service.findOldestYoungestWorker());
//        System.out.println(service.findProjectCost());
//        System.out.println(service.findTopClientProjects());
    }
    private static void executeScript(Statement statement, String scriptFileName) {
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(scriptFileName);
            if (inputStream != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder script = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    script.append(line).append("\n");
                }
                statement.executeUpdate(script.toString());
            } else {
                System.err.println("Script file not found: " + scriptFileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
