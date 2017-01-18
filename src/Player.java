/**
 * Created by jq on 18/01/17.
 */
public abstract class Player {
    private PlayerType playerType;
    protected char mark;

    public Player(PlayerType playerType, char mark) {
        this.playerType = playerType;
        this.mark = mark;
    }

    public abstract void makeMove(Board board);

}
