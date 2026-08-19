package aula04.exercicios;

import static utils.Entradas.leDouble;

/**
 * Crie um método que receba três valores, 'a', 'b' e 'c', que são os coeficientes de uma equação do segundo grau e
 * retorne o valor do delta, que é dado por 'b² - 4ac' , função que verifica se existem raízes reais.
 * Criar outro método, calculaX, para calcular os resultados da equação.
 * Usando as 2 funções acima, crie um aplicativo que calcula as raízes de uma equação do 2o grau: ax² + bx + c=0
 * Para ela existir, o coeficiente 'a' deve ser diferente de zero.
 * Caso o delta seja maior ou igual a zero, as raízes serão reais. Caso o delta seja negativo, as reais serão complexas,
 * apenas informe que não existem raízes racionais
 */
public class Ex001 {

    static double delta (double a, double b, double c) {
        return Math.pow(b, 2) - 4 * a * c;
    }

    static double[] calcX (double a, double b, double c, double delta) {
        double[] arrX = new double[2];

        arrX[0] = (-b + Math.sqrt(delta)) / (2 * a);
        arrX[1] = (-b - Math.sqrt(delta)) / (2 * a);

        return arrX;
    }

    static void main() {
        double a = leDouble("A: ");
        double b = leDouble("B: ");
        double c = leDouble("C: ");

        double valDelta = delta(a, b, c);

        if (valDelta < 0) {
            IO.println("Não é possível realizar o cálculo para delta negativo");
        } else {
            double[] arrResultado = calcX(a, b, c, delta(a, b, c));
            IO.println("X1: " + arrResultado[0]);
            IO.println("X2: " + arrResultado[1]);
        }

    }
}
