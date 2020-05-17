package View;

import javafx.scene.effect.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StsMenuPane extends Pane {
    private Text text;

    private Effect shadow = new DropShadow(5, Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 3);

    public StsMenuPane(String name) {
        Polygon bg = new Polygon(
                0, 0,
                200, 0,
                150, 15,
                200, 30,
                0, 30
        );
        bg.setStroke(Color.color(1, 0, 0, 0.75));
        bg.setEffect(new Glow());



        text = new Text(name);
        text.setTranslateX(5);
        text.setTranslateY(20);
        text.setFont(Font.loadFont("Comic Sans MS", 14));
        text.setFill(Color.WHITE);

        getChildren().addAll(bg, text);
    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}