package utils;

import static utils.Entradas.leInt;

public class MatrizUtils {
    public static int[][] leMatrizInt (int numLinhas, int numColunas) {
        int[][] matriz = new int[numLinhas][numColunas];
        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[l].length; c++) {
                matriz[l][c] = leInt("[" + l + "][" + c + "]: ");
            }
        }
        return matriz;
    }

    public static boolean matrizContemInt (int valor, int[][] matriz) {
        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[c].length; c++) {
                return valor == matriz[l][c];
            }
        }
        return false;
    }
}
