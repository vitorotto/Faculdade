package model;

public enum AccountType {
    CHECKING("Conta Corrente"),
    SAVINGS("Conta Poupanca");

    private final String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
