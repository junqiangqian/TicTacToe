/**
 * Created by jq on 18/01/17.
 */
public class Main {

    static final int BOARD_SIZE = 3;

    public static void main(String[] args) {
        Board board = new Board(BOARD_SIZE) ;
        TicTacToe game = new TicTacToe(board);
        game.play();
    }
}
