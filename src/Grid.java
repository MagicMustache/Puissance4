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
        addGameToHistory();
        game = new Game(game.playerOne.name, game.playerTwo.name);
        tokens.clear();
        for (int i = 0; i < columnsCount; i++) {
            tokens.add(i, new ArrayList<Token>());
        }
        for (int row = 0; row < rowsCount; row++) {
            for (int column = 0; column < columnsCount; column++) {
                squares[column][row].removeCircle();
            }
        }
        updateGameStatus(8, game.playerOne);
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
        squares[column][rowsCount - row - 1].addCircle(tokens.get(column).get(row).player.color);
        updateGameStatus(column, player);
    }

    private Player currentPlayer() {
        if (clicksCount % 2 == 0) {
            return game.playerOne;
        } else {
            return game.playerTwo;
        }
    }

    private void updateGameStatus(int column, Player lastPlayer) {

        //j'ai du ajouter la derniere colonne utilisée pour me simplifier la tache
        //lastPlayer renvoie le dernier a avoir joué
        nextPlayerLabel.setText("It's " + currentPlayer().getName() + " turn !");

        //si c'est le premier updateGameStatus on a pas encore de colonne utilisée, mais comme il en faut une pour appeler updateGameStatus je met 8, car ne correspond pas a une vrai colonne
        //on check si c'est la premiere update ou pas grace a la colonne
        if(column != 8) {
            if (winner(lastPlayer.getName(), column, tokens.get(column).size()-1)) {
                Popup popup = new Popup(gridPanel, "Congratulations !", lastPlayer.getName()+" has won !!", "Replay", "OK");
                popup.show();
                if (popup.getChoice() == Popup.Choice.RIGHT) {
                    addGameToHistory();
                    switchToIntroductionPanel();
                } else {
                    resetGame();
                }
            }
        }

        // Start Debug
        if (gridIsFull()) {
            Popup popup = new Popup(gridPanel, "No one won :(", "The grid is full", "Replay", "OK");
            popup.show();
            if (popup.getChoice() == Popup.Choice.RIGHT) {
                addGameToHistory();
                switchToIntroductionPanel();
            } else {
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

    private boolean winner(String name, int column, int row) {
        // TODO Also set the winner state in Game object
        //TODO add diagonal check
        int horCount = 0;
        int verCount = 0;

        //horizontal check
        for(int i = 0;i<columnsCount;i++){
            if (tokens.get(i).size() != 0 && !(tokens.get(i).size()-1 < row)){
                if (tokens.get(i).get(row).getPlayer().getName() == name) {
                    horCount++;
                    System.out.println("horcount : "+horCount);
                }
                else{
                    horCount = 0;
                }
            }
        }

        //vertical check
        for(int i = 0;i<tokens.get(column).size();i++){
            if(tokens.get(column) != null){
                if(tokens.get(column).get(i) != null) {
                    if (tokens.get(column).get(i).getPlayer().getName() == name) {
                        verCount++;
                        System.out.println("vercount : "+verCount);
                    }
                }
                else{
                    verCount = 0;
                }
            }
        }

        if(horCount == 4||verCount == 4){
            return true;
        }
        else{
            return false;
        }
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
