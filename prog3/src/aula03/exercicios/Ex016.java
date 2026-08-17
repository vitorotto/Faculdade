package aula03.exercicios;

/**
 * 16) Faça um programa que peça dois números, base e expoente, calcule e mostre o primeiro número elevado ao segundo
 * número. Observação: Não utilize a função de potência da linguagem.
 */
public class Ex016 {
    static void main() {
        int base = Integer.parseInt(IO.readln("base: "));
        int exp = Integer.parseInt(IO.readln("expoente: "));
        int resultado = 1;
        for (int i = 0; i < exp; i++) {
            resultado *= base;
        }
        IO.println(resultado);
    }
}
