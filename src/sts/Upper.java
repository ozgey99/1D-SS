package sts;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static sts.Main.game;

public class Upper extends Pane {
    private ArrayList<Text> textList;
    private  int width;
    private int height;
    Text healthText;
    Text goldText;

    HBox box;


    public Upper(int width,int height)
    {
        this.width = width;
        this.height = height;
        healthText = new Text();
        goldText = new Text();
        this.setMinSize(width, height);
        box = new HBox();
        //addBackground();
    }
    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);

        this.getChildren().add(imageView);
    }
    public void initialize()
    {
        healthText.setText("Your health: " + game.getPlayer().getCurrentHP());
        healthText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        goldText.setText("Your gold is: " + game.getPlayer().getGold());
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        box.setSpacing(30);
        box.getChildren().addAll(healthText,goldText);
        this.getChildren().add(box);
    }

}
