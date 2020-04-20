package sts;

import Models.Cards.AbstractCard;
import Models.Cards.Deck;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static sts.Main.game;

public class CardPane extends StackPane{
    private int space;
    private AbstractCard card;
    private int width;
    private int height;
    private int x;
    private int y;
    Rectangle rect;
    Pane pane = new Pane();
    private GridPane general = new GridPane();


    private Effect shadow = new DropShadow(5, Color.BLACK);
    private Effect blur = new BoxBlur(1, 1, 3);

    public CardPane (int width, int height, int x, int y, AbstractCard c) {
        this.width = width;
        this.height = height;
        card = c;
        space = width/10;
        this.setMinSize( width, height);

    }
    public void initialize()
    {

    }

    public void update(AbstractCard c)
    {
        card = c;
        draw();
    }
    public void draw() {
        space = width / 10;

        String firstName = card.getName();
        firstName = firstName + ".png";

        //adjust the coordinates. maybe take them as params
        rect = new Rectangle();
        rect.setFill(new ImagePattern(new Image(firstName)));
        rect.setX(x);
        rect.setY(y);
        rect.setWidth(space);
        rect.setHeight(space);
        pane.getChildren().add(rect);

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(300), rect);
        rect.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scaleTransition.setToX(1.5f);
                scaleTransition.setToY(1.5f);
                scaleTransition.setCycleCount(2);
                scaleTransition.setAutoReverse(true);
                scaleTransition.play();
            }
        });
        rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                TranslateTransition trans = new TranslateTransition();
                trans.setDuration(Duration.millis(1000));
                trans.setToX(((width / 4) * 3 + (width / 9)));
                trans.setNode(rect);
                trans.play();
                ScaleTransition scale = new ScaleTransition(Duration.millis(1000), rect);
                scale.setToX(0);
                scale.setToY(0);
                scale.play();
                RotateTransition rotate = new RotateTransition(Duration.millis(1000), rect);
                rotate.setByAngle(360);
                rotate.setCycleCount(1);
                rotate.setAutoReverse(true);
                rotate.play();
                card.setSelected();
            }
        });
    }

    public void putListener(Rectangle r)
    {

    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }

    public AbstractCard getCard(){
        return card;
    }
}

