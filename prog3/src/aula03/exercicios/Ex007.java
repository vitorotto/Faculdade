package aula03.exercicios;

/**
 * 7) Crie um algoritmo que pergunte ao usuário se ele deseja converter uma temperatura de graus Celsius em graus
 * Fahrenheit ou Fahrenheit em Celsius. Em seguida mostre na tela o valor final correspondente à opção escolhida pelo
 * usuário. F = 1.8 * C + 32 | C = (F – 32) / 1.8;
 */
public class Ex007 {
    static void main() {
        int opc = menu();

        switch (opc) {
            case 1 -> {
                float tempF = Float.parseFloat(IO.readln("Digite a temperatura em F: "));
                IO.println("Temperatura em C: " + fToC(tempF));
            }
            case 2 -> {
                float tempC = Float.parseFloat(IO.readln("Digite a temperatura em C: "));
                IO.println("Temperatura em F: " + cToF(tempC));
            }
            default -> IO.println("Opção inválida");
        }
    }

    public static int menu() {
        IO.println("1. Converter F para C");
        IO.println("2. Converter C para F");
        return Integer.parseInt(IO.readln("\nDigite a opção: "));
    }

    public static float cToF(float c) {
        return 1.8f * c + 32;
    }

    public static float fToC(float f) {
        return (f - 32) / 1.8f;
    }
}
