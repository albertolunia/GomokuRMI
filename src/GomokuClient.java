import java.rmi.Naming;
import java.util.Scanner;

public class GomokuClient {
    public static void main(String[] args) {
        try {
            // Conecta ao servidor RMI
            GomokuInterface jogo = (GomokuInterface) Naming.lookup("rmi://192.168.0.6/Gomoku");

            Scanner scanner = new Scanner(System.in);

            for (;;) {
                jogo.print();

                System.out.println("Jogador " + jogo.getJogadorAtual() + ", faça sua jogada.");

                System.out.print("Linha (1 a 15): ");
                int linha = scanner.nextInt();

                System.out.print("Coluna (1 a 15): ");
                int coluna = scanner.nextInt();

                if (jogo.jogar(linha, coluna)) {
                    if (jogo.verificarVencedor()) {
                        break;
                    }
                }
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}