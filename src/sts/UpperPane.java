package sts;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    VBox box;



    public UpperPane(int width,int height)
    {
        this.width = width;
        this.height = height;
        healthText = new Text();
        goldText = new Text();
        blockText = new Text();
        energyText = new Text();
        space = 800;
        box = new VBox();
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


        blockText.setText("Your block: " + game.getPlayer().getBlock());
        blockText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        goldText.setText("Your gold is: " + game.getPlayer().getGold());
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        energyText.setText("Your enery is: " + game.getPlayer().currentEnergy);
        energyText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        box.getChildren().addAll(healthText,blockText,goldText,energyText);

        String relicsT = new String();
        relicsT = "Your relics are ";
        for(int i = 0; i <  game.getPlayer().relics.size();i++) {
            relicsT = relicsT + game.getPlayer().relics.get(i).getName() + " ";

        }
        Text text = new Text(relicsT);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        box.getChildren().add(text);


        String powersT = new String();
        powersT = "Your powers are ";
        for(int i = 0; i <  game.getPlayer().powers.size();i++) {
            powersT = powersT + game.getPlayer().powers.get(i).getName() + " ";

        }
        Text text2 = new Text(powersT);
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        box.getChildren().add(text2);

        this.getChildren().add(box);


    }
    public void draw()
    {

        box.getChildren().clear();
        box.getChildren().removeAll();


        healthText.setText("Your health: " + game.getPlayer().getCurrentHP());
        healthText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        blockText.setText("Your block: " + game.getPlayer().getBlock());
        blockText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        goldText.setText("Your gold is: " + game.getPlayer().getGold());
        goldText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        energyText.setText("Your enery is: " + game.getPlayer().currentEnergy);
        energyText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));


        box.getChildren().add(0,healthText);
        box.getChildren().add(1,blockText);
        box.getChildren().add(2,goldText);
        box.getChildren().add(3,energyText);
        String relicsT = new String();
        relicsT = "Your relics are ";
        for(int i = 0; i <  game.getPlayer().relics.size();i++) {
            relicsT = relicsT + game.getPlayer().relics.get(i).getName() + " ";

        }
        Text text = new Text(relicsT);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        box.getChildren().add(text);


        String powersT = new String();
        powersT = "Your powers are ";
        for(int i = 0; i <  game.getPlayer().powers.size();i++) {
            powersT = powersT + game.getPlayer().powers.get(i).getName() + " ";

        }
        Text text2 = new Text(powersT);
        text2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        box.getChildren().add(text2);

    }


}