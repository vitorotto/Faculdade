package Aula05.exercicios;

public class ExPratica {

    static int[] maiorMenor(int[] arr) {
        int[] resultado = new int[2];
        int maior = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > maior) {
                maior = arr[i];
            }
        }
        int menor = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < menor) {
                menor = arr[i];
            }
        }
        resultado[0] = maior;
        resultado[1] = menor;

        return resultado;
    }

    static void main() {
        int[] vetor = {10, 2, 5, 6, 30};
        int[] resultado = maiorMenor(vetor);

        for (int num : resultado) {
            IO.print(num + " ");
        }
    }
}
