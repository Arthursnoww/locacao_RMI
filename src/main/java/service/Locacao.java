package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Locacao extends Remote {
    byte[] doOperation(String objectReference, String methodId, byte[] arguments) throws RemoteException;
    byte[] registrarLocacao(byte[] locacaoJson) throws RemoteException;
    byte[] listarEstoqueDisponivel() throws RemoteException;
    byte[] listarEmprestimosRealizados() throws RemoteException;
    
}
