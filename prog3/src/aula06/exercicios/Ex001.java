package aula06.exercicios;

import utils.Entradas;
import utils.MatrizUtils;

/**
 * Ler 6 números inteiros para preencher uma matriz D 2x3, ou seja, com 2 linhas e 3 colunas (considere que não serão)
 * informados valores duplicados). A seguir, ler um número inteiro X e escrever uma mensagem indicando se o valor X
 * existe ou não na matriz D
 */
public class Ex001 {

    static void main() {
        int[][] matriz = MatrizUtils.leMatrizInt(2, 3);
        int valorPesquisa = Entradas.leInt("Informe o valor que deseja pesquisar na matriz: ");
        if (MatrizUtils.matrizContemInt(valorPesquisa, matriz))
            IO.println("Valor " + valorPesquisa + " existe na matriz.");
        else
            IO.println("Valor " + valorPesquisa + " não existe na matriz.");
    }
}
