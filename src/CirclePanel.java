import javax.swing.*;
import java.awt.*;

public class CirclePanel extends JPanel {

    Color color;

    public void addCircle(Color color) {
        this.color = color;
        repaint();
    }

    public void removeCircle() {
        this.color = null;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (color == null) { return; }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(color);
        g2.fillOval(g.getClipBounds().x, g.getClipBounds().y, getWidth(), getHeight());
    }

}