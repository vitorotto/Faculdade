package utils;

public class Saidas {
    public static String mostraVetor(int[] vetor, String nome) {
        String retorno = "\nDados no vetor: " + nome + "\n[";
        for (int i = 0; i < vetor.length - 1; i++) {
            retorno += vetor[i] + ", ";
        }
        retorno += vetor[vetor.length - 1];
        return retorno + "]";
    }
}
