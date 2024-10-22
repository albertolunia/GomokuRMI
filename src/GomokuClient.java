import java.rmi.Naming;
import java.util.Scanner;

public class GomokuClient {
    public static void main(String[] args) {
        try {
            // Conecta ao servidor RMI
            GomokuInterface jogo = (GomokuInterface) Naming.lookup("rmi://192.168.0.6/Gomoku");

            Scanner scanner = new Scanner(System.in);

            char meuJogador = ' ';  // Define o jogador localmente

            System.out.print("Você é o jogador X ou O? ");
            meuJogador = scanner.next().charAt(0);

            while (true) {
                jogo.print();

                // Espera até que seja a vez deste jogador
                while (jogo.getProximoJogador() != meuJogador) {
                    System.out.println("Aguardando o outro jogador...");
                    Thread.sleep(1000);  // Aguarda 1 segundo antes de checar novamente
                }

                System.out.println("Sua vez, jogador " + meuJogador + ". Faça sua jogada.");

                System.out.print("Linha (1 a 15): ");
                int linha = scanner.nextInt();

                System.out.print("Coluna (1 a 15): ");
                int coluna = scanner.nextInt();

                if (jogo.jogar(linha, coluna)) {
                    if (jogo.verificarVencedor()) {
                        System.out.println("Jogador " + meuJogador + " venceu!");
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