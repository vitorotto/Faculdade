package utils;

import java.util.Random;

import static utils.Entradas.leInt;

public class MatrizUtils {

    /**
     * Lê um matriz de inteiros
     * @param numLinhas Número de linhas
     * @param numColunas Número de colunas
     * @return Matriz de inteiros
     */
    public static int[][] leMatrizInt (int numLinhas, int numColunas) {
        int[][] matriz = new int[numLinhas][numColunas];
        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[l].length; c++) {
                matriz[l][c] = leInt("[" + l + "][" + c + "]: ");
            }
        }
        return matriz;
    }

    /**
     * Lê um matriz de inteiros
     * @param numLinhas Número de linhas
     * @param numColunas Número de colunas
     * @param range Valor máximo permitido para cada elemento gerado
     * @return Matriz aleatória de valores inteiros
     */
    public static int[][] geraMatrizInt (int numLinhas, int numColunas, int range) {
        Random rand = new Random();
        int[][] matriz = new int[numLinhas][numColunas];
        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[l].length; c++) {
                matriz[l][c] = rand.nextInt(range);
            }
        }
        return matriz;
    }

    /**
     * Verifica se o valor existe em qualquer lugar da matriz
     * @param valor Valor a ser pesquisado
     * @param matriz Matriz que vai na qual a pesquisa será realizada
     * @return True se o valor existe e False se o valor não existe na matriz
     */
    public static boolean matrizContemInt (int valor, int[][] matriz) {
        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[c].length; c++) {
                return valor == matriz[l][c];
            }
        }
        return false;
    }

    /**
     * Exibe os elementos de uma matriz de números inteiros
     * @param matriz Matriz com os elementos que serão exibidos
     */
    public static void mostraMatrizInt(int[][] matriz) {
        for (int l = 0; l < matriz.length; l++) {
            for (int c = 0; c < matriz[l].length; c++) {
                IO.print("%02d ".formatted(matriz[l][c]));
            }
            IO.println();
        }
    }
}
