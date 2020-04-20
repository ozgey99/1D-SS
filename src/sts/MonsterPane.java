package sts;

import Models.Cards.Deck;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class MonsterPane extends StackPane {
    private int width;
    private int height;

    public MonsterPane( int width, int height){
        this.setEffect( new DropShadow(30, Color.RED) );
        this.setMinSize( width, height);
        this.width = width;
        this.height = height;
        this.setMinSize( width, height);
    }

    void initalize()
    {
        ImageView imageView = new ImageView(new Image("Cultist-pretty.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(width/8);

        this.getChildren().add( imageView );
        this.setAlignment( Pos.BOTTOM_CENTER );
    }
    void update()
    {

    }
    void draw()
    {

    }
}