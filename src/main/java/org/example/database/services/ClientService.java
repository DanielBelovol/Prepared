package org.example.database.services;

import org.example.database.Database;
import org.example.dto.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {
    public long create(String name) {
        long generatedId = -1;

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

        return generatedId;
    }

    public String getById(long id) {
        String nameResult = null;
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
        return nameResult;
    }

    public void setName(long id, String name) {

        try (Connection conn = Database.getInstance().getConnection()) {

            PreparedStatement statement = conn.prepareStatement("UPDATE client SET name = ? WHERE id = ?");
            statement.setString(1, name);
            statement.setLong(2, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(long id) {
        try (Connection conn = Database.getInstance().getConnection()) {

            PreparedStatement statement = conn.prepareStatement("DELETE FROM client WHERE ID = ?");
            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
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
}
