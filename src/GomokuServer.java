import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GomokuServer extends UnicastRemoteObject implements GomokuInterface {
    private Gomuko jogo;
    private char jogadorAtual;

    public GomokuServer() throws RemoteException {
        super();
        jogo = new Gomuko();
        jogadorAtual = 'X';
    }

    @Override
    public void print() throws RemoteException {
        jogo.print();
    }

    @Override
    public boolean jogar(int linha, int coluna) throws RemoteException {
        if (jogo.getJogadorAtual() != jogadorAtual) {  // Verifica se é a vez do jogador correto
            System.out.println("Não é sua vez de jogar!");
            return false;
        }

        boolean jogadaValida = jogo.jogar(linha, coluna);
        if (jogadaValida) {
            // Alterna para o próximo jogador
            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }
        return jogadaValida;
    }

    @Override
    public boolean verificarVencedor() throws RemoteException {
        return jogo.verificarVencedor();
    }

    @Override
    public char getJogadorAtual() throws RemoteException {
        return jogo.getJogadorAtual();
    }

    public char getProximoJogador() throws RemoteException {
        return jogadorAtual;
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
