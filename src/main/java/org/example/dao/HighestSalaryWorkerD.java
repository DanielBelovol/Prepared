package org.example.dao;

import org.example.Database;
import org.example.models.HighestSalaryWorker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HighestSalaryWorkerD {

    public List<HighestSalaryWorker> findHighestSalaryWorker() {
        List<HighestSalaryWorker> result = new ArrayList<>();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("find_max_salary_worker.sql");
             InputStreamReader reader = new InputStreamReader(inputStream);
             Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            boolean hasResultSet = statement.execute(new BufferedReader(reader).lines().collect(Collectors.joining("\n")));

            if (hasResultSet) {
                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        HighestSalaryWorker worker = mapResultSetToHighestSalaryWorker(resultSet);
                        result.add(worker);
                    }
                }
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private HighestSalaryWorker mapResultSetToHighestSalaryWorker(ResultSet resultSet) throws SQLException {
        HighestSalaryWorker highestSalaryWorker = new HighestSalaryWorker();

        highestSalaryWorker.setId(resultSet.getInt("id"));
        highestSalaryWorker.setName(resultSet.getString("name"));
        highestSalaryWorker.setBirthday(resultSet.getDate("birthday"));
        highestSalaryWorker.setLevel(resultSet.getString("level"));
        highestSalaryWorker.setSalary(resultSet.getInt("salary"));

        return highestSalaryWorker;
    }
}
