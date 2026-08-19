package aula04.exercicios;

import java.util.Random;

/**
 * Crie um método chamado dado() que retorna, através de sorteio, um número de 1 até 6.
 */
public class Ex003 {

    static int dado() {
        Random aleatorio = new Random();
        return aleatorio.nextInt(1, 7);
    }

    static void main() {
        for (int i = 0; i < 10; i++) {
            IO.print(dado() + " ");
        }
    }
}
