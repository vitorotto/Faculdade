package aula02.exemplosWhile;

public class Exemplo02 {
    static void main() {
        int num = Integer.parseInt(IO.readln("Digite um número de 1 a 10: "));
        while (num < 1 || num > 10) {
            IO.println("Insira um número válido");
            num = Integer.parseInt(IO.readln("Digite um número de 1 a 10: "));
        }

        IO.println("Tabuada do " + num);
        int cont = 1;
        while (cont <= 10) {
            int resultado = num * cont;
//            IO.println(cont + " x " + num + " = " + resultado);
            IO.println("%2d x %2d = %3d".formatted(cont, num, resultado));
            cont++;
        }
    }
}
