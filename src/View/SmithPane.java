package View;

import Models.Cards.AbstractCard;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;

import static View.Main.game;

public class SmithPane extends StackPane {
    Pane pane;
    VBox vbox;
    int padX;
    int padY;
    private int width;
    private int height;
    ImageView back;
    StackPane stack;
    ScrollPane sp;

    public SmithPane(int width, int height){
        padX = width*3/2;
        padY = height*9/6;
        back = new ImageView(new Image("up.png"));
        pane = new Pane();
        stack = new StackPane();
        stack.setPadding(new Insets(width/4, height/2, width/4, height/2));
        vbox = new VBox(height/70.0);
        sp = new ScrollPane();
        stack.getChildren().add(sp);
        setAlignment(sp, Pos.CENTER);
        this.setMinSize( width, height);
        this.getChildren().add(stack);
        vbox.setBackground( new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void visible(boolean bool){
        this.setVisible(bool);
    }


    public void draw(){
        initialize();
        vbox.setDisable(true);
    }

    public void initialize() {

        sp.setContent(vbox);
        sp.setPrefSize(width,height);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        vbox.getChildren().removeAll();
        vbox.getChildren().clear();

        int size = game.getPlayer().masterDeck.getSize();
        ArrayList<AbstractCard> cards = new ArrayList<AbstractCard>();
        for (int i = 0; i < size; i++){
            if(game.getPlayer().masterDeck.getCard(i).isUpgradable()){
                cards.add(game.getPlayer().masterDeck.getCard(i));
            }
        }

        for (int i = 0; i < cards.size(); i++){
            HBox hbox = new HBox();
            hbox.getChildren().clear();
            for(int count = 0; count < 7; count++ ){
                String name = cards.get(i).getName();
                System.out.println(name);
                name = name + ".png";
                Rectangle rect = new Rectangle();
                rect.setFill(new ImagePattern(new Image(name)));
                rect.setWidth(padX/13.0);
                rect.setHeight(padY/(46/10));
                rect.setVisible(true);
                int j = i;
                rect.setOnMouseClicked(e -> {
                        cards.get(j).upgrade();
                        rect.setStroke(Color.GREEN);
                        draw();
                    }
                );

                rect.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, e -> {
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect);
                        scaleTransition.setToX(1.5f);
                        scaleTransition.setToY(1.5f);
                        scaleTransition.setCycleCount(1);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.play();
                    }
                );

                rect.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, e -> {
                        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), rect);
                        scaleTransition.setToX(1);
                        scaleTransition.setToY(1);
                        scaleTransition.setAutoReverse(true);
                        scaleTransition.play();
                    }
                );

                hbox.getChildren().add(rect);
                if(count  != 6)
                    i++;
                if(i == cards.size() || count == cards.size())
                    break;
            }
            vbox.getChildren().add(hbox);
        }
    }

    public void addBackground() {
        back.setFitWidth(width);
        back.setFitHeight(height);
        back.setOpacity(0.80);
    }

}
