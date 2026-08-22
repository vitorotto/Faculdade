package aula04.exercicios;

/**
 * Localizar Maior Valor: Escreva um método que receba uma matriz de inteiros e imprima a linha e a coluna onde se
 * encontra o maior valor contido nela.
 */
public class Ex009 {
    // Retorna um vetor de dois elementos com a posição do maior valor da matriz
    static int[] localizaMaiorValor(double[][] mtz) {
        // Inicializa o maior com a primeira posição da matriz
        double maior = mtz[0][0];
        // Inicializa o vetor de resultados
        int[] arrPos = new int[2];
        // Laço que percorre as linhas da matriz
        for (int i = 0; i < mtz.length; i++) {
            // Laço que percorre as colunas da matriz
            for (int j = 0; j < mtz[i].length; j++) {
                // Se o maior valor é menor que o valor lido atualmente na matriz
                if (maior < mtz[i][j]) {
                    // O maior recebe o novo valor lido na matriz
                    maior = mtz[i][j];
                    // Indica a nova posição do maior valor
                    arrPos[0] = i;
                    arrPos[1] = j;
                }
            }
        }
        // Retorna o vetor com as posições do maior valor
        return arrPos;
    }
    static void main() {
        // Inicialização da matriz com valores estáticos
        double[][] mtzElementos = {{22, 44, 23}, {40, 89, 100}};
        // Exibe a matriz no terminal
        IO.println("Matriz: ");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                IO.print("[" + mtzElementos[i][j] + "]");
            }
            IO.print("\n");
        }
        // Exibe os resultados
        IO.println("Posição do maior:");
        int[] arrPosMaior = localizaMaiorValor(mtzElementos);
        IO.println("Lin: %d \nCol: %d".formatted(arrPosMaior[0] + 1, arrPosMaior[1] + 1));
    }
}
