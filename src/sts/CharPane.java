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


public class CharPane extends StackPane {
    private int width;
    private int height;
    boolean selected = false;
    AbstractCharacter hero;
    public CharPane(int width, int height, AbstractCharacter hero ){
        this.width = width;
        this.height = height;
        this.setMinSize( width, height);
        listenSelected();
        this.hero = hero;
    }
    public void initialize()
    {
        ImageView imageView = new ImageView(new Image("Ironclad.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(width/8);

        this.getChildren().add( imageView );
        this.setAlignment( Pos.BOTTOM_CENTER );
    }
    public void update()
    {

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
            System.out.println("The HP of the selected character is " + hero.getCurrentHP());
        }
        else
        {
            this.setEffect(null);
            selected = false;
            System.out.println("The HP of the deselected character is " + hero.getCurrentHP());
        }
    }
}