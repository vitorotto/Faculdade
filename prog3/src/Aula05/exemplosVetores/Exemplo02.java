package Aula05.exemplosVetores;

public class Exemplo02 {
    static void main() {
        int[] vetor = {1, 2, 3, 4, 5, 6, 7, 8};
        IO.println("Tamanho do vetor: " + vetor.length);

        for (int i = 0; i < vetor.length; i++) {
            IO.println(vetor[i] + " ");
        }
    }
}
