package aula12.exemplos.colecoes;

import java.util.LinkedList;
import java.util.Queue;

public class Exemplo04 {
    static void main() {
        // Como a Queue é uma interface, precisamos usar uma classe que a implemente (LinkedList)
        Queue<String> fila = new LinkedList<>();

        fila.add("A");
        fila.add("B");
        fila.add("C");
        fila.add("D");
        fila.add("E");
        fila.add("F");
        IO.println(fila);
        IO.println("Quem é o próximo " + fila.peek());
        IO.println("Atendendo o próximo da fila" + fila.poll());
        IO.println(fila);
    }
}
