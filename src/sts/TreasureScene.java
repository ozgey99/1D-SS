package sts;

import Models.Cards.AbstractCard;
import Models.Dungeon.Room.Fight;
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
import Models.Actions.RelicActions;
import java.util.ArrayList;

import static sts.Main.game;

public class TreasureScene extends RoomScene {
    VBox box;
    ArrayList<Rectangle> rectangles;
    ArrayList<AbstractRelic> relics;

    public TreasureScene()
    {
        relics = new ArrayList<>();
        rectangles = new ArrayList<>();
        box = new VBox();
        root.setMinSize( width, height);
        //root.setAlignment(Pos.CENTER);
    }

    public void initialize()
    {
        addBackground();
        initializeRectangles();
    }

    public void initializeRectangles()
    {
        Text goldText = new Text(((Treasure)game.getDungeon().getCurrentRoom()).getGoldAmount() + "");
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        ImageView chest = new ImageView(new Image("SmallChest.png"));
        chest.setFitWidth(width/15);
        chest.setVisible(true);
        chest.setPreserveRatio(true);
        TextFlow goldflow = new TextFlow(chest,goldText);


        chest.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                        System.out.println("ASCENDING CALLED IN TREASSURESCENE");
                        game.getPlayer().changeGold(((Treasure) game.getDungeon().getCurrentRoom()).getGoldAmount());
                        game.getDungeon().ascend();
                   // }

                }

            }
        });
        relics = ((Treasure)game.getDungeon().getCurrentRoom()).getRelics();

        for (int i = 0; i < relics.size(); i++) {

            String name = relics.get(i).getName();
            name = name + ".png";
            ImageView relicImage = new ImageView(new Image(name));
            relicImage.setPreserveRatio(true);
            relicImage.setFitHeight(100);

            System.out.println("NAME IS " + name);

            int finalI = i;
            relicImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {

                    System.out.println("ASCENDING CALLED IN TREASSURE");
                    RelicActions.addRelic(game.getPlayer(),relics.get(finalI)); //game.getPlayer().relics.add(relics.get(finalI));
                    game.getDungeon().ascend();

                }
            });

            String relicDecriptionText = relics.get(i).getDescription();
            Text relicDescription = new Text(relicDecriptionText);
            relicDescription.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

            TextFlow text = new TextFlow(relicImage,relicDescription);

            box.getChildren().add(text);


        }

        box.getChildren().add(goldflow);
        root.getChildren().add(box);
        root.setAlignment(box, Pos.CENTER);

    }
    private void addBackground() {
        ImageView imageView = new ImageView(new Image("treasure_back.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        root.getChildren().add(imageView);
    }

    public void draw()
    {

    }

}