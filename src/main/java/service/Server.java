package service;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
    public static void main(String[] args) {
        try {
            // Inicia o registry na porta padrão 1099
            LocateRegistry.createRegistry(1099);

            // Cria e registra o serviço remoto
            LocacaoImpl locacao = new LocacaoImpl();
            Naming.rebind("LocacaoService", locacao);

            System.out.println("Locação de Aparelhos RMI");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


