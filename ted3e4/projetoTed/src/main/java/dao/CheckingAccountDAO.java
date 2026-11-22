package dao;

import database.Database;
import model.Account;
import model.CheckingAccountModel;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckingAccountDAO {
    private final Database database;

    public CheckingAccountDAO(Database database) {
        this.database = database;
    }

    public boolean updateCheckingAccount(CheckingAccountModel entity) throws SQLException {
        try (Connection connection = database.connect()) {
            String sqlScript = "update tb_conta_corrente set limite=? WHERE conta_id=?;";
            PreparedStatement request = connection.prepareStatement(sqlScript);
            request.setBigDecimal(1, entity.getLimit());
            request.setInt(2, entity.getAccountId());
            return (request.executeUpdate() > 0);
        }
    }

    public CheckingAccountModel findCheckingAccountById(Integer id) throws SQLException {
        try (Connection connection = database.connect()) {
            String sqlScript = "select conta_id, limite from tb_conta_corrente where conta_id=?;";
            PreparedStatement request = connection.prepareStatement(sqlScript);
            request.setInt(1, id);
            ResultSet requestResult = request.executeQuery();
            if (requestResult.next()) {
                return new CheckingAccountModel(
                        requestResult.getInt("conta_id"),
                        requestResult.getBigDecimal("limite")
                );
            }
        }
        return null;
    }
}
