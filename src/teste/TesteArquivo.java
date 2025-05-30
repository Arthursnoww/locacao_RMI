package src.teste;

import src.pojo.Gerador;
import src.stream.GeradorOutputStream;
import src.stream.GeradorInputStream;

import java.io.*;

public class TesteArquivo {
    public static void main(String[] args) {
        Gerador[] lista = {
            new Gerador("Gerador 1", 1000.0f, 5, 3000),
            new Gerador("Gerador 2", 1500.0f, 3, 5000)
        };

        String nomeArquivo = "geradores.dat";

        // Gravando no arquivo
        try (FileOutputStream fos = new FileOutputStream(nomeArquivo);
             GeradorOutputStream gos = new GeradorOutputStream(lista, fos)) {
            gos.writeAll();
            System.out.println("Objetos gravados com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lendo do arquivo
        try (FileInputStream fis = new FileInputStream(nomeArquivo);
             GeradorInputStream gis = new GeradorInputStream(fis)) {
            
            for (int i = 0; i < lista.length; i++) {
                Gerador g = gis.readGerador();
                System.out.println("Gerador lido: " + g);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

