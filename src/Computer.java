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
        int boardSize = board.getBoardSize();
        int y = move / boardSize, x = move % boardSize;
        System.out.println("The computer is thinking...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("The computer (player %c) chose position %d %d\n", mark, y, x);
        board.makeMove(y, x, mark);
    }

    private void updatePossibleMoves(Board board) {
        int boardSize = board.getBoardSize();
        possibleMoves.clear();
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board.checkValidMove(i, j)) {
                    possibleMoves.add(i * boardSize + j);
                }
            }
        }
    }

    private int generateMove(Board board) {
        char opponentMark = mark == 'O'? 'X' : 'O';
        int move = -1, boardSize = board.getBoardSize(), maxPossibleWaysToWin = 0;
        for (Integer i : possibleMoves) {
            int y = i / boardSize, x = i % boardSize;
            board.tryMove(y, x, mark);
            if (board.isWin(y, x, mark) || board.isDraw()) {
                board.undoMove(y, x);
                return i;
            }
            board.undoMove(y, x);
            board.tryMove(y, x, opponentMark);
            if (board.isWin(y, x, opponentMark)) {
                board.undoMove(y, x);
                return i;
            }
            board.undoMove(y, x);
            board.tryMove(y, x, mark);
            int possibleWaysToWin  = board.possibleWaysToWin(y, x, opponentMark);
            if (possibleWaysToWin > maxPossibleWaysToWin || possibleWaysToWin == maxPossibleWaysToWin
                    && board.isCorner(y, x)) {
                maxPossibleWaysToWin = possibleWaysToWin;
                move = i;
            }
            board.undoMove(y, x);
        }
        return move;
    }
}