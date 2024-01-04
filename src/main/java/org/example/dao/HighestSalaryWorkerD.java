package org.example.dao;

import org.example.Database;
import org.example.models.HighestSalaryWorker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HighestSalaryWorkerD {

    public List<HighestSalaryWorker> findHighestSalaryWorker() {
        List<HighestSalaryWorker> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement("RUNSCRIPT FROM 'find_max_salary_worker.sql'");
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    HighestSalaryWorker worker = mapResultSetToHighestSalaryWorker(resultSet);
                    result.add(worker);
                }
            }
        } catch (SQLException e) {
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
