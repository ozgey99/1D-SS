package sts;

import Models.Cards.AbstractCard;
import Models.Dungeon.Room.Fight;
import Models.Dungeon.Room.Treasure;
import Models.Game;
import Models.Object.AbstractRelic;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.util.Duration;
import Models.Dungeon.Room.Treasure;

import java.util.ArrayList;

import static sts.Main.game;

public class TreasureScene extends RoomScene {

    Pane pane;
    ImageView chest;
    private UpperPane gridUpper;
    ArrayList<Rectangle> rectangles;
    ArrayList<AbstractRelic> relics;
    static boolean chosen;
    Node rect;
    ImageView turnBack;

    public TreasureScene()
    {
        turnBack = new ImageView(new Image("goAhead.png"));
        turnBack.setPreserveRatio(true);
        turnBack.setFitHeight(100);
        turnBack.setX(width/5+ width/3*2);
        turnBack.setY(height/4*3);
        pane = new Pane();
        relics = new ArrayList<>();
        rectangles = new ArrayList<>();
        gridUpper = new UpperPane(width,height/15);
        chest = new ImageView(new Image("SmallChest.png"));
        chest.setFitWidth(width/8);
        chest.setX(width/2-width/8);
        chest.setY(height/3*2);
        chest.setVisible(true);
        chest.setPreserveRatio(true);
        chosen = false;
        rect = rewards(width, height);
        root.setMinSize( width, height);
    }

    public void initialize()
    {
        initializeUpper();
        addBackground();
        smallChest();
        root.getChildren().add(pane);
    }

    private void initializeUpper()
    {
        gridUpper.initialize();
        gridUpper.setBackground( new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)) );
        gridUpper.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        GridPane.setConstraints(gridUpper, 0,0,1,1);
        pane.getChildren().add(gridUpper);
        gridUpper.setMinWidth(width);
    }

    static Node rewards(int x, int y) {

        Group g = new Group();
        VBox vbox = new VBox(10);

        HBox box1 = new HBox();
        Text goldText = new Text();
        int gold = ((Treasure)game.getDungeon().getCurrentRoom()).getGoldAmount();
        goldText.setText("   "+gold + " gold");
        goldText.setFill(Color.WHITE);
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        ImageView goldImage = new ImageView(new Image("gold.png"));
        goldImage.setFitWidth(40);
        goldImage.setFitHeight(40);
        goldImage.setVisible(true);
        goldImage.setPreserveRatio(true);
        goldImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                game.getPlayer().changeGold(((Treasure) game.getDungeon().getCurrentRoom()).getGoldAmount());
                chosen = true;
            }
        });
        box1.getChildren().addAll(goldImage, goldText);
        vbox.getChildren().add(box1);


        // 3 tane aynı şey ekleniyor ????????
        ArrayList<AbstractRelic> relics = ((Treasure)game.getDungeon().getCurrentRoom()).getRelics();
        int relicsSize = relics.size();
        System.out.println("relix size: "+ relicsSize);
        for(int i=0; i< relicsSize; i++){
            String name = relics.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView relicImage = new ImageView(new Image(name));
            relicImage.setPreserveRatio(true);
            relicImage.setFitHeight(50);
            vbox.getChildren().add(relicImage);
            int j = i;
            relicImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    RelicActions.addRelic(game.getPlayer(),relics.get(j));
                }
            });
        }

        // 3 tane aynı şey ekleniyor listeye ???????
        ArrayList<AbstractCard> cards = ((Treasure)game.getDungeon().getCurrentRoom()).getCards();
        System.out.println("card size: "+ cards.size());
        Group textGroup = new Group();
        String label = "add a card to your deck";
        Text text = new Text(label);
        Rectangle textRect = new Rectangle();
        textRect.setWidth(label.length());
        textRect.setHeight(5);
        textGroup.getChildren().addAll(textRect, text);
        vbox.getChildren().add(textGroup);
        /*textGroup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });*/

        Rectangle rect = new Rectangle();
        rect.setX(x/2-100);
        rect.setY(y/2-150);
        rect.setFill(Color.GREY);
        rect.setStroke(Color.BLACK);
        rect.setWidth(200); // proportional yap
        rect.setHeight(300); //
        rect.setVisible(true);
        vbox.setPadding(new Insets(y/2-155,100, 100, x/2-105));
        /*ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), rect);
        scaleTransition.setToX(1.5f);
        scaleTransition.setToY(1.5f);
        scaleTransition.setCycleCount(1);
        scaleTransition.setAutoReverse(true);
        scaleTransition.play();

        Text relicText = new Text(desc);
        relicText.setX(x+5);
        relicText.setY(y+13);
        relicText.setFont(Font.font ("Verdana", 10));
        relicText.setFill(Color.WHITE);*/

        g.getChildren().add(rect);
        g.getChildren().add(vbox);

        return g;
    }

    public void delete(){

        pane.getChildren().add( turnBack );
        turnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                rect.setVisible(false);
                draw();
            }
        });
    }


    public void smallChest()
    {
        chest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                {
                    System.out.println("ASCENDING CALLED IN TREASURE SCENE");
                    pane.getChildren().add(rect);
                    delete();
                    chest.setDisable(true);
                }

            }
        });

        pane.getChildren().add(chest);

        /*relics = ((Treasure)game.getDungeon().getCurrentRoom()).getRelics();

        for (int i = 0; i < relics.size(); i++) {

            String name = relics.get(i).getName();
            name = name + ".png";
            ImageView relicImage = new ImageView(new Image(name));
            relicImage.setPreserveRatio(true);
            relicImage.setFitHeight(100);

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


        }*/

    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image("treasure_back.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        root.getChildren().add(imageView);
    }

    public void draw()
    {
        gridUpper.draw();
    }

}