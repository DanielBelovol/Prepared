package org.example.dao;

import org.example.Database;
import org.example.models.HighestSalaryWorker;
import org.example.models.MaxDurationProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MaxDurationProjectD {
    public List<MaxDurationProject> findMaxDurationProject() {
        List<MaxDurationProject> result = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("find_longest_projects.sql");
             InputStreamReader reader = new InputStreamReader(inputStream);
             Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            boolean hasResultSet = statement.execute(new BufferedReader(reader).lines().collect(Collectors.joining("\n")));

            if (hasResultSet) {
                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        MaxDurationProject worker = mapResultSetToMaxDurationProject(resultSet);
                        result.add(worker);
                    }
                }
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private MaxDurationProject mapResultSetToMaxDurationProject(ResultSet resultSet) throws SQLException {
        MaxDurationProject maxDurationProject = new MaxDurationProject();

        if (resultSet.next()) {
            maxDurationProject.setName(resultSet.getString("NAME"));
            maxDurationProject.setMonthCount(resultSet.getInt("MONTH_COUNT"));
        }

        return maxDurationProject;
    }

}
