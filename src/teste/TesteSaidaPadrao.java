package src.teste;

import src.pojo.Gerador;
import src.stream.GeradorOutputStream;
import src.stream.GeradorInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TesteSaidaPadrao {
    public static void main(String[] args) throws IOException {
        Gerador[] lista = {
            new Gerador("Gerador 1", 1000.0f, 5, 3000),
            new Gerador("Gerador 2", 1500.0f, 3, 5000)
        };

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GeradorOutputStream gos = new GeradorOutputStream(lista, baos);
        gos.writeAll();

        byte[] bytes = baos.toByteArray();
        System.out.println("Bytes escritos: " + bytes.length);

        GeradorInputStream gis = new GeradorInputStream(new ByteArrayInputStream(bytes));
        for (int i = 0; i < lista.length; i++) {
            Gerador g = gis.readGerador();
            System.out.println("Gerador lido: " + g);
        }
    }
}
