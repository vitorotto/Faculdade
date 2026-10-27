package aula12.exemplos.colecoes;

import java.util.Stack;

public class Exemplo02 {
    static void main() {
        Stack<String> pilha = new Stack<>();

        // Empilhar um elemento
        pilha.push("A");
        pilha.push("B");
        pilha.push("C");
        pilha.push("D");
        pilha.push("E");
        IO.println(pilha);

        // Espiando o topo da pilha
        IO.println(pilha.peek());
        IO.println(pilha);

        // Removendo um elemento da pilha
        IO.println(pilha.pop());
        IO.println(pilha);

    }
}
