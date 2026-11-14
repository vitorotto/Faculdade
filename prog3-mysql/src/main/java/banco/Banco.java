package banco;

import config.Configuracoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
    private String servidor;
    private String porta;
    private String banco;
    private String usuario;
    private String senha;

    public Banco(Configuracoes config){
        servidor = config.getConfiguracao("bd.servidor");
        porta = config.getConfiguracao("bd.porta");
        banco = config.getConfiguracao("bd.banco");
        usuario = config.getConfiguracao("bd.usuario");
        senha = config.getConfiguracao("bd.senha");
    }

    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://"+servidor+":"+porta+"/"+banco,usuario,senha);
    }
}
