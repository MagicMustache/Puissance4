
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JPanel cards = new JPanel();
        IntroductionPanel menu = new IntroductionPanel(cards);
        Grid grid = new Grid(cards);

        JFrame frame = new JFrame("Puissance4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 550));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        cards.setLayout(new CardLayout());
        cards.add(menu.MenuPanel, "menu");
        cards.add(grid.gridPanel,"grid");
        frame.add(cards);
        frame.setVisible(true);
    }

}
