package sts;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MonsterPane extends StackPane {
    public MonsterPane( int width, int height){
        this.setEffect( new DropShadow(30, Color.RED) );
        this.setMinSize( width, height);
    }
}