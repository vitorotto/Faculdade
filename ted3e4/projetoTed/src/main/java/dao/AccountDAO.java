package dao;

import database.Database;
import model.Account;
import model.AccountType;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO implements CrudDAO<Account> {
    private final Database database;

    public AccountDAO(Database database) {
        this.database = database;
    }

    @Override
    public Account insertAccount(Account entity) throws SQLException {
        try (Connection connection = database.connect()) {
            String sqlScript = "insert into tb_conta(numero, titular, saldo, tipo) values(?, ?, ?, ?)";
            PreparedStatement request = connection.prepareStatement(sqlScript, Statement.RETURN_GENERATED_KEYS);
            request.setString(1, entity.getNumber());
            request.setString(2, entity.getOwner());
            request.setBigDecimal(3, entity.getBalance());
            request.setString(4, entity.getAccountType() == null
                    ? "CHECKING"
                    : entity.getAccountType().name());
            request.executeUpdate();
            ResultSet result = request.getGeneratedKeys();
            if (result.next()) {
                Integer accountId = result.getInt(1);
                entity.setAccountId(accountId);
            }
            return entity;
        }
    }

    @Override
    public boolean updateAccount(Account entity) throws SQLException {
        try (Connection connection = database.connect()) {
            String sqlScript = "UPDATE tb_conta SET numero=?, titular=?, saldo=? WHERE conta_id=?;";
            PreparedStatement request = connection.prepareStatement(sqlScript);
            request.setString(1, entity.getNumber());
            request.setString(2, entity.getOwner());
            request.setBigDecimal(3, entity.getBalance());
            request.setInt(4, entity.getAccountId());
            return (request.executeUpdate() > 0);
        }
    }

    @Override
    public boolean deleteAccount(Account entity) throws SQLException {
        try (Connection connection = database.connect()) {
            String sqlScript = "delete from tb_conta where conta_id = ?";
            PreparedStatement request = connection.prepareStatement(sqlScript);
            request.setInt(1, entity.getAccountId());
            return (request.executeUpdate() > 0);
        }
    }

    @Override
    public List<Account> listAccount() throws SQLException {
        List<Account> list = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sqlScript = "select conta_id, numero, titular, saldo, tipo from tb_conta";
            PreparedStatement request = connection.prepareStatement(sqlScript);
            ResultSet result = request.executeQuery();
            while (result.next()) {
                Integer accountId = result.getInt("conta_id");
                String number = result.getString("numero");
                String owner = result.getString("titular");
                BigDecimal balance = result.getBigDecimal("saldo");
                Account account = new Account(number, owner, balance);
                account.setAccountId(accountId);
                account.setAccountType(AccountType.valueOf(result.getString("tipo")));
                list.add(account);
            }
            return list;
        }
    }

    @Override
    public List<Account> findAccountByTerm(String term) throws SQLException {
        List<Account> list = new ArrayList<>();
        try (Connection connection = database.connect()) {
            String sqlScript = "select conta_id, numero, titular, saldo, tipo from tb_conta where numero like ? or titular like ?";
            PreparedStatement request = connection.prepareStatement(sqlScript);
            request.setString(1, "%" + term + "%");
            request.setString(2, "%" + term + "%");
            ResultSet result = request.executeQuery();
            while (result.next()) {
                Integer accountId = result.getInt("conta_id");
                String number = result.getString("numero");
                String owner = result.getString("titular");
                BigDecimal balance = result.getBigDecimal("saldo");
                Account account = new Account(number, owner, balance);
                account.setAccountId(accountId);
                account.setAccountType(AccountType.valueOf(result.getString("tipo")));
                list.add(account);
            }
            return list;
        }
    }

    @Override
    public Account findAccountById(Integer id) throws SQLException {
        try (Connection connection = database.connect()) {
            String sqlScript = "select conta_id, numero, titular, saldo, tipo from tb_conta where conta_id=?";
            PreparedStatement request = connection.prepareStatement(sqlScript);
            request.setInt(1, id);
            ResultSet requestResult = request.executeQuery();
            if (requestResult.next()) {
                Account account = new Account(
                        requestResult.getString("numero"),
                        requestResult.getString("titular"),
                        requestResult.getBigDecimal("saldo")
                );
                account.setAccountId(requestResult.getInt("conta_id"));
                account.setAccountType(AccountType.valueOf(requestResult.getString("tipo")));
                return account;
            }
        }
        return null;
    }

    public Account findAccountByNumber(String number) throws SQLException {
        try (Connection connection = database.connect()) {
            String sqlScript = "select conta_id, numero, titular, saldo, tipo from tb_conta where numero=?";
            PreparedStatement request = connection.prepareStatement(sqlScript);
            request.setString(1, number);
            ResultSet result = request.executeQuery();
            if (result.next()) {
                Account account = new Account(
                        result.getString("numero"),
                        result.getString("titular"),
                        result.getBigDecimal("saldo")
                );
                account.setAccountId(result.getInt("conta_id"));
                account.setAccountType(AccountType.valueOf(result.getString("tipo")));
                return account;
            }
        }
        return null;
    }

    public boolean updateBalance(String number, BigDecimal newBalance) throws SQLException {
        try (Connection connection = database.connect()) {
            connection.setAutoCommit(false);
            try {
                String sqlScript = "update tb_conta set saldo=? where numero=?";
                PreparedStatement request = connection.prepareStatement(sqlScript);
                request.setBigDecimal(1, newBalance);
                request.setString(2, number);
                int updated = request.executeUpdate();
                if (updated == 0) {
                    connection.rollback();
                    return false;
                }
                connection.commit();
                return true;
            } catch (SQLException exception) {
                connection.rollback();
                throw exception;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    public boolean deleteAccountByNumber(String number) throws SQLException {
        try (Connection connection = database.connect()) {
            Account account = findAccountByNumber(number);
            if (account == null || account.getBalance().compareTo(BigDecimal.ZERO) != 0) {
                return false;
            }
            PreparedStatement request = connection.prepareStatement(
                    "delete from tb_conta where numero=?"
            );
            request.setString(1, number);
            return request.executeUpdate() > 0;
        }
    }

    public Account insertCheckingAccount(Account account, BigDecimal limit) throws SQLException {
        try (Connection connection = database.connect()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement base = connection.prepareStatement(
                        "insert into tb_conta(numero, titular, saldo, tipo) values(?, ?, ?, 'CHECKING')",
                        Statement.RETURN_GENERATED_KEYS
                );
                base.setString(1, account.getNumber());
                base.setString(2, account.getOwner());
                base.setBigDecimal(3, account.getBalance());
                base.executeUpdate();
                ResultSet keys = base.getGeneratedKeys();
                if (!keys.next()) {
                    connection.rollback();
                    throw new SQLException("Conta criada sem identificador.");
                }

                int accountId = keys.getInt(1);
                account.setAccountId(accountId);
                account.setAccountType(AccountType.CHECKING);

                PreparedStatement specialized = connection.prepareStatement(
                        "insert into tb_conta_corrente(conta_id, limite) values(?, ?)"
                );
                specialized.setInt(1, accountId);
                specialized.setBigDecimal(2, limit);
                specialized.executeUpdate();
                connection.commit();
                return account;
            } catch (SQLException exception) {
                connection.rollback();
                throw exception;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    public Account insertSavingsAccount(Account account, BigDecimal yieldRate) throws SQLException {
        try (Connection connection = database.connect()) {
            connection.setAutoCommit(false);
            try {
                PreparedStatement base = connection.prepareStatement(
                        "insert into tb_conta(numero, titular, saldo, tipo) values(?, ?, ?, 'SAVINGS')",
                        Statement.RETURN_GENERATED_KEYS
                );
                base.setString(1, account.getNumber());
                base.setString(2, account.getOwner());
                base.setBigDecimal(3, account.getBalance());
                base.executeUpdate();
                ResultSet keys = base.getGeneratedKeys();
                if (!keys.next()) {
                    connection.rollback();
                    throw new SQLException("Conta criada sem identificador.");
                }

                int accountId = keys.getInt(1);
                account.setAccountId(accountId);
                account.setAccountType(AccountType.SAVINGS);

                PreparedStatement specialized = connection.prepareStatement(
                        "insert into tb_conta_poupanca(conta_id, rendimento) values(?, ?)"
                );
                specialized.setInt(1, accountId);
                specialized.setBigDecimal(2, yieldRate);
                specialized.executeUpdate();
                connection.commit();
                return account;
            } catch (SQLException exception) {
                connection.rollback();
                throw exception;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }
}
