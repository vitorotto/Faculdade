package Aula05.exercicios;

import java.util.Random;

import static utils.Entradas.leInt;

/**
 * Declare três vetores com até 100 números, o primeiro deve receber úmeros aleatórios, o segundo deve receber os
 * números pares do primeiro vetor e o terceiro os números impares do primeiro vetor. Ao final, mostrar os três vetores.
 */
public class Ex003 {
    static int[] carregaValoresAleatorios(int numElementos) {
        Random random = new Random();
        int[] vetor = new int[numElementos];
        for (int i = 0; i < numElementos; i++) {
            vetor[i] = random.nextInt(1, 100);
        }
        return vetor;
    }
    static int[] separaPares(int[] vetor) {
        int contPares = 0;
        for (int num : vetor) {
            if (num % 2 == 0) contPares++;
        }
        int indInc = 0;
        int[] vetorResultado = new int[contPares];
        for (int num : vetor) {
            if (num % 2 == 0) {
                vetorResultado[indInc] = num;
                indInc++;
            }
        }
        return vetorResultado;
    }
    static int[] separaImpares(int[] vetor) {
        int contImpares = 0;
        for (int num : vetor) {
            if (num % 2 != 0) contImpares++;
        }
        int  indInc = 0;
        int[] vetorResultado = new int[contImpares];
        for (int num : vetor) {
            if (num % 2 != 0) {
                vetorResultado[indInc] = num;
                indInc++;
            }
        }
        return vetorResultado;
    }
    static void main() {
        int numElementos = leInt("Numero de elementos: ");
        int[] vetorPrincipal = carregaValoresAleatorios(numElementos);
        int[] vetorPares = separaPares(vetorPrincipal);
        int[] vetorImpares = separaImpares(vetorPrincipal);

        IO.println("Vetor principal: ");
        for (int num : vetorPrincipal) {
            IO.print(num + " ");
        }
        IO.println("\nVetor pares: ");
        for (int num : vetorPares) {
            IO.print(num + " ");
        }
        IO.println("\nVetor impares: ");
        for (int num : vetorImpares) {
            IO.print(num + " ");
        }
    }
}
