import java.util.ArrayList;
import java.util.List;

/**
 * Created by jq on 18/01/17.
 */
public class Computer extends Player {

    private List<Integer> possibleMoves;


    public Computer(char mark) {
        super(PlayerType.COMPUTER, mark);
        this.possibleMoves = new ArrayList<Integer>();
    }

    public void makeMove(Board board) {
        updatePossibleMoves(board);
        int move = generateMove(board);
        int y = move / 3, x = move % 3;
        System.out.printf("The computer (player %c) chose position %d %d\n", mark, y, x);
        board.makeMove(y, x, mark);
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
        int maxPossibleWaysToWin = 0;
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
            board.tryMove(y, x, mark);
            int possibleWaysToWin  = board.possibleWaysToWin(y, x, opponentMark);
            if (possibleWaysToWin > maxPossibleWaysToWin) {
                maxPossibleWaysToWin = possibleWaysToWin;
                move = i;
            }
            board.undoMove(y, x);
        }
        return move;
    }
}