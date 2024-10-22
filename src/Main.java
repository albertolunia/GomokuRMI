import java.util.Scanner;

public static void main(String[] args) {
    Gomuko gomoku = new Gomuko();
    Scanner scanner = new Scanner(System.in);

    for(;;) {
        gomoku.print();

        System.out.println(STR."Jogador \{gomoku.getJogadorAtual()}, faça sua jogada.");

        System.out.print("Linha (1 a 15): ");
        int linha = scanner.nextInt();

        System.out.print("Coluna (1 a 15): ");
        int coluna = scanner.nextInt();

        if (gomoku.jogar(linha, coluna)) {
            if (gomoku.verificarVencedor()) {
                break;
            }
        }
    }

    scanner.close();
}