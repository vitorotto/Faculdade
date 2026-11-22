package dao;

import database.Database;
import model.Account;
import model.AccountType;
import model.Operation;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO {
    private final Database database;

    public OperationDAO(Database database) {
        this.database = database;
    }

    public boolean deposit(String number, BigDecimal value) throws SQLException {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        AccountDAO accountDAO = new AccountDAO(database);
        Account account = accountDAO.findAccountByNumber(number);
        if (account == null) {
            return false;
        }
        BigDecimal newBalance = account.getBalance().add(value);
        if (!accountDAO.updateBalance(number, newBalance)) {
            return false;
        }
        recordOperation(account.getAccountId(), "DEPOSITO", value);
        return true;
    }

    public boolean withdraw(String number, BigDecimal value) throws SQLException {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        AccountDAO accountDAO = new AccountDAO(database);
        Account account = accountDAO.findAccountByNumber(number);
        if (account == null) {
            return false;
        }

        BigDecimal available = account.getBalance();
        if (account.getAccountType() == AccountType.CHECKING) {
            CheckingAccountDAO checkingDAO = new CheckingAccountDAO(database);
            model.CheckingAccountModel checking =
                    checkingDAO.findCheckingAccountById(account.getAccountId());
            if (checking != null) {
                available = available.add(checking.getLimit());
            }
        }

        if (value.compareTo(available) > 0) {
            return false;
        }
        BigDecimal newBalance = account.getBalance().subtract(value);
        if (!accountDAO.updateBalance(number, newBalance)) {
            return false;
        }
        recordOperation(account.getAccountId(), "SAQUE", value);
        return true;
    }

    public List<Operation> listOperations(String number) throws SQLException {
        AccountDAO accountDAO = new AccountDAO(database);
        Account account = accountDAO.findAccountByNumber(number);
        if (account == null) {
            return List.of();
        }

        List<Operation> operations = new ArrayList<>();
        try (Connection connection = database.connect()) {
            PreparedStatement request = connection.prepareStatement(
                    "select operacoes_id, conta_id, operacao, valor, data_operacao " +
                    "from tb_operacoes where conta_id=? order by data_operacao desc"
            );
            request.setInt(1, account.getAccountId());
            ResultSet result = request.executeQuery();
            while (result.next()) {
                Timestamp timestamp = result.getTimestamp("data_operacao");
                operations.add(new Operation(
                        result.getInt("operacoes_id"),
                        result.getInt("conta_id"),
                        result.getString("operacao"),
                        result.getBigDecimal("valor"),
                        timestamp == null ? null : timestamp.toLocalDateTime()
                ));
            }
        }
        return operations;
    }

    private void recordOperation(Integer accountId, String operation,
                                  BigDecimal value) throws SQLException {
        try (Connection connection = database.connect()) {
            PreparedStatement request = connection.prepareStatement(
                    "insert into tb_operacoes(conta_id, operacao, valor) values(?, ?, ?)"
            );
            request.setInt(1, accountId);
            request.setString(2, operation);
            request.setBigDecimal(3, value);
            request.executeUpdate();
        }
    }
}
