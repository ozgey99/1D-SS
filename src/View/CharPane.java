package View;

import javafx.geometry.Pos;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;

import static View.Main.game;


public class CharPane extends GridPane {
    private int width;
    private int height;
    boolean selected = false;



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