package aula12.exemplos.listaPessoa;

import java.util.ArrayList;
import java.util.List;

public class Cadastro {
    static void main() {
        List<Pessoa> lista = new ArrayList<>(10);

        lista.add(new Pessoa("Jonas", "Silva"));
        lista.add(new Pessoa("Ana", "Souza"));

        IO.println(lista);
    }
}
