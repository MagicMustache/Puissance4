import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid {

    public JPanel gridPanel;
    private JPanel buttonsPanel;
    private JPanel squaresPanel;
    private JLabel nextPlayerLabel;
    private JButton[] buttons = new JButton[7];
    private JPanel[][] squares = new JPanel[7][6];

    private static int rowsCount = 6;
    private static int columnsCount = 7;
    private Game game;
    private ArrayList<ArrayList<Token>> tokens = new ArrayList<ArrayList<Token>>();
    private int clicksCount = 0;


    public Grid(String playerOneName, String playerTwoName) {
        game = new Game(playerOneName, playerTwoName);
        resetGame();
        setupUI();
    }

    private void resetGame() {
        // TODO Save current game to history if not null before creating the new one
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
        if (tokens.get(column).size() == rowsCount) { return; }
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
        if (gridIsFull()) {
            Popup popup = new Popup(gridPanel, "Done !", "The grid is full", "Replay", "OK");
            popup.show();
            // TODO Show a popup if the grid is full but no one won.
        }
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

}
