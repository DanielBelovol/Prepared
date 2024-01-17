package org.example.database.services.populate;

import lombok.SneakyThrows;
import org.example.database.Database;
import org.example.database.DatabaseUtility;
import org.example.database.services.populate.models.Client;
import org.example.database.services.populate.models.Project;
import org.example.database.services.populate.models.ProjectWorker;
import org.example.database.services.populate.models.Worker;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    Flyway flyway;

    @SneakyThrows
    public static void main(String[] args) {

        DatabaseUtility databaseUtility = new DatabaseUtility();
        Database database = Database.getInstance();
        Client[] clients = {
                new Client(01, "Danya"),
                new Client(02, "Vanya"),
                new Client(03, "Vika"),
                new Client(04, "Molfar"),
                new Client(05, "Kasper")
        };
        Worker[] workers = {
                new Worker(1, "Bob Strange", "1990-02-01", "Junior", 800),
                new Worker(2, "Kim Korty", "1985-05-15", "Senior", 3000),
                new Worker(3, "Stan Frozen", "1983-06-11", "Senior", 3100),
                new Worker(4, "Alfred Montery", "1994-09-21", "Senior", 2900),
                new Worker(5, "Bill Spaw", "1981-02-28", "Middle", 1200),
                new Worker(6, "Gregor McBoo", "1985-03-10", "Middle", 1500),
                new Worker(7, "Suren Vasardyan", "1991-11-03", "Junior", 900),
                new Worker(8, "Li Mo", "1995-07-19", "Trainee", 700),
                new Worker(9, "Dunkan Bread", "1989-07-17", "Junior", 850),
                new Worker(10, "Dan", "1992-09-11", "Trainee", 1001)
        };
        Project[] projects = {
                new Project(1, 1, "2023-01-01", "2023-12-30"),
                new Project(2, 2, "2023-02-01", "2023-11-30"),
                new Project(3, 3, "2023-01-01", "2023-10-31"),
                new Project(4, 4, "2023-03-01", "2023-11-30"),
                new Project(5, 5, "2023-02-01", "2023-09-30"),
                new Project(6, 2, "2023-04-01", "2023-11-30"),
                new Project(7, 1, "2023-06-01", "2023-09-30"),
                new Project(8, 5, "2023-03-01", "2023-11-30"),
                new Project(9, 3, "2023-06-01", "2023-11-30"),
                new Project(10, 4, "2023-05-01", "2023-10-31")
        };
        ProjectWorker[] projectWorkers = {
                new ProjectWorker(1, 1),
                new ProjectWorker(2, 3),
                new ProjectWorker(3, 4),
                new ProjectWorker(4, 5),
                new ProjectWorker(5, 6),
                new ProjectWorker(6, 2),
                new ProjectWorker(7, 3),
                new ProjectWorker(8, 2),
                new ProjectWorker(9, 3),
                new ProjectWorker(10, 5),
                new ProjectWorker(4, 1),
                new ProjectWorker(5, 5),
                new ProjectWorker(6, 8),
                new ProjectWorker(7, 10),
                new ProjectWorker(1, 10),
                new ProjectWorker(2, 8),
                new ProjectWorker(3, 7),
                new ProjectWorker(4, 7),
                new ProjectWorker(5, 9)
        };

        try (Connection connection = database.getConnection()) {
            for (Client client : clients) {
                addDataForClient(client, connection);
            }

            for (Worker worker : workers) {
                addDataForWorker(worker, connection);
            }

            for (Project project : projects) {
                addDataForProject(project, connection);
            }

            for (ProjectWorker projectWorker : projectWorkers) {
                addDataForProjectWorker(projectWorker, connection);
            }
        }
    }

    public static void addDataForClient(Client client, Connection connection) {
        String query = "INSERT INTO client(id, name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDataForWorker(Worker worker, Connection connection) {
        String query = "INSERT INTO worker(id, name, birthday, level, salary) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, worker.getId());
            preparedStatement.setString(2, worker.getName());
            preparedStatement.setString(3, worker.getBirthday());
            preparedStatement.setString(4, worker.getLevel());
            preparedStatement.setInt(5, worker.getSalary());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDataForProject(Project project, Connection connection) {
        String query = "INSERT INTO project(id, client_id, start_date, finish_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, project.getId());
            preparedStatement.setInt(2, project.getClientId());
            preparedStatement.setString(3, project.getStartDate());
            preparedStatement.setString(4, project.getFinishData());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addDataForProjectWorker(ProjectWorker projectWorker, Connection connection) {
        String query = "INSERT INTO project_worker(project_id, worker_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, projectWorker.getProjectId());
            preparedStatement.setInt(2, projectWorker.getWorkerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
