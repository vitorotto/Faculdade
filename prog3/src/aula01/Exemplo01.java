package aula01;

public class Exemplo01 {

    static void main() {
        // Para imprimir uma mensagem
        IO.println("Mensagem a ser impressa na tela");

        // Float
        // Todas as constantes de ponto flutuante no java são consideradas double
        float numeroFLoat = (float) 5.5;
        // float numeroFLoat = 5.5f;

        // double
        // O tipo objeto relacionado é o Double
        double numeroDouble = 10.2;

        // Armazenar uma palavra ou texto
        String texto = "Agora eu estou esvrevendo um texto em uma variável Java";

        // Para armazenar uma letra
        // O tipo objeto é o Character
        char letra = 'a';

        // boolean
        // Por padrão ele é false
        boolean flag = true;

        String nome = "Jonas";
        double altura = 1.8;
        int idade = 20;

        // Imprimindo os valores das variáveis na tela
        IO.println("A pessoa chama " + nome + " tem " + idade + " anos e mede " + altura +  " metros");

        // Imprimindo usando o método format da classe String
        IO.println("A pessoa chama %s tem %d anos e mede %.2f metros".formatted(nome, idade, altura));
    }
}