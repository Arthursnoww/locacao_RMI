package src.pojo;

public class Palco extends Aparelho {
    private float area;  // área do palco em metros quadrados

    public Palco(String nome, float preco, int qtd, float area) {
        super(nome, preco, qtd);
        this.area = area;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Palco [nome=" + getNome() +
               ", preco=" + getPreco() +
               ", quantidadeDisponivel=" + getQtd() +
               ", area=" + area + "]";
    }
}

