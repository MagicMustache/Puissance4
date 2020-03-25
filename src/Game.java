import java.awt.*;
import java.time.LocalDateTime;

public class Game {

    LocalDateTime time = LocalDateTime.now();
    // grid
    Player playerOne;
    Player playerTwo;
    Boolean playerOneWon = false;
    Boolean playerTwoWon = false;
    Popup endPopup;

    public Game(String playerOneName, String playerTwoName) {
        playerOne = new Player(playerOneName, Color.ORANGE);
        playerTwo = new Player(playerTwoName, Color.RED);
    }

}
