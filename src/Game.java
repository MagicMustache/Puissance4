import java.awt.*;
import java.time.LocalDateTime;

public class Game {

    public LocalDateTime time = LocalDateTime.now();
    public Player playerOne;
    public Player playerTwo;

    private Boolean playerOneWon = false;
    private Boolean playerTwoWon = false;

    public Game(String playerOneName, String playerTwoName) {
        playerOne = new Player(playerOneName, Color.ORANGE);
        playerTwo = new Player(playerTwoName, Color.RED);
    }

    public Boolean isDone() {
        return playerOneWon || playerTwoWon;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }
}
