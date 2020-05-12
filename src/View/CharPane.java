package View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;

import static View.Main.game;


public class CharPane extends StackPane {
    private int width;
    private int height;
    boolean selected = false;
    Circle circle;
    ImageView pet;


    public CharPane(int width, int height){
        this.width = width;
        this.height = height;
        this.setMinSize( width, height);
        this.getChildren().clear();
        this.getChildren().removeAll();
    }

    public void initialize()
    {
        ImageView imageView = new ImageView(new Image("Ironclad.png"));
        pet = new ImageView(new Image("dragon.gif"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(width/5);

        this.getChildren().add( imageView );
        this.setAlignment(imageView, Pos.BOTTOM_CENTER );
        System.out.println("char pane width: "+ width);
        System.out.println("char pane height: "+ height);
        charPet();
    }

    public void charPet(){
        pet.setPreserveRatio(true);
        pet.setFitHeight(height/3);
        pet.setEffect( new DropShadow(30, Color.RED) );
        if(game.getPlayer().getPet() != null){
            this.getChildren().add( pet );
            this.setAlignment(pet, Pos.BOTTOM_RIGHT );
        }
    }

    public void draw()
    {
        circle = new Circle();
        circle.setCenterX(width/3);
        circle.setCenterY((height/5.0)*4);
        circle.setFill(Color.RED);
        circle.setStroke(Color.BLACK);
        circle.setRadius(height/12);

        int player = game.getPlayer().currentEnergy;
        String playerEnergy = ""+ player;
        Text text = new Text(playerEnergy);
        text.setFill(Color.BLACK);
        text.setFont(Font.font ("Verdana", height/20));
        text.setBoundsType(TextBoundsType.VISUAL);
        StackPane stack = new StackPane();
        stack.getChildren().addAll(circle, text);

        stack.setPadding(new Insets((height/6.0)*5, width/2, height/10, 0));
        this.getChildren().add(stack);

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