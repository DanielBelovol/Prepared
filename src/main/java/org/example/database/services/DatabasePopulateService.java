package org.example.database.services;

import lombok.SneakyThrows;
import org.example.database.Database;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationVersion;


public class DatabasePopulateService {
    @SneakyThrows
    public static void main(String[] args) {
        migrate();
    }
    public static void migrate(){
        Flyway flyway = Flyway.configure().dataSource(Database.getURL(),Database.getUSERNAME(),Database.getPASSWORD()).baselineOnMigrate(true).locations("classpath:db.migration").target(MigrationVersion.fromVersion("2")).load();
        flyway.migrate();
    }
}