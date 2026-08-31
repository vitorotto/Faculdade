package Aula05.exercicios;

import javax.swing.*;

import static utils.Entradas.leInt;

/**
 * Faça um programa para ler 10 números DIFERENTES a serem armazenados em um vetor. Os dados deverão ser armazenados no
 * vetor na ordem que forem sendo lidos, sendo que caso o usuário digite um número que já foi digitado anteriormente, o
 * programa deverá pedir para ele digitar outro número. Note que cada valor digitado pelo usuário deve ser pesquisado no
 * vetor, verificando se ele existe entre os números que já foram fornecidos. Exibir na tela o vetor final que foi
 * digitado
 */
public class Ex002 {
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
                IO.println("Valor já inserido. Digite novamente.");
                valorEntrada = leInt("Valor " + cont + ": ");
            } else {
                vetorResultado[cont] = valorEntrada;
                cont++;
            }
        }
        return vetorResultado;
    }

    static void main() {
        int numElementos = leInt("Qual a quantidade de elementos? ");
        int[] vetorSemRepetir = carregaVetorSemRepetir(numElementos);
        for (int num : vetorSemRepetir) {
            IO.println(num + " ");
        }
    }
}
