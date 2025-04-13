package models;
import interfaces.Imposto;

public class ContaCorrente extends ContaBancaria implements Imposto {
    private static final double tarifaMensal = 10.0;

    public ContaCorrente(String numeroConta, String titular) {
        super(numeroConta, titular);
    }

    @Override
    public double calcularImposto() {
        return tarifaMensal;
    }

    @Override
    public void aplicarTarifaOuImposto() {
        saldo -= tarifaMensal;
    }

    @Override
    public String getTipoConta() {
        return "Conta Corrente";
    }
}