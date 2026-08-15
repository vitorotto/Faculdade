package aula03.exercicios;

import java.util.Arrays;

public class Ex008 {
    static void main() {
        String calculo = IO.readln("C: ");
        try {
            IO.println(calculo + " = " + interpretaCalculo(calculo));            
        } catch (Exception e) {
            IO.println("Algo deu errado! Verifique o cálculo e tente novamente...");
        }
    }

    // Método para tratar a string retornada no cálculo
    public static double interpretaCalculo(String calculo) {

        char[] caracteresCalculo = calculo.toCharArray();
        char operador = ' ';

        double resultado = 0;

        // Salva o operador
        for (char caractere : caracteresCalculo) {
            if (caractere == '+' || caractere == '-' || caractere == '*' || caractere == '/') {
                operador = caractere;
            }
        }

        // Troca o operador da String recebida por ";" para fazer o split
        String calculoParaSplit = calculo.replace(operador, ';');
        String[] operandosString = calculoParaSplit.split(String.valueOf(";").trim());

        // Transforma os operandos em números
        double[] operandos = Arrays.stream(operandosString).mapToDouble(Double::parseDouble).toArray();

        // Realiza o cálculo
        resultado = realizaCalculo(operandos, operador);
        
        return resultado;
    }

    // método para selecionar a operação
    public static double realizaCalculo(double[] operandos, char operador) {
        double resultado = 0;
        switch (operador) {
            case '+' -> resultado = soma(operandos[0], operandos[1]);
            case '-' -> resultado = subtracao(operandos[0], operandos[1]);
            case '*' -> resultado = multiplicacao(operandos[0], operandos[1]);
            case '/' -> resultado = divisao(operandos[0], operandos[1]);
        }

        return resultado;
    }

    // Métodos dos cálculos
    public static double soma(double num1, double num2) {
        return num1 + num2;
    }
    public static double subtracao(double num1, double num2) {
        return num1 - num2;
    }
    public static double multiplicacao(double num1, double num2) {
        return num1 * num2;
    }
    public static double divisao(double num1, double num2) {
        if (num1 == 0 || num2 == 0)
            return 0;
        else
            return num1 / num2;
    }
}
