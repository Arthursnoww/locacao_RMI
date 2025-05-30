package src.stream;

import src.pojo.Gerador;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class GeradorOutputStream extends OutputStream {
    private OutputStream out;
    private Gerador[] geradores;
    private int index = 0;

    public GeradorOutputStream(Gerador[] geradores, OutputStream out) {
        this.geradores = geradores;
        this.out = out;
    }

    // Exemplo: escreve um gerador por vez (simplificado)
    @Override
    public void write(int b) throws IOException {
        // Não usado nesse contexto
    }

    public void writeAll() throws IOException {
        for (Gerador g : geradores) {
            writeGerador(g);
        }
        out.flush();
    }

    private void writeGerador(Gerador g) throws IOException {
        // Exemplo de serialização simples (nome fixo 20 bytes, preco float 4 bytes, qtd int 4 bytes, potencia int 4 bytes)
        byte[] nomeBytes = new byte[20];
        byte[] nameSrc = g.getNome().getBytes();
        System.arraycopy(nameSrc, 0, nomeBytes, 0, Math.min(nameSrc.length, 20));

        out.write(nomeBytes);

        // preço
        byte[] precoBytes = ByteBuffer.allocate(4).putFloat(g.getPreco()).array();
        out.write(precoBytes);

        // quantidade
        byte[] qtdBytes = ByteBuffer.allocate(4).putInt(g.getQtd()).array();
        out.write(qtdBytes);

        // potencia
        byte[] potBytes = ByteBuffer.allocate(4).putInt(g.getPotencia()).array();
        out.write(potBytes);
    }
}
