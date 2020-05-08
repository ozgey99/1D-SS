package View;

import Models.Object.AbstractPower;
import Models.Object.AbstractRelic;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.ArrayList;

import static View.Main.game;

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
    Pane pane;
    boolean relicDesc;
    boolean powerDesc;


    public UpperPane(int width,int height)
    {
        pane = new Pane();
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
        box3 = new HBox(10);
        box4 = new HBox(10);
        energy = new HBox();
        relicDesc = false;
        powerDesc = false;
        //addBackground();
    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image("background1.jpg"));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        this.getChildren().add(imageView);
    }

    static Node relicDescription(ArrayList<AbstractRelic> relic, int ind, int x, int y) {
        Group g = new Group();

        String desc = relic.get(ind).getDescription();
        int len = desc.length();

        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setFill(Color.GREY);
        rect.setStroke(Color.BLACK);
        rect.setWidth(len*6);
        rect.setHeight(20);
        rect.setVisible(true);

        Text relicText = new Text(desc);
        relicText.setX(x+5);
        relicText.setY(y+13);
        relicText.setFont(Font.font ("Verdana", 10));
        relicText.setFill(Color.WHITE);

        g.getChildren().add(rect);
        g.getChildren().add(relicText);

        return g;
    }

    static Node powerDescription(ArrayList<AbstractPower> power, int ind, int x, int y) {
        Group g = new Group();

        String desc = power.get(ind).getDescription();
        int len = desc.length();

        Rectangle rect = new Rectangle();
        rect.setX(x);
        rect.setY(y);
        rect.setFill(Color.GREY);
        rect.setStroke(Color.BLACK);
        rect.setWidth(len*6);
        rect.setHeight(20);
        rect.setVisible(true);

        Text powerText = new Text(desc);
        powerText.setX(x+5);
        powerText.setY(y+13);
        powerText.setFont(Font.font ("Verdana", 10));
        powerText.setFill(Color.WHITE);

        g.getChildren().add(rect);
        g.getChildren().add(powerText);

        return g;
    }

    public void initialize()
    {
        clearPane();
        // box 1 -- health
        healthText.setText(game.getPlayer().getCurrentHP()+ " / "+ game.getPlayer().getMaxHP());
        healthText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        healthText.setFill(Color.RED);
        ImageView heart = new ImageView(new Image("heart.png"));
        heart.setFitWidth(40);
        heart.setFitHeight(40);
        heart.setVisible(true);
        heart.setPreserveRatio(true);
        box1.getChildren().add(heart);
        box1.getChildren().add(healthText);

        // block box
        blockText.setText("" + game.getPlayer().getBlock());
        blockText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        ImageView blockPic = new ImageView(new Image("blockPic.png"));
        blockPic.setFitWidth(40);
        blockPic.setFitHeight(40);
        blockPic.setVisible(true);
        blockPic.setPreserveRatio(true);
        block.getChildren().addAll(blockPic, blockText);

        // box 2 -- gold
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

        // box 3 -- relic
        for(int i=0; i< game.getPlayer().relics.size(); i++){
            String name = game.getPlayer().relics.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView relicImage = new ImageView(new Image(name));
            relicImage.setPreserveRatio(true);
            relicImage.setFitHeight(30);

            Node desc = relicDescription(game.getPlayer().relics,i, width/2, height-20);
            pane.getChildren().add(desc);
            desc.setVisible(false);

            if(! this.getChildren().contains(pane)){
                this.getChildren().add(pane);
                relicDesc = true;
            }

            relicImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(true);
                }
            });
            relicImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(false);
                }
            });
            box3.getChildren().add(relicImage);
        }

        // box 4 -- power
        for(int i=0; i< game.getPlayer().powers.size(); i++){
            String name = game.getPlayer().powers.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView powerImage = new ImageView(new Image(name));
            powerImage.setPreserveRatio(true);
            powerImage.setFitHeight(30);
            box4.getChildren().add(powerImage);

            Node desc = powerDescription(game.getPlayer().powers,i, width/2, height-20);
            pane.getChildren().add(desc);
            desc.setVisible(false);
            if(! this.getChildren().contains(pane)){
                this.getChildren().add(pane);
                relicDesc = true;
            }

            powerImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(true);
                }
            });
            powerImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(false);
                }
            });
        }
        box.getChildren().addAll(box1,block,energy,box2,box3,box4);

        if(! this.getChildren().contains(box)){
            this.getChildren().add(box);
            relicDesc = true;
        }


    }

    private void clearPane(){

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
        box4.getChildren().clear();
        box4.getChildren().removeAll();
        box4.getChildren().clear();
        energy.getChildren().clear();
        energy.getChildren().removeAll();
    }

    public void draw()
    {
        clearPane();

        // box 1 -- health
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
        blockText.setText("" + game.getPlayer().getBlock());
        blockText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        ImageView blockPic = new ImageView(new Image("blockPic.png"));
        blockPic.setFitWidth(40);
        blockPic.setFitHeight(40);
        blockPic.setVisible(true);
        blockPic.setPreserveRatio(true);
        block.getChildren().addAll(blockPic, blockText);

        // box 2 -- gold
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

        // box 3 -- relic
        for(int i=0; i< game.getPlayer().relics.size(); i++){
            String name = game.getPlayer().relics.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView relicImage = new ImageView(new Image(name));
            relicImage.setPreserveRatio(true);
            relicImage.setFitHeight(30);
            box3.getChildren().add(relicImage);

            Node desc = relicDescription(game.getPlayer().relics,i, width/2, height-20);
            pane.getChildren().add(desc);
            desc.setVisible(false);

            relicImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(true);
                }
            });
            relicImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(false);
                }
            });

        }

        // box 4 -- power
        for(int i=0; i< game.getPlayer().powers.size(); i++){
            String name = game.getPlayer().powers.get(i).getName();
            System.out.println(name);
            name = name + ".png";
            ImageView powerImage = new ImageView(new Image(name));
            powerImage.setPreserveRatio(true);
            powerImage.setFitHeight(30);
            box4.getChildren().add(powerImage);

            Node desc = powerDescription(game.getPlayer().powers,i, width/2, height-20);
            pane.getChildren().add(desc);
            desc.setVisible(false);

            powerImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(true);
                }
            });
            powerImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    desc.setVisible(false);
                }
            });
        }
        box.getChildren().addAll(box1,block,energy,box2,box3,box4);
    }


}