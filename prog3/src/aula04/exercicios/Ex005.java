package aula04.exercicios;

import static utils.Entradas.*;

/**
 * Crie uma classe de conversão entre as temperaturas Celsius e Farenheit.
 * Primeiro o usuário deve escolher se vai entrar com a temperatura em Célsius ou Farenheit, depois a conversão
 * escolhida é realizada através de um comando SWITCH.
 * Se C é a temperatura em Célsius e F em farenheit, as fórmulas de conversão são:C= 5.(F-32)/9 F= (9.C/5) + 32
 */
public class Ex005 {
    static void exibeMenu () {
        IO.println("1. Fahrenheigth - Celcius");
        IO.println("2. Celcius - Fahrenheigth");
    }

    static int aceitaOpc (String mensagem) {
        return leInt(mensagem);
    }

    static double aceitaTemperatura (String mensagem) {
        return leDouble(mensagem);
    }

    static double converteTemperatura (int opc, double temperatura) {
        double resultado = 0;
        switch (opc) {
            case 1 -> resultado = 5 * (temperatura - 32) / 9;
            case 2 -> resultado =  (9 * temperatura / 5) + 32;
            default -> IO.println("Opção inválida");
        }
        return resultado;
    }
    static void main() {
        exibeMenu();
        int opc = aceitaOpc("Opção: ");
        double temperatura = aceitaTemperatura("Temperatura: ");
        IO.println(converteTemperatura(opc, temperatura));
    }
}
