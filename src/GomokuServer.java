import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GomokuServer extends UnicastRemoteObject implements GomokuInterface {
    private Gomuko jogo;

    public GomokuServer() throws RemoteException {
        super();
        jogo = new Gomuko();
    }

    @Override
    public void print() throws RemoteException {
        jogo.print();
    }

    @Override
    public boolean jogar(int linha, int coluna) throws RemoteException {
        return jogo.jogar(linha, coluna);
    }

    @Override
    public boolean verificarVencedor() throws RemoteException {
        return jogo.verificarVencedor();
    }

    @Override
    public char getJogadorAtual() throws RemoteException {
        return jogo.getJogadorAtual();
    }

    public static void main(String[] args) {
        try {
            GomokuServer server = new GomokuServer();
            java.rmi.Naming.rebind("rmi://192.168.0.6/Gomoku", server);
            System.out.println("Servidor de Gomoku pronto!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
