/**
 * Created by jq on 18/01/17.
 */
public class Human extends Player {

    public Human(char mark) {
        super(PlayerType.HUMAN, mark);
    }

    public void makeMove(Board board) {
        System.out.println(mark);
    }

}
