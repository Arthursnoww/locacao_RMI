package src.network;

import src.pojo.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ClienteConsole {
    private static final String SERVIDOR = "192.168.0.8";
    private static final int PORTA = 12345;
    private static final Gson gson = new Gson();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Menu Locação de Aparelhos ===");
            System.out.println("1 - Listar produtos disponíveis");
            System.out.println("2 - Fazer locação");
            //System.out.println("3 - Mostrar configuração de aparelho");
            System.out.println("3 - Listar empréstimos feitos");
            System.out.println("4 - Mostrar estoque disponível");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    listarProdutos();
                    break;
                case 2:
                    fazerLocacao();
                    break;

                case 3:
                    listarEmprestimos();
                    break;
                case 4:
                    mostrarEstoque();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    
private static void listarProdutos() {
    try (Socket socket = new Socket(SERVIDOR, PORTA);
         BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
         PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

        out.println("LISTAR_ESTOQUE");

        String jsonResp = in.readLine();
        Type listType = new TypeToken<List<String>>() {}.getType();
        List<String> produtos = gson.fromJson(jsonResp, listType);

        System.out.println("\nLista de produtos disponíveis:");
        for (String linha : produtos) {
            System.out.println(linha);
        }

    } catch (IOException e) {
        System.err.println("Erro ao listar produtos: " + e.getMessage());
    }
}
    

    private static void fazerLocacao() {
        try (Socket socket = new Socket(SERVIDOR, PORTA);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();
            System.out.print("Digite seu CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Digite seu telefone: ");
            String telefone = scanner.nextLine();
            Cliente cliente = new Cliente(nome, cpf, telefone);
            
            System.out.println("Escolha um aparelho para alugar:");
            System.out.println("1 - Gerador X");
            System.out.println("2 - Mesa Redonda");
            System.out.println("3 - Palco Médio");
            System.out.println("4 - Talher Conjunto");
            System.out.print("Opção: ");
            int escolha = Integer.parseInt(scanner.nextLine());

            Aparelho aparelho = null;
            switch (escolha) {
                case 1: aparelho = new Aparelho("Gerador X", 1500.0f, 0); break;
                case 2: aparelho = new Aparelho("Mesa Redonda", 200.0f, 0); break;
                case 3: aparelho = new Aparelho("Palco Médio", 1000.0f, 0); break;
                case 4: aparelho = new Aparelho("Talher Conjunto", 50.0f, 0); break;
                default:
                    System.out.println("Opção inválida!");
                    return;
            }


            System.out.print("Quantidade a alugar: ");
            int quantidade = Integer.parseInt(scanner.nextLine());

            RequisicaoLocacao requisicao = new RequisicaoLocacao(cliente, aparelho, quantidade);
            String jsonReq = gson.toJson(requisicao);

            out.println(jsonReq);

            String jsonResp = in.readLine();
            RespostaLocacao resposta = gson.fromJson(jsonResp, RespostaLocacao.class);
            System.out.println("Resposta do servidor: " + resposta.getMensagem());

        } catch (IOException e) {
            System.err.println("Erro na comunicação: " + e.getMessage());
        }
    }

    
    
    
    

    private static void listarEmprestimos() {
        try (Socket socket = new Socket(SERVIDOR, PORTA);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Solicitar histórico de empréstimos
            out.println("LISTAR_EMPRESTIMOS");

            String jsonResp = in.readLine();
            System.out.println("\nHistórico de Empréstimos:");
            System.out.println(jsonResp);

        } catch (IOException e) {
            System.err.println("Erro na comunicação: " + e.getMessage());
        }
    }

    private static void mostrarEstoque() {
        try (Socket socket = new Socket(SERVIDOR, PORTA);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Solicitar estoque disponível
            out.println("LISTAR_ESTOQUE");

            String jsonResp = in.readLine();
            System.out.println("\nEstoque disponível:");
            System.out.println(jsonResp);

        } catch (IOException e) {
            System.err.println("Erro na comunicação: " + e.getMessage());
        }
    }
}
