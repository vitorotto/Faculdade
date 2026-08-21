package aula04.exercicios;

import static aula04.exercicios.Ex003.dado;

/**
 * Use a função da questão passado e lance o dado 1 milhão de vezes. Conte quantas vezes cada número saiu. Ou seja, a
 * porcentagem dos números foi parecida?
 */
public class Ex004 {
    static void main() {
        int[] contReps = new int[6];
        for (int i = 0; i < 1000000; i++) {
            contReps[dado() - 1]++;
        }

        for (int i = 0; i < contReps.length; i++) {
            double percentual = ((double) contReps[i] / 1000000) * 100;
            IO.println("Num: %d - %.2f%%".formatted(i + 1, percentual));
        }
    }
}
