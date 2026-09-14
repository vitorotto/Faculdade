package aula06.exercicios;

import utils.MatrizUtils;

public class Ex004 {
    static void main() {
        int[][] matriz = MatrizUtils.leMatrizInt(5, 5);
        IO.println("Matriz: ");
        MatrizUtils.mostraMatrizInt(matriz);

        // Somas
        int somaDiagonalPrincipal = 0;
        int somaAcimaPrincipal = 0;
        int somaAbaixoPrincipal = 0;

        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[l].length; c++) {
                // Soma da diagonal principal
                if (l == c) {
                    somaDiagonalPrincipal += matriz[l][c];
                }
                // Soma da parte superior à diagonal principal
                if (c > l) {
                    somaAcimaPrincipal += matriz[l][c];
                }
                // Soma da parte inferior à diagonal principal
                if (c < l) {
                    somaAbaixoPrincipal += matriz[l][c];
                }
            }
        }

        IO.println("\nSomas");
        IO.println("Diagonal principal = " + somaDiagonalPrincipal);
        IO.println("Acima da diagonal principal = " + somaAcimaPrincipal);
        IO.println("Abaixo da diagonal principal = " + somaAbaixoPrincipal);
    }
}
