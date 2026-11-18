package dao;

import banco.Banco;
import model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO implements CrudDAO<Contato>{

    private Banco banco;

    public ContatoDAO(Banco banco){
        this.banco = banco;
    }

    @Override
    public Contato inserir(Contato entidade) throws SQLException {
        Connection conexao = banco.conectar();
        String sql = "insert into tb_contato(nome, e_mail, telefone) values(?,?,?)";
        PreparedStatement requisicao = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        requisicao.setString(1,entidade.getNome());
        requisicao.setString(2,entidade.geteMail());
        requisicao.setString(3,entidade.getTelefone());
        //.execute() -> retorna um boolean usado para insert ou delete
        //.executeQuery() -> retorna um ResultSet usado para select
        //.executeUpdate() -> recorna um inteiro com a contagem de linhas alteradas
        requisicao.execute();
        ResultSet resultado = requisicao.getGeneratedKeys();
        //Para navegar nos registros podemos usar
        //.next() retorna true enquanto conseguir passar para o próximo registro
        //.previous() retorna true enquanto conseguir voltar um registro
        //.first() vai direto para o primeiro registro, retorna false se não houver
        //.last() vai direto para o último registro
        if(resultado.next()){
            Integer contatoID = resultado.getInt(1);
            entidade.setContatoId(contatoID);
        }

        return entidade;
    }

    @Override
    public boolean alterar(Contato entidade) throws SQLException {
        Connection conexao = banco.conectar();
        String sql = "update tb_contato set nome = ?, e_mail = ?, telefone = ? where contato_id = ?";
        PreparedStatement requisicao = conexao.prepareStatement(sql);
        requisicao.setString(1,entidade.getNome());
        requisicao.setString(2,entidade.geteMail());
        requisicao.setString(3,entidade.getTelefone());
        requisicao.setInt(4,entidade.getContatoId());
        int contLinha = requisicao.executeUpdate();
        if(contLinha>0)
            return true;
        return false;
    }

    @Override
    public boolean deletar(Contato entidade) throws SQLException {
        Connection conexao = banco.conectar();
        String sql = "delete from tb_contato where contato_id = ?";
        PreparedStatement requisicao = conexao.prepareStatement(sql);
        requisicao.setInt(1,entidade.getContatoId());
        int contLinha = requisicao.executeUpdate();
        if(contLinha>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Contato> listar() throws SQLException {
        List<Contato> lista = new ArrayList<>();
        Connection conexao = banco.conectar();
        String sql = "select contato_id, nome, e_mail, telefone from tb_contato";
        PreparedStatement requisicao = conexao.prepareStatement(sql);
        ResultSet resultado = requisicao.executeQuery();
        while(resultado.next()){
            //Lembre que para usar o getInt ou getString usamos o nome da coluna na tabela do banco
            Integer contatoID = resultado.getInt("contato_id");
            String nome = resultado.getString("nome");
            String e_mail = resultado.getString("e_mail");
            String telefone = resultado.getString("telefone");
            Contato contato = new Contato(nome,e_mail,telefone);
            contato.setContatoId(contatoID);
            lista.add(contato);
        }
        return lista;
    }

    @Override
    public List<Contato> busca(String termo) throws SQLException {
        Connection conexao = banco.conectar();
        List<Contato> lista = new ArrayList<>();
        String sql = "select contato_id, nome, e_mail, telefone from tb_contato " +
                "where nome LIKE ? or telefone LIKE ? or e_mail LIKE ?";
        PreparedStatement requisicao = conexao.prepareStatement(sql);
        requisicao.setString(1,"%"+termo+"%");
        requisicao.setString(2,"%"+termo+"%");
        requisicao.setString(3,"%"+termo+"%");
        ResultSet resultado = requisicao.executeQuery();
        while(resultado.next()){
            Integer contatoID = resultado.getInt("contato_id");
            String nome = resultado.getString("nome");
            String eMail = resultado.getString("e_mail");
            String telefone = resultado.getString("telefone");
            Contato contato = new Contato(nome, eMail, telefone);
            contato.setContatoId(contatoID);
            lista.add(contato);
        }
        return lista;
    }

    @Override
    public Contato buscaID(Integer id) throws SQLException {
        Connection conexao = banco.conectar();
        String sql = "select nome, e_mail, telefone from tb_contato where contato_id = ?";
        PreparedStatement requisicao = conexao.prepareStatement(sql);
        requisicao.setInt(1,id);
        ResultSet resultato = requisicao.executeQuery();
        if(resultato.next()){
            String nome = resultato.getString("nome");
            String eMail = resultato.getString("e_mail");
            String telefone = resultato.getString("telefone");
            Contato contato = new Contato(nome, eMail, telefone);
            contato.setContatoId(id);
            return contato;
        }
        return null;
    }
}
