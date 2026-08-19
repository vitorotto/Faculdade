package aula04.exercicios;

import static utils.Entradas.leInt;

/**
 * Crie um método que receba 3 números e retorne o menor valor
 */
public class Ex002 {

    static int encontraMenor(int[] arr) {
        int menor = arr[0];
        for (int j : arr) {
            if (j < menor) {
                menor = j;
            }
        }
        return menor;
    }

    static void main() {
        int quantidadeValores = leInt("Quantidade de valores: ");
        int[] valores = new int[quantidadeValores];

        for (int i = 1; i <= quantidadeValores; i++) {
            valores[i] = leInt("Insira o valor " + i + ": ");
        }

        IO.println("Menor: " + encontraMenor(valores));
    }
}
