package aula06.exemplosMatrizes;

import java.util.Random;

public class Exemplo01 {
    static void main() {
        // Declarando uma matriz
        int[][] matriz = new int[5][3];
        Random rand = new Random();

        // Carrega a matriz com valores aleatórios
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = rand.nextInt(matriz.length * 2);
            }
        }

        // Exibe a matriz
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                IO.print("%02d ".formatted(matriz[i][j]));
            }
            IO.println();
        }
    }
}
