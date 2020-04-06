import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Grid {

    public JPanel gridPanel;
    private JPanel cards;
    private JPanel buttonsPanel;
    private JPanel squaresPanel;
    private JLabel nextPlayerLabel;
    private JButton[] buttons = new JButton[7];
    private JPanel[][] squares = new JPanel[7][6];

    private static int rowsCount = 6;
    private static int columnsCount = 7;
    private Game game;
    private ArrayList<Game> games;
    private ArrayList<ArrayList<Token>> tokens = new ArrayList<ArrayList<Token>>();
    private int clicksCount = 0;


    public Grid(JPanel cards, String playerOneName, String playerTwoName, ArrayList<Game> games) {
        this.cards = cards;
        game = new Game(playerOneName, playerTwoName);
        this.games = games;
        resetGame();
        setupUI();
    }

    private void resetGame() {
        addGameToHistory();
        game = new Game(game.playerOne.name, game.playerTwo.name);
        tokens.clear();
        for (int i = 0; i < columnsCount; i++) {
            tokens.add(i, new ArrayList<Token>());
        }
        updateGameStatus();
    }

    private void setupUI() {
        setupButtons();
        setupGrid();
    }

    private void setupButtons() {
        GridLayout gridLayout = new GridLayout(1, columnsCount);
        buttonsPanel.setLayout(gridLayout);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new BasicArrowButton(BasicArrowButton.SOUTH);
            int finalI = i;
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addToken(finalI, currentPlayer());
                }
            });
            buttonsPanel.add(buttons[i]);
        }
    }

    private void setupGrid() {
        GridLayout gridLayout = new GridLayout(rowsCount, columnsCount);
        squaresPanel.setLayout(gridLayout);
        for (int row = 0; row < rowsCount; row++) {
            for (int column = 0; column < columnsCount; column++) {
                squares[column][row] = new JPanel();
                squares[column][row].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                squaresPanel.add(squares[column][row]);
            }
        }
    }

    private void addToken(int column, Player player) {
        if (tokens.get(column).size() == rowsCount) {
            return;
        }
        clicksCount += 1;
        tokens.get(column).add(new Token(player));
        int row = tokens.get(column).size() - 1;
        squares[column][rowsCount - row - 1].setBackground(tokens.get(column).get(row).player.color);
        updateGameStatus();
    }

    private Player currentPlayer() {
        if (clicksCount % 2 == 0) {
            return game.playerOne;
        } else {
            return game.playerTwo;
        }
    }

    private void updateGameStatus() {
        nextPlayerLabel.setText("It's " + currentPlayer().getName() + " turn !");
        // TODO Check if someone won (using `winner()`) and show a popup.
        // TODO Show a popup if the grid is full but no one won.

        // Start Debug
        if (gridIsFull()) {
            Popup popup = new Popup(gridPanel, "Done !", "The grid is full", "Replay", "OK");
            popup.show();
            if (popup.getChoice() == Popup.Choice.RIGHT) {
                addGameToHistory();
                switchToIntroductionPanel();
            } else {
                for (int row = 0; row < rowsCount; row++) {
                    for (int column = 0; column < columnsCount; column++) {
                        squares[column][row].setBackground(Color.white);
                    }
                }
                resetGame();
            }
        }
        // End Debug
    }

    private boolean gridIsFull() {
        boolean full = true;
        int columnIndex = 0;
        while (full && columnIndex < tokens.size()) {
            full = tokens.get(columnIndex).size() == rowsCount;
            columnIndex += 1;
        }
        return full;
    }

    private int winner() {
        // TODO Also set the winner state in Game object
        return -1;
    }

    private void addGameToHistory() {
        //TODO dont forget to remove commentary
        //if (game.isDone()) {
            games.add(game);
        //}
    }

    private void switchToIntroductionPanel() {
        CardLayout cl = (CardLayout) cards.getLayout();
        System.out.println(games.size());
        cards.add(new Introduction(cards, games).introductionPanel, "introduction");
        cl.show(cards, "introduction");
    }

}
