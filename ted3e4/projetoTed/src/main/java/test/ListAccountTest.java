package test;

import config.DatabaseConfig;
import dao.AccountDAO;
import database.Database;

import java.sql.SQLException;

public class ListAccountTest {
    static void executeAccountListTest() {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        Database database = new Database(databaseConfig);
        try {
            AccountDAO accountDAO = new AccountDAO(database);
            IO.println(!accountDAO.listAccount().isEmpty() ? "List OK" : "List ERROR");
        } catch (SQLException e) {
            IO.println("Connection error: \n" + e);
        }
    }
}
