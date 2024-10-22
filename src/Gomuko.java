public class Gomuko {
    public static final int TAMANHO_TABULEIRO = 15;
    public static final int PONTOS_GANHAR = 5;

    private final char[][] tabuleiro;
    private char jogadorAtual;

    public Gomuko() {
        tabuleiro = IniciaTabuleiro();
    }

    private char[][] IniciaTabuleiro() {
        final char[][] tabuleiro;
        tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];

        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
        jogadorAtual = 'X';

        return tabuleiro;
    }

    public void print() {
        System.out.print("\n");
        System.out.print("     ");
        for (int i = 1; i <= TAMANHO_TABULEIRO; i++) {
            System.out.printf("| %02d ", i);
        }
        System.out.println(" | ");
        System.out.println(STR."     |\{"-----".repeat(TAMANHO_TABULEIRO)}|");

        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            System.out.printf("%02d   ", i + 1);

            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                System.out.printf("| %2c ", tabuleiro[i][j]);
            }
            System.out.println(" |");

            if (i < TAMANHO_TABULEIRO - 1) {
                System.out.println(STR."     |----\{"+----".repeat(TAMANHO_TABULEIRO - 1)} |");
            } else {
                System.out.println(STR."     |\{"-----".repeat(TAMANHO_TABULEIRO)}|");
            }
        }
    }

    public boolean jogar(int linha, int coluna) {
        if (linha < 1 || linha > TAMANHO_TABULEIRO || coluna < 1 || coluna > TAMANHO_TABULEIRO) {
            System.out.println("Jogada fora dos limites. Tente novamente.");
            return false;
        }

        if (tabuleiro[linha - 1][coluna - 1] != ' ') {
            System.out.println("Posição já ocupada. Tente novamente.");
            return false;
        }

        tabuleiro[linha - 1][coluna - 1] = jogadorAtual;

        if(jogadorAtual == 'X'){
            jogadorAtual = 'O';
        } else {
            jogadorAtual = 'X';
        }

        return true;
    }

    public boolean verificarVencedor() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {

                char jogador = tabuleiro[i][j];

                if (jogador == ' ') {
                    continue;
                }

                if (verificarDirecao(i, j, 0, 1) ||
                        verificarDirecao(i, j, 1, 0) ||
                        verificarDirecao(i, j, 1, 1) ||
                        verificarDirecao(i, j, 1, -1))
                {
                    print();
                    System.out.println(STR."Jogador \{jogador} venceu!");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean verificarDirecao(int linha, int coluna, int deltaLinha, int deltaColuna) {
        char jogador = tabuleiro[linha][coluna];
        int contador = 0;

        for (int k = 0; k < PONTOS_GANHAR; k++) {
            int novaLinha = linha + k * deltaLinha;
            int novaColuna = coluna + k * deltaColuna;

            if (novaLinha < 0 || novaLinha >= TAMANHO_TABULEIRO || novaColuna < 0 || novaColuna >= TAMANHO_TABULEIRO) {
                return false;
            }
            
            if (tabuleiro[novaLinha][novaColuna] == jogador) {
                contador++;
            } else {
                return false;
            }
        }

        return contador == PONTOS_GANHAR;
    }

    public char getJogadorAtual() {
        return jogadorAtual;
    }
}