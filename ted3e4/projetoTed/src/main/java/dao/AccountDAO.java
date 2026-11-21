package dao;

import database.Database;
import model.Account;

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
            String sqlScript = "insert into tb_conta(numero, titular, saldo) values(?, ?, ?)";
            PreparedStatement request = connection.prepareStatement(sqlScript, Statement.RETURN_GENERATED_KEYS);
            request.setString(1, entity.getNumber());
            request.setString(2, entity.getOwner());
            request.setBigDecimal(3, entity.getBalance());
            request.execute();
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
        try (Connection connection = database.connect();) {
            String sqlScript = "select conta_id, numero, titular, saldo from tb_conta";
            PreparedStatement request = connection.prepareStatement(sqlScript);
            ResultSet result = request.executeQuery();
            while (result.next()) {
                Integer accountId = result.getInt("conta_id");
                String number = result.getString("numero");
                String owner = result.getString("titular");
                BigDecimal balance = result.getBigDecimal("saldo");
                Account account = new Account(number, owner, balance);
                account.setAccountId(accountId);
                list.add(account);
            }
            return list;
        }
    }

    @Override
    public List<Account> findAccountByTerm(String term) throws SQLException {
        List<Account> list = new ArrayList<>();
        try (Connection connection = database.connect();) {
            String sqlScript = "select conta_id, numero, titular, saldo from tb_conta where numero like ? or titular like ?";
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
                list.add(account);
            }
            return list;
        }
    }

    @Override
    public Account findAccountById(Integer id) throws SQLException {
        try (Connection connection = database.connect()) {
            String sqlScript = "select conta_id, numero, titular, saldo from tb_conta where conta_id=?;";
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
                return account;
            }
        }
        return null;
    }
}
