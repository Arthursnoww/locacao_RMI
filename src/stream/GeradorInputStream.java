package src.stream;

import src.pojo.Gerador;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class GeradorInputStream extends InputStream {
    private InputStream in;

    public GeradorInputStream(InputStream in) {
        this.in = in;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

    public Gerador readGerador() throws IOException {
        byte[] nomeBytes = new byte[20];
        if (in.read(nomeBytes) != 20) throw new IOException("Erro ao ler nome");

        String nome = new String(nomeBytes).trim();

        byte[] precoBytes = new byte[4];
        if (in.read(precoBytes) != 4) throw new IOException("Erro ao ler preco");
        float preco = ByteBuffer.wrap(precoBytes).getFloat();

        byte[] qtdBytes = new byte[4];
        if (in.read(qtdBytes) != 4) throw new IOException("Erro ao ler qtd");
        int qtd = ByteBuffer.wrap(qtdBytes).getInt();

        byte[] potBytes = new byte[4];
        if (in.read(potBytes) != 4) throw new IOException("Erro ao ler potencia");
        int potencia = ByteBuffer.wrap(potBytes).getInt();

        return new Gerador(nome, preco, qtd, potencia);
    }
}
