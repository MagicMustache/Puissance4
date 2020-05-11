import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class History {

    public JPanel historyPanel;
    private JPanel cards;
    private JButton backButton;
    private JTable table;
    private ArrayList<Game> games;
    private String[] colNames = new String[] {"Date", "Joueur 1", "Joueur 2"};

    public History(JPanel cards, ArrayList<Game> games) {
        this.cards = cards;
        this.games = games;

        setupTable();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToIntroductionPanel("introduction");
            }
        });
    }

    private void setupTable() {
        String[][] rowData = new String[games.size() + 1][colNames.length];
        rowData[0] = colNames;
        for (int i = 0; i<games.size();i++) {
            Game game = games.get(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            String strDate = game.getTime().format(formatter);
            String[] values = new String[] {
                    strDate,
                    game.getPlayerOne().getName() + (game.getWinner() == game.getPlayerOne() ? " (gagnant)" : ""),
                    game.getPlayerTwo().getName() + (game.getWinner() == game.getPlayerTwo() ? " (gagnant)" : ""),
            };
            rowData[i + 1] = values;
        }
        table.setModel(new DefaultTableModel(rowData, colNames));

        table.setCellSelectionEnabled(false);
        table.setColumnSelectionAllowed(false);
        table.setDragEnabled(false);
        table.setRowSelectionAllowed(false);
    }

    private void switchToIntroductionPanel(String name) {
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, name);
    }

}
