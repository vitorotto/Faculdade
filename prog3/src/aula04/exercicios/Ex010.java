package aula04.exercicios;

import java.util.Random;

import static utils.Entradas.leInt;

/**
 * Filtro de Primos no Vetor: Utilizando o conceito do exercício 6 da sua lista original, crie um método que receba um 
 * vetor de números aleatórios e retorne um novo vetor contendo apenas os números que são primos.
 */
public class Ex010 {
    static boolean ePrimo(int num) {
        int contDiv = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                contDiv++;
            }
            if (contDiv > 2)
                return false;
        }
        return contDiv == 2;
    }
    
    static int[] listaPrimos(int[] arr) {
        // Conta a quantidade de números primos
        int contPrimos = 0;
        for (int num : arr) {
            if (ePrimo(num))
                contPrimos++;
        }
        // Cria um vetor com a quantidade certa de números primos
        int[] arrPrimos = new int[contPrimos];
        int indInclusao = 0;
        // Adiciona os valores primos no vetor
        for (int i = 0; i < arr.length; i++) {
            if (ePrimo(arr[i])) {
                arrPrimos[indInclusao] = arr[i];
                indInclusao++;
            }
        }
        return arrPrimos;
    }
    
    static void main() {
        // Cria instância de um novo objeto Random
        Random aleatorio = new Random();
        // Recebe a quantidade de elementos que o vetor de entrada terá
        int tamVetor = leInt("Número de elementos: ");
        // Inicializa o vetor de entrada
        int[] vetorEntrada = new int[tamVetor];
        // Preenche o vetor de entrada com valores aleatórios
        for (int i = 0; i < tamVetor; i++) {
            vetorEntrada[i] = aleatorio.nextInt(1, 20);
        }
        // Exibe o vetor de entrada
        IO.println("Vetor de entrada: ");
        for (int num : vetorEntrada)
            IO.print(num + " ");
        // Cria o vetor com os valores primos
        int[] vetorPrimos = listaPrimos(vetorEntrada);
        // Exibe o vetor de primos encontrados
        IO.println("\nVetor de primos: ");
        for (int num : vetorPrimos)
            IO.print(num + " ");
    }
}
