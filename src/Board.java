import com.sun.deploy.util.StringUtils;

import java.util.Collections;

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
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }
        this.moveCount = 0;
    }

    public void displayBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < BOARD_SIZE - 1) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < BOARD_SIZE - 1) {
                System.out.println(String.join("", Collections.nCopies(BOARD_SIZE, "===")));
            }
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
        board[y][x] = ' ';
        moveCount--;
    }

    public boolean gameOver() {
        return gameOver;
    }


    public boolean checkValidMove(int y, int x) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[y][x] == ' ';
    }

    public boolean isWin(int y, int x, char mark) {
        return checkHorizontal(y, mark) || checkVertical(x, mark) || checkDiagonal(mark) || checkReverseDiagonal(mark);
    }

    public boolean isDraw() {
        return moveCount == BOARD_SIZE * BOARD_SIZE;
    }

    public int possibleWaysToWin(int y, int x, char opponentMark) {
        int result = 0;
        if (checkIfPossibleWinningColumn(x, opponentMark)) {
            result++;
        }
        if (checkIfPossibleWinningRow(y, opponentMark)) {
            result++;
        }
        if (y == x && checkIfPossibleWinningDiagonal(opponentMark)) {
            result++;
        }
        if (x + y == BOARD_SIZE - 1 && checkIfPossibleWinningReverseDiagonal(opponentMark)) {
            result++;
        }
        return result;
    }

    public boolean isCorner(int y, int x) {
        return y == 0 && x == 0 || y == 0 && x == BOARD_SIZE - 1 || y == BOARD_SIZE - 1 && x == BOARD_SIZE - 1
                ||  y == BOARD_SIZE - 1 && x == 0;
    }

    private void checkIfGameOver(int y, int x, char mark) {
        if (isWin(y, x, mark)) {
            System.out.printf("Player %c has won\n", mark);
            gameOver = true;
        } else if (isDraw()) {
            System.out.println("Draw");
            gameOver = true;
        } else {
            gameOver = false;
        }
    }

    private boolean checkVertical(int x, char mark) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][x] != mark) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(char mark) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][i] != mark) {
                return false;
            }
        }
        return true;
    }

    private boolean checkReverseDiagonal(char mark) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][board.length - 1 - i] != mark) {
                return false;
            }
        }
        return true;
    }

    private boolean checkHorizontal(int y, char mark) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[y][i] != mark) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfPossibleWinningRow(int y, char mark) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[y][i] == mark) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfPossibleWinningColumn(int x, char mark) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][x] == mark) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfPossibleWinningDiagonal(char mark) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][i] == mark) {
                return false;
            }
        }
        return true;
    }

    private boolean checkIfPossibleWinningReverseDiagonal(char mark) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (board[i][BOARD_SIZE - 1 - i] == mark) {
                return false;
            }
        }
        return true;
    }

}
