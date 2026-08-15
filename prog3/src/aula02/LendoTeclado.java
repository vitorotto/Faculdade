package aula02;

public class LendoTeclado {
    static void main() {
        // Lendo uma String do teclado
        String nome = IO.readln("Digite o seu nome: ");
        // Lendo um valor float do teclado
        float altura = Float.parseFloat(IO.readln(nome + ", digite a sua altura em metros: "));

        // Lendo um número inteiro do teclado
        int idade = Integer.parseInt(IO.readln(nome + ", qual sua idade: "));

        // Sem formatação
        IO.println("O nome é " + nome + " tem " + altura + " metros e " + idade + " anos");
        IO.println("O nome é %s tem %.2f metros e %d anos".formatted(nome, altura, idade));
    }
}
