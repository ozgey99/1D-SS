package sts;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static sts.Main.game;

public class UpperPane extends GridPane {
    private ArrayList<Text> textList;
    private  int width;
    private int height;
    Text healthText;
    Text goldText;
    Text blockText;
    Text energyText;
    int space;
    HBox box;
    HBox box1;
    HBox block;
    HBox box2;
    HBox box3;
    HBox box4;
    HBox energy;

    public UpperPane(int width,int height)
    {
        this.width = width;
        this.height = height;
        healthText = new Text();
        goldText = new Text();
        blockText = new Text();
        energyText = new Text();
        space = 800;
        box = new HBox(70);
        box1 = new HBox();
        block = new HBox();
        box2 = new HBox();
        box3 = new HBox(5);
        box4 = new HBox(5);
        energy = new HBox();
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
        // box 1
        healthText.setText(game.getPlayer().getCurrentHP()+ " / "+ game.getPlayer().getMaxHP());
        healthText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        healthText.setFill(Color.RED);
        ImageView heart = new ImageView(new Image("heart.png"));
        heart.setFitWidth(40);
        heart.setFitHeight(40);
        heart.setVisible(true);
        heart.setPreserveRatio(true);
        box1.getChildren().addAll(heart, healthText);

        // block box
        blockText.setText("Your block: " + game.getPlayer().getBlock());
        blockText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        block.getChildren().add(blockText);

        // box 2
        goldText.setText("" + game.getPlayer().getGold());
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        ImageView gold = new ImageView(new Image("gold.png"));
        gold.setFitWidth(40);
        gold.setFitHeight(40);
        gold.setVisible(true);
        gold.setPreserveRatio(true);
        box2.getChildren().addAll(gold, goldText);

        // energy box
        energyText.setText("Your enery is: " + game.getPlayer().currentEnergy);
        energyText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        energy.getChildren().add(energyText);

        // box 3
        for(int i=0; i< game.getPlayer().relics.size(); i++){
            String name = game.getPlayer().relics.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView relicImage = new ImageView(new Image(name));
            relicImage.setPreserveRatio(true);
            relicImage.setFitHeight(30);
            box3.getChildren().add(relicImage);
        }

        // box 4
        for(int i=0; i< game.getPlayer().powers.size(); i++){
            String name = game.getPlayer().powers.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView powerImage = new ImageView(new Image(name));
            powerImage.setPreserveRatio(true);
            powerImage.setFitHeight(30);
            box4.getChildren().add(powerImage);
        }

        box.getChildren().addAll(box1,block,energy,box2,box3,box4);
        this.getChildren().add(box);


    }
    public void draw()
    {

        box.getChildren().clear();
        box.getChildren().removeAll();
        box1.getChildren().clear();
        box1.getChildren().removeAll();
        block.getChildren().removeAll();
        block.getChildren().clear();
        box2.getChildren().removeAll();
        box2.getChildren().clear();
        box3.getChildren().removeAll();
        box3.getChildren().clear();
        box4.getChildren().removeAll();
        energy.getChildren().clear();
        energy.getChildren().removeAll();

        // box 1
        healthText.setText(game.getPlayer().getCurrentHP()+ " / "+ game.getPlayer().getMaxHP());
        healthText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        healthText.setFill(Color.RED);
        ImageView heart = new ImageView(new Image("heart.png"));
        heart.setFitWidth(40);
        heart.setFitHeight(40);
        heart.setVisible(true);
        heart.setPreserveRatio(true);
        box1.getChildren().addAll(heart, healthText);

        // block box
        blockText.setText("Your block: " + game.getPlayer().getBlock());
        blockText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        block.getChildren().add(blockText);

        // box 2
        goldText.setText("" + game.getPlayer().getGold());
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        ImageView gold = new ImageView(new Image("gold.png"));
        gold.setFitWidth(40);
        gold.setFitHeight(40);
        gold.setVisible(true);
        gold.setPreserveRatio(true);
        box2.getChildren().addAll(gold, goldText);

        // energy box
        energyText.setText("Your enery is: " + game.getPlayer().currentEnergy);
        energyText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        energy.getChildren().add(energyText);

        // box 3
        for(int i=0; i< game.getPlayer().relics.size(); i++){
            String name = game.getPlayer().relics.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView relicImage = new ImageView(new Image(name));
            relicImage.setPreserveRatio(true);
            relicImage.setFitHeight(30);
            box3.getChildren().add(relicImage);
        }

        // box 4
        for(int i=0; i< game.getPlayer().powers.size(); i++){
            String name = game.getPlayer().powers.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView powerImage = new ImageView(new Image(name));
            powerImage.setPreserveRatio(true);
            powerImage.setFitHeight(30);
            box4.getChildren().add(powerImage);
        }
        box.getChildren().addAll(box1,block,energy,box2,box3,box4);
    }


}