package utils;

public class VetorUtils {
    /**
     * Cria uma String com os dados de um vetor de valores inteiros
     * @param vetor Vetor que será exibido
     * @param nome Nome do vetor que será exibido
     * @return String formatada com os dados do vetor informado como parâmetro
     */
    public static String stringVetorInt(int[] vetor, String nome) {
        String retorno = "\nDados no vetor: " + nome + "\n[";
        for (int i = 0; i < vetor.length - 1; i++) {
            retorno += vetor[i] + ", ";
        }
        retorno += vetor[vetor.length - 1];
        return retorno + "]";
    }

    /**
     * Exibe os dados de um vetor de valores inteiros
     * @param vetor Vetor que será exibido
     * @param nome Nome do vetor que será exibido
     */
    public static void mostraVetorInt(int[] vetor, String nome) {
        String retorno = "\nDados no vetor: " + nome + "\n[";
        for (int i = 0; i < vetor.length - 1; i++) {
            retorno += vetor[i] + ", ";
        }
        retorno += vetor[vetor.length - 1];
        retorno += "]";
        IO.println(retorno);
    }
}
