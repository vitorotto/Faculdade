package teste;

import banco.Banco;
import config.Configuracoes;
import dao.ContatoDAO;
import model.Contato;

import java.sql.SQLException;

public class Deletar {
    static void main() {
        Configuracoes configuracoes = new Configuracoes();
        Banco banco = new Banco(configuracoes);

        try{
            Contato contato = new Contato("Jonas","jonas@gmail.com","51965969");
            contato.setContatoId(19);
            ContatoDAO contatoDAO = new ContatoDAO(banco);
            if(contatoDAO.deletar(contato)){
                IO.println("Contado apagado");
            }
            else{
                IO.println("Erro ao deletar o contato");
            }
            IO.println(contatoDAO.listar());
        }
        catch (SQLException e){
            IO.println("Erro ao conectar "+e);
        }
    }
}
