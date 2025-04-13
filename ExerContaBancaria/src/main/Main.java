package main;
import models.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Menu do Banco ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Criar Conta");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Transferir");
            System.out.println("6. Listar Contas do Cliente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    clientes.add(new Cliente(nome, cpf));
                    System.out.println("Cliente cadastrado com sucesso.");
                }

                case 2 -> {
                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Cliente cliente = Cliente.buscarClientePorCPF(cpf, clientes);

                    if (cliente != null) {
                        System.out.print("Número da conta: ");
                        String numero = sc.nextLine();
                        System.out.print("Tipo (1-Corrente, 2-Poupança): ");
                        int tipo = sc.nextInt();
                        sc.nextLine();

                        ContaBancaria conta;
                        if (tipo == 1) {
                            conta = new ContaCorrente(numero, cliente.getNome());
                        } else {
                            conta = new ContaPoupanca(numero, cliente.getNome());
                        }
                        cliente.adicionarConta(conta);
                        System.out.println("Conta criada com sucesso.");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }

                case 3 -> {
                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Cliente cliente = Cliente.buscarClientePorCPF(cpf, clientes);

                    if (cliente != null) {
                        System.out.print("Número da conta: ");
                        String numero = sc.nextLine();
                        ContaBancaria conta = cliente.buscarContaPorNumero(numero);

                        if (conta != null) {
                            System.out.print("Valor para depositar: ");
                            double valor = sc.nextDouble();
                            conta.depositar(valor);
                            System.out.println("Depósito realizado. Saldo atual: R$ " + conta.getSaldo());
                        } else {
                            System.out.println("Conta não encontrada.");
                        }
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }

                case 4 -> {
                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Cliente cliente = Cliente.buscarClientePorCPF(cpf, clientes);

                    if (cliente != null) {
                        System.out.print("Número da conta: ");
                        String numero = sc.nextLine();
                        ContaBancaria conta = cliente.buscarContaPorNumero(numero);

                        if (conta != null) {
                            System.out.print("Valor para sacar: ");
                            double valor = sc.nextDouble();
                            if (conta.sacar(valor)) {
                                System.out.println("Saque realizado. Saldo atual: R$ " + conta.getSaldo());
                            } else {
                                System.out.println("Saldo insuficiente.");
                            }
                        } else {
                            System.out.println("Conta não encontrada.");
                        }
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }

                case 5 -> {
                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Cliente cliente = Cliente.buscarClientePorCPF(cpf, clientes);

                    if (cliente != null) {
                        System.out.print("Conta origem: ");
                        String origem = sc.nextLine();
                        System.out.print("Conta destino: ");
                        String destino = sc.nextLine();

                        ContaBancaria c1 = cliente.buscarContaPorNumero(origem);
                        ContaBancaria c2 = cliente.buscarContaPorNumero(destino);

                        if (c1 != null && c2 != null) {
                            System.out.print("Valor a transferir: ");
                            double valor = sc.nextDouble();
                            if (c1.transferir(c2, valor)) {
                                System.out.println("Transferência realizada.");
                            } else {
                                System.out.println("Saldo insuficiente.");
                            }
                        } else {
                            System.out.println("Uma das contas não foi encontrada.");
                        }
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }

                case 6 -> {
                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Cliente cliente = Cliente.buscarClientePorCPF(cpf, clientes);

                    if (cliente != null) {
                        System.out.println("Contas do cliente " + cliente.getNome() + ":");
                        for (ContaBancaria conta : cliente.getContas()) {
                            System.out.println("- Tipo: " + conta.getTipoConta()
                                    + " | Número: " + conta.getNumeroConta()
                                    + " | Saldo: R$ " + conta.getSaldo());
                        }
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }

                case 0 -> {
                    System.out.println("Encerrando o sistema...");
                    sc.close();
                    System.exit(0);
                }

                default -> System.out.println("Opção inválida!");
            }

        }
    }
}
