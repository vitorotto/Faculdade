package test;

import config.DatabaseConfig;
import dao.AccountDAO;
import database.Database;
import model.Account;

import java.math.BigDecimal;
import java.sql.SQLException;

public class DeleteAccountTest {
    static void executeDeleteTest(Account account, Integer accountId) {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        Database database = new Database(databaseConfig);
        try {
            account.setAccountId(accountId);
            AccountDAO accountDAO = new AccountDAO(database);
            IO.println(accountDAO.deleteAccount(account) ? "Delete OK" : "Delete ERROR");
        } catch (SQLException e) {
            IO.println("Connection error: \n" + e);
        }
    }
}
