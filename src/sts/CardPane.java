package sts;

import Models.Cards.AbstractCard;
import Models.Cards.Deck;
import Models.Dungeon.Room.Fight;
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
    Pane pane = new Pane();
    private GridPane general = new GridPane();
    AbstractCard returnedCard;
    private FightScene scene;
    int id = 4;


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
        this.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {

                        if (t.getX() < space) {
                            System.out.println("Selected card id "+ 0);
                            deck.getCard(0).setSelected();
                            boolean used = ( (Fight) game.getDungeon().getCurrentRoom()).useCard(deck.getCard(0));
                            deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();
                            scene.draw();





                        }
                        if (t.getX() >  space && t.getX() < 2*space) {
                            System.out.println("Selected card id "+ 1);
                            deck.getCard(1).setSelected();
                            boolean used = ( (Fight) game.getDungeon().getCurrentRoom()).useCard(deck.getCard(id-3));
                            deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();
                            scene.draw();
                            id--;


                        }
                        if (t.getX() >  2*space && t.getX() < 3*space) {
                            System.out.println("Selected card id "+ 2);
                            deck.getCard(2).setSelected();
                            boolean used = ( (Fight) game.getDungeon().getCurrentRoom()).useCard(deck.getCard(id-2));
                            deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();
                            id--;
                            scene.draw();


                        }
                        if (t.getX() >  3*space && t.getX() < 4*space) {
                            System.out.println("Selected card id "+ id);
                            deck.getCard(id-1).setSelected();
                            boolean used = ( (Fight) game.getDungeon().getCurrentRoom()).useCard(deck.getCard(id-1));
                            deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();
                            id--;
                            scene.draw();


                        }
                        if (t.getX() >  4*space && t.getX() < 5*space) {
                            System.out.println("Selected card id "+ id + " " +   deck.getCard(id).getName());
                            deck.getCard(id).setSelected();
                            boolean used = ( (Fight) game.getDungeon().getCurrentRoom()).useCard(deck.getCard(id));
                            deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();
                            id--;
                            scene.draw();

                        }
                        if (t.getX() >  5*space && t.getX() < 6*space) {
                            System.out.println("Selected card id "+ 5);
                            ( (Fight) game.getDungeon().getCurrentRoom()).nextState();
                            deck = ( (Fight) game.getDungeon().getCurrentRoom()).getHand();
                            id = 4;
                            scene.draw();
                            draw();


                        }
                    }
                });
        draw();

    }

    public void update() {

    }
    public void draw() {
        space = width / 10;

        AbstractCard firstHandCard = deck.getCard(0);
        String firstName = firstHandCard.getName();
        firstName = firstName + ".png";

        AbstractCard firstHandCard2 = deck.getCard(1);
        String firstName2 = firstHandCard2.getName();
        firstName2 = firstName2 + ".png";

        AbstractCard firstHandCard3 = deck.getCard(2);
        String firstName3 = firstHandCard3.getName();
        firstName3 = firstName3 + ".png";

        AbstractCard firstHandCard4= deck.getCard(3);
        String firstName4 = firstHandCard4.getName();
        firstName4 = firstName4 + ".png";

        AbstractCard firstHandCard5= deck.getCard(4);
        String firstName5 = firstHandCard5.getName();
        firstName5 = firstName5 + ".png";
        System.out.println("FIRSTNAME 5 IS "+firstName5 );



        rect5 = new Rectangle();
        rect5.setFill(new ImagePattern(new Image(firstName5)));
        rect5.setX(4 * space);
        rect5.setY(0);
        rect5.setWidth(space);
        rect5.setHeight(space);
        pane.getChildren().add(rect5);

        rect4 = new Rectangle();
        rect4.setFill(new ImagePattern(new Image(firstName4)));
        rect4.setX(3 * space);
        rect4.setY(0);
        rect4.setWidth(space);
        rect4.setHeight(space);
        pane.getChildren().add(rect4);

        rect3 = new Rectangle();
        rect3.setFill(new ImagePattern(new Image(firstName3)));
        rect3.setX(2 * space);
        rect3.setY(0);
        rect3.setWidth(space);
        rect3.setHeight(space);
        pane.getChildren().add(rect3);

        rect2 = new Rectangle();
        rect2.setFill(new ImagePattern(new Image(firstName2)));
        rect2.setX(space + 1);
        rect2.setY(0);
        rect2.setWidth(space);
        rect2.setHeight(space);
        pane.getChildren().add(rect2);

        rect1 = new Rectangle();
        rect1.setFill(new ImagePattern(new Image(firstName)));
        rect1.setX(0);
        rect1.setY(0);
        rect1.setWidth(space);
        rect1.setHeight(space);
        pane.getChildren().add(rect1);

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
        rect1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                TranslateTransition trans = new TranslateTransition();
                trans.setDuration(Duration.millis(1000));
                trans.setToX(((width / 4) * 3 + (width / 9)));
                trans.setNode(rect1);
                trans.play();
                ScaleTransition scale = new ScaleTransition(Duration.millis(1000), rect1);
                scale.setToX(0);
                scale.setToY(0);
                scale.play();
                RotateTransition rotate = new RotateTransition(Duration.millis(1000), rect1);
                rotate.setByAngle(360);
                rotate.setCycleCount(1);
                rotate.setAutoReverse(true);
                rotate.play();
            }
        });

        ScaleTransition scaleTransition2 = new ScaleTransition(Duration.millis(300), rect2);
        rect2.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleTransition2.setToX(1.5f);
                scaleTransition2.setToY(1.5f);
                scaleTransition2.setCycleCount(2);
                scaleTransition2.setAutoReverse(true);
                scaleTransition2.play();
            }
        });
        rect2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                TranslateTransition trans = new TranslateTransition();
                trans.setDuration(Duration.millis(1000));
                trans.setToX(((width / 4) * 3 + (width / 9)) - 110);
                trans.setNode(rect2);
                trans.play();
                ScaleTransition scale = new ScaleTransition(Duration.millis(1000), rect2);
                scale.setToX(0);
                scale.setToY(0);
                scale.play();
                RotateTransition rotate = new RotateTransition(Duration.millis(1000), rect2);
                rotate.setByAngle(360);
                rotate.setCycleCount(1);
                rotate.setAutoReverse(true);
                rotate.play();
            }
        });

        ScaleTransition scaleTransition3 = new ScaleTransition(Duration.millis(300), rect3);
        rect3.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleTransition3.setToX(1.5f);
                scaleTransition3.setToY(1.5f);
                scaleTransition3.setCycleCount(2);
                scaleTransition3.setAutoReverse(true);
                scaleTransition3.play();
            }
        });
        rect3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                TranslateTransition trans = new TranslateTransition();
                trans.setDuration(Duration.millis(1000));
                trans.setToX(((width / 4) * 3 + (width / 9)) - 220);
                trans.setNode(rect3);
                trans.play();
                ScaleTransition scale = new ScaleTransition(Duration.millis(1000), rect3);
                scale.setToX(0);
                scale.setToY(0);
                scale.play();
                RotateTransition rotate = new RotateTransition(Duration.millis(1000), rect3);
                rotate.setByAngle(360);
                rotate.setCycleCount(1);
                rotate.setAutoReverse(true);
                rotate.play();
            }
        });

        ScaleTransition scaleTransition4 = new ScaleTransition(Duration.millis(300), rect4);
        rect4.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleTransition4.setToX(1.5f);
                scaleTransition4.setToY(1.5f);
                scaleTransition4.setCycleCount(2);
                scaleTransition4.setAutoReverse(true);
                scaleTransition4.play();
            }
        });
        rect4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                TranslateTransition trans = new TranslateTransition();
                trans.setDuration(Duration.millis(1000));
                trans.setToX(((width / 4) * 3 + (width / 9)) - 330);
                trans.setNode(rect4);
                trans.play();
                ScaleTransition scale = new ScaleTransition(Duration.millis(1000), rect4);
                scale.setToX(0);
                scale.setToY(0);
                scale.play();
                RotateTransition rotate = new RotateTransition(Duration.millis(1000), rect4);
                rotate.setByAngle(360);
                rotate.setCycleCount(1);
                rotate.setAutoReverse(true);
                rotate.play();
            }
        });

        ScaleTransition scaleTransition5 = new ScaleTransition(Duration.millis(300), rect5);
        rect5.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleTransition5.setToX(1.5f);
                scaleTransition5.setToY(1.5f);
                scaleTransition5.setCycleCount(2);
                scaleTransition5.setAutoReverse(true);
                scaleTransition5.play();
            }
        });
        rect5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                TranslateTransition trans = new TranslateTransition();
                trans.setDuration(Duration.millis(1000));
                trans.setToX(((width / 4) * 3 + (width / 9)) - 440);
                trans.setNode(rect5);
                trans.play();
                ScaleTransition scale = new ScaleTransition(Duration.millis(1000), rect5);
                scale.setToX(0);
                scale.setToY(0);
                scale.play();
                RotateTransition rotate = new RotateTransition(Duration.millis(1000), rect5);
                rotate.setByAngle(360);
                rotate.setCycleCount(1);
                rotate.setAutoReverse(true);
                rotate.play();
            }
        });
        this.getChildren().addAll(pane);
    }

    public void putListener(Rectangle rect) {

    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}