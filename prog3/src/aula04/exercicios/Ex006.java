package aula04.exercicios;

/**
 * Faça um programa em que acha todos os números primos até 1000. Número primo é aquele que é divisível somente por 1 e
 * por ele mesmo.
 */
public class Ex006 {

    static boolean ePrimo (int num) {
        // Presume que o número será primo
        boolean resultado = true;
        int contDiv = 0;
        // Loop de 1 até o número informado
        for (int i = 1; i <= num; i++) {
            // Se o número atual do loop dividido pelo número escolhido tiver resto zero
            if (num % i == 0)
                contDiv++;
            // Se o contador de divisores do número ficar maior que 2, significa que o resto da divisão foi zero para
            // mais algum valor que é diferente de 1 ou próprio número
            if (contDiv > 2)
                resultado = false;
        }
        // Se o contador de divisores for igual a 2 no final do laço
        if (contDiv == 2)
            resultado = true;
        // Retorna o resultado da verificação
        return resultado;
    }

    static void main() {
        for (int i = 1; i <= 1000; i++) {
            if (ePrimo(i))
                IO.print(" [" + i + "] ");
        }
    }
}
