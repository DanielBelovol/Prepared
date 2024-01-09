package org.example.services;

import org.example.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("RUNSCRIPT FROM 'classpath:init_db.sql'");
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
}
