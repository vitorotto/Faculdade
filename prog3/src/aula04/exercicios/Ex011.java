package aula04.exercicios;

public class Ex011 {

    static int[] ordenaVetor(int[] arr) {
        // Inicializa a quantidade de elementos não ordenados
        int n = arr.length;
        // Laço que percorre o vetor até o penúltimo elemento já que o último será o maior no final
        for (int i = 0; i < n - 1; i++) {
            // Inicializa a posição do menor com o índice atual
            int minIdx = i;
            // Laço que percorre o vetor a partir da posição seguinte ao índice "i",
            // já que não precisamos verificar o número da posição atual
            for (int j = i + 1; j < n; j++) {
                // Se o menor valor atualmente for maior que o valor lido no loop
                if (arr[minIdx] > arr[j]) {
                    // Atualiza a posição do menor
                    minIdx = j;
                }
            }
            // Salva o menor encontrado em uma variável temporária
            int aux = arr[minIdx];
            // O antigo menor passa para a posição antiga do novo menor valor encontrado
            arr[minIdx] = arr[i];
            // O novo menor valor encontrado passa para a posição do antigo menor
            arr[i] = aux;
        }
        // Retorna o próprio vetor
        return arr;
    }

    static void main() {
        int[] arrEntrada = {2, 3, 1, 7, 6};
        int[] arrOrdenado = ordenaVetor(arrEntrada);

        for (int num : arrOrdenado) {
            IO.print(num + " ");
        }
    }

}
