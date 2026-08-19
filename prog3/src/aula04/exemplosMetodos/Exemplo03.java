package aula04.exemplosMetodos;

public class Exemplo03 {
    static int leNumero(String mensagem) {
        return Integer.parseInt(IO.readln(mensagem + ": "));
    }
    static float leFloat(String mensagem) {
        return Float.parseFloat(IO.readln(mensagem + ": "));
    }
    static String leString(String mensagem) {
        return IO.readln(mensagem + ": ");
    }

    static void main() {
        String nome = leString("Insira o nome");
        int idade = leNumero("Idade");
        float altura = leFloat("Altura");

        IO.println(nome + ", sua idade é " + idade + " e sua altura é " + altura);
    }
}
