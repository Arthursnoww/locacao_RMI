package src.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import src.pojo.Aparelho;
import src.pojo.Cliente;
import src.pojo.RequisicaoLocacao;
import src.pojo.RespostaLocacao;

public class LocacaoImpl implements Locacao {

    // Banco de dados fictício de aparelhos
    private static Aparelho[] estoque = {
        new Aparelho("Gerador X", 1500.0f, 10),
        new Aparelho("Mesa Redonda", 200.0f, 5),
        new Aparelho("Palco Médio", 1000.0f, 3),
        new Aparelho("Talher Conjunto", 50.0f, 100)
    };

    // Histórico de locações
    private static List<String> historicoLocacoes = new ArrayList<>();

    @Override
    public RespostaLocacao alugarAparelho(RequisicaoLocacao requisicao) {
        // Verificar disponibilidade do aparelho
        Aparelho aparelhoSolicitado = requisicao.getAparelho();
        int quantidadeSolicitada = requisicao.getQuantidade();

        for (Aparelho aparelho : estoque) {
            if (aparelho.getNome().equals(aparelhoSolicitado.getNome())) {
                if (aparelho.getQtd() >= quantidadeSolicitada) {
                    // Atualizar o estoque
                    aparelho.setQtd(aparelho.getQtd() - quantidadeSolicitada);

                    // Registrar no histórico de locação
                    Cliente cliente = requisicao.getCliente();
                    String registro = "Cliente: " + cliente.getNome() + " alugou " + quantidadeSolicitada + " " + aparelho.getNome();
                    historicoLocacoes.add(registro);

                    // Retornar resposta de sucesso
                    return new RespostaLocacao(true, "Locação realizada com sucesso!");
                } else {
                    // Não há quantidade suficiente
                    return new RespostaLocacao(false, "Quantidade solicitada não disponível.");
                }
            }
        }

        // Caso o aparelho não seja encontrado
        return new RespostaLocacao(false, "Aparelho não encontrado.");
    }

    // Método para mostrar o histórico de locações
    public static List<String> getHistoricoLocacoes() {
        return historicoLocacoes;
    }

    public static String listarEstoque() {
        List<String> linhas = new ArrayList<>();
        for (Aparelho aparelho : estoque) {
            linhas.add(aparelho.getNome() + ": " + aparelho.getQtd() + " unidades disponíveis");
        }
        return new Gson().toJson(linhas);
    }

    

    
    
}
