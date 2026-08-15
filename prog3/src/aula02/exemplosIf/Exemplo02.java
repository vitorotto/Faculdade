package aula02.exemplosIf;

public class Exemplo02 {
    static void main() {
        String nome = IO.readln("Qual seu nome: ");
        if (nome.equalsIgnoreCase("jonas"))
            IO.println("O nome é Jonas");
        else
            IO.println("O nome não é Jonas");
    }
}
