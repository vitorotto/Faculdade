package aula03.exercicios;

/**
 * 9) A partir de 3 notas fornecidas de um aluno, informe se ele foi aprovado, ficou de recuperação ou foi reprovado. 
 * A média de aprovação é >= 7.0; a média de recuperação é >= 5.0 e < 7.0; e a média do reprovado é < 5.0
 */
public class Ex009 {
    static void main() {
        String entradaNotas = IO.readln("Notas (Ex: 10;8;6): ");
        String[] strNotas = entradaNotas.split(";");

        // Transforma os elementos do vetor de String para o vetor de double
        double[] vetorNotas = stringParaDouble(strNotas);

        // Valida os valores do vetor recebido
        if (!validaEntrada(vetorNotas))
            return;

        // Exibe a média
        IO.println("Média: " + calculaMedia(vetorNotas));
    }

    /**
     * Transforma um vetor de String para double
     * @param vetorString vetor do tipo String
     * @return Vetor com elementos do tipo double
     */
    private static double[] stringParaDouble(String[] vetorString) {
        double[] vetorDouble = new double[vetorString.length];
        for (int i = 0; i < vetorString.length; i++) {
            vetorDouble[i] = Double.parseDouble(vetorString[i]);
        }
        return vetorDouble;
    }

    /**
     * Valida os valores de entrada 
     * @param vetor vetor de elementos do tipo double
     * @return true ou false
     */
    static boolean validaEntrada(double[] vetor) {
        // Presume que não haverá erro
        boolean ehValido = true;

        // Verifica se algum número é menor que zero
        int cont = 0;
        while (ehValido && cont < vetor.length) {
            if (vetor[cont] < 0) ehValido = false;
            cont++;
        }

        return ehValido;
    }

    /**
     * Calcula a média dos elementos de um vetor do tipo double
     * @param vetorDouble vetor com elementos do tipo double
     * @return A Média dos elementos do vetor
     */
    static double calculaMedia(double[] vetorDouble) {
        double resultado;
        double soma = 0;
        for (double num : vetorDouble) {
            soma += num;
        }
        resultado = soma / vetorDouble.length;
        return resultado;
    }
}
