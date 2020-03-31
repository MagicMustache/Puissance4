import javax.swing.*;
import java.awt.*;

public class Popup {

    Component parentComponent;
    String title;
    String message;
    String buttonLeftTitle;
    String buttonRightTitle;
    int choice = -1;

    enum Choice {
        UNKNOWN,
        LEFT,
        RIGHT
    }

    public Popup(
            Component parentComponent,
            String title,
            String message,
            String buttonLeftTitle,
            String buttonRightTitle
    ) {
        this.parentComponent = parentComponent;
        this.title = title;
        this.message = message;
        this.buttonLeftTitle = buttonLeftTitle;
        this.buttonRightTitle = buttonRightTitle;
    }

    public void show() {
        Object[] options = {buttonRightTitle, buttonLeftTitle};
        choice = JOptionPane.showOptionDialog(
                parentComponent,
                message,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    public Choice getChoice() {
        switch (choice) {
            case 0:
                return Choice.RIGHT;
            case 1:
                return Choice.LEFT;
            default:
                return Choice.UNKNOWN;
        }
    }

}
