package org.example.database.services;

import org.example.database.Database;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;
import org.flywaydb.core.extensibility.MigrationType;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
//
        Flyway flyway = Flyway.configure().dataSource(Database.getURL(),Database.getUSERNAME(),Database.getPASSWORD())
                .baselineOnMigrate(true)
                .locations("classpath:db.migration")
                .target(MigrationVersion.fromVersion("1"))
                .load();
        flyway.migrate();

        // TEST
//        DatabaseQueryService service = new DatabaseQueryService();
//        System.out.println(service.findHighestSalaryWorker());
//        System.out.println(service.findMaxDurationProject());
//        System.out.println(service.findOldestYoungestWorker());
//        System.out.println(service.findProjectCost());
//        System.out.println(service.findTopClientProjects());
    }
}
