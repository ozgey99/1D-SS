package sts;

import Models.Cards.AbstractCard;
import Models.Cards.Deck;
import Models.Dungeon.Room.Fight;
import Models.Dungeon.Room.Rest;
import Models.Dungeon.Room.Treasure;
import Models.Game;
import Models.Object.AbstractRelic;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
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

import java.util.ArrayList;

import static sts.Main.game;
public class RestScene extends RoomScene {

    HBox box;

    public RestScene()
    {
        box = new VBox();
        root.setMinSize( width, height);

    }


    public void initialize() {
        System.out.println("INITIALIZE IN RESTSCENE");
        Rectangle rect1 = new Rectangle();
        rect1.setFill(new ImagePattern(new Image("Rest.png")));
        rect1.setWidth(width / 10);
        rect1.setHeight(height / 4);
        rect1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                {

                   /* if (game.getDungeon().getCurrentRoom().getChildren() == null) {
                        System.out.println("you win the game");
                        rect1.setFill(new ImagePattern(new Image("win.jpg")));
                        rect1.setHeight(height);
                        rect1.setWidth(width);
                    }*/
                    // else {
                    System.out.println("ASCENDING CALLED IN RESTSCENE");
                    game.getPlayer().recharge();
                    game.getDungeon().ascend();
                    // }

                }

            }
        });
        box.getChildren().add(rect1);
        Deck deck = game.getPlayer().masterDeck;
        for (int i = 0; i < deck.getSize(); i++) {

            AbstractCard firstHandCard = deck.getCard(i);
            String firstName = firstHandCard.getName();
            firstName = firstName + ".png";

            Rectangle rect2 = new Rectangle();
            rect2.setFill(new ImagePattern(new Image(firstName)));

            rect2.setWidth(width / 10);
            rect2.setHeight(height / 4);


            int finalI = i;
            rect1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                   deck.getCard(finalI).upgrade();

                }
            });


            box.getChildren().add(rect2);


        }
        root.getChildren().add(box);
    }
    public void draw()
    {

    }
}
