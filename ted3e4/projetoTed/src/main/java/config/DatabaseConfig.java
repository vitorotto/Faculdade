package config;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    Properties properties;

    public DatabaseConfig() {
        properties = new Properties();
        try (InputStream readFile = DatabaseConfig.class.
                getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(readFile);
        } catch (Exception e) {
            throw new IllegalStateException("Não foi possível ler o arquivo de configuração");
        }
    }

    public String getDatabaseConfig(String chave) {
        return properties.getProperty(chave);
    }
}
