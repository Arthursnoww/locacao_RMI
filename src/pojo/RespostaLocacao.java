package src.pojo;

public class RespostaLocacao {
    private boolean sucesso;
    private String mensagem;

    public RespostaLocacao(boolean sucesso, String mensagem) {
        this.sucesso = sucesso;
        this.mensagem = mensagem;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public String getMensagem() {       // <<<<<<<<<<<<< Aqui o getter que faltava
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

