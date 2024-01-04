package org.example.dao;

import org.example.Database;
import org.example.models.ProjectCost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectCostD {
    private static final String FIND_PROJECT_COST_QUERY_FILE_PATH =
            "src/main/resources/sql/print_project_prices.sql";

    public List<ProjectCost> findProjectCost() {
        List<ProjectCost> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection()) {
                        try (PreparedStatement statement = connection.prepareStatement("RUNSCRIPT FROM 'print_project_prices.sql'");
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    ProjectCost projectCost = mapResultSetToProjectCost(resultSet);
                    result.add(projectCost);
                }
            }
        } catch (SQLException e) {
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
