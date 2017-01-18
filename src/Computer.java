/**
 * Created by jq on 18/01/17.
 */
public class Computer extends Player {

    public Computer(char mark) {
        super(PlayerType.COMPUTER, mark);
    }

    public void makeMove(Board board) {
        System.out.println(mark);
    }
}
