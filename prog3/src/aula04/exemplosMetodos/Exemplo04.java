package aula04.exemplosMetodos;

/**
 * Refazer o exercício 16 da aula passada usando funções
 */
public class Exemplo04 {

    static int leInt (String mensagem) {
        return Integer.parseInt(IO.readln(mensagem));
    }

    static int potencia(int base, int expoente) {
        int resultado = 1;
        for (int i = 0; i < expoente; i++) {
            resultado *= base;
        }
        return resultado;
    }
    static void main() {
        int entBase = leInt("Base: ");
        int entExpoente = leInt("Expoente: ");
        int resultado = potencia(entBase, entExpoente);
        IO.println(entBase + " ^" + entExpoente + " = " + resultado);
    }
}
