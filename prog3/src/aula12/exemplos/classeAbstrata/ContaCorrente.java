package aula12.exemplos.classeAbstrata;

public class ContaCorrente extends Conta {

    private double saldo;
    private double limite;

    public ContaCorrente(String titular, double limite) {
        super(titular);
        saldo = 0;
        this.limite = limite;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0)
            saldo += valor;
    }

    @Override
    public boolean sacar(double valor) {

        if (saldo + limite >= valor) {
            saldo -= valor;
            return true;
        }

        return false;
    }

    @Override
    public String verificarSaldo() {
        String retorno = "Seu saldo atual é de %.2f".formatted(saldo);
        if (saldo < 0)
            retorno += "\nSeu limite disponível é de R$%.2f".formatted(saldo + limite);
        else
            retorno += "\nSeu limite disponível é de R$%.2f".formatted(limite);
        return retorno;
    }
}
