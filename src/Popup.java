import java.beans.EventHandler;

public class Popup {

    String title;
    String message;
    Button buttonLeft;
    Button buttonRight;

    public Popup(
            String title,
            String message,
            String buttonLeftTitle,
            String buttonRightTitle,
            EventHandler buttonLeftEventHandler,
            EventHandler buttonRightEventHandler
    ) {
        this.title = title;
        this.message = message;
        buttonLeft = new Button(buttonLeftTitle);
        buttonRight = new Button(buttonRightTitle);
        buttonLeft.setOneMouseClicked(buttonLeftEventHandler);
        buttonRight.setOneMouseClicked(buttonRightEventHandler);
    }

    public void show() {
        // TODO
    }

    public void hide() {
        // TODO
    }

}
