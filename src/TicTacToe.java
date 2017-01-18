import java.util.Scanner;

public class TicTacToe {

    static final int BOARD_SIZE = 3;

    private Board board;
    private Player player1;
    private Player player2;

    public TicTacToe(Board board) {
        this.board = board;
    }

    public void play() {
       start();
    }

    private void start() {
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
        this.player1 = new Human('x');
        this.player2 = new Human('o');
        System.out.println("Human vs Human Mode");
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

}
