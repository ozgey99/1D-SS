package sts;

import Models.Cards.Deck;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MonsterPane extends StackPane {
    private int width;
    private int height;
    private String monsterName;



    public MonsterPane( int width, int height){
        this.setEffect( new DropShadow(30, Color.RED) );
        this.setMinSize( width, height);
        this.width = width;
        this.height = height;
        this.setMinSize( width, height);

    }

    void initalize()
    {
       draw();
    }
    void update()
    {
      //FIND NEW HEALTH, GOLD, DECK, ETC
    }
    void draw()
    {



        ImageView imageView = new ImageView(new Image("Cultist-pretty.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(width/8);

        this.getChildren().add( imageView );
        this.setAlignment( Pos.BOTTOM_CENTER );
    }
}