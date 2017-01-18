import java.util.Scanner;

/**
 * Created by jq on 18/01/17.
 */
public class Board {

    private char[][] board;
    private int moveCount;

    public Board(final int boardSize) {
        this.board = new char[boardSize][boardSize];
        this.moveCount = 0;
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void makeMove(char mark) {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Please enter a valid move, 0 0 is top left, 0 1 is top middle, 0 2 is top right etc");
            while (!scanner.hasNextInt()) {
                System.out.println("Not a number, try again");
                scanner.next();
            }
            y = scanner.nextInt();
            while (!scanner.hasNextInt()) {
                System.out.println("Not a number, try again");
                scanner.next();
            }
            x = scanner.nextInt();
        } while (!checkValidMove(y, x));
        board[y][x] = mark;
        moveCount++;
        checkWin(y, x);
    }

    private boolean checkValidMove(int y, int x) {
        return x >= 0 && x < board[0].length && y >= 0 && y < board.length && board[y][x] == 0;
    }

    private boolean checkWin(int y, int x) {
        return checkHorizontal(y, x) || checkVertical(y, x) || checkDiagonal(y, x) || checkReverseDiagonal(y, x);
    }

    private boolean checkVertical(int y, int x) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][x] != board[y][x]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(int y, int x) {
        for (int i = 0; i < board.length; i++) {
            if (board[y][x] != board[i][i]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkReverseDiagonal(int y, int x) {
        for (int i = 0; i < board.length; i++) {
            if (board[y][x] != board[i][board.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkHorizontal(int y, int x) {
        for (int i = 0; i < board[0].length; i++) {
            if (board[y][i] != board[y][x]) {
                return false;
            }
        }
        return true;
    }

}
