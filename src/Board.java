/**
 * Created by jq on 18/01/17.
 */
public class Board {

    private char[][] board;

    public Board(final int boardSize) {
        this.board = new char[boardSize][boardSize];
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
