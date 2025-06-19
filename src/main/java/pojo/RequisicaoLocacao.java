package pojo;

public class RequisicaoLocacao {
    private Cliente cliente;
    private Aparelho aparelho;
    private int quantidade;

    public RequisicaoLocacao(Cliente cliente, Aparelho aparelho, int quantidade) {
        this.cliente = cliente;
        this.aparelho = aparelho;
        this.quantidade = quantidade;
    }

    // Adicione estes getters:
    public Cliente getCliente() {
        return cliente;
    }

    public Aparelho getAparelho() {
        return aparelho;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
