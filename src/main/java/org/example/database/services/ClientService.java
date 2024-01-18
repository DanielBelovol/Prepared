package org.example.database.services;

import org.example.database.Database;
import org.example.dto.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    public long create(String name) {
        long generatedId = -1;
        if(name.length()>2) {
            try (Connection conn = Database.getInstance().getConnection()) {

                PreparedStatement statement = conn.prepareStatement("INSERT INTO client (Name) VALUES (?) RETURNING id;");
                statement.setString(1, name);


                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    generatedId = resultSet.getLong("id");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return generatedId;
    }

    public String getById(long id) {
        String nameResult = null;
        if(id>=0) {
            try (Connection conn = Database.getInstance().getConnection()) {

                PreparedStatement statement = conn.prepareStatement("SELECT name FROM client WHERE id = ?");
                statement.setLong(1, id);

                boolean statementResult = statement.execute();
                if (statementResult) {
                    return statement.getResultSet().getString("name");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nameResult;
    }

    public void setName(long id, String name) {
        if (id >= 0 && name.length() > 2) {
            try (Connection conn = Database.getInstance().getConnection()) {

                // Check if the user with the given ID exists
                if (userExists(conn, id)) {
                    // If the user exists, update the name
                    updateUserName(conn, id, name);
                } else {
                    // If the user does not exist, create a new user
                    createUser(conn, id, name);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteById(long id) {
        if(id>=0) {
            try (Connection conn = Database.getInstance().getConnection()) {

                PreparedStatement statement = conn.prepareStatement("DELETE FROM client WHERE ID = ?");
                statement.setLong(1, id);

                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Client> listAll() {
        List<Client> allClients = new ArrayList<>();
        try (Connection conn = Database.getInstance().getConnection()) {

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client");

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                allClients.add(new Client(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allClients;
    }
    private boolean userExists(Connection conn, long id) throws SQLException {
        // Check if the user with the given ID exists
        try (PreparedStatement checkStatement = conn.prepareStatement("SELECT 1 FROM client WHERE id = ?")) {
            checkStatement.setLong(1, id);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private void updateUserName(Connection conn, long id, String name) throws SQLException {
        // Update the name of the existing user
        try (PreparedStatement updateStatement = conn.prepareStatement("UPDATE client SET name = ? WHERE id = ?")) {
            updateStatement.setString(1, name);
            updateStatement.setLong(2, id);
            updateStatement.executeUpdate();
        }
    }

    private void createUser(Connection conn, long id, String name) throws SQLException {
        // Create a new user with the specified ID and name
        try (PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO client (id, name) VALUES (?, ?)")) {
            insertStatement.setLong(1, id);
            insertStatement.setString(2, name);
            insertStatement.executeUpdate();
        }
    }
}
