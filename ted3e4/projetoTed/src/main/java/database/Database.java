package database;

import config.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final String server;
    private final String port;
    private final String database;
    private final String user;
    private final String password;

    public Database(DatabaseConfig config) {
        server = config.getDatabaseConfig("bd.server");
        port = config.getDatabaseConfig("bd.port");
        database = config.getDatabaseConfig("bd.database");
        user = config.getDatabaseConfig("bd.user");
        password = config.getDatabaseConfig("bd.password");
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://" + server + ":" + port + "/" + database, user, password);
    }
}
