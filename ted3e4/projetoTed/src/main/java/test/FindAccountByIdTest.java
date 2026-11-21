package test;

import config.DatabaseConfig;
import dao.AccountDAO;
import database.Database;
import model.Account;

import java.sql.SQLException;

public class FindAccountByIdTest {
    public static void executeFindAccountByIdTest(Integer id) {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        Database database = new Database(databaseConfig);
        try {
            AccountDAO accountDAO = new AccountDAO(database);
            Account account = accountDAO.findAccountById(id);
            IO.println(account != null ? "FindAccountById OK" : "FindAccountById ERROR");
        } catch (SQLException e) {
            IO.println("Connection error: \n" + e);
        }
    }
}
