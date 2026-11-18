package teste;

import banco.Banco;
import config.Configuracoes;
import dao.ContatoDAO;
import model.Contato;

import java.sql.SQLException;

public class Listar {
    static void main() {
        Configuracoes configuracoes = new Configuracoes();
        Banco banco = new Banco(configuracoes);

        try{
            Contato contato = new Contato("Jonas","jonas@gmail.com","51965969");
            ContatoDAO contatoDAO = new ContatoDAO(banco);
            IO.println(contatoDAO.listar());
        }
        catch (SQLException e){
            IO.println("Erro ao conectar "+e);
        }
    }
}
