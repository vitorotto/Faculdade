package teste;

import banco.Banco;
import config.Configuracoes;
import dao.ContatoDAO;
import model.Contato;

import java.sql.SQLException;

public class Inserir {
    static void main() {
        Configuracoes configuracoes = new Configuracoes();
        Banco banco = new Banco(configuracoes);

        try{
            //Connection conexao = banco.conectar();
            //IO.println("Conectado");
            Contato contato = new Contato("Jonas","jonas@gmail.com","51965969");
            ContatoDAO contatoDAO = new ContatoDAO(banco);
            contato = contatoDAO.inserir(contato);
            IO.println("Contato "+contato.getNome()+" gravado obteve o id "+contato.getContatoId());
        }
        catch (SQLException e){
            IO.println("Erro ao conectar "+e);
        }
    }
}
