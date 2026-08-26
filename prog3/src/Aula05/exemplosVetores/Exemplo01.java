package Aula05.exemplosVetores;

public class Exemplo01 {
    static void main() {
        int[] vetor = new int[10];
        // Lendo valores do teclado
        for (int i = 0; i < 10; i++) {
            vetor[i] = Integer.parseInt(IO.readln("vetor[" + i + "]: "));
        }

        // Mostrando o vetor
        for (int num : vetor) {
            IO.println(num + " ");
        }
    }
}
