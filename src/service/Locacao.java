package src.service;

import src.pojo.RequisicaoLocacao;
import src.pojo.RespostaLocacao;

public interface Locacao {
    RespostaLocacao alugarAparelho(RequisicaoLocacao requisicao);
}
