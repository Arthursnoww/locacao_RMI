package src.network;

import com.google.gson.Gson;
import src.pojo.RequisicaoLocacao;
import src.pojo.RespostaLocacao;
import src.service.Locacao;
import src.service.LocacaoImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServidorTCP {
    public static void main(String[] args) {
        int porta = 12345;
        Gson gson = new Gson();
        Locacao servicoLocacao = new LocacaoImpl();

        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor TCP rodando na porta " + porta);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    String jsonReq = in.readLine();

                    if (jsonReq == null) {
                        System.err.println("Cliente desconectado sem enviar dados.");
                        continue;
                    }

                    if (jsonReq.equals("LISTAR_ESTOQUE")) {
                        out.println(LocacaoImpl.listarEstoque());
                    }
                    else if (jsonReq.equals("LISTAR_EMPRESTIMOS")) {
                        List<String> historico = LocacaoImpl.getHistoricoLocacoes();
                        out.println(historico.isEmpty() ? "Nenhum empréstimo registrado." : String.join("\n", historico));
                    }
                    else {
                        // Requisição de locação em JSON
                        RequisicaoLocacao requisicao = gson.fromJson(jsonReq, RequisicaoLocacao.class);
                        RespostaLocacao resposta = servicoLocacao.alugarAparelho(requisicao);
                        out.println(gson.toJson(resposta));
                    }

                    if (jsonReq == null) {
                        System.err.println("Cliente desconectado sem enviar dados.");
                        continue;
                    }

                    System.out.println("Requisição recebida do cliente: " + jsonReq); // << AQUI!


                } catch (IOException e) {
                    System.err.println("Erro na comunicação com cliente: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Erro ao iniciar servidor: " + e.getMessage());
        }
    }
}
