package service;

import java.lang.reflect.Type;
import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import pojo.Aparelho;
import pojo.Cliente;
import pojo.RequisicaoLocacao;

public class Client {
    private static final Gson gson = new Gson();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Locacao locacao = (Locacao) Naming.lookup("rmi://localhost:1099/LocacaoService");

            while (true) {
                System.out.println("\n=== Menu Locação de Aparelhos ===");
                System.out.println("1 - Listar produtos disponíveis");
                System.out.println("2 - Fazer locação");
                System.out.println("3 - Listar empréstimos feitos");
                System.out.println("4 - Sair");
                System.out.print("Escolha uma opção: ");

                int opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1 -> listarProdutos(locacao);
                    case 2 -> fazerLocacao(locacao);
                    case 3 -> listarEmprestimos(locacao);
                    case 4 -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listarProdutos(Locacao locacao) {
        try {
            byte[] resposta = locacao.doOperation("LocacaoService", "listarEstoqueDisponivel", new byte[0]);
            Type listType = new TypeToken<List<Aparelho>>(){}.getType();
            List<Aparelho> produtos = gson.fromJson(new String(resposta), listType);

            System.out.println("\nLista de produtos disponíveis:");
            for (int i = 0; i < produtos.size(); i++) {
                Aparelho a = produtos.get(i);
                System.out.printf("%d - %s | R$ %.2f | Qtd: %d\n", i + 1, a.getNome(), a.getPreco(), a.getQtd());
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    private static void fazerLocacao(Locacao locacao) {
        try {
            byte[] resposta = locacao.doOperation("LocacaoService", "listarEstoqueDisponivel", new byte[0]);
            Type listType = new TypeToken<List<Aparelho>>(){}.getType();
            List<Aparelho> produtos = gson.fromJson(new String(resposta), listType);

            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite seu CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Digite seu telefone: ");
            String telefone = scanner.nextLine();
            Cliente cliente = new Cliente(nome, cpf, telefone);

            System.out.println("Escolha um aparelho para alugar:");
            for (int i = 0; i < produtos.size(); i++) {
                Aparelho a = produtos.get(i);
                System.out.printf("%d - %s | R$ %.2f | Qtd: %d\n", i + 1, a.getNome(), a.getPreco(), a.getQtd());
            }
            System.out.print("Opção: ");
            int escolha = Integer.parseInt(scanner.nextLine());

            if (escolha < 1 || escolha > produtos.size()) {
                System.out.println("Opção inválida!");
                return;
            }

            Aparelho aparelho = produtos.get(escolha - 1);

            System.out.print("Quantidade a alugar: ");
            int quantidade = Integer.parseInt(scanner.nextLine());

            RequisicaoLocacao requisicao = new RequisicaoLocacao(cliente, aparelho, quantidade);
            String jsonReq = gson.toJson(requisicao);
            resposta = locacao.doOperation("LocacaoService", "registrarLocacao", jsonReq.getBytes());

            System.out.println("Resposta do servidor: " + new String(resposta));
        } catch (Exception e) {
            System.err.println("Erro ao realizar locação: " + e.getMessage());
        }
    }

    private static void listarEmprestimos(Locacao locacao) {
        try {
            byte[] resposta = locacao.doOperation("LocacaoService", "listarEmprestimosRealizados", new byte[0]);
            System.out.println("\nHistórico de Empréstimos:");
            System.out.println(new String(resposta));
        } catch (Exception e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
        }
    }

    
}