import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid {

    ArrayList<Token>[] tokens = new ArrayList[7];
    Button[] buttons;
    Color colorOne;
    Color colorTwo;
    Integer clickCount = 0;

    public Grid(Color colorOne, Color colorTwo) {
        this.colorOne = colorOne;
        this.colorTwo = colorTwo;
        Arrays.fill(tokens, new ArrayList<Token>());
        // TODO Init the 7 buttons with position, etc.
        show();
    }

    public void show() {
        // TODO
    }

    public void hide() {
        // TODO
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
