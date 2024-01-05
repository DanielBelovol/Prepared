package org.example.dao;

import org.example.Database;
import org.example.models.MaxDurationProject;
import org.example.models.ProjectCost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectCostD {
    private static final String FIND_PROJECT_COST_QUERY_FILE_PATH =
            "src/main/resources/sql/print_project_prices.sql";

    public List<ProjectCost> findProjectCost() {
        List<ProjectCost> result = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("print_project_prices.sql");
             InputStreamReader reader = new InputStreamReader(inputStream);
             Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            boolean hasResultSet = statement.execute(new BufferedReader(reader).lines().collect(Collectors.joining("\n")));

            if (hasResultSet) {
                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        ProjectCost worker = mapResultSetToProjectCost(resultSet);
                        result.add(worker);
                    }
                }
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private ProjectCost mapResultSetToProjectCost(ResultSet resultSet) throws SQLException {
        ProjectCost projectCost = new ProjectCost();

        projectCost.setProjectID(resultSet.getInt("Project_ID"));
        projectCost.setClientName(resultSet.getString("Client_Name"));
        projectCost.setTotalCost(resultSet.getInt("Total_Cost"));

        return projectCost;
    }
}
