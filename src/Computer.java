import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jq on 18/01/17.
 */
public class Computer extends Player {

    private List<Integer> possibleMoves;
    private int[] positionScores = {2, 1, 2, 1, 3, 1, 2, 1, 2};

    public Computer(char mark) {
        super(PlayerType.COMPUTER, mark);
        this.possibleMoves = new ArrayList<Integer>();
    }

    public void makeMove(Board board) {
        int x = 0, y = 0;
        updatePossibleMoves(board);
        int move = generateMove(board);
        System.out.printf("The computer (player %c) chose position %d %d\n", mark, move / 3, move % 3);
        board.makeMove(move / 3, move % 3, mark);
    }

    private void updatePossibleMoves(Board board) {
        possibleMoves.clear();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.checkValidMove(i, j)) {
                    possibleMoves.add(i * 3 + j);
                }
            }
        }
    }

    private int generateMove(Board board) {
        char opponentMark = mark == 'O'? 'X' : 'O';
        int move = -1;
        for (Integer i : possibleMoves) {
            int y = i / 3, x = i % 3;
            board.tryMove(y, x, mark);
            if (board.isWin(y, x) || board.isDraw()) {
                board.undoMove(y, x);
                return i;
            }
            board.undoMove(y, x);
            board.tryMove(y, x, opponentMark);
            if (board.isWin(y, x)) {
                board.undoMove(y, x);
                return i;
            }
            board.undoMove(y, x);
            if (positionScores[i] > move) {
                move = i;
            }
        }
        return move;
    }
}