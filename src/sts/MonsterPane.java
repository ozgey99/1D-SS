package sts;

import Models.Cards.Deck;
import Models.Creatures.Monsters.AbstractMonster;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private AbstractMonster monster;

    public MonsterPane( int width, int height, AbstractMonster m){
        this.setEffect( new DropShadow(30, Color.RED) );
        this.setMinSize( width, height);
        this.width = width;
        this.height = height;
        this.setMinSize( width, height);
        monster = m;
    }

    void initialize()
    {
       draw();
    }
    void update(AbstractMonster m)
    {
        monster = m;
        draw();
    }
    void draw()
    {
        if(monster.getCurrentHP() > 0) {
            ImageView imageView = new ImageView(new Image("Cultist-pretty.png"));
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(width / 8);

            this.getChildren().add(imageView);
            this.setAlignment(Pos.BOTTOM_CENTER);

            imageView.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent t) {
                    monster.setSelected();
                }
            });
        }
    }
    public AbstractMonster getMonster(){
        return monster;
    }
}