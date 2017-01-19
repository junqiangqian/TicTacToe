import java.util.Scanner;

public class TicTacToe {

    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';

    public TicTacToe() {
        this.board = new Board();
    }

    public void play() {
        System.out.println("Welcome to TicTacToe, please enter which mode which you would like to play");
        Scanner scanner = new Scanner(System.in);
        int mode;
        do {
            System.out.println("0 - Human vs Human, 1 - Human vs Computer, 2 - Computer vs Computer");
            while (!scanner.hasNextInt()) {
                System.out.println("Error, not a number, please try again");
                scanner.next();
            }
            mode = scanner.nextInt();
        } while (mode < 0 || mode > 2);
        initialisePlayers(mode);
        int turn;
        do {
            System.out.println("Which player should go first? Enter 1 for Player X, 2 for Player O");
            while (!scanner.hasNextInt()) {
                System.out.println("Error, not a number, please try again");
                scanner.next();
            }
            turn = scanner.nextInt();
        } while (!(turn == 1 || turn == 2));
        currentPlayer = turn == 1? player1: player2;
        gameLoop();
    }

    private void initialisePlayers(int mode) {
        switch(mode) {
            case 0:
                this.player1 = new Human(PLAYER_ONE);
                this.player2 = new Human(PLAYER_TWO);
                break;
            case 1:
                this.player1 = new Human(PLAYER_ONE);
                this.player2 = new Computer(PLAYER_TWO);
                break;
            case 2:
                this.player1 = new Computer(PLAYER_ONE);
                this.player2 = new Computer(PLAYER_TWO);
                break;
            default:
                System.out.println("Error, invalid mode");
        }
    }

    private void switchTurn() {
        currentPlayer = currentPlayer.mark == PLAYER_ONE? player2: player1;
    }

    private void gameLoop() {
        while (!board.gameOver()) {
            System.out.printf("It is Player %c's turn\n", currentPlayer.mark);
            currentPlayer.makeMove(board);
            board.displayBoard();
            switchTurn();
        }
    }

}
