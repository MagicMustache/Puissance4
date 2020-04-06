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
    private String[] colNames = new String[] {"Date", "PlayerOne", "PlayerTwo"};

    public History(JPanel cards, ArrayList<Game> games) {
        this.cards = cards;
        this.games = games;
        System.out.println(games.size());
        setupTable();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel("introduction");
            }
        });
    }

    private void setupTable() {
        //creating matrix with data from games and column names
        String[][] rowData = new String[games.size()][colNames.length];
        for (int i = 0; i<games.size();i++){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String strDate = games.get(i).getTime().format(formatter);
            String[] values = new String[]{strDate, games.get(i).getPlayerOne().getName(), games.get(i).getPlayerTwo().getName()};
            rowData[i] = values;
        }
        //recreating table model with data and column names (only way i found)
        //TODO display column names on table
        table.setModel(new DefaultTableModel(rowData, colNames));
    }

    private void switchToPanel(String name) {
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, name);
    }

}
