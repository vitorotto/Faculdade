package test;

import config.DatabaseConfig;
import dao.AccountDAO;
import database.Database;
import model.Account;

import java.sql.SQLException;

public class FindAccountByTermTest {
    public static void executeFindAccountByTermTest(String term) {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        Database database = new Database(databaseConfig);
        try {
            AccountDAO accountDAO = new AccountDAO(database);
            IO.println(!accountDAO.findAccountByTerm(term).isEmpty() ? "FindAccountByTerm OK" : "FindAccountByTerm ERROR");
        } catch (SQLException e) {
            IO.println("Connection error: \n" + e);
        }
    }
}
