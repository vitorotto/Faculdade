package aula03.exercicios;

/**
 * 12) Faça um programa para ler: quantidade atual em estoque, quantidade máxima em estoque e quantidade mínima em
 * estoque de um produto. Calcular e escrever a quantidade média ((quantidade média = quantidade máxima + quantidade
 * mínima)/2). Se a quantidade em estoque for maior ou igual à quantidade média escrever a mensagem 'Não efetuar
 * compra', senão escrever a mensagem 'Efetuar compra'.
 */
public class Ex012 {
    static void main() {
        String nome = IO.readln("Produto: ");
        int estoqueAtual = Integer.parseInt(IO.readln("Estoque atual: "));
        int estoqueMaximo = Integer.parseInt(IO.readln("Estoque máximo: "));
        int estoqueMinimo = Integer.parseInt(IO.readln("Estoque mínimo: "));

        double mediaEstoque = ((double) estoqueMaximo + estoqueMinimo) / 2;

        if (estoqueAtual >= mediaEstoque)
            IO.println("Não efetuar compra");
        else
            IO.println("Efetuar compra");
    }
}
