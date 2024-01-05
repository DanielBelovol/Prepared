package org.example.dao;

import org.example.Database;
import org.example.models.MaxDurationProject;
import org.example.models.TopClientProjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TopClientProjectsD {
    public List<TopClientProjects> findTopClientProjects() {
        List<TopClientProjects> result = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("find_max_projects_client.sql");
             InputStreamReader reader = new InputStreamReader(inputStream);
             Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            boolean hasResultSet = statement.execute(new BufferedReader(reader).lines().collect(Collectors.joining("\n")));

            if (hasResultSet) {
                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        TopClientProjects worker = mapResultSetToTopClientProjects(resultSet);
                        result.add(worker);
                    }
                }
            }

        } catch (IOException | SQLException e) {
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
