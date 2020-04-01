import java.awt.*;
import java.time.LocalDateTime;

public class Game {

    private LocalDateTime time = LocalDateTime.now();
    // grid
    private Player playerOne;
    private Player playerTwo;
    private Boolean playerOneWon = false;
    private Boolean playerTwoWon = false;
    private Popup endPopup;

    public Game(String playerOneName, String playerTwoName) {
        playerOne = new Player(playerOneName, Color.ORANGE);
        playerTwo = new Player(playerTwoName, Color.RED);
    }

}
