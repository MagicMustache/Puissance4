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

    public Boolean gameIsDone() {
        return !playerOneWon && !playerTwoWon;
    }

    public void setWinnerToPlayerOne(Boolean playerOneIsTheWinner) {
        playerOneWon = playerOneIsTheWinner;
        playerTwoWon = !playerOneIsTheWinner;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }
}
