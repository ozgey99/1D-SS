package sts;

import Models.Cards.AbstractCard;
import Models.Dungeon.Room.Fight;
import Models.Dungeon.Room.Treasure;
import Models.Game;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.text.Text;

import static sts.Main.game;

public class TreasureScene extends RoomScene {
    private int width = 1300; //1920;
    private int gold = 0;
    private String relic = "DefaultRelicName";
    private String potion = "DefaultPotionName";
    private int height = 700; //1080;
    Text goldText = new Text("You gold reward is "+ gold);
    Text relicText = new Text("You relic reward is "+ relic);
    Text potionText = new Text("You potion reward is "+ gold);
    HBox box = new HBox();


    public TreasureScene()
    {
        Text goldText = new Text();
        Text relicText = new Text();
        Text potionText = new Text();
        root.setMinSize( width, height);

    }
    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        root.getChildren().add(imageView);
    }

    public void initialize()
    {
        Rectangle rect1 = new Rectangle();
        rect1.setFill(new ImagePattern(new Image("Cultist.png")));

        rect1.setWidth(width/10);
        rect1.setHeight(height/4);

        rect1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
               {
                   ((Treasure)game.getDungeon().getCurrentRoom()).addRewards();

                   if (game.getDungeon().getCurrentRoom().getChildren() == null) {
                       rect1.setFill(new ImagePattern(new Image("win.jpg")));
                       rect1.setHeight(height);
                       rect1.setWidth(width);
                   }
                   game.getDungeon().ascend();

                }

            }
        });

        System.out.println("I AM CALLED");
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));

        goldText.setText("You gold reward is "+ ((Treasure)game.getDungeon().getCurrentRoom()).getGoldAmount());
        box.getChildren().add(goldText);
        root.getChildren().add(box);
        root.getChildren().add(rect1);

    }

    public void draw()
    {
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        goldText.setText("You gold reward is "+ ((Treasure)game.getDungeon().getCurrentRoom()).getGoldAmount());
    }

    private void addClickables(){
        Button button1 = new Button("Button 1");
        root.getChildren().add(button1);
    }
}