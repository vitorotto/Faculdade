package aula12.exemplos.interfaces;

public class Main {
    static void main() {
        // O tipo genérico precisa ser um objeto
        // Integer
        // Float
        // Double
        // Pessoa (ou qualquer objeto definido pelo usuário)
        Vetor<String> v1 = new Vetor<>(10);
        v1.adiciona("A");
        v1.adiciona("B");
        v1.adiciona("C");
        v1.adiciona("D");
        v1.adiciona("E");
        v1.adiciona("F");
        IO.println("O tamanho da lista é " + v1.obterTamanho());
        IO.println(v1);

        Lista<Integer> l1 = new Vetor<>(10);
    }
}
