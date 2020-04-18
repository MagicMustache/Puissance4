import java.awt.*;
import java.time.LocalDateTime;

public class Game {

    public LocalDateTime time = LocalDateTime.now();
    private Player playerOne;
    private Player playerTwo;
    private Player winner = null;

    public Game(String playerOneName, String playerTwoName) {
        playerOne = new Player(playerOneName, Color.ORANGE);
        playerTwo = new Player(playerTwoName, Color.RED);
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

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
