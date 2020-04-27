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
    private CirclePanel[][] squares = new CirclePanel[7][6];

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
        setupUI();
        resetGame();
    }

    private void resetGame() {
        game = new Game(game.getPlayerOne().getName(), game.getPlayerTwo().getName());
        tokens.clear();
        for (int i = 0; i < columnsCount; i++) {
            tokens.add(i, new ArrayList<Token>());
        }
        for (int row = 0; row < rowsCount; row++) {
            for (int column = 0; column < columnsCount; column++) {
                squares[column][row].removeCircle();
            }
        }
        updateGameStatus(-1, game.getPlayerOne());
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
                squares[column][row] = new CirclePanel();
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
        squares[column][rowsCount - row - 1].addCircle(tokens.get(column).get(row).getPlayer().getColor());
        updateGameStatus(column, player);
    }

    private Player currentPlayer() {
        if (clicksCount % 2 == 0) {
            return game.getPlayerOne();
        } else {
            return game.getPlayerTwo();
        }
    }

    private void updateGameStatus(int lastColumn, Player lastPlayer) {
        nextPlayerLabel.setText("It's " + currentPlayer().getName() + " turn !");

        if(lastColumn == -1) { return; }

        if (winner(lastPlayer, lastColumn, tokens.get(lastColumn).size() - 1)) {
            Popup popup = new Popup(gridPanel, "Bravo !", lastPlayer.getName()+" a gagné !!", "Rejouer", "Terminer");
            popup.show();
            if (popup.getChoice() == Popup.Choice.RIGHT) {
                switchToIntroductionPanel();
            } else {
                resetGame();
            }
        } else if (gridIsFull()) {
            Popup popup = new Popup(gridPanel, "Personne n'a gagné... :(", "Vous devez remettre ça !", "Rejouer", "Terminer");
            popup.show();
            if (popup.getChoice() == Popup.Choice.RIGHT) {
                switchToIntroductionPanel();
            } else {
                resetGame();
            }
        }
    }

    private boolean gridIsFull() {
        boolean full = true;
        int columnIndex = 0;
        while (full && columnIndex < tokens.size()) {
            full = tokens.get(columnIndex).size() == rowsCount;
            columnIndex += 1;
        }

        if (full) {
            addGameToHistory();
        }

        return full;
    }

    private boolean winner(Player player, int column, int row) {
        // Horizontal check
        int horCount = 0;
        for (int i = 0; i < columnsCount; i++) {
            if (tokens.get(i).size() != 0 && !(tokens.get(i).size() - 1 < row)) {
                if (tokens.get(i).get(row).getPlayer() == player) {
                    horCount++;
                } else {
                    horCount = 0;
                }
            }
        }

        // Vertical check
        int verCount = 0;
        ArrayList<Token> currentColumn = tokens.get(column);
        if (currentColumn != null) {
            for (Token token : currentColumn) {
                if (token != null) {
                    if (token.getPlayer() == player) {
                        verCount++;
                    } else {
                        verCount = 0;
                    }
                }
            }
        }

        // Diagonals check
        int diagonalOneCount =
                checkDiagonal(1, 1, column, row, player) +
                        checkDiagonal(-1, -1, column, row, player) + 1;
        int diagonalTwoCount =
                checkDiagonal(-1, 1, column, row, player) +
                        checkDiagonal(1, -1, column, row, player) + 1;

        // Total check
        if (horCount == 4 || verCount == 4 || diagonalOneCount == 4 || diagonalTwoCount == 4) {
            game.setWinner(player);
            addGameToHistory();
            return true;
        }

        return false;
    }

    private int checkDiagonal(int xDirection, int yDirection, int column, int row, Player player) {
        int count = 0;
        boolean previousIsGood = true;
        int x = column + xDirection;
        int y = row + yDirection;
        do {
            if (tokenIsPresent(player, x, y)) {
                previousIsGood = true;
                count += 1;
            } else {
                previousIsGood = false;
            }
            x += xDirection;
            y += yDirection;
        } while (previousIsGood);
        return count;
    }

    private boolean tokenIsPresent(Player player, int column, int row) {
        if (column < 0 || row < 0 || column >= tokens.size()) {
            return false;
        }

        ArrayList<Token> currentColumn = tokens.get(column);
        if (currentColumn != null) {
            if (row < currentColumn.size()) {
                return currentColumn.get(row).getPlayer() == player;
            }
        }
        return false;
    }

    private void addGameToHistory() {
        games.add(game);
    }

    private void switchToIntroductionPanel() {
        CardLayout cl = (CardLayout) cards.getLayout();
        System.out.println(games.size());
        cards.add(new Introduction(cards, games).introductionPanel, "introduction");
        cl.show(cards, "introduction");
    }

}
