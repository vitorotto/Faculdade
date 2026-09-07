package aula06.exercicios;

import utils.MatrizUtils;

/**
 * Carregar uma matriz SOMA 4x4, calcular e escrever as seguintes somas:
 * a) da linha de índice 3;
 * b) da coluna de índice 2;
 * c) de todos os elementos da matriz.
 */
public class Ex002 {
    static void main() {
        int[][] matrizSoma = MatrizUtils.geraMatrizInt(4,5, 50);
        int somaA = 0;
        int somaB = 0;
        int somaC = 0;

        // Processamento das somas
        for (int l = 0; l < matrizSoma.length; l++) {
            for (int c = 0; c < matrizSoma[l].length; c++) {
                if (l == 3) somaA += matrizSoma[l][c];
                if (c == 2) somaB += matrizSoma[l][c];
                somaC += matrizSoma[l][c];
            }
        }

        IO.println("\nDados na matriz SOMA: ");
        MatrizUtils.mostraMatrizInt(matrizSoma);

        IO.println("\nResultados das somas: ");
        IO.println("\na) soma da linha de índice 3: " + somaA);
        IO.println("\na) soma da coluna de índice 2 " + somaB);
        IO.println("\na) soma de todos os elementos da matriz: " + somaC);
    }
}
