import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntroductionPanel {

    private JButton jouerButton;
    private JTextField joueur1TextField;
    private JTextField joueur2TextField;
    public JPanel MenuPanel, GridPanel, PanelCont;
    private JLabel titre;
    private JLabel titreJ1;
    private JLabel titreJ2;
    private Game newGame;


    public IntroductionPanel(JPanel cards) {
        jouerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout cl = (CardLayout) cards.getLayout();
                Game game = new Game(getJoueur1TextField().toString(), getJoueur2TextField().toString());
                Grid grid = new Grid(game);
                cards.add(grid.gridPanel,"grid");
                cl.show(cards, "grid");
            }
        });
    }

    public void setNewGame(Game game){
        this.newGame = game;
    }

    public String getJoueur1TextField() {

        return joueur1TextField.getText();
    }

    public String getJoueur2TextField() {
        return joueur2TextField.getText();
    }

    public JLabel getTitre() {
        return titre;
    }

}
