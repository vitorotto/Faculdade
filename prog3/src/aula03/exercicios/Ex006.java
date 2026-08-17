package aula03.exercicios;

/**
 * 6) Faça um programa que solicite ao usuário digitar dois valores, em seguida, exiba na tela qual dos dois é o maior.
 * OBS: o usuário poderá informar valores iguais, logo, o sistema deve dizer que foram digitados valores iguais.
 */
public class Ex006 {
    static void main() {
        int n1 = Integer.parseInt(IO.readln("Insira um número: "));
        int n2 = Integer.parseInt(IO.readln("Insira outro número: "));

        if (n1 == n2)
            IO.println("Valores iguais");
        else if (n1 > n2) {
            IO.println("O " + n1 + " é o maior");
        } else
            IO.println("O " + n2 + " é o maior");
    }
}
