package models;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private List<ContaBancaria> contas;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(ContaBancaria conta) {
        contas.add(conta);
    }

    public List<ContaBancaria> getContas() {
        return contas;
    }

    public String getNome() {
        return nome;
    }

    public ContaBancaria buscarContaPorNumero(String numeroConta) {
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                return conta;
            }
        }
        return null;
    }

    public static Cliente buscarClientePorCPF(String cpf, List<Cliente> clientes) {
        for (Cliente cliente : clientes) {
            if (cliente.cpf.equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
}
