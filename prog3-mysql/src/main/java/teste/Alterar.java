package teste;

import banco.Banco;
import config.Configuracoes;
import dao.ContatoDAO;
import model.Contato;

import java.sql.SQLException;

public class Alterar {
    static void main() {
        Configuracoes configuracoes = new Configuracoes();
        Banco banco = new Banco(configuracoes);

        try{
            Contato contato = new Contato("Jonas","jonas@gmail.com","51965969");
            contato.setContatoId(2);
            contato.setNome("Ana");
            contato.seteMail("ana@gmail.com");
            ContatoDAO contatoDAO = new ContatoDAO(banco);
            boolean okAlterar = contatoDAO.alterar(contato);
            if(okAlterar)
                IO.println("Contato alterado com sucesso!");
            else
                IO.println("Não foi possível alterar o contato");
        }
        catch (SQLException e){
            IO.println("Erro ao conectar "+e);
        }
    }
}
