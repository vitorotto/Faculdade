package aula01;

public class OpIncremento {
    static void main() {
        int numero = 10;
        // Incrementa a variável e depois usa
        IO.println("O valor de número é " + ++numero);

        numero = 10;
        // Usa a variável e depois incrementa
        IO.println("O valor do número é " + numero++);
    }
}
