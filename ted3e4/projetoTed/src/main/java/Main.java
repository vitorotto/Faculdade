import database.Database;
import config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

void main() {
    DatabaseConfig databaseConfig = new DatabaseConfig();
    Database bank = new Database(databaseConfig);
    try {
        Connection connection = bank.connect();
        IO.println("Connected");
    } catch (SQLException e) {
        IO.println(e);
    }
}
