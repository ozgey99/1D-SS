package sts;

import Models.Cards.AbstractCard;
import Models.Cards.Deck;
import Models.Creatures.Monsters.AbstractMonster;
import Models.Dungeon.Room.Fight;
import Models.TextBasedUI;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.EventHandler;

import static sts.Main.game;

public class CardPane  extends GridPane {
    private int maxHand = 5;
    private int space;
    private StackPane root;
    private Text text;
    private Deck deck;
    private int width;
    private int height;
    boolean selected = false;
    boolean selected2 = false;
    Rectangle rect1;
    Rectangle rect2;
    Rectangle rect3;
    Rectangle rect4;
    Rectangle rect5;
    private int privLen;
    Pane pane = new Pane();
    private GridPane general = new GridPane();
    AbstractCard returnedCard;
    private FightScene scene;
    int id = 0;
    HBox box;



    private Effect shadow = new DropShadow(5, Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 3);

    public CardPane(int width, int height) {
        this.width = width;
        this.height = height;
        this.deck = deck;
        space = width / 10;
        this.setMinSize(width, height);
        this.scene = scene;
        box = new HBox(10);

    }

    public AbstractCard getCard() {
        return deck.getCard(id);
    }

    public void initialize()
    {
        initializeRectangles();
    }

    public void draw() {
        drawRectangles();
    }

    private void initializeRectangles()
    {
        this.getChildren().addAll(box);
    }

    public void drawRectangles() {
        box.getChildren().removeAll();
        box.getChildren().clear();

        deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();
        if(deck != null) {

            int len = deck.getSize();
            privLen = len;
            int i;
            for (i = 0; i < len; i++) {

                AbstractCard firstHandCard = deck.getCard(i);
                String firstName = firstHandCard.getName();
                firstName = firstName + ".png";

                Rectangle rect1 = new Rectangle();
                rect1.setFill(new ImagePattern(new Image(firstName)));

                rect1.setWidth(width/10);
                rect1.setHeight(height/4);


                int finalI = i;
                rect1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();
                        AbstractCard firstHandCard = deck.getCard(finalI);
                        if(((Fight) game.getDungeon().getCurrentRoom()).useCard(firstHandCard) ) {
                            firstHandCard.setSelected();
                            System.out.println("PRESSED ID " + firstHandCard.getName());
                            id = finalI;
                            deck = ((Fight) game.getDungeon().getCurrentRoom()).getHand();
                            System.out.println("NEW BLOCK " + game.getPlayer().getBlock());
                            game.currentScene.draw();
                        }

                    }
                });


                box.getChildren().add(rect1);
                box.setPadding(new Insets(0,0,0,50));


            }
        }

    }




    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}