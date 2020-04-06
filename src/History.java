import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class History {

    public JPanel historyPanel;
    private JPanel cards;
    private JButton backButton;
    private JTable table;
    private ArrayList<Game> games;

    public History(JPanel cards, ArrayList<Game> games) {
        this.cards = cards;
        this.games = games;

        setupTable();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel("introduction");
            }
        });
    }

    private void setupTable() {
        // TODO
    }

    private void switchToPanel(String name) {
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, name);
    }

}
