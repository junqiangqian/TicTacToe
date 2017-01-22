import java.util.Scanner;

/**
 * Created by jq on 18/01/17.
 */
public class Board {

    private final int BOARD_SIZE = 3;

    private char[][] board;
    private int moveCount;
    private boolean gameOver;

    public Board() {
        this.board = new char[BOARD_SIZE][BOARD_SIZE];
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

    public int getBoardSize() {
        return BOARD_SIZE;
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


    public boolean checkValidMove(int y, int x) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[y][x] == 0;
    }

    public boolean isWin(int y, int x) {
        return checkHorizontal(y, x) || checkVertical(y, x) || checkDiagonal(y, x) || checkReverseDiagonal(y, x);
    }

    public boolean isDraw() {
        return moveCount == BOARD_SIZE * BOARD_SIZE;
    }

    public int possibleWaysToWin(int y, int x, char opponentMark) {
        int result = 0;
        boolean possibleToWin = true;
        for (int i = 0; i < BOARD_SIZE; i++) {
            possibleToWin = possibleToWin && (board[i][x] != opponentMark);
        }
        if (possibleToWin) {
            result++;
        }
        possibleToWin = true;
        for (int j = 0; j < BOARD_SIZE; j++) {
            possibleToWin = possibleToWin && (board[y][j] != opponentMark);
        }
        if (possibleToWin) {
            result++;
        }
        possibleToWin = true;
        if (y == x) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                possibleToWin = possibleToWin && (board[i][i] != opponentMark);
            }
            if (possibleToWin) {
                result++;
            }
        }
        if (x + y == BOARD_SIZE - 1) {
            possibleToWin = true;
            for (int j = 0; j < BOARD_SIZE; j++) {
                possibleToWin = possibleToWin && (board[j][BOARD_SIZE - j - 1] != opponentMark);
            }
            if (possibleToWin) {
                result++;
            }
        }
        return result;
    }

    /* Returns true iff position is along diagonal or reverse diagonal */
    private boolean alongDiagonal(int y, int x) {
        return y == x || (x + y) == BOARD_SIZE - 1;
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
