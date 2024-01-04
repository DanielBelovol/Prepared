package org.example.dao;

import org.example.Database;
import org.example.models.OldestYoungestWorker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OldestYoungestWorkerD {
    public List<OldestYoungestWorker> findOldestYoungestWorker() {
        List<OldestYoungestWorker> result = new ArrayList<>();

        try (Connection connection = Database.getInstance().getConnection()) {
                       try (PreparedStatement statement = connection.prepareStatement("RUNSCRIPT FROM 'find_youngest_eldest_workers.sql'");
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    OldestYoungestWorker worker = mapResultSetToOldestYoungestWorker(resultSet);
                    result.add(worker);
                }
            }
        } catch (SQLException e) {
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
