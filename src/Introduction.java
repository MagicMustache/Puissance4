import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Introduction {

    public JPanel introductionPanel;
    private JPanel cards;
    private JTextField playerOneTextField;
    private JTextField playerTwoTextField;
    private JButton playButton;
    private JButton rulesButton;
    private JButton historyButton;
    private ArrayList<Game> games;

    public Introduction(JPanel cards) {
        PersistanceHandler ph = new PersistanceHandler();
        this.cards = cards;
        this.games = ph.readPersistance();
        refreshPlayersNames(this.games);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (getPlayerOneTextField().isEmpty() || getPlayerTwoTextField().isEmpty()) { return; }
                addAndSwitchToPanel(
                        new Grid(cards, getPlayerOneTextField(), getPlayerTwoTextField(), games).gridPanel,
                        "grid"
                );
            }
        });

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = {"OK"};
                JOptionPane.showOptionDialog(
                        null,
                        "Les règles sont très simples :" +
                                "\nLe premier à aligner quatre de ses jetons verticalement, horizontalement ou diagonalement a gagné.\n" +
                                "Que le meilleur gagne !",
                        "Règles du jeu",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                History hist = new History(cards, games);
                addAndSwitchToPanel(hist.historyPanel, "history");
            }
        });
    }

    private void refreshPlayersNames(ArrayList<Game> games) {
        if (games.size() > 0) {
            Game latestGame = games.get(games.size() - 1);
            setPlayerOneTextField(latestGame.getPlayerOne().getName());
            setPlayerTwoTextField(latestGame.getPlayerTwo().getName());
        }
    }

    private String getPlayerOneTextField() {
        return playerOneTextField.getText();
    }

    private String getPlayerTwoTextField() {
        return playerTwoTextField.getText();
    }

    private void setPlayerOneTextField(String text) {
        playerOneTextField.setText(text);
    }

    private void setPlayerTwoTextField(String text) {
        playerTwoTextField.setText(text);
    }

    private void addAndSwitchToPanel(Component component, String name) {
        CardLayout cl = (CardLayout) cards.getLayout();
        cards.add(component, name);
        cl.show(cards, name);
    }

}
