package Aula05.exemplosVetores;

import java.util.Random;

public class Exemplo07 {
    static int[] geraVetor( int capacidade) {
        Random aleatorio = new Random();
        int[] vetor = new int[capacidade];
        for (int i = 0; i < capacidade; i++) {
            vetor[i] = aleatorio.nextInt(1, 20);
        }

        return vetor;
    }

    static int soma(int[] vetor) {
        int soma = 0;
        for (int num : vetor) {
            soma += num;
        }
        return soma;
    }

    static void mostraVetor(int[] vetor) {
        for (int num : vetor) {
            IO.print(num + " ");
        }
    }

    static void main() {
        int[] vetor = geraVetor(10);
        IO.println("Vetor: ");
        mostraVetor(vetor);
        int soma = soma(vetor);;
        IO.println("Soma: " + soma);

    }
}
