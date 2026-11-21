package dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {
    T insertAccount(T entity) throws SQLException;

    boolean updateAccount(T entity) throws SQLException;

    boolean deleteAccount(T entity) throws SQLException;

    List<T> listAccount() throws SQLException;

    List<T> findAccount(String term) throws SQLException;

    T findAccountById(Integer id) throws SQLException;
}
