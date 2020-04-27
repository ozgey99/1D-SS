package sts;

import Models.Creatures.AbstractCharacter;
import Models.Game;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.paint.ImagePattern;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.event.EventHandler;

import static sts.Main.game;

import java.awt.*;


public class CharPane extends GridPane {
    private int width;
    private int height;
    boolean selected = false;



    public CharPane(int width, int height){
        this.width = width;
        this.height = height;
        this.setMinSize( width, height);
        listenSelected();
    }
    public void initialize()
    {


        ImageView imageView = new ImageView(new Image("Ironclad.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(width/8);

        this.getChildren().add( imageView );
        this.setAlignment( Pos.BOTTOM_CENTER );


    }

    public void draw()
    {

    }
    void listenSelected(){
        this.setOnMouseClicked( e->setSelected());
    }
    void setSelected(){
        if( selected == false ) {
            this.setEffect( new Shadow(30, Color.YELLOW));
            selected = true;
            System.out.println("The HP of the selected character is " + game.getPlayer().getCurrentHP());
        }
        else
        {
            this.setEffect(null);
            selected = false;
            System.out.println("The HP of the deselected character is " + game.getPlayer().getCurrentHP());
        }
    }
}