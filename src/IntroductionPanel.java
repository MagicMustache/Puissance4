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


    public IntroductionPanel(JPanel cards) {
        jouerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.show(cards, "grid");
            }
        });
    }

    public JTextField getJoueur1TextField() {
        return joueur1TextField;
    }

    public JTextField getJoueur2TextField() {
        return joueur2TextField;
    }

    public JLabel getTitre() {
        return titre;
    }

}
