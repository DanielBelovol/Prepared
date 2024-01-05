package org.example.dao;

import org.example.Database;
import org.example.models.MaxDurationProject;
import org.example.models.OldestYoungestWorker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OldestYoungestWorkerD {
    public List<OldestYoungestWorker> findOldestYoungestWorker() {
        List<OldestYoungestWorker> result = new ArrayList<>();


        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("find_youngest_eldest_workers.sql");
             InputStreamReader reader = new InputStreamReader(inputStream);
             Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement()) {

            boolean hasResultSet = statement.execute(new BufferedReader(reader).lines().collect(Collectors.joining("\n")));

            if (hasResultSet) {
                try (ResultSet resultSet = statement.getResultSet()) {
                    while (resultSet.next()) {
                        OldestYoungestWorker worker = mapResultSetToOldestYoungestWorker(resultSet);
                        result.add(worker);
                    }
                }
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private OldestYoungestWorker mapResultSetToOldestYoungestWorker(ResultSet resultSet) throws SQLException {
        OldestYoungestWorker oldestYoungestWorker = new OldestYoungestWorker();

        oldestYoungestWorker.setType(resultSet.getString("type"));
        oldestYoungestWorker.setName(resultSet.getString("name"));
        oldestYoungestWorker.setBirthday(resultSet.getDate("birthday"));

        return oldestYoungestWorker;
    }
}
