import java.util.Scanner;

public class TicTacToe {

    private Board board;
    private Player player1;
    private Player player2;
    private char currentPlayer;

    private static final char PLAYER_ONE = 'X';
    private static final char PLAYER_TWO = 'O';

    public TicTacToe(Board board) {
        this.board = board;
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
        int turn;
        do {
            System.out.println("Which player should go first? Enter 1 for Player X, 2 for Player O");
            while (!scanner.hasNextInt()) {
                System.out.println("Error, not a number, please try again");
                scanner.next();
            }
            turn = scanner.nextInt();
        } while (!(turn == 1 || turn == 2));
        currentPlayer = turn == 1? PLAYER_ONE : PLAYER_TWO;
        playMode(mode);
    }

    private void playMode(int mode) {
        switch(mode) {
            case 0:
                humanVsHuman();
                break;
            case 1:
                humanVsComputer();
                break;
            case 2:
                computerVsComputer();
                break;
            default:
                System.out.println("Error, invalid mode");
        }
    }

    private void humanVsHuman() {
        this.player1 = new Human(PLAYER_ONE);
        this.player2 = new Human(PLAYER_TWO);
        System.out.println("Human vs Human Mode");
        while (!board.gameOver()) {
            System.out.printf("It is Player %c's turn\n", currentPlayer);
            board.makeMove(currentPlayer);
            board.displayBoard();
            switchTurn();
        }
    }

    private void humanVsComputer() {
        this.player1 = new Human('x');
        this.player2 = new Computer('o');
        System.out.println("Human vs Computer Mode");
    }

    private void computerVsComputer() {
        this.player1 = new Computer('x');
        this.player2 = new Computer('o');
        System.out.println("Computer vs Computer Mode");
    }

    private void switchTurn() {
        currentPlayer = currentPlayer == PLAYER_ONE? PLAYER_TWO : PLAYER_ONE;
    }

}
