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
    Pane pane;
    HBox box;
    HBox options;
    SmithPane smithPane;
    private UpperPane gridUpper;
    ImageView turnBack;
    public RestScene()
    {
        pane = new Pane();
        box = new HBox();
        options = new HBox();
        turnBack = new ImageView(new Image("goAhead.png"));
        turnBack.setPreserveRatio(true);
        turnBack.setFitHeight(100);
        int size = game.getPlayer().masterDeck.getSize();
        int num = size/7;
        turnBack.setX(width/5+ width/3*2); // smith pane' in sonuna ekledik ---------BUNLARI DÜZENLE
        turnBack.setY(height/4*3);
        gridUpper = new UpperPane(width,height/15);
        smithPane  = new SmithPane(width/3*2 , height/9*6);
        root.setMinSize( width, height);

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
        //gridUpper.setMinHeight(height/9);

    }

    public void initialize() {
        initializeUpper();
        addBackground();
        showOptions();
    }

    public void proceed(){

        pane.getChildren().add( turnBack );
        turnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println(" you are in next scene");
                game.getDungeon().ascend();
            }
        });
    }

    /*public void proceedForSmith(){
        smithPane.pane.getChildren().add( turnBack );
        turnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                smithPane.visible(false);
                game.getDungeon().ascend();
            }
        });
    }*/

    public void showOptions(){
        System.out.println("INITIALIZE IN RESTSCENE");
        int imageWidth = width / 10;
        int imageHeight = height / 4;

        ImageView rest = new ImageView(new Image("Rest.png"));
        rest.setFitWidth(imageWidth);
        rest.setFitHeight(imageHeight);
        rest.setX(width/6*2);
        rest.setY(height/3);
        rest.setVisible(true);
        rest.setPreserveRatio(true);
        rest.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                {
                    System.out.println("ASCENDING CALLED IN REST SCENE");
                    game.getPlayer().recharge();
                    proceed();
                }
            }
        });
        pane.getChildren().add(rest);

        ImageView smith = new ImageView(new Image("Smith.png"));
        smith.setVisible(true);
        smith.setPreserveRatio(true);
        smith.setFitWidth(imageWidth);
        smith.setFitHeight(imageHeight);
        smith.setX(width/6*2+ imageWidth);
        smith.setY(height/3);
        smith.setVisible(true);
        smith.setPreserveRatio(true);
        smith.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                {
                    smithPane.visible(true);
                    smithPane.addBackground();
                    smithPane.initialize();
                    root.getChildren().add(smithPane);
                    proceed();
                }

            }
        });
        pane.getChildren().add(smith);
        root.getChildren().add(pane);
    }


    private void addBackground() {
        ImageView imageView = new ImageView(new Image("campFire.gif"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        root.getChildren().add(imageView);
    }
    public void draw() {}


}
