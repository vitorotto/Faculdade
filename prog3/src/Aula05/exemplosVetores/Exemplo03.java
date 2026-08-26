package Aula05.exemplosVetores;

public class Exemplo03 {
    static void main() {
        int[] vetor = {1, 5, 7, 2, 10};
        IO.println("Dados vetor 1");
        for (int num : vetor) {
            IO.println(num + " ");
        }

        int[] vetorDois = new int[vetor.length];
        // Faz com que os dois vetores apontem para o mesmo endereço da memória
        // vetorDois = vetor;

        // Cópia manual
        for (int i = 0; i < vetor.length; i++) {
            vetorDois[i] = vetor[i];
        }

        // Cópia usando o System.arrayCopy
        System.arraycopy(vetor, 0, vetorDois, 0, vetor.length);
        vetor[2] = 100;

        IO.println("Dados em vetor 2\n");
        for (int num : vetorDois) {
            IO.println(num + " ");
        }

        IO.println("Dados em vetor 1\n");
        for (int num : vetor) {
            IO.println(num + " ");
        }
    }
}
