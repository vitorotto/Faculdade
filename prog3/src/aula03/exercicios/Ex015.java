package aula03.exercicios;

/**
 * Escreva um programa para ler 2 números e escrever a soma dos inteiros existentes entre os 2 números lidos (incluindo
 * os números lidos na soma). Exemplo: Números lidos: 2 e 5 Resultado: 2+3+4+5 = 14. Observação: Considere que o segundo
 * valor lido será sempre maior que o primeiro valor lido.
 */
public class Ex015 {
    static void main() {
        int num = Integer.parseInt(IO.readln("Informe um número: "));
        int num2 = Integer.parseInt(IO.readln("Informe outro número: "));

        // Verifica o maior e o menor
        int menor = num;
        int maior = num2;
        if (num > maior) {
            maior = num;
            menor = num2;
        }

        // Faz a soma
        int soma = 0;
        String resultado = "";
        for (int i = menor; i <= maior; i++) {
            soma += i;
            if (i < maior) {
                resultado = resultado.concat(i + "+");
            } else
                resultado = resultado.concat(String.valueOf(i));
        }
        IO.println(resultado + "=" + soma);
    }
}
