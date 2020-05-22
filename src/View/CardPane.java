package View;

import Models.Cards.AbstractCard;
import Models.Cards.Deck;
import Controller.Dungeon.Room.Fight;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;
import javafx.util.Duration;

import static View.Main.game;

public class CardPane  extends StackPane {
    private int space;

    private Deck deck;
    private int width;
    private int height;
    private int privLen;
    Pane pane = new Pane();
    private FightScene scene;
    int id = 0;
    HBox box;



    public CardPane(int width, int height) {
        this.width = width;
        this.height = height;
        space = width / 10;
        this.setMinSize(width, height);
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
                rect1.setHeight(height/3);


                int finalI = i;
                rect1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();
                        AbstractCard firstHandCard = deck.getCard(finalI);
                        if(((Fight) game.getDungeon().getCurrentRoom()).useCard(firstHandCard) ) {
                            firstHandCard.setSelected();
                            id = finalI;
                            deck = ((Fight) game.getDungeon().getCurrentRoom()).getHand();
                            Main.currentScene.draw();
                        }

                    }
                });

                rect1.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
                            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect1);
                            scaleTransition.setToX(1.3f);
                            scaleTransition.setToY(1.3f);
                            scaleTransition.setCycleCount(1);
                            scaleTransition.setAutoReverse(true);
                            scaleTransition.play();
                        }
                );

                rect1.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, e -> {

                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect1);
                    scaleTransition.setToX(1);
                    scaleTransition.setToY(1);
                    scaleTransition.setAutoReverse(true);
                    scaleTransition.play();
                });

                box.getChildren().add(rect1);
                box.setPadding(new Insets(0,0,0,height/5*3));


            }
        }
       // addPiles();

    }
 /*   private void addPiles(){
        Rectangle rectDraw = new Rectangle();
        rectDraw.setFill( new ImagePattern(new Image("drawPile.png")) );
        this.getChildren().add( rectDraw );
        this.setAlignment( rectDraw, Pos.BOTTOM_LEFT);
        Rectangle rectDiscard = new Rectangle();
        rectDraw.setFill( new ImagePattern(new Image("discardPile.png")) );
        this.getChildren().add( rectDiscard );
        this.setAlignment( rectDraw, Pos.BOTTOM_RIGHT);
    }*/



}