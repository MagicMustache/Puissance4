import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid {

    ArrayList<Token>[] tokens = new ArrayList[7];
    Color colorOne;
    Color colorTwo;
    Integer clickCount = 0;
    JPanel gridPanel;
    private JButton firstColumnButton;
    private JButton secondColumnButton;
    private JButton thirdColumnButton;
    private JButton fourthColumnButton;
    private JButton fifthColumnButton;
    private JButton sixthColumnButton;
    private JButton seventhColumnButton;
    private JPanel oneOneSquare;

    public Grid(Color colorOne, Color colorTwo) {
        this.colorOne = colorOne;
        this.colorTwo = colorTwo;
        Arrays.fill(tokens, new ArrayList<Token>());
    }

    private void addToken(Integer column, Color color) {
        tokens[column + 1].add(new Token(color));
        // TODO Refresh UI with new token and message to say 'next player turn'
    }

    public Integer winner() {
        // TODO
        return 0;
    }

}
