package aula03.exercicios;

/**
 * 5) Crie um programa que teste se o valor que usuário digitou está entre 10 e 15 e exibe alguma mensagem na tela.
 */
public class Ex005 {
    static void main() {
        int num = Integer.parseInt(IO.readln("Informe um número: "));
        if (num >= 10 && num <= 15)
            IO.println("Número entre 10 e 15");
        else
            IO.println("Número fora do limite");
    }
}
