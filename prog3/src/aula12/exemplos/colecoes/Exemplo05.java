package aula12.exemplos.colecoes;

import java.util.HashMap;

public class Exemplo05 {
    static void main() {
        HashMap<String, String> capitais = new HashMap<>();
        capitais.put("RS", "Porto Alegre");
        capitais.put("SC", "Florianópolis");
        capitais.put("PR", "Paraná");
        capitais.put("RJ", "Rio de Janeiro");
        capitais.put("SP", "São Paulo");
        capitais.put("PI", "Teresina");

        capitais.put("RS", "Rio Grande");
        IO.println(capitais);

    }
}
