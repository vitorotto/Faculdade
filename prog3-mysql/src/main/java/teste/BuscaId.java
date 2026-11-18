package teste;

import banco.Banco;
import config.Configuracoes;
import dao.ContatoDAO;
import model.Contato;

import java.sql.SQLException;

public class BuscaId {
    static void main() {
        Configuracoes configuracoes = new Configuracoes();
        Banco banco = new Banco(configuracoes);

        try{
            ContatoDAO contatoDAO = new ContatoDAO(banco);
            int contatoID = 15;
            Contato contato = contatoDAO.buscaID(contatoID);
            if(contato!=null){
                IO.println(contato);
            }
            else {
                IO.println("Contato id " + contatoID + " não encontrado");
            }

        }
        catch (SQLException e){
            IO.println("Erro ao conectar "+e);
        }
    }
}
