package sts;

import Models.Cards.AbstractCard;
import Models.Cards.Deck;
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
    ArrayList<String> CardNames = new ArrayList<>();
    ArrayList<Rectangle> Rectangles = new ArrayList<>();



    private Effect shadow = new DropShadow(5, Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 3);

    public CardPane(int width, int height, Deck deck,FightScene scene) {
        this.width = width;
        this.height = height;
        this.deck = deck;
        space = width / 10;
        this.setMinSize(width, height);
        this.scene = scene;

    }

    public AbstractCard getCard() {
        return deck.getCard(id);
    }

    public void initialize()
    {
        initializeRectangles();


    }
    private void initializeRectangles()
    {

        int len = deck.getSize();
        privLen = len;
        System.out.println("LEN IS INITIALIZE " + len);
        int i;
        for( i = 0; i < len;i++)
        {

            AbstractCard firstHandCard = deck.getCard(i);
            String firstName = firstHandCard.getName();
            firstName = firstName + ".png";
            CardNames.add(firstName);
            Rectangle rect1 = new Rectangle();
            rect1.setFill(new ImagePattern(new Image(firstName)));
            rect1.setX(i * space);
            rect1.setY(0);
            rect1.setWidth(space);
            rect1.setHeight(space);
            Rectangles.add(rect1);
            pane.getChildren().add(Rectangles.get(i));
            Rectangles.get(i).setVisible(false);
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), rect1);
            rect1.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    scaleTransition.setToX(1.5f);
                    scaleTransition.setToY(1.5f);
                    scaleTransition.setCycleCount(2);
                    scaleTransition.setAutoReverse(true);
                    scaleTransition.play();
                }
            });
            int finalI = i;
            rect1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent t) {
                    if(((Fight) game.getDungeon().getCurrentRoom()).useCard(firstHandCard) ) {
                        Rectangles.get(finalI).setVisible(false);
                        Rectangles.get(finalI).setFill(new ImagePattern(new Image("used.png")));
                         firstHandCard.setSelected();
                        System.out.println("PRESSED ID " + firstHandCard.getName());
                        id = finalI;
                        deck = ((Fight) game.getDungeon().getCurrentRoom()).getHand();
                        game.fightScene.draw();
                   }

                }
            });

        }



        this.getChildren().addAll(pane);
    }


    public void draw() {

        deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();

        int len = deck.getSize();

        System.out.println("LEN IS IN DRAW "+ len);

       // TextBasedUI.displayDeck(deck,"hand  deck ");
        for(int i = 0; i < len;i++) {

            AbstractCard firstHandCard = deck.getCard(i);
            String firstName = firstHandCard.getName();
            System.out.println("NAME IS " + firstHandCard.getName());
            firstName = firstName + ".png";
            CardNames.add(firstName);
            Rectangles.get(i).setFill(new ImagePattern(new Image(firstName)));
            Rectangles.get(i).setX(i * space);
            Rectangles.get(i).setY(0);
            Rectangles.get(i).setVisible(true);

        }

    }



    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}