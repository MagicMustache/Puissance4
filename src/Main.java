import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        JPanel cards = new JPanel();
        Introduction introduction = new Introduction(cards, new ArrayList<Game>());

        JFrame frame = new JFrame("Puissance 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 550));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        cards.setLayout(new CardLayout());
        cards.add(introduction.introductionPanel, "introduction");
        frame.add(cards);
        frame.setVisible(true);
    }

}
