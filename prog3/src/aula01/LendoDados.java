package aula01;

public class LendoDados {
    static void main() {
        // Solicita a entrada de dados (String)
        String nome = IO.readln("Qual seu nome: ");

        // Solicita a entrada de dados (int)
        int numero = Integer.parseInt(IO.readln("Digite um número: "));

        // Solicita a entrada de dados (float)
        float numeroFloat = Float.parseFloat(IO.readln("Digite um número real: "));

        // Solicita a entrada de dados (double)
        double numeroDouble = Double.parseDouble((IO.readln("Insira outro número real: ")));

        // Solicita a entrada de dados (char)
        char letra = IO.readln("Digite uma letra: ").charAt(0);
    }
}
