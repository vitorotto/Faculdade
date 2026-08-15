package aula02.exemplosIf;

public class Exemplo01 {
    static void main() {
        /* Resumo dos operadores relacionais
        [>] - Maior que
        [<] - Menor que
        [>=] - Maior ou igual
        [<=] - Menor ou igual
        [!=] - Diferente de
        [==] - Igual a
         */

        /* Operadores Lógicos
        [&&] - É um E lógico, significa que ambos os termos da equação lógica precisam ser verdadeiros para ter um resultado verdadeiro
        [||] - É um OU lógico, significa que pelo menos um dos termos da equação precisa ser verdadeiro para o resultado ser verdadeiro
        [^ ] - É um XOR e funciona como o OU lógico, porém só da verdade quando os termos da equação forem diferentes. Ex: true ^ false = true, true ^ true = false
        [! ] - É um NÃO lógico, que simplesmente inverte o resultado de uma equação lógica
         */

        float n1 = Float.parseFloat(IO.readln("Qual a primeira nota: "));
        float n2 = Float.parseFloat(IO.readln("Qual a segunda nota: "));

        float media = (n1 + n2) / 2;

        if (media >= 8)
            IO.println("O aluno está aprovado com média " + media);
        else if (media >= 6)
            IO.println("O aluno está em recuperação com média " + media);
        else
            IO.println("O aluno não obteve a aprovação");
    }
}
