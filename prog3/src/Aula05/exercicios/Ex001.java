package Aula05.exercicios;

import java.util.Random;

/**
 * 1. Criar um vetor A com 5 elementos, carregar ele com números aleatórios. Declarar um vetor B. Copiar todos os
 * elementos do Vetor A para o Vetor B e mostrar os dois na tela
 */
public class Ex001 {
    public static int[] geraVetor(int numElementos) {
        int[] vetor = new int[numElementos];
        Random aleatorio = new Random();
        for (int i = 0; i < numElementos; i++) {
            vetor[i] = aleatorio.nextInt(1, 100);
        }
        return vetor;
    }

    static void main() {
        IO.println("Vetor 1: ");
        int[] vetor = geraVetor(10);
        for (int num : vetor) {
            IO.print(num + " ");
        }
        IO.println("\nVetor 2: ");
        int[] vetorDois = new int[10];
        System.arraycopy(vetor, 0, vetorDois, 0, vetor.length);
        for (int num : vetorDois) {
            IO.print(num + " ");
        }
    }
}
