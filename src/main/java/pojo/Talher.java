package pojo;

public class Talher extends Aparelho {
    private String tipo;  // tipo do talher, ex: garfo, faca, colher

    public Talher(String nome, float preco, int qtd, String tipo) {
        super(nome, preco, qtd);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Talher [nome=" + getNome() +
               ", preco=" + getPreco() +
               ", quantidadeDisponivel=" + getQtd() +
               ", tipo=" + tipo + "]";
    }
}
