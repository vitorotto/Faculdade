package aula03.exemplosDoWhile;

public class Exemplo02 {
    public static void main(String[] args) {
        char resp = 's';
        while (resp == 's') {
            resp = IO.readln("Deseja continuar? (S/N)").charAt(0);
        }

        // Usando o do-while
        char resposta;
        do {
            resposta = IO.readln("Deseja continuar? (S/N)").charAt(0);
        } while (resposta == 's');
    }
}
