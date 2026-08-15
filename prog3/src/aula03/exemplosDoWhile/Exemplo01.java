package aula03.exemplosDoWhile;

public class Exemplo01 {
    public static void main(String[] args) {
        int i = 10;
        while (i < 10) {
            IO.println(i + " ");
            i++;
        }

        do {
            IO.println(i + " ");
            i++;
        } while (i < 10);
    }
}
