package dao;

import database.Database;
import model.SavingsAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SavingsAccountDAO {
    private final Database database;

    public SavingsAccountDAO(Database database) {
        this.database = database;
    }

    public SavingsAccount findSavingsAccountById(Integer accountId) throws SQLException {
        try (Connection connection = database.connect()) {
            PreparedStatement request = connection.prepareStatement(
                    "select conta_id, rendimento from tb_conta_poupanca where conta_id=?"
            );
            request.setInt(1, accountId);
            ResultSet result = request.executeQuery();
            if (result.next()) {
                return new SavingsAccount(
                        result.getInt("conta_id"),
                        result.getBigDecimal("rendimento")
                );
            }
        }
        return null;
    }

    public boolean updateSavingsAccount(SavingsAccount account) throws SQLException {
        try (Connection connection = database.connect()) {
            PreparedStatement request = connection.prepareStatement(
                    "update tb_conta_poupanca set rendimento=? where conta_id=?"
            );
            request.setBigDecimal(1, account.getYieldRate());
            request.setInt(2, account.getAccountId());
            return request.executeUpdate() > 0;
        }
    }
}
