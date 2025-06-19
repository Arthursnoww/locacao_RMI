package pojo;

public class Aparelho {
    private String nome;
    private float preco;
    private int qtd;

    public Aparelho(String nome, float preco, int qtd) {
        this.nome = nome;
        this.preco = preco;
        this.qtd = qtd;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
    public int getQtd() {
        return qtd;
    }
    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public String toString() {
        return "Aparelho [nome=" + nome + ", preco=" + preco + ", quantidadeDisponivel=" + qtd + "]";
    }
    
}

