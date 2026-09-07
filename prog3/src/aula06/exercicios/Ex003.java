package aula06.exercicios;

import utils.MatrizUtils;
import utils.VetorUtils;

public class Ex003 {
    static void main(String[] args) {
        int[][] matrizG = MatrizUtils.geraMatrizInt(3,3, 20);
        int[] vetSL = new int[3];
        int[] vetSC = new int[3];

        for (int l = 0; l < matrizG.length; l++) {
            for (int c = 0; c < matrizG[l].length; c++) {
                vetSL[l] += matrizG[l][c];
                vetSC[c] += matrizG[l][c];
            }
        }

        IO.println("\nDados da matriz G: ");
        MatrizUtils.mostraMatrizInt(matrizG);

        IO.println("\nDados nos vetores: ");
        VetorUtils.mostraVetorInt(vetSL, "Vetor SL");
        VetorUtils.mostraVetorInt(vetSC, "Vetor SC");
    }
}
