package test;

import config.DatabaseConfig;
import dao.AccountDAO;
import database.Database;
import model.Account;

import java.sql.SQLException;

public class InsertAccountTest {
    static void executeInsertTest(Account account) {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        Database database = new Database(databaseConfig);
        try {
            AccountDAO accountDAO = new AccountDAO(database);
            account = accountDAO.insertAccount(account);
            IO.println(account.getAccountId() != null ? "Insert OK" : "Insert ERROR");
        } catch (SQLException e) {
            IO.println("Connection error: \n" + e);
        }
    }
}
