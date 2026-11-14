package config;

import java.io.InputStream;
import java.util.Properties;

public class Configuracoes {
    Properties propriedades;

    public Configuracoes(){
        propriedades = new Properties();
        try(InputStream lerArquivo = Configuracoes.class.
                getClassLoader().getResourceAsStream("config.properties")){
            propriedades.load(lerArquivo);
        }
        catch (Exception e){
            throw new IllegalStateException("Não foi possível ler o arquivo de configuração");
        }
    }

    public String getConfiguracao(String chave){
        return propriedades.getProperty(chave);
    }
}
