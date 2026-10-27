package aula12.exemplos.colecoes;

import java.util.ArrayList;

public class Exemplo01 {
    static void main() {
        // Podemos usar apenas tipos objetos
        // Double, Float, Integer, String, Pessoa
        ArrayList<String> lista = new ArrayList<>();

        // Adicionando elementos no final da lista
        lista.add("A");
        lista.add("C");
        lista.add("D");
        lista.add("E");
        IO.println(lista);

        // Adiciona elementos em qualquer posição da lista
        lista.add(1, "B");
        IO.println(lista);

        // Verificar se a lista contém determinado elemento
        IO.println("A lista contem o elemento 'A'? " + (lista.contains("A") ? "Sim" : "Não"));
        IO.println("A lista contem o elemento 'T'? " + (lista.contains("T") ? "Sim" : "Não"));

        // Verificando a posição de um elemento na lista
        IO.println("Qual a posição do elemento 'B' na lista? " + (lista.indexOf("B")));
        IO.println("Qual a posição do elemento 'T' na lista? " + (lista.indexOf("T")));

        // Verificando a última ocorrência de um elemento na lista
        lista.add("D");
        IO.println("A última posição que contém um 'D' é " + lista.lastIndexOf("D"));

        // Obtendo um elemento
        IO.println("O elemento na posição 2 é " + lista.get(2));

        // Removendo elementos da lista
        // Retorna TRUE em caso de sucesso e FALSE em caso de falha (elemento não encontrado)
        IO.println("Removendo o elemento 'D' pelo elemento " + lista.remove("D"));

        // Retorna o elemento removido
        IO.println("Removendo um elemento pela posição " + lista.remove(3));
        IO.println(lista);

        // Verificando se uma lista está vazia
        IO.println("A lista está vazia? " + (lista.isEmpty() ? "Sim" : "Não"));

        // Apagando todos os elementos da lista
        lista.clear();
        IO.println(lista);

        // Retorna a quantidade de elementos da lista
        IO.println("A lista contém " + lista.size() + " elementos");

    }
}
