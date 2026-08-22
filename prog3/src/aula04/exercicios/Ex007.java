package aula04.exercicios;

/**
 * Crie um método que receba um vetor de números reais (double[]) e retorne a média aritmética dos elementos.
 * Como desafio extra, crie outro método que utilize essa média para calcular e retornar o maior valor presente no
 * vetor.
 */
public class Ex007 {
    // Encontra a média dos elementos do vetor
    static double mediaElementos (double[] arr) {
        // Inicializa a soma dos elementos do vetor
        double soma = 0;
        // Descobre a soma do valor total de todos os elementos do vetor recebido juntos
        for (double num : arr) {
            // Cada valor lido no laço é adicionado ao valor total do vetor
            soma += num;
        }
        // Retorna a média dos valores do vetor
        return soma / arr.length;
    }

    // Descobre o maior valor do vetor
    static double maiorValor (double[] arr) {
        // Inicializa o maior valor com a primeira posição do vetor
        double maior = arr[0];
        // Encontra o maior valor disponível no vetor
        for (int i = 1; i < arr.length; i++) {
            // Se o maior valor for menor que o valor lido
            if (maior < arr[i])
                // O valor lido se torna o novo maior
                maior = arr[i];
        }
        // Retorna o maior valor encontrado no vetor
        return maior;
    }

    static void main() {
        double[] arrElementos = {3, 8, 1};
        IO.println("Média dos elementos: " + mediaElementos(arrElementos));
        IO.println("Maior elemento: " + maiorValor(arrElementos));
    }
}
