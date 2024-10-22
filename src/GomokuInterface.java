import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GomokuInterface extends Remote {
    void print() throws RemoteException;
    boolean jogar(int linha, int coluna) throws RemoteException;
    boolean verificarVencedor() throws RemoteException;
    char getJogadorAtual() throws RemoteException;
}