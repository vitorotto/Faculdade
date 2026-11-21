package test;

import config.DatabaseConfig;
import dao.AccountDAO;
import database.Database;
import model.Account;

import java.math.BigDecimal;
import java.sql.SQLException;

public class UpdateAccountTest {
    static void executeUpdateTest(Account account, Integer accountId) {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        Database database = new Database(databaseConfig);
        try {
            account.setAccountId(accountId);
            AccountDAO accountDAO = new AccountDAO(database);
            IO.println(accountDAO.updateAccount(account) ? "Update OK" : "Update ERROR");
        } catch (SQLException e) {
            IO.println("Connection error: \n" + e);
        }
    }
}
