package aula03.exercicios;

/**
 * 11) Ler o código de um produto e exibir seu tipo de acordo com a tabela a seguir:
 * Cód. Produto / Nome do produto
 * 1 / Caderno
 * 2 / Lápis
 * 3 / Borracha
 * Outros / Diversos
 */
public class Ex011 {
    static void main() {
        int codProduto = Integer.parseInt(IO.readln("Código: "));
        switch (codProduto) {
            case 1 -> IO.println("Caderno");
            case 2 -> IO.println("Lápis");
            case 3 -> IO.println("Borracha");
            default -> IO.println("Diversos");
        }
    }
}
