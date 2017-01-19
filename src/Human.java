import java.util.Scanner;

/**
 * Created by jq on 18/01/17.
 */
public class Human extends Player {

    public Human(char mark) {
        super(PlayerType.HUMAN, mark);
    }

    public void makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        int x, y;
        do {
            System.out.println("Please enter a valid move, 0 0 is top left, 0 1 is top middle, 0 2 is top right etc");
            while (!scanner.hasNextInt()) {
                System.out.println("Not a number, try again");
                scanner.next();
            }
            y = scanner.nextInt();
            while (!scanner.hasNextInt()) {
                System.out.println("Not a number, try again");
                scanner.next();
            }
            x = scanner.nextInt();
        } while (!board.checkValidMove(y, x));
        board.makeMove(y, x, mark);
    }

}