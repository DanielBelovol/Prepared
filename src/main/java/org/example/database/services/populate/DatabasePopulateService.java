package org.example.database.services.populate;

import lombok.SneakyThrows;
import org.example.database.Database;
import org.example.database.DatabaseUtility;
import org.example.database.services.populate.models.Project;
import org.example.database.services.populate.models.ProjectWorker;
import org.example.database.services.populate.models.Client;
import org.example.database.services.populate.models.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatabasePopulateService {
    @SneakyThrows
    public static void main(String[] args) {

        PreparedStatement clientStatement = null;
        PreparedStatement workerStatement = null;
        PreparedStatement projectStatement = null;
        PreparedStatement projectWorkerStatement = null;

        DatabaseUtility databaseUtility = new DatabaseUtility();
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
        clientStatement = DatabaseUtility.initializeStatementForClient();
        workerStatement = DatabaseUtility.initializeStatementForWorker();
        projectStatement = DatabaseUtility.initializeStatementForProject();
        projectWorkerStatement = DatabaseUtility.initializeStatementForProjectWorker();

        for (Client client : clients) {
            addBatchForClientData(client, clientStatement);
        }
        clientStatement.executeBatch();
        clientStatement.clearBatch();

        for (Worker worker : workers) {
            addBatchForWorkerData(worker, workerStatement);
        }
        workerStatement.executeBatch();
        workerStatement.clearBatch();

        for (Project project : projects) {
            addBatchForProjectData(project, projectStatement);
        }
        projectStatement.executeBatch();
        projectStatement.clearBatch();

        for (ProjectWorker projectWorker : projectWorkers) {
            addBatchForProjectWorkerData(projectWorker, projectWorkerStatement);
        }
        projectWorkerStatement.executeBatch();
        projectWorkerStatement.clearBatch();
    }

    public static void addBatchForClientData(Client client, PreparedStatement clientStatement) {
        try {
            clientStatement.setInt(1, client.getId());
            clientStatement.setString(2, client.getName());
            clientStatement.addBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addBatchForWorkerData(Worker worker, PreparedStatement workerStatement) {
        try {
            workerStatement.setInt(1, worker.getId());
            workerStatement.setString(2, worker.getName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(worker.getBirthday());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            workerStatement.setDate(3, sqlDate);
            workerStatement.setString(4, worker.getLevel());
            workerStatement.setInt(5, worker.getSalary());
            workerStatement.addBatch();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }


    public static void addBatchForProjectData(Project project, PreparedStatement projectStatement) {
        try {
            projectStatement.setInt(1, project.getId());
            projectStatement.setInt(2, project.getClientId());
            // Преобразование строки даты в объект Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilStartDate = sdf.parse(project.getStartDate());
            java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
            projectStatement.setDate(3, sqlStartDate);
            // Преобразование строки даты в объект Date
            java.util.Date utilFinishDate = sdf.parse(project.getFinishData());
            java.sql.Date sqlFinishDate = new java.sql.Date(utilFinishDate.getTime());
            projectStatement.setDate(4, sqlFinishDate);
            projectStatement.addBatch();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void addBatchForProjectWorkerData(ProjectWorker projectWorker, PreparedStatement projectWorkerStatement) {
        try {
            projectWorkerStatement.setInt(1, projectWorker.getProjectId());
            projectWorkerStatement.setInt(2, projectWorker.getWorkerId());
            projectWorkerStatement.addBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}