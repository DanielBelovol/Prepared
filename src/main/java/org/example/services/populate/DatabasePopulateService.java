package org.example.services.populate;

import org.example.Database;
import org.example.services.populate.models.Client;
import org.example.services.populate.models.Project;
import org.example.services.populate.models.ProjectWorker;
import org.example.services.populate.models.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabasePopulateService {
    public static void main(String[] args) {
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
        insertClientData(clients);
        insertWorkerData(workers);
        insertProjectData(projects);
        insertProjectWorkerData(projectWorkers);

    }

    public static void insertClientData(Client[] clients) {
        try (Connection con = Database.getInstance().getConnection()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO client (ID, Name) VALUES (?,?)");

            for (Client client : clients) {
                statement.setInt(1, client.getId());
                statement.setString(2, client.getName());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertWorkerData(Worker[] workers) {
        try (Connection con = Database.getInstance().getConnection()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO worker (ID, Name, Birthday, Level, Salary) VALUES (?,?,?,?,?)");

            for (Worker worker : workers) {
                statement.setInt(1, worker.getId());
                statement.setString(2, worker.getName());
                statement.setString(3, worker.getBirthday());
                statement.setString(4, worker.getLevel());
                statement.setInt(5, worker.getSalary());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertProjectData(Project[] projects) {
        try (Connection con = Database.getInstance().getConnection()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO project (ID, CLIENT_ID, START_DATE, FINISH_DATE) VALUES (?,?,?,?)");

            for (Project project : projects) {
                statement.setInt(1, project.getId());
                statement.setInt(2, project.getClientId());
                statement.setString(3, project.getStartDate());
                statement.setString(4, project.getFinishData());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertProjectWorkerData(ProjectWorker[] projectWorkers) {
        try (Connection con = Database.getInstance().getConnection()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES (?,?)");

            for (ProjectWorker projectWorker : projectWorkers) {
                statement.setInt(1, projectWorker.getProjectId());
                statement.setInt(2, projectWorker.getWorkerId());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

