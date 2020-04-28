package sts;

import Models.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TreasureScene extends RoomScene {
    private int width = 1300; //1920;
    private int gold = 0;
    private String relic = "DefaultRelicName";
    private String potion = "DefaultPotionName";
    private int height = 700; //1080;
    Text goldText = new Text("You gold reward is "+ gold);
    Text relicText = new Text("You relic reward is "+ relic);
    Text potionText = new Text("You potion reward is "+ gold);
    HBox box = new HBox();





    public TreasureScene()
    {


        box.getChildren().addAll(goldText);
        box.getChildren().addAll(relicText);
        root.getChildren().addAll(box);

        root.setMinSize( width, height);


        root.getChildren().add(goldText);
        //addBackground();
        initialize();



    }
    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        root.getChildren().add(imageView);
    }
    public void initialize()
    {

    }

    public void draw()
    {

    }

    private void addClickables(){
        Button button1 = new Button("Button 1");
        root.getChildren().add(button1);
    }
}