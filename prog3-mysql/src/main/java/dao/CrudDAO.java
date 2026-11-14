package dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {

    T inserir(T entidade) throws SQLException;

    boolean alterar(T entidade) throws SQLException;

    boolean deletar(T entidade) throws SQLException;

    List<T> listar() throws SQLException;

    List<T> busca() throws SQLException;

    T buscaID(Integer id) throws SQLException;
}
