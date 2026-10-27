package aula12.exemplos.colecoes;

import java.util.HashSet;

public class Exemplo03 {
    static void main() {
        HashSet<String> lista = new HashSet<>();

        IO.println(lista.add("Mouse"));
        IO.println(lista.add("Impressora"));
        IO.println(lista.add("Monitor"));
        IO.println(lista.add("CPU"));
        IO.println(lista.add("Memória"));
        IO.println(lista.add("Impressora"));
        IO.println(lista);

        // Mesmos métodos da classe ArrayList
        // Não permite elementos duplicados

    }
}
