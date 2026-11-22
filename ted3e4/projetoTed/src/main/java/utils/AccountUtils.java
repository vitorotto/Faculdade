package utils;

import config.DatabaseConfig;
import dao.AccountDAO;
import dao.CheckingAccountDAO;
import dao.SavingsAccountDAO;
import database.Database;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Account;
import model.AccountType;

import java.sql.SQLException;

public class AccountUtils {
    public static Account loadAccount(String number) throws SQLException {
        return new AccountDAO(
                new Database(new DatabaseConfig())
        ).findAccountByNumber(number);
    }

    public static void fillSpecificData(
            Account account,
            Label specificLabel,
            TextField specificField
    ) throws SQLException {
        fillSpecificData(account, specificLabel, specificField, false);
    }

    public static void fillSpecificData(
            Account account,
            Label specificLabel,
            TextField specificField,
            boolean editable
    ) throws SQLException {
        Database database = new Database(new DatabaseConfig());
        if (account.getAccountType() == AccountType.CHECKING) {
            var checking = new CheckingAccountDAO(database)
                    .findCheckingAccountById(account.getAccountId());
            specificLabel.setText("Limite:");
            specificField.setText(checking == null
                    ? ""
                    : checking.getLimit().toPlainString());
        } else {
            var savings = new SavingsAccountDAO(database)
                    .findSavingsAccountById(account.getAccountId());
            specificLabel.setText("Rendimento:");
            specificField.setText(savings == null
                    ? ""
                    : savings.getYieldRate().toPlainString());
        }
        specificField.setEditable(editable);
        specificField.setDisable(false);
    }

    public static void closeWindows(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
