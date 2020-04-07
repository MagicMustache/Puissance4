import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Puissance 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 500));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Keep 1:1 ratio
                Rectangle b = e.getComponent().getBounds();
                e.getComponent().setBounds(b.x, b.y, b.width, b.width);
            }
            @Override
            public void componentMoved(ComponentEvent e) { }
            @Override
            public void componentShown(ComponentEvent e) { }
            @Override
            public void componentHidden(ComponentEvent e) { }
        });

        JPanel cards = new JPanel();
        cards.setLayout(new CardLayout());
        Introduction introduction = new Introduction(cards, new ArrayList<Game>());
        cards.add(introduction.introductionPanel, "introduction");
        frame.add(cards);
        frame.setVisible(true);
    }

}
