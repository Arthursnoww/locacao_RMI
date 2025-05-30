package src.network;

import com.google.gson.Gson;
import src.pojo.Cliente;
import src.pojo.Aparelho;
import src.pojo.RequisicaoLocacao;
import src.pojo.RespostaLocacao;

import java.io.*;
import java.net.Socket;

public class ClienteTCP {
    public static void main(String[] args) {
        String servidor = "localhost";
        int porta = 12345;
        Gson gson = new Gson();

        // Criar dados de exemplo
        Cliente cliente = new Cliente("Arthur Roberto", "123.456.789-00", "99999-9999");
        Aparelho aparelho = new Aparelho("Gerador X", 1500.0f, 10);
        RequisicaoLocacao requisicao = new RequisicaoLocacao(cliente, aparelho, 2);

        try (Socket socket = new Socket(servidor, porta);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Enviar JSON da requisição
            String jsonReq = gson.toJson(requisicao);
            out.println(jsonReq);
            System.out.println("Requisição enviada: " + jsonReq);

            // Ler resposta JSON
            String jsonResp = in.readLine();
            RespostaLocacao resposta = gson.fromJson(jsonResp, RespostaLocacao.class);
            System.out.println("Resposta recebida: " + resposta.getMensagem());

        } catch (IOException e) {
            System.err.println("Erro na comunicação: " + e.getMessage());
        }
    }
}
