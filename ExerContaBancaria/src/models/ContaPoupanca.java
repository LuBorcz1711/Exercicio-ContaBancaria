package models;
import interfaces.Imposto;

public class ContaPoupanca extends ContaBancaria implements Imposto {
    private static final double taxaRendimento = 0.02;

    public ContaPoupanca(String numeroConta, String titular) {
        super(numeroConta, titular);
    }

    @Override
    public double calcularImposto() {
        return saldo * 0.01;
    }

    @Override
    public void aplicarTarifaOuImposto() {
        saldo += saldo * taxaRendimento;
        saldo -= calcularImposto();
    }

    @Override
    public String getTipoConta() {
        return "Conta Poupança";
    }
}
