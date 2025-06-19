package service;

import pojo.Aparelho;
import pojo.Cliente;
import pojo.RequisicaoLocacao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocacaoImpl extends UnicastRemoteObject implements Locacao {

    private final List<Aparelho> estoque = new ArrayList<>();
    private final List<RequisicaoLocacao> historico = new ArrayList<>();
    private final Gson gson = new Gson();

    public LocacaoImpl() throws RemoteException {
        super();
        estoque.add(new Aparelho("Gerador", 1500.0f, 5));
        estoque.add(new Aparelho("Mesa", 200.0f, 10));
        estoque.add(new Aparelho("Palco", 1000.0f, 2));
        estoque.add(new Aparelho("Talher", 50.0f, 100));
    }

    @Override
    public byte[] doOperation(String objectReference, String methodId, byte[] arguments) throws RemoteException {
        System.out.println("Requisição recebida:");
        System.out.println("- objeto remoto: " + objectReference);
        System.out.println("- método: " + methodId);
        System.out.println("- dados (JSON): " + new String(arguments));

        return switch (methodId) {
            case "registrarLocacao" -> registrarLocacao(arguments);
            default -> gson.toJson("Método não reconhecido.").getBytes();
        };
    }

    @Override
    public byte[] registrarLocacao(byte[] locacaoJson) throws RemoteException {
        try {
            RequisicaoLocacao locacao = gson.fromJson(new String(locacaoJson), RequisicaoLocacao.class);
            Aparelho aparelhoSolicitado = locacao.getAparelho();
            int qtdSolicitada = locacao.getQuantidade();

            for (Aparelho a : estoque) {
                if (a.getNome().equalsIgnoreCase(aparelhoSolicitado.getNome())) {
                    if (a.getQtd() >= qtdSolicitada) {
                        a.setQtd(a.getQtd() - qtdSolicitada);
                        historico.add(locacao);
                        return gson.toJson("Locação realizada com sucesso!").getBytes();
                    } else {
                        return gson.toJson("Quantidade indisponível para o aparelho solicitado.").getBytes();
                    }
                }
            }

            return gson.toJson("Aparelho não encontrado no estoque.").getBytes();

        } catch (Exception e) {
            return gson.toJson("Erro ao processar a locação: " + e.getMessage()).getBytes();
        }
    }

    
}
