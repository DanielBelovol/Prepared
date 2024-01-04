package org.example.dao;

import org.example.Database;
import org.example.models.TopClientProjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopClientProjectsD {
    public List<TopClientProjects> findTopClientProjects() {
        List<TopClientProjects> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection()) {
                       try (PreparedStatement statement = connection.prepareStatement("RUNSCRIPT FROM 'find_max_projects_client.sql'");
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    TopClientProjects topClientProjects = mapResultSetToTopClientProjects(resultSet);
                    result.add(topClientProjects);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private TopClientProjects mapResultSetToTopClientProjects(ResultSet resultSet) throws SQLException {
        TopClientProjects topClientProjects = new TopClientProjects();

        topClientProjects.setName(resultSet.getString("NAME"));
        topClientProjects.setProjectCount(resultSet.getInt("ProjectCount"));

        return topClientProjects;
    }
}
