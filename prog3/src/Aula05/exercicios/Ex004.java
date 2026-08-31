package Aula05.exercicios;

import static utils.Entradas.leDouble;
import static utils.Entradas.leInt;

/**
 * Leia números de matrículas de alunos e armazene-os em um vetor até o vetor ser preenchido por 10 matrículas. Esses
 * números são distintos, ou seja, o vetor não armazenará valores repetidos. Para cada aluno, calcule a média de 3 notas.
 * Armazenar a média em outro vetor. Ao final mostrar cada aluno e suas respectivas notas (médias)
 */
public class Ex004 {

    static boolean procuraElemento(int numEntrada, int[] arr) {
        for (int num : arr) {
            if (num == numEntrada) return true;
        }
        return false;
    }

    static int[] carregaVetorSemRepetir(int numElementos) {
        int[] vetorResultado = new int[numElementos];
        int valorEntrada = 0;
        int cont = 0;
        valorEntrada = leInt("Valor " + cont + ": ");
        while (cont < numElementos) {
            if (procuraElemento(valorEntrada, vetorResultado)) {
                valorEntrada = leInt("Valor " + cont + ": ");
            } else {
                vetorResultado[cont] = valorEntrada;
                cont++;
            }
        }
        return vetorResultado;
    }

    static void main() {
        int numElementos = leInt("Informe a quantidade de alunos: ");
        while (numElementos > 10) {
            IO.println("O limite é de 10 alunos.");
            numElementos = leInt("Informe a quantidade de alunos: ");
        }
        int[] vetorMatriculas = carregaVetorSemRepetir(numElementos);

        double[] vetorMedias = new double[numElementos];
        for (int i = 0; i < numElementos; i++) {
            double n1 = leDouble("Nota 1: ");
            double n2 = leDouble("Nota 2: ");
            double n3 = leDouble("Nota 3: ");
            double soma = n1 + n2 + n3;
            double media = soma / 3;
            vetorMedias[i] = media;
        }

        IO.println("Resultados dos alunos:");
        for (int i = 0; i < numElementos; i++) {
            IO.println("Aluno " + vetorMatriculas[i] + " - " + vetorMedias[i]);
        }
    }
}
