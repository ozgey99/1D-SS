package sts;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class StsTitle extends Pane {
    private Text text;

    public StsTitle(String name) {
        String spread = "";
        for (char c : name.toCharArray()) {
            if( c != ' ')
                spread += c + " ";
            else
                spread += '\t';
        }

        text = new Text(spread);
        text.setFill(Color.color(1, 1, 1, 0.75));
        text.setFont(Font.font("Comic Sans MS", 72));
        text.setEffect(new DropShadow(30, Color.WHITE));

        getChildren().addAll(text);
    }

    public double getTitleWidth() {
        return text.getLayoutBounds().getWidth();
    }
}