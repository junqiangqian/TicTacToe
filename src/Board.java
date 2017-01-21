import java.util.Scanner;

/**
 * Created by jq on 18/01/17.
 */
public class Board {

    private static final int BOARD_SIZE = 3;

    private char[][] board;
    private int moveCount;
    private boolean gameOver;

    public Board() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
        /*board[0][0] = 'O';
        board[0][1] = 'X';
        board[0][2] = 'X';
        board[1][0] = 'X';
        board[1][1] = 'X';
        board[1][2] = 'O';
        board[2][0] = 'O';
        board[2][1] = 'O';
        this.moveCount = 8; */
        //this.moveCount = 0;
        board[0][0] = 'X';
        board[2][2] = 'X';
        board[2][0] = 'O';
        this.moveCount = 0;
    }

    public void displayBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void tryMove(int y, int x, char mark) {
        board[y][x] = mark;
        moveCount++;
    }

    public void makeMove(int y, int x, char mark) {
        board[y][x] = mark;
        moveCount++;
        checkIfGameOver(y, x, mark);
    }

    public void undoMove(int y, int x) {
        board[y][x] = 0;
        moveCount--;
    }

    public boolean gameOver() {
        return gameOver;
    }

    private void checkIfGameOver(int y, int x, char mark) {
        if (isWin(y, x)) {
            System.out.printf("Player %c has won\n", mark);
            gameOver = true;
        } else if (isDraw()) {
            System.out.println("Draw");
            gameOver = true;
        } else {
            gameOver = false;
        }
    }

    public boolean checkValidMove(int y, int x) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[y][x] == 0;
    }

    public boolean isWin(int y, int x) {
        return checkHorizontal(y, x) || checkVertical(y, x) || checkDiagonal(y, x) || checkReverseDiagonal(y, x);
    }

    public boolean isDraw() {
        return moveCount == BOARD_SIZE * BOARD_SIZE;
    }

    private boolean checkVertical(int y, int x) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][x] != board[y][x]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(int y, int x) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[y][x] != board[i][i]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkReverseDiagonal(int y, int x) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[y][x] != board[i][board.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    private boolean checkHorizontal(int y, int x) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[y][i] != board[y][x]) {
                return false;
            }
        }
        return true;
    }

}
