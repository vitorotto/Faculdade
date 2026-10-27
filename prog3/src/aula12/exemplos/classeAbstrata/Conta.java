package aula12.exemplos.classeAbstrata;

public abstract class Conta {
    private String titular;
    private String operacoes;

    public Conta(String titular) {
        this.titular = titular;
        this.operacoes = "";
    }

    public abstract void depositar(double valor);

    public abstract boolean sacar(double valor);

    public abstract String verificarSaldo();

    public void registrarOperacao(String operacao) {
        operacao += "\n" + operacao;
    }

    public String verificarExtrato() {
        return operacoes;
    }
}
