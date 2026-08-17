package aula03.exercicios;

/**
 * 13) Ler 3 valores (considere que não serão informados valores iguais) e escrevê-los em ordem crescente.
 */
public class Ex013 {
    static void main() {
        int[] valores = new int[3];
        int[] valoresOrdenados;
        for (int i = 0; i < valores.length; i++) {
            valores[i] = Integer.parseInt(IO.readln("Num " + (i + 1) + ": "));
        }

        // Ordena o array de valores
        valoresOrdenados = ordenacaoPorSelecao(valores);
        for (int num : valoresOrdenados) {
            IO.println(num);
        }

    }

    // Busca o menor e a posição
    static int buscaMenor(int[] arr) {
        int menor = arr[0];
        int posMenor = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < menor) {
                menor = arr[i];
                posMenor = i;
            }
        }
        return posMenor;
    }

    static int[] ordenacaoPorSelecao(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int menor = buscaMenor(arr);
            int aux = arr[menor];
            arr[menor] = arr[i];
            arr[i] = aux;
        }
        return arr;
    }
}
