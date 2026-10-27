package aula12.exemplos.classeAbstrata;

public class Main {
    static void main() {
        ContaCorrente contaCorrente = new ContaCorrente("Jonas", 300);
        IO.println(contaCorrente.verificarSaldo());
        contaCorrente.sacar(100);
        IO.println(contaCorrente.verificarSaldo());
    }
}
