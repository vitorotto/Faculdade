package aula03.exercicios;

/**
 * 14) Escreva um programa que pergunte ao usuário um número e após, escreva na tela a soma total de 1 até o número
 * lido. Exemplo: Número lido é o 5. Resultado: 1+2+3+4+5 = 15
 */
public class Ex014 {
    static void main() {
        int num = Integer.parseInt(IO.readln("Informe um número: "));
        int soma = 0;
        String resultado = "";
        for (int i = 1; i <= num; i++) {
            soma += i;
            if (i < num) {
                resultado = resultado.concat(i + "+");
            } else
                resultado = resultado.concat(String.valueOf(i));
        }
        IO.println(resultado + "=" + soma);
    }
}
