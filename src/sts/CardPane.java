package sts;

import Models.Cards.AbstractCard;
import Models.Cards.Deck;
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

public class CardPane  extends GridPane
{
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

    private GridPane general = new GridPane();
    private int maxHand = 5;
    private int space;

    private Effect shadow = new DropShadow(5, Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 3);

    public CardPane(int width, int height, Deck deck) {
        this.width = width;
        this.height = height;
        this.deck = deck;
        space = width/10;
        this.setMinSize( width, height);

    }
    public void initialize()
    {

        AbstractCard firstHandCard = deck.getCard(0);
        Pane pane = new Pane();

        Rectangle rect5 = new Rectangle();
        rect5.setFill(new ImagePattern(new Image("Strike.png")));
        rect5.setX(width/6+440);
        rect5.setY(5);
        rect5.setWidth(100);
        rect5.setHeight(140);
        pane.getChildren().add(rect5);

        Rectangle rect4 = new Rectangle();
        rect4.setFill(new ImagePattern(new Image("Defend.png")));
        rect4.setX(width/6+330);
        rect4.setY(5);
        rect4.setWidth(100);
        rect4.setHeight(140);
        pane.getChildren().add(rect4);

        Rectangle rect3 = new Rectangle();
        rect3.setFill(new ImagePattern(new Image("clash.png")));
        rect3.setX(width/6+220);
        rect3.setY(5);
        rect3.setWidth(100);
        rect3.setHeight(140);
        pane.getChildren().add(rect3);

        Rectangle rect2 = new Rectangle();
        rect2.setFill(new ImagePattern(new Image("Bash.png")));
        rect2.setX(width/6+110);
        rect2.setY(5);
        rect2.setWidth(100);
        rect2.setHeight(140);
        pane.getChildren().add(rect2);

        Rectangle rect1 = new Rectangle();
        rect1.setFill(new ImagePattern(new Image("Anger.png")));
        rect1.setX(width/6);
        rect1.setY(5);
        rect1.setWidth(100);
        rect1.setHeight(140);
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
        rect1.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                TranslateTransition trans = new TranslateTransition();
                trans.setDuration(Duration.millis(1000));
                trans.setToX( ((width/4)*3+(width/9)) );
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
        rect2.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                TranslateTransition trans = new TranslateTransition();
                trans.setDuration(Duration.millis(1000));
                trans.setToX(((width/4)*3+(width/9))-110);
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
        rect3.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                TranslateTransition trans = new TranslateTransition();
                trans.setDuration(Duration.millis(1000));
                trans.setToX(((width/4)*3+(width/9))-220);
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
        rect4.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                TranslateTransition trans = new TranslateTransition();
                trans.setDuration(Duration.millis(1000));
                trans.setToX(((width/4)*3+(width/9))-330);
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
        rect5.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                TranslateTransition trans = new TranslateTransition();
                trans.setDuration(Duration.millis(1000));
                trans.setToX(((width/4)*3+(width/9))-440);
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

    public void update()
    {

    }
    public void draw()
    {
        /*
        card1.getChildren().remove(rect1);
        card1.getChildren().addAll( new ImageView(new Image("Ironclad.png")) );
        card2.getChildren().addAll( new ImageView(new Image("Ironclad.png")) );
        card3.getChildren().addAll( new ImageView(new Image("Ironclad.png")) );
        card4.getChildren().addAll( new ImageView(new Image("Ironclad.png")) );
        card5.getChildren().addAll( new ImageView(new Image("Ironclad.png")) );
        box = new HBox(card1,card2,card3,card4,card5);
        box.setSpacing(0);
        this.getChildren().addAll(box);
         */
    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}