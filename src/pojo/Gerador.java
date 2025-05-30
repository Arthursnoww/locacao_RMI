package src.pojo;

public class Gerador extends Aparelho {
    private int potencia; // potência em watts

    // Construtor que recebe todos os parâmetros, incluindo os da superclasse
    public Gerador(String nome, float preco, int qtd, int potencia) {
        super(nome, preco, qtd);  // chama o construtor da superclasse
        this.potencia = potencia;
    }

    // Getter para potencia
    public int getPotencia() {
        return potencia;
    }

    // Setter para potencia
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }


    public String toString() {
        return "Gerador [nome=" + getNome() + 
               ", preco=" + getPreco() + 
               ", quantidadeDisponivel=" + getQtd() + 
               ", potencia=" + potencia + "]";
    }
}
