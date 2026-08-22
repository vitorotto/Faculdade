package aula04.exercicios;

import static utils.Entradas.leInt;

/**
 * Desenvolva um método chamado posicaoDoElemento que receba um vetor de inteiros e um valor alvo. O método deve
 * retornar o índice (posição) da primeira ocorrência desse valor no vetor. Caso o valor não exista, retorne -1.
 */
public class Ex008 {
    // Busca a posição de um número inteiro em um vetor
    static int posicaoDoElemento (int[] arr, int num) {
        // Percorre o vetor
        for (int i = 0; i < arr.length; i++) {
            // Se o valor lido for igual ao valor informado
            if (arr[i] == num)
                // Retorna a posição da primeira ocorrência do número recebido como parâmetro e encerra o método
                return i;
        }
        // Se, ao chegar no final dos valores do vetor e não encontrou nenhum valor igual, retorna −1;
        return -1;
    }

    static void main() {
        int[] arrElementos = {10, 20, -1, 17, 24};
        int num = leInt("Número: ");
        IO.println("Posição do número %d: %d".formatted(num, posicaoDoElemento(arrElementos, num)));
    }
}
